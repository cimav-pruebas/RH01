/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nominahisto;

import cimav.client.data.domain.EmpleadoBase;
import cimav.client.view.catalogos.empleados.ICellListResources;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.EmpleadoListCell;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.provider.EmpleadosBaseProvider;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.cellview.client.HasKeyboardSelectionPolicy;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconFlip;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author calderon
 */
public class NominaHistoList extends Composite {

    private static NominaHistoListUiBinder uiBinder = GWT.create(NominaHistoListUiBinder.class);
    interface NominaHistoListUiBinder extends UiBinder<Widget, NominaHistoList> {
    }

    @UiField
    TextBox searchTxt;
    @UiField
    Button reloadBtn;
    
    @UiField
    public ScrollPanel scrollPanel;
    CellList<EmpleadoBase> cellList;
    
    private static EmpleadosBaseProvider empleadosBaseProvider;
    private final SingleSelectionModel<EmpleadoBase> selectionModel;
    private EmpleadoListCell empleadoListCell;
    
    
    @UiField
    NominaHistoUI nominaHistoUI;


    
    public NominaHistoList() {
        initWidget(uiBinder.createAndBindUi(this));

        empleadosBaseProvider = new EmpleadosBaseProvider();

        //CellList.Resources cellListResources = GWT.create(CellList.Resources.class);
        CellList.Resources cellListResources = GWT.create(ICellListResources.class);
        selectionModel = new SingleSelectionModel<>();
        empleadoListCell = new EmpleadoListCell(selectionModel);
        cellList = new CellList<EmpleadoBase>(empleadoListCell, cellListResources, empleadosBaseProvider.getDataProvider());
        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionHandler());
        cellList.setPageSize(800);  // máximo son 400 empleados. Al mostrarlos todos, no se requiere Pager.
        scrollPanel.add(cellList);
        
        /* Inyectarle style absolute al Abuelo para que funcione el scroll del cellList */
        Element divAbue = cellList.getElement().getParentElement().getParentElement();
        divAbue.getStyle().setPosition(Style.Position.ABSOLUTE);
        divAbue.getStyle().setTop(0, Style.Unit.PX);
        divAbue.getStyle().setLeft(0, Style.Unit.PX);
        divAbue.getStyle().setBottom(0, Style.Unit.PX);
        divAbue.getStyle().setRight(0, Style.Unit.PX);

        // Add the CellList to the adapter in the database.
        empleadosBaseProvider.addDataDisplay(cellList);

        // Escucha los metodos y las acciones (find_all, update, create, save, reloadById, etc.)
        empleadosBaseProvider.addMethodExecutedListener(new ProviderMethodExecutedListener());
        
        reloadBtn.setIconFlip(IconFlip.HORIZONTAL);
        reloadBtn.addClickHandler(new ReloadClickHandler());

        searchTxt.addKeyUpHandler(new SearchKeyUpHandler());

// >>        empleadosEditorUI.addActionListener(new EditorActionListener());
        // orden inicial
        orderBy = EmpleadosBaseProvider.ORDER_BY_NAME;
        // filtro inicial
        empleadosBaseProvider.getDataProvider().setFilter("");

        /* Al arrancar, cargar a todos los empleados */
        reloadAll();

    }
    
    private class ReloadClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            reloadAll();
        }
    }

    private class SearchKeyUpHandler implements KeyUpHandler {

        @Override
        public void onKeyUp(KeyUpEvent event) {
            filtrar();
        }
    }
    
    private void deseleccionar() {
        EmpleadoBase empSel = selectionModel.getSelectedObject();
        if (empSel != null) {
            // deseleccionar sin lanzar el listener
            selectionModel.setSelected(empSel, false);
            
            //nominaUI.setSelectedBean(0); // muestra Empleado inexistente
        }
    }

    private void filtrar() {
        final String txtToSearch = searchTxt.getText();
        empleadosBaseProvider.getDataProvider().setFilter(txtToSearch);

        String rows = empleadosBaseProvider.getRowCountPropotional();
        reloadBtn.setText(rows);

        EmpleadoBase empSel = selectionModel.getSelectedObject();
        if (empSel != null) {
            if (empleadosBaseProvider.containsItem(empSel)) {
                // si aparece en la lista filtrada, mostrarlo
                scrollIntoView(empSel);
            } else {
                // si en la lista filtrada no aparece el seleccionado, deseleccionar
                deseleccionar();
                
                //nominaUI.setSelectedBean(null);
            }
        }

    }

    private int orderBy;

    @UiHandler({"btnByName", "btnByCode", "btnByGrupo", "btnByNivel"})
    void handleClick(ClickEvent e) {
        orderBy = -1;
        if (e.getSource().toString().contains("Nombre")) {
            orderBy = EmpleadosBaseProvider.ORDER_BY_NAME;
        } else if (e.getSource().toString().contains("Num")) {
            orderBy = EmpleadosBaseProvider.ORDER_BY_CODE;
        } else if (e.getSource().toString().contains("Grp")) {
            orderBy = EmpleadosBaseProvider.ORDER_BY_GRUPO;
        } else if (e.getSource().toString().contains("Niv")) {
            orderBy = EmpleadosBaseProvider.ORDER_BY_NIVEL;

            //selectionModel.setSelected(new Empleado(), true);
        }
        this.orderBy();
    }

    private void orderBy() {
        empleadosBaseProvider.order(this.orderBy);

        EmpleadoBase empleadoSelected = (EmpleadoBase) selectionModel.getSelectedObject();
        scrollIntoView(empleadoSelected);
    }

    private void reloadAll() {
        // ReCargar todos los empleados
        empleadosBaseProvider.findAllBase();
    }
    
    private class ProviderMethodExecutedListener implements EmpleadosBaseProvider.MethodExecutedListener {

        @Override
        public void onMethodExecuted(MethodEvent event) {
            if (EMethod.FIND_BASE_ALL.equals(event.getMethod())) {

                if (ETypeResult.SUCCESS.equals(event.getTypeResult())) {
                    Growl.growl("Carga de registros realizada");
                } else {
                    Window.alert("Falló carga de registros");
                }
            }
        }
    }
    
    private int scrollIntoView(EmpleadoBase empleadoToView) {
        int idx = -2;
        if (cellList.getVisibleItemCount() > 0 && empleadoToView != null) {
            idx = cellList.getVisibleItems().indexOf(empleadoToView);
            if (idx >= 0) {
                cellList.getRowElement(idx).scrollIntoView();
            }
        }
        return idx;
    }
    
    private class SelectionHandler implements SelectionChangeEvent.Handler {

        @Override
        public void onSelectionChange(SelectionChangeEvent event) {
            if (event.getSource() instanceof SingleSelectionModel) {
                
                    EmpleadoBase empleadoSelected = null;
                    try {
                        SingleSelectionModel selModel = (SingleSelectionModel) event.getSource();
                        empleadoSelected = (EmpleadoBase) selModel.getSelectedObject();
                        
                        Integer id = empleadoSelected == null ? 0 : empleadoSelected.getId();
                        
                        
                        
                        
                        nominaHistoUI.setSelectedBean(id);

                        int idx = scrollIntoView(empleadoSelected);
                        
                    } catch (Exception e) {
                        Window.alert("onSelectionChange >> " + empleadoSelected + " >> " + e.getMessage());
                    }
            }
        }
    }
    

}
