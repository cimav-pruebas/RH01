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
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.uibinder.client.UiBinder;
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
    
    public NominaMovimientosUI() {

        this.buildGrid(); // antes del initWidget
        
        initWidget(uiBinder.createAndBindUi(this));
        
//        dataGrid.setRowStyles(new RowStyles<NominaQuincenal>() {
//            @Override
//            public String getStyleNames(NominaQuincenal row, int rowIndex) {
//                if (rowIndex % 2 == 0) {
//                    return "mycellTableEvenRow";
//                } else {
//                    return "mycellTableOddRow";
//                }
//            }
//        });

    }

    /*
    @Override
    protected void onLoad() {
        GQuery cqFront = GQuery.$(".face.front");

        GQuery cqIcon = cqFront.$("fa.trigger.fa-pencil").attr("class","fa fa-pencil conceptos-icon-edit").attr("style",
            " border: 1px solid blueviolet; " +
            " cursor: pointer; " +
            " position: absolute; " +
            " bottom: 5px; " +
            " left: 45px; " +
            " z-index: 300; " 
        );
        
        Widget iCon = cqIcon.widget();
        iCon.addDomHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                GWT.log(">>>>FRONT " + event.getX() + ":" + event.getClientY());
            }
        }, ClickEvent.getType());
    }
    */
    
    private void buildGrid() { 
        
        List<NominaQuincenal> nominaQuincenalList = new ArrayList<>();
        provider = new ListDataProvider<>(nominaQuincenalList);
        
        CustomDataGridResources customDataGridResources = GWT.create(CustomDataGridResources.class);
        dataGrid = new DataGrid<>(60, customDataGridResources);
        //dataGrid = new DataGrid<>(provider.getKeyProvider());

        /*
         * Do not refresh the headers every time the data is updated. The footer
         * depends on the current data, so we do not disable auto refresh on the
         * footer.
         */
        dataGrid.setAutoHeaderRefreshDisabled(true);

        // Set the message to display when the table is empty.
        dataGrid.setEmptyTableWidget(new Label("Sin movimientos"));

//        // Attach a column sort handler to the ListDataProvider to sort the list.
//        ColumnSortEvent.ListHandler<NominaQuincenal> sortHandler = new ColumnSortEvent.ListHandler<>(nominaQuincenalProvider.getList());
//        dataGrid.addColumnSortHandler(sortHandler);
        
        dataGrid.setPageSize(20);
        dataGrid.setMinimumTableWidth(400, Style.Unit.PX);
        
//        // Add a selection model so we can select cells.
//        final SelectionModel<NominaQuincenal> selectionModel = new SingleSelectionModel<>(nominaQuincenalProvider);
//        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
//            @Override
//            public void onSelectionChange(SelectionChangeEvent event) {
//                if (event.getSource() instanceof SingleSelectionModel) {
////                    SingleSelectionModel selecter = (SingleSelectionModel) event.getSource();
////                    NominaQuincenal sel = (NominaQuincenal) selecter.getSelectedObject();
////                    GWT.log("" + sel);
//                }
//            }
//        });
//        dataGrid.setSelectionModel(selectionModel);

//        dataGrid.addDomHandler(new DoubleClickHandler() {
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
        provider.addDataDisplay(dataGrid);

        
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
                //return concepto.getCode() + " " + concepto.getIdTipoConcepto()+ " " +  concepto.getIdTipoMovimiento()+ " " + concepto.getName();
                return concepto.getName();
            }
        };
        dataGrid.addColumn(conceptoCol, "Concepto");
        dataGrid.setColumnWidth(conceptoCol, 80, Style.Unit.PCT);
        
        // Cantidad
        Column<NominaQuincenal, String> cantidadCol = new Column<NominaQuincenal, String>(new TextCell()) {
                    @Override
                    public String getValue(NominaQuincenal object) {
                        return Utils.formatCantidad(object.getCantidad());
                    }
                };
        cantidadCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
//        // Total Percepciones
//        Header<String> subTotalFooter = new Header<String>(new TextCell()) {
//            @Override
//            public String getValue() {
//                
//                List<NominaQuincenal> items = dataGrid.getVisibleItems();
//                if (items.isEmpty()) {
//                    return "";
//                } else {
//                    // create MathContext object with 2 precision
//                    BigDecimal totalPercepciones = BigDecimal.ZERO;
//                    for (NominaQuincenal nomQuin : items) {
//                        totalPercepciones = totalPercepciones.add(nomQuin.getCantidad());
//                    }
//                    return Utils.formatCurrency(totalPercepciones);
//                }
//            }
//            
//        };
        SafeHtmlHeader headerCantidad = new SafeHtmlHeader(new SafeHtml() { 
            @Override 
            public String asString() { 
                return "<p style='text-align:center; margin-bottom: 0px;'>Cantidad</p>"; 
            } 
         });
        SafeHtmlHeader footerCantidad = new SafeHtmlHeader(new SafeHtml() { 
            @Override 
            public String asString() { 
                String result = "0.00";
                List<NominaQuincenal> items = dataGrid.getVisibleItems();
                if (!items.isEmpty()) {
                    // create MathContext object with 2 precision
                    BigDecimal totalPercepciones = BigDecimal.ZERO;
                    for (NominaQuincenal nomQuin : items) {
                        totalPercepciones = totalPercepciones.add(nomQuin.getCantidad());
                    }
                    result = Utils.formatCurrency(totalPercepciones);
                }
                return "<p style=\"text-align:right;\">" + result.trim() + "</p>"; 
            } 
         });
        dataGrid.addColumn(cantidadCol, headerCantidad, footerCantidad);
        dataGrid.setColumnWidth(cantidadCol, 20, Style.Unit.PCT);
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
        
        Collections.sort(percepciones, new Comparator<NominaQuincenal>() {
            @Override
            public int compare(NominaQuincenal obj1, NominaQuincenal obj2) {
                return obj1.getConcepto().getCode().compareTo(obj2.getConcepto().getCode());
            }
        });
        
        provider.setList(percepciones);
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
