/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.ETipoMovimiento;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NominaDeduccionesUI extends Composite {
    
    private static NominaDeduccionesUIUiBinder uiBinder = GWT.create(NominaDeduccionesUIUiBinder.class);
    
    interface NominaDeduccionesUIUiBinder extends UiBinder<Widget, NominaDeduccionesUI> {
    }
    
    @UiField(provided = true) 
    DataGrid<NominaQuincenal> dataGridDeducciones;

    private ListDataProvider<NominaQuincenal> nominaQuincenalProvider;
    
    public NominaDeduccionesUI() {
        
        this.buildGrid(); // antes del initWidget

        initWidget(uiBinder.createAndBindUi(this));
        
    }
    
    private void buildGrid() {

        List<NominaQuincenal> nominaQuincenalList = new ArrayList<>();
        nominaQuincenalProvider = new ListDataProvider<>(nominaQuincenalList);
        dataGridDeducciones = new DataGrid<>(nominaQuincenalProvider.getKeyProvider());

        /*
         * Do not refresh the headers every time the data is updated. The footer
         * depends on the current data, so we do not disable auto refresh on the
         * footer.
         */
        dataGridDeducciones.setAutoHeaderRefreshDisabled(true);

        // Set the message to display when the table is empty.
        dataGridDeducciones.setEmptyTableWidget(new Label("No hay deducciones"));

        dataGridDeducciones.setPageSize(20);

        initTableColumns();

        // Add the CellList to the adapter in the database.
        nominaQuincenalProvider.addDataDisplay(dataGridDeducciones);

    }
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns() {

//        // ID
//        Column<NominaQuincenal, String> idCol = new Column<NominaQuincenal, String>(new TextCell()) {
//                    @Override
//                    public String getValue(NominaQuincenal object) {
//                        return object.getId().toString();
//                    }
//                };
//        dataGridDeducciones.addColumn(idCol, "ID");
//        dataGridDeducciones.setColumnWidth(idCol, 40, Style.Unit.PX);

        // Concepto
        Column<NominaQuincenal, String> conceptoCol = new Column<NominaQuincenal, String>((new TextCell())) {
            @Override
            public String getValue(NominaQuincenal object) {
                Concepto concepto = object.getConcepto();
                return concepto.getCode() + " " + concepto.getIdTipoCalculo()+ " " + concepto.getName();
            }
        };
        dataGridDeducciones.addColumn(conceptoCol, "Concepto");

        // Cantidad
        Column<NominaQuincenal, String> cantidadCol = new Column<NominaQuincenal, String>(new TextCell()) {
                    @Override
                    public String getValue(NominaQuincenal object) {
                        return Utils.formatCantidad(object.getCantidad());
                    }
                };
        cantidadCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
        // Total Percepciones
        Header<String> subTotalFooter = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                List<NominaQuincenal> items = dataGridDeducciones.getVisibleItems();
                if (items.isEmpty()) {
                    return "";
                } else {
                    // create MathContext object with 2 precision
                    BigDecimal totalPercepciones = BigDecimal.ZERO;
                    for (NominaQuincenal nomQuin : items) {
                        totalPercepciones = totalPercepciones.add(nomQuin.getCantidad());
                    }
                    return Utils.formatCurrency(totalPercepciones);
                }
            }
        };
        dataGridDeducciones.addColumn(cantidadCol,  new SafeHtmlHeader(SafeHtmlUtils.fromString("Cantidad")), subTotalFooter);
        dataGridDeducciones.setColumnWidth(cantidadCol, 100, Style.Unit.PX);

    }
    
    public void setList(List<NominaQuincenal> deducciones) {
        nominaQuincenalProvider.setList(deducciones);
    }
    
}
