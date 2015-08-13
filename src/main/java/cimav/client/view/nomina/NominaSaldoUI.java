/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.ETipoMovimiento;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NominaSaldoUI extends Composite {
    
    private static NominaSaldoUIUiBinder uiBinder = GWT.create(NominaSaldoUIUiBinder.class);
    
    interface NominaSaldoUIUiBinder extends UiBinder<Widget, NominaSaldoUI> {
    }
    
    @UiField(provided = true) 
    DataGrid<NominaQuincenal> dataGrid;

    private ListDataProvider<NominaQuincenal> provider;
    
    public NominaSaldoUI() {
        
        this.buildGrid(); // antes del initWidget
        
        initWidget(uiBinder.createAndBindUi(this));
    }
    
    private void buildGrid() {
        
        List<NominaQuincenal> nominaQuincenalList = new ArrayList<>();
        provider = new ListDataProvider<>(nominaQuincenalList);
        dataGrid = new DataGrid<>(provider.getKeyProvider());

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("No hay movimientos"));

        dataGrid.setPageSize(20);
        
        initTableColumns(); 
        
//        // Add the CellList to the adapter in the database.
        provider.addDataDisplay(dataGrid);

        
    }
    
    private List<Concepto> conceptos;
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns() {

        Concepto uno = new Concepto();
        uno.setId(1);
        uno.setCode("S_UNO");
        uno.setConsecutivo(1);
        uno.setTipoConcepto(ETipoConcepto.PERCEPCION);
        uno.setTipoMovimiento(ETipoMovimiento.SALDO);
        uno.setName("Saldo Uno");
        conceptos = new ArrayList<>();
        conceptos.add(uno);
        
        // Concepto
        Column<NominaQuincenal, String> conceptoCol = new Column<NominaQuincenal, String>((new TextCell())) {
            @Override
            public String getValue(NominaQuincenal object) {
                Concepto concepto = object.getConcepto();
                return concepto.getCode() + " " + concepto.getIdTipoConcepto()+ " " +  concepto.getIdTipoMovimiento()+ " " + concepto.getName();
            }
        };
        dataGrid.addColumn(conceptoCol, "Concepto");
        dataGrid.setColumnWidth(conceptoCol, 200, Style.Unit.PX);
        
        // Saldo Descuento
        Column<NominaQuincenal, String> descuentoCol = new Column<NominaQuincenal, String>(new TextCell()) {
                    @Override
                    public String getValue(NominaQuincenal object) {
                        return Utils.formatCantidad(object.getSaldoDescuento());
                    }
                };
        descuentoCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(descuentoCol,  "Descuento");
        dataGrid.setColumnWidth(descuentoCol, 100, Style.Unit.PX);
        
    }
    public void setList(List<NominaQuincenal> percepciones) {
        
        provider.setList(percepciones);
    }
    
}
