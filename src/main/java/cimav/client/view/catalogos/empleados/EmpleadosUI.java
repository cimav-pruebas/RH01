/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados;

import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.EmpleadoBase;
import cimav.client.view.common.EmpleadoListCell;
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
import java.util.List;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.constants.IconFlip;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author juan.calderon
 */
public class EmpleadosUI extends Composite {

    private static final EmpleadosUIUiBinder uiBinder = GWT.create(EmpleadosUIUiBinder.class);

    interface EmpleadosUIUiBinder extends UiBinder<Widget, EmpleadosUI> {
    }

    @UiField
    public ScrollPanel scrollPanel;
    CellList<EmpleadoBase> cellList;

    @UiField
    TextBox searchTxt;
    @UiField
    Button reloadBtn;
    @UiField
    Button addBtn;

    @UiField
    EmpleadosEditorUI empleadosEditorUI;
    
    private static EmpleadosBaseProvider empleadosBaseProvider;
    
    private final SingleSelectionModel<EmpleadoBase> selectionModel;

    public EmpleadosUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
        empleadosBaseProvider = new EmpleadosBaseProvider();
        
        //CellList.Resources cellListResources = GWT.create(CellList.Resources.class);
        CellList.Resources cellListResources = GWT.create(ICellListResources.class);
        selectionModel = new SingleSelectionModel<>();
        EmpleadoListCell empleadoListCell = new EmpleadoListCell(selectionModel);
        cellList = new CellList<EmpleadoBase>(empleadoListCell, cellListResources, empleadosBaseProvider.getDataProvider());
        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionHandler());
        cellList.setPageSize(800);  // máximo son 400 empleados. Al mostrarlos todos, no se requiere Pager.
        scrollPanel.add(cellList);

//                SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
//        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
//        pager.setDisplay(dataGrid);
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

        addBtn.addClickHandler(new AddClickHandler());

        searchTxt.addKeyUpHandler(new SearchKeyUpHandler());
        
        empleadosEditorUI.addActionEditorListener(new EditorActionListener());
        
        // orden inicial
        orderBy = EmpleadosBaseProvider.ORDER_BY_NAME;
        // filtro inicial
        empleadosBaseProvider.getDataProvider().setFilter("");

        /* Al arrancar, cargar a todos los empleados */
        reloadAll();
    }
    
    private class EditorActionListener implements EmpleadosEditorUI.ActionEditorListener {
        @Override
        public void onActionEditor(MethodEvent restEvent) {
            if (EMethod.CREATE.equals(restEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                    EmpleadoBase created = (EmpleadoBase) restEvent.getResult();

                    // requiere usar la bases de intermediario; de lo contrario no recarga bien la lista
                    // requiere usar la bases de intermediario; de lo contrario no recarga bien la lista
                    List<EmpleadoBase> bases = empleadosBaseProvider.getDataProvider().getList();
                    bases.add(created);
                    empleadosBaseProvider.getDataProvider().setList(bases);

                    //selectionModel.setSelected(created, true);
                }
            } else if (EMethod.UPDATE.equals(restEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                    EmpleadoBase reloaded = (EmpleadoBase) restEvent.getResult();
                    EmpleadoBase selected = selectionModel.getSelectedObject(); 
                    selected.setName(reloaded.getName());
                    selected.setCode(reloaded.getCode());
                    selected.setDepartamento(reloaded.getDepartamento());
                    selected.setGrupo(reloaded.getGrupo());
                    selected.setSede(reloaded.getSede());
                    selected.setNivel(reloaded.getNivel());

                    // requiere usar la bases de intermediario; de lo contrario no recarga bien la lista
                    List<EmpleadoBase> bases = empleadosBaseProvider.getDataProvider().getList();
                    int idx = bases.indexOf(selected);
                    bases.set(idx, selected);
                    empleadosBaseProvider.getDataProvider().setList(bases);
                }
            } else if (EMethod.CANCEL.equals(restEvent.getMethod())) {
                // re-envia el base seleccionado al editor
                EmpleadoBase empBaseSelected = selectionModel.getSelectedObject();
                empleadosEditorUI.setSelectedBean(empBaseSelected.getId());
            }
        }
    }
    
    private class ReloadClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            reloadAll();
        }
    }

    private class AddClickHandler implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
                // Deseleccionar
                deseleccionar();
                
                // enviar empleado nuevo al Bean del Editor
                empleadosEditorUI.addNewEmpleado();
        }
    }
    
    private class SearchKeyUpHandler implements KeyUpHandler {
        @Override
        public void onKeyUp(KeyUpEvent event) {
            EmpleadosUI.this.filtrar();
        }
    }

    private void deseleccionar() {
        EmpleadoBase empSel = selectionModel.getSelectedObject();
        if (empSel != null) {
            // deseleccionar sin lanzar el listener
            selectionModel.setSelected(empSel, false);
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
                empleadosEditorUI.setSelectedBean(null);
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
                EmpleadosUI.this.orderBy();

                //EmpleadosUI.this.selectionModel.setSelected(null, true);
                deseleccionar();
                empleadosEditorUI.setSelectedBean(null);
                searchTxt.setFocus(true);

                EmpleadosUI.this.filtrar();

                if (ETypeResult.SUCCESS.equals(event.getTypeResult())) {
                    Growl.growl("Carga de registros realizada");
                } else {
                    Window.alert("Falló carga de registros");
                }
            } /*else if (EMethod.CREATE.equals(event.getMethod())) {
                if (ETypeResult.SUCCESS.equals(event.getTypeResult())) {
                    Growl.growl("Registro nuevo agregado");
                    EmpleadoBase empCreado = (EmpleadoBase) event.getResult();
                    selectionModel.setSelected(empCreado, true);
                } else {
                    //setSelectedBean(null);
                    String msgError = "Falló creación de registro nuevo \n" + event.getReason();
                    Window.alert(msgError);
                }
            } */
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
                
                    if (empleadoSelected == null) {
                        return;
                    }
                
                    int idx = scrollIntoView(empleadoSelected);
                
                    // GWT.log(idx + " >>> Sel: " + empleadoSelected);
                
                    ////Empleado sel = (Empleado) empleadoSelected;
                    
                    empleadosEditorUI.setSelectedBean(empleadoSelected.getId());
                    
                } catch (Exception e) {
                    Window.alert("onSelectionChange >> " + empleadoSelected + " >> " + e.getMessage());
                }
            }
        }
    }
    
  //  private EmpleadoREST empleadoREST;
    
}
