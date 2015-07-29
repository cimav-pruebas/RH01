/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados;

import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.Empleado;
import cimav.client.view.provider.EmpleadosProvider;
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
 * @author juan.calderon
 */
public class EmpleadosUI extends Composite {

    private static final EmpleadosUIUiBinder uiBinder = GWT.create(EmpleadosUIUiBinder.class);

    interface EmpleadosUIUiBinder extends UiBinder<Widget, EmpleadosUI> {
    }

    @UiField
    public ScrollPanel scrollPanel;
    CellList<Empleado> cellList;

    @UiField
    TextBox searchTxt;
    @UiField
    Button reloadBtn;
    @UiField
    Button addBtn;

    @UiField
    EmpleadosEditorUI empleadosEditorUI;
    
    private final SingleSelectionModel<Empleado> selectionModel;

    public EmpleadosUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
        //CellList.Resources cellListResources = GWT.create(CellList.Resources.class);
        CellList.Resources cellListResources = GWT.create(ICellListResources.class);
        selectionModel = new SingleSelectionModel<>();
        cellList = new CellList<>(new EmpleadoCell(selectionModel), cellListResources, EmpleadosProvider.get().getDataProvider());
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
        EmpleadosProvider.get().addDataDisplay(cellList);

        // Escucha los metodos y las acciones (find_all, update, create, save, reloadById, etc.)
        EmpleadosProvider.get().addMethodExecutedListener(new ProviderMethodExecutedListener());
        
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
                
