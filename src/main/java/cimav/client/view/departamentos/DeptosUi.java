/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.departamentos;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Departamento;
import cimav.client.view.empleados.ICellListResources;
import cimav.client.view.provider.DeptosProvider;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.KeyUpEvent;
import com.google.gwt.event.dom.client.KeyUpHandler;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
 * @author juan.calderon
 */
public class DeptosUi extends Composite {
    
    private static DeptosUiUiBinder uiBinder = GWT.create(DeptosUiUiBinder.class);
    
    interface DeptosUiUiBinder extends UiBinder<Widget, DeptosUi> {
    }
    
    @UiField public ScrollPanel scrollPanel;
    CellList<Departamento> cellList;

    @UiField TextBox searchTxt;
    @UiField Button reloadBtn;
    @UiField Button addBtn;

    @UiField DeptosEditorUi deptosEditorUi;
    
    private final SingleSelectionModel<Departamento> selectionModel;
    
    public DeptosUi() {
        initWidget(uiBinder.createAndBindUi(this));
        
        CellList.Resources cellListResources = GWT.create(ICellListResources.class);
        selectionModel = new SingleSelectionModel<>();
        cellList = new CellList<>(new DeptoCell(selectionModel), cellListResources, DeptosProvider.get().getDataProvider());
        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
        cellList.setSelectionModel(selectionModel);
        selectionModel.addSelectionChangeHandler(new SelectionHandler());
        cellList.setPageSize(400);
        scrollPanel.add(cellList);

        Element divAbue = cellList.getElement().getParentElement().getParentElement();
        divAbue.getStyle().setPosition(Style.Position.ABSOLUTE);
        divAbue.getStyle().setTop(0, Style.Unit.PX);
        divAbue.getStyle().setLeft(0, Style.Unit.PX);
        divAbue.getStyle().setBottom(0, Style.Unit.PX);
        divAbue.getStyle().setRight(0, Style.Unit.PX);

        // Add the CellList to the adapter in the database.
        DeptosProvider.get().addDataDisplay(cellList);

        // Escucha los metodos y las acciones (find_all, update, create, save, reloadById, etc.)
        DeptosProvider.get().addMethodExecutedListener(new ProviderMethodExecutedListener());
        
        reloadBtn.setIconFlip(IconFlip.HORIZONTAL);
        reloadBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                reloadAll();
            }
        });

        addBtn.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                
                // Deseleccionar
                deseleccionar();
                
                // enviar nuevo al Bean del Editor
                Departamento nuevo = new Departamento();
                deptosEditorUi.setSelectedBean(nuevo);
                
            }
        });

        searchTxt.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {

                DeptosUi.this.filtrar();
            }
        });

        // filtro inicial
        DeptosProvider.get().getDataProvider().setFilter("");

        /* Al arrancar, cargar a todos los empleados */
        reloadAll();
    }

    private void deseleccionar() {
        Departamento sel = selectionModel.getSelectedObject();
        if (sel != null) {
            // deseleccionar sin lanzar el listener
            selectionModel.setSelected(sel, false);
        }
    }
    
    private void filtrar() {
        final String txtToSearch = searchTxt.getText();
        DeptosProvider.get().getDataProvider().setFilter(txtToSearch);

        String rows = DeptosProvider.get().getRowCountPropotional();
        reloadBtn.setText(rows);
        
        Departamento sel = selectionModel.getSelectedObject();
        if (sel != null) {
            if (DeptosProvider.get().containsItem(sel)) {
                // si aparece en la lista filtrada, mostrarlo
                scrollIntoView(sel);
            } else {
                // si en la lista filtrada no aparece el seleccionado, deseleccionar
                deseleccionar();
                deptosEditorUi.setSelectedBean(null);
            }
        }
        
    }
    
    private void reloadAll() {
        // ReCargar todos los empleados
        DeptosProvider.get().findAll();
    }

    private class ProviderMethodExecutedListener implements DeptosProvider.MethodExecutedListener {

        @Override
        public void onMethodExecuted(MethodEvent event) {
            if (EMethod.FIND_ALL.equals(event.getMethod())) {

                deseleccionar();
                deptosEditorUi.setSelectedBean(null);
                searchTxt.setFocus(true);

                DeptosUi.this.filtrar();

                if (ETypeResult.SUCCESS.equals(event.getTypeResult())) {
                    Growl.growl("Carga de departamentos realizada");
                } else {
                    Window.alert("Falló carga de departamentos");
                }
            } else if (EMethod.CREATE.equals(event.getMethod())) {
                if (ETypeResult.SUCCESS.equals(event.getTypeResult())) {
                    Growl.growl("Departamento nuevo agregado");
                    Departamento created = (Departamento) event.getResult();
                    selectionModel.setSelected(created, true);
                } else {
                    //setSelectedBean(null);
                    String msgError = "Falló creación de Departamento nuevo \n" + event.getReason();
                    Window.alert(msgError);
                }
            }
        }
    }
    
    private class DeptoCell extends AbstractCell<Departamento> {

        private SingleSelectionModel<Departamento> selectionModel;

        DeptoCell(SingleSelectionModel<Departamento> docSelectionModel) {
            this.selectionModel = docSelectionModel;
        }

        @Override
        public void render(Cell.Context context, Departamento value, SafeHtmlBuilder sb) {
            if (value == null) {
                return;
            }

            boolean isSelected = this.selectionModel != null && this.selectionModel.getSelectedObject() != null
                    && this.selectionModel.getSelectedObject().equals(value);

            String html
                    = "<table width='100%' cellspacing='0' cellpadding='0' style='cursor: pointer; text-align: left; vertical-align: middle; border-bottom:1px solid lightgray;'>\n"
                    + "  <tr>\n"
                    + "    <td width='4px' rowspan='6' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "    <td width='4px' rowspan='6' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='2' style='vertical-align: top;'><h5 style='margin-top: 0px; margin-bottom: 0px;'>NOMBRE_REEMPLAZO</h5></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td  colspan='1'> "
                    + "         <code class='label-cyt-grp-niv'><span >CODE_REEMPLAZO</span></code> "
                    + "    </td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='3' style='height:10px; border-bottom:1px solid lightgray;'></td>\n"
                    + "  </tr>\n"
                    + "</table>";

            if (isSelected) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #628cd5;");
            } else if (value.isDirty() != null && value.isDirty()) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: lightgray;");
            } else {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #F8F8F8;");
            }
            
            html = html.replace("CODE_REEMPLAZO", chkStrNull(value.getCode()));
            html = html.replace("NOMBRE_REEMPLAZO", chkStrNull(value.getName()));

            sb.appendHtmlConstant(html);
        }
    }
    
    private String chkStrNull(String str) {
        return str != null ? str.trim() : "---";
    }

    public static String ellipse(String value, int len) {
        if (value != null && value.length() > len) {
            return value.substring(0, len - 3) + "...";
        }
        return value;
    }

    private int scrollIntoView(Departamento itemToView) {
        int idx = -2;
        if (cellList.getVisibleItemCount() > 0 && itemToView != null) {
            idx = cellList.getVisibleItems().indexOf(itemToView);
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

                Departamento selected = null;
                try {
                    SingleSelectionModel selModel = (SingleSelectionModel) event.getSource();
                    selected = (Departamento) selModel.getSelectedObject();
                
                    if (selected == null) {
                        return;
                    }
                
                    int idx = scrollIntoView(selected);
                
                    //GWT.log(idx + " >>> Sel: " + selected);
                
                    deptosEditorUi.setSelectedBean(selected);
                } catch (Exception e) {
                    Window.alert("onSelectionChange >> " + selected + " >> " + e.getMessage());
                }
            }
        }
    }
    
}
