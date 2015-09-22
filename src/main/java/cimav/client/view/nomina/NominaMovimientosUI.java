/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NominaMovimientosUI extends Composite {

    private static NominaMovimientosUIUiBinder uiBinder = GWT.create(NominaMovimientosUIUiBinder.class);

    interface NominaMovimientosUIUiBinder extends UiBinder<Widget, NominaMovimientosUI> {
    }

    @UiField(provided = true)
    DataGrid<NominaQuincenal> dataGrid;

    private ListDataProvider<NominaQuincenal> provider;

    interface CustomDataGridResources extends DataGrid.Resources {

        @Override
        @CssResource.NotStrict
        @Source(value = {DataGrid.Style.DEFAULT_CSS, "cimav/client/view/nomina/mominaDataGridStyle.css"})
        CustomStyle dataGridStyle();
    }

    public interface CustomStyle extends DataGrid.Style {
    }

    private final ETipoConcepto tipoConcepto;

    @UiConstructor
    public NominaMovimientosUI(String idTipoConcepto) {

        this.tipoConcepto = ETipoConcepto.get(idTipoConcepto);

        this.buildGrid(); // antes del initWidget

        initWidget(uiBinder.createAndBindUi(this));

    }

    private void buildGrid() {

        List<NominaQuincenal> nominaQuincenalList = new ArrayList<>();
        provider = new ListDataProvider<>(nominaQuincenalList);

        CustomDataGridResources customDataGridResources = GWT.create(CustomDataGridResources.class);
        dataGrid = new DataGrid<>(60, customDataGridResources);

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin movimientos"));

        dataGrid.setPageSize(20);
        dataGrid.setMinimumTableWidth(400, Style.Unit.PX);

        initTableColumns();

        provider.addDataDisplay(dataGrid);

    }

    private void initTableColumns() {

                // Concepto
        Column<NominaQuincenal, SafeHtml> conceptoCol = new Column<NominaQuincenal, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(NominaQuincenal object) {
                SafeHtmlBuilder a = new SafeHtmlBuilder();
                if (object.getConcepto().getSuma()) {
                    a.appendHtmlConstant("<span>" + object.getConcepto().getName() + "</span>");
                } else {
                    a.appendHtmlConstant("<span style='color: grey; font-style: italic;'>" + object.getConcepto().getName() + "</span>");
                }
		return a.toSafeHtml();            
            }
        };
        dataGrid.addColumn(conceptoCol, "Concepto");
        dataGrid.setColumnWidth(conceptoCol, 80, Style.Unit.PCT);

        // Cantidad
        Column<NominaQuincenal, SafeHtml> cantidadCol = new Column<NominaQuincenal, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(NominaQuincenal object) {
                String result = Utils.formatCantidad(object.getCantidad());
                SafeHtmlBuilder a = new SafeHtmlBuilder();
                if (object.getConcepto().getSuma()) {
                    a.appendHtmlConstant("<span>" + result + "</span>");
                } else {
                    a.appendHtmlConstant("<span style='color: grey; font-style: italic;'>" + result + "</span>");
                }
		return a.toSafeHtml();            
            }
        };
        cantidadCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        SafeHtmlHeader headerCantidad = new SafeHtmlHeader(new SafeHtml() {
            @Override
            public String asString() {
                return "<p style='text-align:center; margin-bottom: 0px;'>Cantidad</p>";
            }
        });
        if (ETipoConcepto.PERCEPCION.equals(this.tipoConcepto) || ETipoConcepto.DEDUCCION.equals(this.tipoConcepto)) {
            SafeHtmlHeader footerCantidad = new SafeHtmlHeader(new SafeHtml() {
                @Override
                public String asString() {
                    String result = "0.00";
                    List<NominaQuincenal> items = dataGrid.getVisibleItems();
                    if (!items.isEmpty()) {
                        // create MathContext object with 2 precision
                        BigDecimal totalPercepciones = BigDecimal.ZERO;
                        for (NominaQuincenal nomQuin : items) {
                            if (nomQuin.getConcepto().getSuma()) { // si el concepto suma (ej. Despensa no suma)
                                totalPercepciones = totalPercepciones.add(nomQuin.getCantidad());
                            }
                        }
                        result = Utils.formatCurrency(totalPercepciones);
                    }
                    return "<p style=\"text-align:right;\">" + result.trim() + "</p>";
                }
            });
            dataGrid.addColumn(cantidadCol, headerCantidad, footerCantidad);
        } else {
            dataGrid.addColumn(cantidadCol, headerCantidad);
        }
        dataGrid.setColumnWidth(cantidadCol, 20, Style.Unit.PCT);
    }

    public void setList(List<NominaQuincenal> percepciones) {

        Collections.sort(percepciones, new Comparator<NominaQuincenal>() {
            @Override
            public int compare(NominaQuincenal obj1, NominaQuincenal obj2) {
                return obj1.getConcepto().getCode().compareTo(obj2.getConcepto().getCode());
            }
        });

        provider.setList(percepciones);
    }

//    // <editor-fold defaultstate="collapsed" desc="interface ActionListener"> 
//    public interface ActionListener extends java.util.EventListener {
//        void onActionEditor(MethodEvent restEvent);
//    }
//    private final ArrayList listeners = new ArrayList();
//    public void addActionListener(ActionListener listener) {
//        listeners.add(listener);
//    }
//    public void removeActionListener(ActionListener listener) {
//        listeners.remove(listener);
//    }
//    public void onAction(MethodEvent restEvent) {
//        for(Iterator it = listeners.iterator(); it.hasNext();) {
//            ActionListener listener = (ActionListener) it.next();
//            listener.onActionEditor(restEvent);
//        }
//    }
//    // </editor-fold>
}