                // enviar empleado nuevo al Bean del Editor
                Empleado empNuevo = new Empleado();
                empleadosEditorUI.setSelectedBean(empNuevo);
                
            }
        });

        searchTxt.addKeyUpHandler(new KeyUpHandler() {
            @Override
            public void onKeyUp(KeyUpEvent event) {

                EmpleadosUI.this.filtrar();
            }
        });

        // orden inicial
        orderBy = EmpleadosProvider.ORDER_BY_NAME;
        // filtro inicial
        EmpleadosProvider.get().getDataProvider().setFilter("");

        /* Al arrancar, cargar a todos los empleados */
        reloadAll();
    }

    private void deseleccionar() {
        Empleado empSel = selectionModel.getSelectedObject();
        if (empSel != null) {
            // deseleccionar sin lanzar el listener
            selectionModel.setSelected(empSel, false);
        }
    }
    
    private void filtrar() {
        final String txtToSearch = searchTxt.getText();
        EmpleadosProvider.get().getDataProvider().setFilter(txtToSearch);

        String rows = EmpleadosProvider.get().getRowCountPropotional();
        reloadBtn.setText(rows);
        
        Empleado empSel = selectionModel.getSelectedObject();
        if (empSel != null) {
            if (EmpleadosProvider.get().containsItem(empSel)) {
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
            orderBy = EmpleadosProvider.ORDER_BY_NAME;
        } else if (e.getSource().toString().contains("Num")) {
            orderBy = EmpleadosProvider.ORDER_BY_CODE;
        } else if (e.getSource().toString().contains("Grp")) {
            orderBy = EmpleadosProvider.ORDER_BY_GRUPO;
        } else if (e.getSource().toString().contains("Niv")) {
            orderBy = EmpleadosProvider.ORDER_BY_NIVEL;

            //selectionModel.setSelected(new Empleado(), true);
        }
        this.orderBy();
    }

    private void orderBy() {
        EmpleadosProvider.get().order(this.orderBy);
        
        Empleado empleadoSelected = (Empleado) selectionModel.getSelectedObject();
        scrollIntoView(empleadoSelected);
    }

    private void reloadAll() {
        // ReCargar todos los empleados
        EmpleadosProvider.get().findAll();
    }

    private class ProviderMethodExecutedListener implements EmpleadosProvider.MethodExecutedListener {

        @Override
        public void onMethodExecuted(MethodEvent event) {
            if (EMethod.FIND_ALL.equals(event.getMethod())) {
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
            } else if (EMethod.CREATE.equals(event.getMethod())) {
                if (ETypeResult.SUCCESS.equals(event.getTypeResult())) {
                    Growl.growl("Registro nuevo agregado");
                    Empleado empCreado = (Empleado) event.getResult();
                    selectionModel.setSelected(empCreado, true);
                } else {
                    //setSelectedBean(null);
                    String msgError = "Falló creación de registro nuevo \n" + event.getReason();
                    Window.alert(msgError);
                }
            }
        }
    }

    private class EmpleadoCell extends AbstractCell<Empleado> {

        private SingleSelectionModel<Empleado> selectionModel;

        EmpleadoCell(SingleSelectionModel<Empleado> docSelectionModel) {
            this.selectionModel = docSelectionModel;
        }

        @Override
        public void render(Cell.Context context, Empleado value, SafeHtmlBuilder sb) {
            if (value == null) {
                return;
            }

            boolean isSelected = this.selectionModel != null && this.selectionModel.getSelectedObject() != null
                    && this.selectionModel.getSelectedObject().equals(value);

            // TODO reemplazar código a pie por EmpleadosItem
            String es_null = "---";
            String grupoStr = value.getGrupo() != null ? value.getGrupo().getCode() : es_null;
            String deptoCodeStr = value.getDepartamento() != null ? value.getDepartamento().getCode() : es_null;
            String deptoNameStr = value.getDepartamento() != null ? value.getDepartamento().getName() : es_null;
            String nivelStr = value.getNivel() != null ? value.getNivel().getCode() : es_null;
            String sedeStr = value.getSede() != null ? value.getSede().getAbrev() : es_null;
            //sedeStr = value.isDirty() != null ? value.isDirty().toString() : es_null;

            String html
                    = "<table width='100%' cellspacing='0' cellpadding='0' style='cursor: pointer; text-align: left; vertical-align: middle; border-bottom:1px solid lightgray;'>\n"
                    + "  <tr>\n"
                    + "    <td width='4px' rowspan='6' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    //                    + "    <td colspan='3' style='height:10px;'><span STYLE_INDICADOR_REEMPLAZO /></td>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "    <td width='4px' rowspan='6' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td width='78px' rowspan='3' style='text-align: center;'><img src='URL_FOTO_REEMPLAZO' style='border:1px solid lightgray; margin-top: 3px; border-radius:50%; padding:2px;'></td>\n"
                    + "    <td colspan='2' style='vertical-align: bottom;'><h4 style='margin-top: 0px; margin-bottom: 0px; font-size: 17px;'>APELLIDOS_REEMPLAZO,</h4></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='2' style='vertical-align: top;'><h5 style='margin-top: 0px; margin-bottom: 0px;'>NOMBRE_REEMPLAZO</h5></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td  colspan='1'> "
                    //                    + " <code class='label-cyt-grp-niv'><span style='font-size: medium;' >CODE_REEMPLAZO</span></code> "
                    + " <code class='label-cyt-grp-niv'><span >CODE_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >GRUPO_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >NIVEL_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >SEDE_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >DEPTO_CODIGO_REEMPLAZO</span></code> "
                                        + " <code class=\"label-cyt-grp-niv\"><span >ID_REEMPLAZO</span></code> "
                    + "    </td>\n"
                    //                    + "    <td style='text-align: right;'><i class='fa fa-info-circle fa-lg' style='opacity: 0.5; padding-right: 5px;'></i></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td style='text-align:center;' ></td>\n"
                    + "    <td>"
                    + " <code class=\"label-cyt-grp-niv\"><span >DEPTO_NAME_REEMPLAZO</span></code> "
                    + "    </td>\n"
                    + "    <td></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "  </tr>\n"
                    + "</table>";

            if (isSelected) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #628cd5;");
//                html = html.replace("STYLE_INDICADOR_REEMPLAZO", 
//                        "style = 'position: relative; float: right; top: 4px; width: 14px; height: 14px; border-top: 3px solid #628cd5;\n" +
//                                                                 "-moz-transform: rotate(45deg); -ms-transform: rotate(45deg); -webkit-transform: rotate(45deg);\n" +
//                                                                 "transform: rotate(45deg); overflow: hidden; right: 8px; border-right: 3px solid #628cd5;' ");
            } else if (value.isDirty() != null && value.isDirty()) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: lightgray;");
            } else {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #F8F8F8;");
//                html = html.replace("STYLE_INDICADOR_REEMPLAZO", "");
            }
            
            try {
            html = html.replace("CODE_REEMPLAZO", chkStrNull(value.getCode()));
            html = html.replace("URL_FOTO_REEMPLAZO", chkStrNull(value.getUrlPhoto()));
            html = html.replace("APELLIDOS_REEMPLAZO", EmpleadosUI.ellipse(chkStrNull(value.getApellidoPaterno()), 18) 
                    + " " + EmpleadosUI.ellipse(chkStrNull(value.getApellidoMaterno()), 18));
            html = html.replace("NOMBRE_REEMPLAZO", chkStrNull(value.getNombre()));
            html = html.replaceAll("RFC_REEMPLAZO", chkStrNull(value.getRfc()));
            html = html.replace("GRUPO_REEMPLAZO", chkStrNull(grupoStr));
            html = html.replace("NIVEL_REEMPLAZO", chkStrNull(nivelStr));
            html = html.replace("DEPTO_CODIGO_REEMPLAZO", chkStrNull(deptoCodeStr));
            html = html.replace("DEPTO_NAME_REEMPLAZO", EmpleadosUI.ellipse(chkStrNull(deptoNameStr), 32));
            html = html.replace("SEDE_REEMPLAZO", chkStrNull(sedeStr));
            html = html.replace("ID_REEMPLAZO", value.getId().toString());

            sb.appendHtmlConstant(html);
            } catch (Exception e) {
                Window.alert("Catch it! " + html);
            }
        }
    }
    
    private String chkStrNull(String str) {
        return str != null ? str.trim() : "---";
    }

//    public static native String camelize(String str)/*-{
//            return (str.match(/\-/gi) ? str.toLowerCase().replace(/\-(\w)/gi, function(a, c){return c.toUpperCase();}) : str);
//    }-*/;    
//    
//    public static String capitalize(String value) {
//    return value == null ? value : value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
//  }
//
    /**
     * Truncate a string and add an ellipsis ('...') to the end if it exceeds
     * the specified length.
     *
     * @param value the string to truncate
     * @param len the maximum length to allow before truncating
     * @return the converted text
     */
    public static String ellipse(String value, int len) {
        if (value != null && value.length() > len) {
            return value.substring(0, len - 3) + "...";
        }
        return value;
    }

    private int scrollIntoView(Empleado empleadoToView) {
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

                Empleado empleadoSelected = null;
                try {
                    SingleSelectionModel selModel = (SingleSelectionModel) event.getSource();
                    empleadoSelected = (Empleado) selModel.getSelectedObject();
                
                    if (empleadoSelected == null) {
                        return;
                    }
                
                    int idx = scrollIntoView(empleadoSelected);
                
                    //GWT.log(idx + " >>> Sel: " + empleadoSelected);
                
                    empleadosEditorUI.setSelectedBean(empleadoSelected);
                } catch (Exception e) {
                    Window.alert("onSelectionChange >> " + empleadoSelected + " >> " + e.getMessage());
                }
            }
        }
    }
    
}