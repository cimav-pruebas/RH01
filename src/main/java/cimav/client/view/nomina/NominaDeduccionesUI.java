/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.data.domain.Tabulador;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.ArrayList;

/**
 *
 * @author juan.calderon
 */
public class NominaDeduccionesUI extends Composite {
    
    private static NominaDeduccionesUIUiBinder uiBinder = GWT.create(NominaDeduccionesUIUiBinder.class);
    
    interface NominaDeduccionesUIUiBinder extends UiBinder<Widget, NominaDeduccionesUI> {
    }
    
    @UiField(provided = true) CellTable<NominaQuincenal> dataGridDeducciones;

    private ListDataProvider<NominaQuincenal> nominaQuincenalProvider;
    
    public NominaDeduccionesUI() {
        
        this.buildGrid(); // antes del initWidget

        initWidget(uiBinder.createAndBindUi(this));
        
    }
    
    private void buildGrid() {
        // super.onLoad(); //To change body of generated methods, choose Tools | Templates.

        /*
         * Set a key provider that provides a unique key for each contact. If key is
         * used to identify contacts when fields (such as the name and address)
         * change.
         */
        nominaQuincenalProvider = new ListDataProvider<>(new ArrayList<NominaQuincenal>());
        dataGridDeducciones = new CellTable<>(nominaQuincenalProvider.getKeyProvider());
        //cellTable.setWidth("100%");
        //cellTable.setHeight("100%");

        /*
         * Do not refresh the headers every time the data is updated. The footer
         * depends on the current data, so we do not disable auto refresh on the
         * footer.
         */
        dataGridDeducciones.setAutoHeaderRefreshDisabled(true);

        // Set the message to display when the table is empty.
        dataGridDeducciones.setEmptyTableWidget(new Label("No hay conceptos"));

        // Attach a column sort handler to the ListDataProvider to sort the list.
        ColumnSortEvent.ListHandler<NominaQuincenal> sortHandler = new ColumnSortEvent.ListHandler<>(nominaQuincenalProvider.getList());
        dataGridDeducciones.addColumnSortHandler(sortHandler);

        dataGridDeducciones.setPageSize(20);

        // Add a selection model so we can select cells.
        final SelectionModel<NominaQuincenal> selectionModel = new SingleSelectionModel<>(nominaQuincenalProvider);
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                //System.out.println("123> " + event.getSource() + " - " + event.getAssociatedType());
                if (event.getSource() instanceof SingleSelectionModel) {

                    SingleSelectionModel selecter = (SingleSelectionModel) event.getSource();
                    Tabulador sel = (Tabulador) selecter.getSelectedObject();

                    GWT.log("Sel>> " + sel);
                }
            }
        });
        dataGridDeducciones.setSelectionModel(selectionModel);

        dataGridDeducciones.addDomHandler(new DoubleClickHandler() {
            @SuppressWarnings("unchecked")
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
//                DataGrid<Departamento> grid = (DataGrid<Departamento>) event.getSource();
//                int row = grid.getKeyboardSelectedRow();
//                Departamento item = grid.getVisibleItem(row);
            }
        }, DoubleClickEvent.getType());

        initTableColumns(sortHandler);

        // Add the CellList to the adapter in the database.
        nominaQuincenalProvider.addDataDisplay(dataGridDeducciones);

    }
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns(ColumnSortEvent.ListHandler<NominaQuincenal> sortHandler) {

        // ID
        Column<NominaQuincenal, String> idCol = new Column<NominaQuincenal, String>(new TextCell()) {
                    @Override
                    public String getValue(NominaQuincenal object) {
                        return object.getId().toString();
                    }
                };
        dataGridDeducciones.addColumn(idCol, "ID");
        dataGridDeducciones.setColumnWidth(idCol, 40, Style.Unit.PX);

        // Concepto
        Column<NominaQuincenal, String> conceptoCol = new Column<NominaQuincenal, String>((new TextCell())) {
            @Override
            public String getValue(NominaQuincenal object) {
                Concepto concepto = object.getConcepto();
                return concepto.getCode() + " " + concepto.getIdTipoMovimiento()+ " " + concepto.getIdTipoCalculo()+ " " + concepto.getName();
            }
        };
//        conceptoCol.setSortable(true);
//        sortHandler.setComparator(conceptoCol, new Comparator<Tabulador>() {
//            @Override
//            public int compare(Tabulador o1, Tabulador o2) {
//                return o1.getCode().compareTo(o2.getCode());
//            }
//        });
        dataGridDeducciones.addColumn(conceptoCol, "Concepto");
        dataGridDeducciones.setColumnWidth(conceptoCol, 300, Style.Unit.PX);

        // Cantidad
        Column<NominaQuincenal, String> cantidadCol = new Column<NominaQuincenal, String>(new TextCell()) {
                    @Override
                    public String getValue(NominaQuincenal object) {
                        NumberFormat fmt = NumberFormat.getCurrencyFormat();
                        String output = object.getCantidad().toString();
                        Double value = Double.parseDouble(output);
                        String formatted = fmt.format(value);
                        return formatted;
                    }
                };
        cantidadCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
//        cantidadCol.setSortable(true);
//        sortHandler.setComparator(sueldoCol, new Comparator<Tabulador>() {
//            @Override
//            public int compare(Tabulador o1, Tabulador o2) {
//                return o1.getSueldo().compareTo(o2.getSueldo());
//            }
//        });
        dataGridDeducciones.addColumn(cantidadCol, "Cantidad");
        dataGridDeducciones.setColumnWidth(cantidadCol, 60, Style.Unit.PX);

    }
    
    private EmpleadoREST empleadoREST;
    private EmpleadoREST getEmpleadosREST() {
        if (empleadoREST == null) {
            empleadoREST = new EmpleadoREST();

            empleadoREST.addRESTExecutedListener(new RestMethodExecutedListener());
        }
        return empleadoREST;
    }
    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_EMPLEADO_NOMINA_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // re-carga el provider con el empleado reloaded
                    EmpleadoNomina reloaded = (EmpleadoNomina) methodEvent.getResult();
                    
                    nominaQuincenalProvider.setList(reloaded.getNominaQuincenalCollection());
                } else {
                    
                }
            } 
            
        }
    }
    
    public void setSelectedBean(Integer idEmpleadoBaseSelected) {
        
        idEmpleadoBaseSelected = idEmpleadoBaseSelected == null ? 0 : idEmpleadoBaseSelected;
        
        getEmpleadosREST().findEmpleadoNominaById(idEmpleadoBaseSelected);
        
    }
    
}
