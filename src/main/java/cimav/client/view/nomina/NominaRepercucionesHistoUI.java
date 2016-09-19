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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NominaRepercucionesHistoUI extends Composite {
    
    private static NominaRepercucionesHistoUIUiBinder uiBinder = GWT.create(NominaRepercucionesHistoUIUiBinder.class);
    
    interface NominaRepercucionesHistoUIUiBinder extends UiBinder<Widget, NominaRepercucionesHistoUI> {
    }
    
    @UiField(provided = true)
    DataGrid<MovimientoHisto> dataGrid;

    private ListDataProvider<MovimientoHisto> provider;

    private final ETipoConcepto tipoConcepto;

    @UiConstructor
    public NominaRepercucionesHistoUI(String idTipoConcepto) {
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

        dataGrid.setEmptyTableWidget(new Label("Sin repercuciones de momento"));

        dataGrid.setPageSize(20);
        dataGrid.setMinimumTableWidth(400, Style.Unit.PX);

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
        dataGrid.setColumnWidth(conceptoCol, 80, Style.Unit.PCT);

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
                return "<p style='text-align:center; margin-bottom: 0px;'>Empleado</p>";
            }
        });
        dataGrid.addColumn(cantidadCol, headerCantidad);
        dataGrid.setColumnWidth(cantidadCol, 20, Style.Unit.PCT);
        
        // Cantidad_empresa
        Column<MovimientoHisto, SafeHtml> cantidadEmpresaCol = new Column<MovimientoHisto, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(MovimientoHisto object) {
                String result = Utils.formatCantidad(object.getCantidadEmpresa());
                SafeHtmlBuilder a = new SafeHtmlBuilder();
                a.appendHtmlConstant("<span style='padding-right: 5px;'>" + result + "</span>");
		return a.toSafeHtml();            
            }
        };
        cantidadEmpresaCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        SafeHtmlHeader headerCantidadEmpresa = new SafeHtmlHeader(new SafeHtml() {
            @Override
            public String asString() {
                return "<p style='text-align:center; margin-bottom: 0px;'>Empresa</p>";
            }
        });
        dataGrid.addColumn(cantidadEmpresaCol, headerCantidadEmpresa);
        dataGrid.setColumnWidth(cantidadEmpresaCol, 20, Style.Unit.PCT);
        
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

    public DataGrid<MovimientoHisto> getDataGrid() {
        return dataGrid;
    }
    
    
    
}
