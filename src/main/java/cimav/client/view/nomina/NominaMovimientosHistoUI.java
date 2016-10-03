/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.MovimientoHisto;
import cimav.client.view.common.ICustomDataGridResource;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
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
 * @author calderon
 */
public class NominaMovimientosHistoUI extends Composite {

    private static NominaMovimientosHistoUIUiBinder uiBinder = GWT.create(NominaMovimientosHistoUIUiBinder.class);

    interface NominaMovimientosHistoUIUiBinder extends UiBinder<Widget, NominaMovimientosHistoUI> {
    }

    @UiField(provided = true)
    DataGrid<MovimientoHisto> dataGrid;

    private ListDataProvider<MovimientoHisto> provider;

    private final ETipoConcepto tipoConcepto;

    @UiConstructor
    public NominaMovimientosHistoUI(String idTipoConcepto) {

        this.tipoConcepto = ETipoConcepto.get(idTipoConcepto);

        this.buildGrid(); // antes del initWidget

        initWidget(uiBinder.createAndBindUi(this));

    }

    private void buildGrid() {
        
        List<MovimientoHisto> nominaQuincenalList = new ArrayList<>();
        provider = new ListDataProvider<>(nominaQuincenalList);

        ICustomDataGridResource dataGridResource = GWT.create(ICustomDataGridResource.class);
        dataGridResource.dataGridStyle().ensureInjected();
        
        dataGrid = new DataGrid<>(60, dataGridResource);

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin movimientos de momento"));

        dataGrid.setPageSize(20);
        dataGrid.setMinimumTableWidth(100, Style.Unit.PX);

        initTableColumns();

        provider.addDataDisplay(dataGrid);

    }

    private void initTableColumns() {

                // Concepto
        Column<MovimientoHisto, SafeHtml> conceptoCol = new Column<MovimientoHisto, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(MovimientoHisto object) {
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
        dataGrid.setColumnWidth(conceptoCol, 40, Style.Unit.PCT);

        // Cantidad
        Column<MovimientoHisto, SafeHtml> cantidadCol = new Column<MovimientoHisto, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(MovimientoHisto object) {
                String result = Utils.formatCantidad(object.getCantidad());
                SafeHtmlBuilder a = new SafeHtmlBuilder();
                if (object.getConcepto().getSuma()) {
                    a.appendHtmlConstant("<span style='padding-right: 5px;'>" + result + "</span>");
                } else {
                    a.appendHtmlConstant("<span style='padding-right: 5px; color: grey; font-style: italic;'>" + result + "</span>");
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
                    List<MovimientoHisto> items = dataGrid.getVisibleItems();
                    if (!items.isEmpty()) {
                        // create MathContext object with 2 precision
                        BigDecimal totalPercepciones = BigDecimal.ZERO;
                        for (MovimientoHisto nomQuin : items) {
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

    public void setList(List<MovimientoHisto> percepciones) {

        Collections.sort(percepciones, new Comparator<MovimientoHisto>() {
            @Override
            public int compare(MovimientoHisto obj1, MovimientoHisto obj2) {
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

    public DataGrid<MovimientoHisto> getDataGrid() {
        return dataGrid;
    }
}

