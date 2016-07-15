/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.departamentos;

import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.Departamento;
import cimav.client.data.domain.EmpleadoBase;
import cimav.client.view.catalogos.empleados.ICellListResources;
import cimav.client.view.common.EmpleadoListCell;
import cimav.client.view.provider.DeptosProvider;
import cimav.client.view.provider.EmpleadosBaseProvider;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.TabListItem;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.handler.property.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.handler.property.PropertyChangeHandler;
//import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
//import org.jboss.errai.databinding.client.api.PropertyChangeHandler;

/**
 *
 * @author juan.calderon
 */
public class DeptosEditorUi extends Composite {
    
    private static DeptosEditorUiUiBinder uiBinder = GWT.create(DeptosEditorUiUiBinder.class);
    
    interface DeptosEditorUiUiBinder extends UiBinder<Widget, DeptosEditorUi> {
    }
    
    @UiField HTMLPanel panelEditorGlass;

    @UiField FlexTable flexEditorGeneral;
    public @UiField ScrollPanel scrollPanel;

    @UiField Button saveBtn;
    @UiField Button cancelBtn;
    
    @UiField com.google.gwt.user.client.ui.Label txtStatus;
    @UiField TabListItem tabEditorMiembros;
    
    private final Label nombreLbl;
    private final TextBox nombreTxtBox;
    private final CellList<EmpleadoBase> cellListEmpleados;

    // Model & DataBinder
    private Departamento selected;
    private DataBinder<Departamento> binder;
    
    private EmpleadosBaseProvider empleadosBaseProvider;
    
    public DeptosEditorUi() {
        initWidget(uiBinder.createAndBindUi(this));
        
        empleadosBaseProvider = new EmpleadosBaseProvider();
        
        FlexTable.FlexCellFormatter cellFormatterGeneral = flexEditorGeneral.getFlexCellFormatter();

        flexEditorGeneral.setCellSpacing(0);
        flexEditorGeneral.setCellPadding(0);
//        flexEditorGeneral.getColumnFormatter().setWidth(0, "50px;");

        nombreLbl = new Label();
        nombreLbl.addStyleName("code-label");
        nombreLbl.getElement().getStyle().setFontSize(100.0, Style.Unit.PCT);
        nombreLbl.getElement().getStyle().setDisplay(Style.Display.INLINE_BLOCK);
        nombreTxtBox = new TextBox();
        nombreTxtBox.setWidth("600px");
        
        String htmlColSpc = "<span style='margin-right: 10px;'></span>";
        String htmlRowSpc = "<span style='margin-bottom: 10px; display: block;'></span>";
        
        flexEditorGeneral.setWidget(0, 0, new HTML("<strong>Código</strong>"));
        flexEditorGeneral.setWidget(1, 0, nombreLbl);
        flexEditorGeneral.setWidget(2, 0, new HTML(htmlRowSpc));
        
        flexEditorGeneral.setWidget(3, 0, new HTML("<strong>Nombre</strong>"));
        flexEditorGeneral.setWidget(4, 0, nombreTxtBox);

        CellList.Resources cellListResources = GWT.create(ICellListResources.class);
        cellListEmpleados = new CellList<>(new EmpleadoListCell(null), cellListResources, empleadosBaseProvider.getDataProvider());
//        cellList.setKeyboardSelectionPolicy(HasKeyboardSelectionPolicy.KeyboardSelectionPolicy.ENABLED);
//        cellList.setSelectionModel(selectionModel);
//        selectionModel.addSelectionChangeHandler(new EmpleadosUI.SelectionHandler());
        cellListEmpleados.setPageSize(260);  // máximo son 400 empleados. Al mostrarlos todos, no se requiere Pager.
        scrollPanel.add(cellListEmpleados);
       

//                SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
//        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
//        pager.setDisplay(dataGrid);
        /* Inyectarle style absolute al Abuelo para que funcione el scroll del cellList */
        Element divAbue = cellListEmpleados.getElement().getParentElement().getParentElement();
        divAbue.getStyle().setPosition(Style.Position.ABSOLUTE);
        divAbue.getStyle().setTop(0, Style.Unit.PX);
        divAbue.getStyle().setLeft(0, Style.Unit.PX);
        divAbue.getStyle().setBottom(0, Style.Unit.PX);
        divAbue.getStyle().setRight(0, Style.Unit.PX);

        // Add the CellList to the adapter in the database.
        empleadosBaseProvider.addDataDisplay(cellListEmpleados);
       
        saveBtn.addClickHandler(new SaveClickHandler());
        cancelBtn.addClickHandler(new CancelClickHandler());

        DeptosProvider.get().addMethodExecutedListener(new ProviderMethodExecutedListener());
        empleadosBaseProvider.addMethodExecutedListener(new EmpleadosProviderMethodExecutedListener());

        /* Binding */
        try {
            binder = DataBinder.forType(Departamento.class);
            selected = binder
                    .bind(nombreLbl, "code")
                    .bind(nombreTxtBox, "name")
                    .getModel();

            binder.addPropertyChangeHandler(new BinderPropertyChange());

        } catch (Exception e) {
            Window.alert("DeptosEditorUI DataBinder.bind:> " + e.getMessage());
        }
        
    }
   
