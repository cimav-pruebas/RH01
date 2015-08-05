/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;


import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.NominaQuincenal;
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
public class NominaPercepcionesUI extends Composite {
    
    private static NominaPercepcionesUIUiBinder uiBinder = GWT.create(NominaPercepcionesUIUiBinder.class);
    
    interface NominaPercepcionesUIUiBinder extends UiBinder<Widget, NominaPercepcionesUI> {
    }
    
    @UiField(provided = true) 
    DataGrid<NominaQuincenal> dataGridPercepciones;

    private ListDataProvider<NominaQuincenal> nominaQuincenalProvider;
    
    public NominaPercepcionesUI() {
        
        this.buildGrid(); // antes del initWidget

        initWidget(uiBinder.createAndBindUi(this));
        
    }
    
    private void buildGrid() {
        
        List<NominaQuincenal> nominaQuincenalList = new ArrayList<>();
        nominaQuincenalProvider = new ListDataProvider<>(nominaQuincenalList);
        dataGridPercepciones = new DataGrid<>(nominaQuincenalProvider.getKeyProvider());

        /*
         * Do not refresh the headers every time the data is updated. The footer
         * depends on the current data, so we do not disable auto refresh on the
         * footer.
         */
        dataGridPercepciones.setAutoHeaderRefreshDisabled(true);

        // Set the message to display when the table is empty.
        dataGridPercepciones.setEmptyTableWidget(new Label("No hay percepciones"));

//        // Attach a column sort handler to the ListDataProvider to sort the list.
//        ColumnSortEvent.ListHandler<NominaQuincenal> sortHandler = new ColumnSortEvent.ListHandler<>(nominaQuincenalProvider.getList());
//        dataGridPercepciones.addColumnSortHandler(sortHandler);
        
        dataGridPercepciones.setPageSize(20);

//        // Add a selection model so we can select cells.
//        final SelectionModel<NominaQuincenal> selectionModel = new SingleSelectionModel<>(nominaQuincenalProvider);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            @Override
//            public void onSelectionChange(SelectionChangeEvent event) {
//                //System.out.println("123> " + event.getSource() + " - " + event.getAssociatedType());
//                if (event.getSource() instanceof SingleSelectionModel) {
//
//                    SingleSelectionModel selecter = (SingleSelectionModel) event.getSource();
//                    Tabulador sel = (Tabulador) selecter.getSelectedObject();
//
//                    GWT.log("Sel>> " + sel);
//                }
//            }
//        });
//        dataGridPercepciones.setSelectionModel(selectionModel);

//        dataGridPercepciones.addDomHandler(new DoubleClickHandler() {
//            @SuppressWarnings("unchecked")
//            @Override
//            public void onDoubleClick(DoubleClickEvent event) {
////                DataGrid<Departamento> grid = (DataGrid<Departamento>) event.getSource();
////                int row = grid.getKeyboardSelectedRow();
////                Departamento item = grid.getVisibleItem(row);
//            }
//        }, DoubleClickEvent.getType());
//
        initTableColumns(); //sortHandler);
//
//        // Add the CellList to the adapter in the database.
        nominaQuincenalProvider.addDataDisplay(dataGridPercepciones);

    }
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns() {

        // Concepto
        Column<NominaQuincenal, String> conceptoCol = new Column<NominaQuincenal, String>((new TextCell())) {
            @Override
            public String getValue(NominaQuincenal object) {
                Concepto concepto = object.getConcepto();
                return concepto.getCode() + " " + concepto.getIdTipoCalculo()+ " " + concepto.getName();
            }
        };
        dataGridPercepciones.addColumn(conceptoCol, "Concepto");

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
                
                List<NominaQuincenal> items = dataGridPercepciones.getVisibleItems();
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
        dataGridPercepciones.addColumn(cantidadCol,  new SafeHtmlHeader(SafeHtmlUtils.fromString("Cantidad")), subTotalFooter);
        dataGridPercepciones.setColumnWidth(cantidadCol, 100, Style.Unit.PX);
        
    }
    
//    private EmpleadoREST getEmpleadosREST() {
//        if (empleadoREST == null) {
//            empleadoREST = new EmpleadoREST();
//
//            empleadoREST.addRESTExecutedListener(new RestMethodExecutedListener());
//        }
//        return empleadoREST;
//    }
//    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {
//
//        @Override
//        public void onRESTExecuted(MethodEvent methodEvent) {
//            if (EMethod.FIND_EMPLEADO_NOMINA_BY_ID.equals(methodEvent.getMethod())) {
//                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
//                    // re-carga el provider con el empleado reloaded
//                    empleadoNominaSelected = (EmpleadoNomina) methodEvent.getResult();
//                    
//                    nominaQuincenalProvider.setList(empleadoNominaSelected.getNominaQuincenalCollectionByTipoMovimiento(ETipoMovimiento.PERCEPTION));
//                } else {
//                    
//                }
//            } 
//            
//        }
//    }
    
    public void setList(List<NominaQuincenal> percepciones) {
        nominaQuincenalProvider.setList(percepciones);
    }
    
//    public void setSelectedBean(Integer idEmpleadoBaseSelected) {
//        
//        idEmpleadoBaseSelected = idEmpleadoBaseSelected == null ? 0 : idEmpleadoBaseSelected;
//        
////        getEmpleadosREST().findEmpleadoNominaById(idEmpleadoBaseSelected);
//        
//    }

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