    private class BinderPropertyChange implements PropertyChangeHandler<Object> {
        @Override
        public void onPropertyChange(PropertyChangeEvent<Object> event) {
            DeptosProvider.get().dataProvider.refresh();

            // becomes Dirty
            selected.becomesDirty();
            updateWidgets();
        }
    }
    
    private class SaveClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {

            if (selected == null) {
                Window.alert("DeptosEditorUI.SaveClickHandler selected es NULL.");
                return;
            }
            
            if (isValid()) {
                boolean isNuevo = selected == null || selected.getId() == null || selected.getId() <= 0;
                if (isNuevo) {
                    // add
                    DeptosProvider.get().add(selected);
                } else {
                    // update
                    DeptosProvider.get().update(selected);
                }
            }

        }
    }

    private boolean isValid() {
        boolean result = true;
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Departamento>> violations = validator.validate(selected);            
        if(!violations.isEmpty()){
            result = false;
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<Departamento> violation : violations) {
//                    builder.append(violation.getMessage());
//                    builder.append(" : <i>(");
//                    builder.append(violation.getPropertyPath().toString());
//                    builder.append(" = ");
//                    builder.append("" + violation.getInvalidValue());
//                    builder.append(")</i>");
//                    builder.append("<br/>");
                builder.append("• ").append(violation.getMessage()).append("\n");
                builder.append("  ").append(violation.getPropertyPath().toString()).append(" = ").append(violation.getInvalidValue()).append("\n\n");

            }
            Window.alert(builder.toString());
        }
            
        return result;
    }
    
    private class CancelClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (selected == null) {
                Window.alert("DeptosEditorUI.SaveClickHandler selected es NULL.");
                return;
            }
            boolean isNuevo = selected == null || selected.getId() == null || selected.getId() <= 0;
            if (isNuevo) {
                setSelectedBean(null);
            } else {
                // cancelar update
                DeptosProvider.get().reloadById(selected.getId());
            }
        }
    }
    
    /**
     * Si el panel es activo, esconde el glass. Si el panel no es activo,
     * muestra el glass. El glass es un panel que se antepone o pospone segun
     * sea el caso.
     */
    private void setActive(boolean active) {
        int z_index_val = active ? -200 : 200;
        panelEditorGlass.getElement().getStyle().setZIndex(z_index_val);
    }

    private class ProviderMethodExecutedListener implements DeptosProvider.MethodExecutedListener {

        @Override
        public void onMethodExecuted(MethodEvent methodEvent) {
            if (EMethod.UPDATE.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Growl.growl("Departamento actualizado");
                    
                    // Si actualizÃ³ bien:
                    // toma el Bean
                    // limpia el Bean
                    selected.cleanDirty();
                    // lo pasa al Provider
                    
                } else {
                    Window.alert("Falló actualización de Departamento. " + methodEvent.getReason());
                }
                
                updateWidgets();
                
            } else if (EMethod.FIND_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Growl.growl("Departamento cancelado");
                    Departamento cancelado = (Departamento) methodEvent.getResult();
                    // recarga  desde la DB
                    // viene clean
                    // lo asigna al Bean
                    setSelectedBean(cancelado);
                    
                } else {
                    Window.alert("Falló cancelación de departamento. "  + methodEvent.getReason());
                }
                
                updateWidgets();
            }

        }
    }

    private class EmpleadosProviderMethodExecutedListener implements EmpleadosBaseProvider.MethodExecutedListener {

        @Override
        public void onMethodExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_EMPLEADO_BASE_BY_ID_DEPTO.equals(methodEvent.getMethod())) {
                
                tabEditorMiembros.setText("Miembros (" + empleadosBaseProvider.getDataProvider().getList().size() + ")");
            }
        }
        
    }
    
    public void setSelectedBean(Departamento selected) {
        this.selected = selected;
        
        this.updateWidgets();
        
        this.binder.setModel(this.selected != null ? this.selected : new Departamento());//, InitialState.FROM_MODEL, true);
        
        if (this.selected != null) {
            this.nombreTxtBox.setFocus(true);
        }

        int deptoId = selected != null && selected.getId() != null ? selected.getId() : 0;
            empleadosBaseProvider.findAllBaseByDepto(deptoId);
    }
    
    private void updateWidgets() {
        boolean selNotNull = selected != null;
        
        this.setActive(selNotNull);

        if (selected != null && (selected.getId() == null || selected.getId() <= 0)) {
            // cuando es nuevo, hacerlo dirty
            selected.becomesDirty();
        }
        
        this.saveBtn.setEnabled(selNotNull && this.selected.isDirty());
        this.cancelBtn.setEnabled(selNotNull && this.selected.isDirty());
        
        this.txtStatus.setVisible(this.saveBtn.isEnabled());
        
        if (selNotNull) {
            String strStatus = selected.getId() != null && selected.getId() > 0 ? "Actualización" : "Inserción";
            this.txtStatus.setText(strStatus);
        }

    }
        
}
