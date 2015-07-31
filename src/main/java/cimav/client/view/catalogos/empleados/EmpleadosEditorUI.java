/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados;

import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.EBanco;
import cimav.client.data.domain.EClinica;
import cimav.client.data.domain.EEdoCivil;
import cimav.client.data.domain.ESede;
import cimav.client.data.domain.ESexo;
import cimav.client.data.domain.EStatusEmpleado;
import cimav.client.data.domain.Empleado;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.FechaDateBox;
import cimav.client.view.catalogos.departamentos.DeptoChosen;
import cimav.client.view.catalogos.empleados.jefe.JefeChosen;
import cimav.client.view.catalogos.empleados.sni.TipoSNIChosen;
import cimav.client.view.catalogos.empleados.tipoantiguedad.TipoAntiguedadChosen;
import cimav.client.view.catalogos.empleados.tipocontrato.TipoContratoChosen;
import cimav.client.view.catalogos.empleados.tipoempleado.TipoEmpleadoChosen;
import cimav.client.view.catalogos.grupo.GrupoChosen;
import cimav.client.view.catalogos.tabulador.TabuladorChosen;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.jboss.errai.databinding.client.api.DataBinder;
import org.jboss.errai.databinding.client.api.PropertyChangeEvent;
import org.jboss.errai.databinding.client.api.PropertyChangeHandler;

/**
 *
 * @author juan.calderon
 */
public class EmpleadosEditorUI extends Composite {

    private static EmpleadosEditorUIUiBinder uiBinder = GWT.create(EmpleadosEditorUIUiBinder.class);

    interface EmpleadosEditorUIUiBinder extends UiBinder<Widget, EmpleadosEditorUI> {
    }

    @UiField
    HTMLPanel panelEditorGlass;

    @UiField
    FlexTable flexEditorGeneral;
    @UiField
    FlexTable flexEditorLaboral;
    @UiField
    FlexTable flexEditorPersonal;

    @UiField
    Button saveBtn;
    @UiField
    Button cancelBtn;
    
    @UiField com.google.gwt.user.client.ui.Label txtStatus;
    
    // general
    private final TextBox nombreTxtBox;
    private final TextBox paternoTxtBox;
    private final TextBox maternoTxtBox;
    private final TextBox rfcTxtBox;
    private final TextBox curpTxtBox;
    private final TextBox imssTxtBox;
    private final ValueListBox<EClinica> imssClinicaChosen;
    private final ValueListBox<EBanco> bancoChosen;
    private final TextBox cuentaBancoTxtBox;
    private final TextBox cuentaCimavTxtBox;
    // laboral
    private final ValueListBox<ESede> sedeChosen;
    private final DeptoChosen deptoChosen;
    private final FechaDateBox fechaIngresoDatePicker = new FechaDateBox();
    private final FechaDateBox fechaContratoFinDatePicker = new FechaDateBox();
    private final FechaDateBox fechaContratoInicioDatePicker = new FechaDateBox();
    private final FechaDateBox fechaBajaDatePicker = new FechaDateBox();
    private final FechaDateBox fechaAntiguedadDatePicker = new FechaDateBox();
    private final FechaDateBox fechaSNIDatePicker = new FechaDateBox();
    private final ValueListBox<EStatusEmpleado> statusEmpladoChose;
    private final JefeChosen jefeChosen;
    private final GrupoChosen grupoChosen;
    private final TabuladorChosen tabuladorChosen;
    private final TipoEmpleadoChosen tipoEmpleadoChosen;
    private final TipoContratoChosen tipoContratoChosen;
    private final TipoAntiguedadChosen tipoAntiguedadChosen;
    private final TipoSNIChosen tipoSniChosen;
    private final TextBox numSNITxtBox;
    // personal
    private final ValueListBox<ESexo> sexoChose;
    private final ValueListBox<EEdoCivil> edoCivilChose;
    private final FechaDateBox fechaNacimientoDatePicker = new FechaDateBox();
    private final TextBox direccionCalle;
    private final TextBox direccionColonia;
    private final TextBox direccionCP;
    private final TextBox telefono;
    private final TextBox emailPersonal;
    
    // Model & DataBinder
    private Empleado empleadoSelected;
    private DataBinder<Empleado> empleadoBinder;
    
    private EmpleadoREST empleadoREST;

    public EmpleadosEditorUI() {

        initWidget(uiBinder.createAndBindUi(this));

        FlexTable.FlexCellFormatter cellFormatterGeneral = flexEditorGeneral.getFlexCellFormatter();

        flexEditorGeneral.setCellSpacing(0);
        flexEditorGeneral.setCellPadding(0);
//        flexEditorGeneral.getColumnFormatter().setWidth(0, "50px;");

        nombreTxtBox = new TextBox();
        nombreTxtBox.setWidth("244px");
        paternoTxtBox = new TextBox();
        paternoTxtBox.setWidth("244px");
        maternoTxtBox = new TextBox();
        maternoTxtBox.setWidth("244px");

        rfcTxtBox = new TextBox();
        rfcTxtBox.setWidth("244px");
        curpTxtBox = new TextBox();
        curpTxtBox.setWidth("244px");

        imssTxtBox = new TextBox();
        imssTxtBox.setWidth("244px");

        imssClinicaChosen = new ValueListBox<>(new Renderer<EClinica>() {
            @Override
            public String render(EClinica object) {
                if (object == null) {
                    return "Nada";
                }
                return object.getName();
            }

            @Override
            public void render(EClinica object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<EClinica> clinicas = Arrays.asList(EClinica.values());
        imssClinicaChosen.setValue(EClinica.CLINICA_044); //default
        imssClinicaChosen.setAcceptableValues(clinicas);
        imssClinicaChosen.setWidth("244px");

        bancoChosen = new ValueListBox<>(new Renderer<EBanco>() {
            @Override
            public String render(EBanco object) {
                if (object == null) {
                    return "None";
                }
                return object.getNombre();
            }

            @Override
            public void render(EBanco object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<EBanco> bancos = Arrays.asList(EBanco.values());
        bancoChosen.setValue(EBanco.BANORTE); //default
        bancoChosen.setAcceptableValues(bancos);
        bancoChosen.setWidth("244px");
        cuentaBancoTxtBox = new TextBox();
        cuentaBancoTxtBox.setWidth("244px");

        cuentaCimavTxtBox = new TextBox();
        cuentaCimavTxtBox.setWidth("244px");

        deptoChosen = new DeptoChosen();
        deptoChosen.setWidth("244px");

        String htmlColSpc = "<span style='margin-right: 10px;'></span>";
        String htmlRowSpc = "<span style='margin-bottom: 10px; display: block;'></span>";

        // Add some standard form options
        // r, c, obj
        flexEditorGeneral.setWidget(0, 0, new HTML("<strong>Nombre(s)</strong>"));
        flexEditorGeneral.setWidget(0, 1, new HTML(htmlColSpc));
        flexEditorGeneral.setWidget(1, 0, nombreTxtBox);
        flexEditorGeneral.setHTML(0, 2, "A. Paterno");
        flexEditorGeneral.setWidget(0, 3, new HTML(htmlColSpc));
        flexEditorGeneral.setWidget(1, 2, paternoTxtBox);
        flexEditorGeneral.setHTML(0, 4, "A. Materno");
        flexEditorGeneral.setWidget(1, 4, maternoTxtBox);

        flexEditorGeneral.setWidget(2, 0, new HTML(htmlRowSpc));
        flexEditorGeneral.setHTML(3, 0, "RFC");
        flexEditorGeneral.setWidget(4, 0, rfcTxtBox);
        flexEditorGeneral.setHTML(3, 2, "CURP");
        flexEditorGeneral.setWidget(4, 2, curpTxtBox);

        flexEditorGeneral.setWidget(5, 0, new HTML(htmlRowSpc));
        flexEditorGeneral.setHTML(6, 0, "IMSS");
        flexEditorGeneral.setWidget(7, 0, imssTxtBox);
        flexEditorGeneral.setHTML(6, 2, "Clinica");
        flexEditorGeneral.setWidget(7, 2, imssClinicaChosen);

        flexEditorGeneral.setWidget(11, 0, new HTML(htmlRowSpc));
        flexEditorGeneral.setHTML(12, 0, "Banco");
        flexEditorGeneral.setWidget(13, 0, bancoChosen);
        flexEditorGeneral.setHTML(12, 2, "Cuenta");
        flexEditorGeneral.setWidget(13, 2, cuentaBancoTxtBox);

        flexEditorGeneral.setWidget(14, 0, new HTML(htmlRowSpc));
        flexEditorGeneral.setHTML(15, 0, "Cuenta CIMAV");
        flexEditorGeneral.setWidget(16, 0, cuentaCimavTxtBox);

        FlexTable.FlexCellFormatter cellFormatterLaboral = flexEditorLaboral.getFlexCellFormatter();

        flexEditorLaboral.setCellSpacing(0);
        flexEditorLaboral.setCellPadding(0);

        String width = "262px";
        sedeChosen = new ValueListBox<>(new Renderer<ESede>() {
            @Override
            public String render(ESede object) {
                if (object == null) {
                    return "None";
                }
                return object.getNombre();
            }

            @Override
            public void render(ESede object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<ESede> sedes = Arrays.asList(ESede.values());
        sedeChosen.setValue(ESede.CHIHUAHUA); //default
        sedeChosen.setAcceptableValues(sedes);
        sedeChosen.setWidth(width);
        //fechaIngresoDatePicker = new FechaPicker(width);
//        fechaContratoFinDatePicker = new FechaPicker(width);
//        fechaContratoInicioDatePicker = new FechaPicker(width);
//        fechaBajaDatePicker = new FechaPicker(width);
//        fechaAntiguedadDatePicker = new FechaPicker(width);
//        fechaSNIDatePicker = new FechaPicker(width);
        List<EStatusEmpleado> status = Arrays.asList(EStatusEmpleado.values());
        statusEmpladoChose = new ValueListBox<>(new Renderer<EStatusEmpleado>() {
            @Override
            public String render(EStatusEmpleado object) {
                if (object == null) {
                    return "Nada";
                }
                return object.getNombre();
            }

            @Override
            public void render(EStatusEmpleado object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        statusEmpladoChose.setValue(EStatusEmpleado.ACTIVO); //default
        statusEmpladoChose.setAcceptableValues(status);
        statusEmpladoChose.setWidth(width);
        jefeChosen = new JefeChosen();
        jefeChosen.setWidth("400px");
        grupoChosen = new GrupoChosen();
        grupoChosen.setWidth(width);
        tabuladorChosen = new TabuladorChosen();
        tabuladorChosen.setWidth(width);
        tipoEmpleadoChosen = new TipoEmpleadoChosen();
        tipoEmpleadoChosen.setWidth(width);
        tipoContratoChosen = new TipoContratoChosen();
        tipoContratoChosen.setWidth(width);
        tipoAntiguedadChosen = new TipoAntiguedadChosen();
        tipoAntiguedadChosen.setWidth(width);
        tipoSniChosen = new TipoSNIChosen();
        tipoSniChosen.setWidth(width);
        numSNITxtBox = new TextBox();
        numSNITxtBox.setWidth(width);

        int row = 1;
        flexEditorLaboral.setHTML(row, 0, "Sede");
        flexEditorLaboral.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 2, "Departamento");
        flexEditorLaboral.setWidget(row, 3, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 4, "Status");
        row++;
        flexEditorLaboral.setWidget(row, 0, sedeChosen);
        flexEditorLaboral.setWidget(row, 2, deptoChosen);
        flexEditorLaboral.setWidget(row, 4, statusEmpladoChose);
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Jefe");
        flexEditorLaboral.setWidget(row, 3, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 4, "Proyecto");
        row++;
        flexEditorLaboral.setWidget(row, 0, jefeChosen);
        cellFormatterLaboral.setColSpan(row, 0, 3);
        flexEditorLaboral.setWidget(row, 2, new Label("Not Yet..."));
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Grupo");
        flexEditorLaboral.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 2, "Nivel tabulador");
        row++;
        flexEditorLaboral.setWidget(row, 0, grupoChosen);
        flexEditorLaboral.setWidget(row, 2, tabuladorChosen);
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Tipo empleado");
        flexEditorLaboral.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 2, "Tipo contrato");
        row++;
        flexEditorLaboral.setWidget(row, 0, tipoEmpleadoChosen);
        flexEditorLaboral.setWidget(row, 2, tipoContratoChosen);
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Fecha ingreso HERE");
        flexEditorLaboral.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 2, "Fecha inicio contrato");
        flexEditorLaboral.setWidget(row, 3, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 4, "Fecha fin contrato");
        row++;
        flexEditorLaboral.setWidget(row, 0, fechaIngresoDatePicker);
        flexEditorLaboral.setWidget(row, 2, fechaContratoInicioDatePicker);
        flexEditorLaboral.setWidget(row, 4, fechaContratoFinDatePicker);
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Tipo antiguedad");
        flexEditorLaboral.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 2, "Fecha antiguedad");
        row++;
        flexEditorLaboral.setWidget(row, 0, tipoAntiguedadChosen);
        flexEditorLaboral.setWidget(row, 2, fechaAntiguedadDatePicker);
        row++;
        flexEditorLaboral.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Nivel SNI");
        flexEditorLaboral.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 2, "Núm SNI");
        flexEditorLaboral.setWidget(row, 3, new HTML(htmlColSpc));
        flexEditorLaboral.setHTML(row, 4, "Fecha SNI");
        row++;
        flexEditorLaboral.setWidget(row, 0, tipoSniChosen);
        flexEditorLaboral.setWidget(row, 2, numSNITxtBox);
        flexEditorLaboral.setWidget(row, 4, fechaSNIDatePicker);
        row++;
        cellFormatterLaboral.setColSpan(row, 0, 5);
        flexEditorLaboral.setWidget(row, 0, new HTML("<span style='padding-bottom: 10px; display: block; border-bottom: 1px solid lightgray;;'></span>"));
        row++;
        flexEditorLaboral.setHTML(row, 0, "Número estímulos");
        row++;
        flexEditorLaboral.setWidget(row, 0, new Label("Not Yet..."));

        
        FlexTable.FlexCellFormatter cellFormatterPersonal = flexEditorPersonal.getFlexCellFormatter();

        flexEditorPersonal.setCellSpacing(0);
        flexEditorPersonal.setCellPadding(0);
        
        sexoChose = new ValueListBox<>(new Renderer<ESexo>() {
            @Override
            public String render(ESexo object) {
                if (object == null) {
                    return "Nada";
                }
                return object.getNombre();
            }

            @Override
            public void render(ESexo object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<ESexo> sexos = Arrays.asList(ESexo.values());
        sexoChose.setAcceptableValues(sexos);
        sexoChose.setWidth("244px");
        
        edoCivilChose = new ValueListBox<>(new Renderer<EEdoCivil>() {
            @Override
            public String render(EEdoCivil object) {
                if (object == null) {
                    return "Nada";
                }
                return object.getNombre();
            }

            @Override
            public void render(EEdoCivil object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<EEdoCivil> edoCiviles = Arrays.asList(EEdoCivil.values());
        edoCivilChose.setAcceptableValues(edoCiviles);
        edoCivilChose.setWidth("244px");
        
        direccionCalle = new TextBox();
        direccionCalle.setWidth(width);
        direccionColonia = new TextBox();
        direccionColonia.setWidth(width);
        direccionCP = new TextBox();
        direccionCP.setWidth(width);
        telefono = new TextBox();
        telefono.setWidth(width);
        emailPersonal = new TextBox();
        emailPersonal.setWidth(width);

        row = 1;
        flexEditorPersonal.setHTML(row, 0, "Sexo");
        flexEditorPersonal.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorPersonal.setHTML(row, 2, "Edo civil");
        flexEditorPersonal.setWidget(row, 3, new HTML(htmlColSpc));
        flexEditorPersonal.setHTML(row, 4, "Fecha nacimiento");
        row++;
        flexEditorPersonal.setWidget(row, 0, sexoChose);
        flexEditorPersonal.setWidget(row, 2, edoCivilChose);
        flexEditorPersonal.setWidget(row, 4, fechaNacimientoDatePicker);
        row++;
        flexEditorPersonal.setWidget(row, 0, new HTML(htmlRowSpc));
        row++;
        flexEditorPersonal.setHTML(row, 0, "Calle");
        flexEditorPersonal.setWidget(row, 1, new HTML(htmlColSpc));
        flexEditorPersonal.setHTML(row, 2, "Colonia");
        flexEditorPersonal.setWidget(row, 3, new HTML(htmlColSpc));
        flexEditorPersonal.setHTML(row, 4, "CP");
        row++;
        flexEditorPersonal.setWidget(row, 0, direccionCalle);
        flexEditorPersonal.setWidget(row, 2, direccionColonia);
        flexEditorPersonal.setWidget(row, 4, direccionCP);
        row++;
        flexEditorPersonal.setHTML(row, 0, "Teléfono");
        row++;
        flexEditorPersonal.setWidget(row, 0, telefono);
        row++;
        flexEditorPersonal.setHTML(row, 0, "Email personal");
        row++;
        flexEditorPersonal.setWidget(row, 0, emailPersonal);
        
//        editor.setHTML(2, 0, "DescripciÃ³n");
//        cellFormatter.setColSpan(2, 0, 2);
//        cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
//        editor.setWidget(3, 0, txt3);
//        cellFormatter.setColSpan(3, 0, 2);
        
        saveBtn.addClickHandler(new SaveClickHandler());
        cancelBtn.addClickHandler(new CancelClickHandler());

//        EmpleadosProvider.get().addMethodExecutedListener(new ProviderMethodExecutedListener());

        /* Binding */
        try {
            empleadoBinder = DataBinder.forType(Empleado.class);
            empleadoSelected = empleadoBinder
                    .bind(nombreTxtBox, "nombre")
                    .bind(paternoTxtBox, "apellidoPaterno")
                    .bind(maternoTxtBox, "apellidoMaterno")
                    .bind(rfcTxtBox, "rfc")
                    .bind(curpTxtBox, "curp")
                    .bind(fechaSNIDatePicker, "fechaSni")
                    .bind(imssTxtBox, "imss")
                    .bind(imssClinicaChosen, "clinica")
                    .bind(bancoChosen, "banco")
                    .bind(cuentaBancoTxtBox, "cuentaBanco")
                    .bind(cuentaCimavTxtBox, "cuentaCimav")
                    //                // laboral
                    .bind(sedeChosen, "sede")
                    .bind(deptoChosen.getChosen(), "departamento")
                    .bind(fechaIngresoDatePicker, "fechaIngreso")
                    .bind(fechaSNIDatePicker, "fechaSni")
                    .bind(fechaContratoFinDatePicker, "fechaFinContrato")
                    .bind(fechaContratoInicioDatePicker, "fechaInicioContrato")
                    .bind(fechaBajaDatePicker, "fechaBaja")
                    .bind(fechaAntiguedadDatePicker, "fechaAntiguedad")
                    .bind(statusEmpladoChose, "status")
                    .bind(jefeChosen.getChosen(), "jefe")
                    .bind(grupoChosen.getChosen(), "grupo")
                    .bind(tabuladorChosen.getChosen(), "nivel")
                    .bind(tipoEmpleadoChosen.getChosen(), "tipoEmpleado")
                    .bind(tipoContratoChosen.getChosen(), "tipoContrato")
                    .bind(tipoAntiguedadChosen.getChosen(), "tipoAntiguedad")
                    .bind(tipoSniChosen.getChosen(), "tipoSNI")
                    .bind(numSNITxtBox, "numSni")
                    // personal
                    .bind(sexoChose, "sexo")
                    .bind(edoCivilChose, "edoCivil")
                    .bind(fechaNacimientoDatePicker, "fechaNacimiento")
                    .bind(direccionCalle, "direccionCalle")
                    .bind(direccionColonia, "direccionColonia")
                    .bind(direccionCP, "direccionCP")
                    .bind(telefono, "telefono")
                    .bind(emailPersonal, "emailPersonal")
                    .getModel();

            empleadoBinder.addPropertyChangeHandler(new BinderPropertyChange());

        } catch (Exception e) {
            Window.alert("EmpleadosEditorUI DataBinder.bind:> " + e.getMessage());
        }
    }

    private class BinderPropertyChange implements PropertyChangeHandler<Object> {
        @Override
        public void onPropertyChange(PropertyChangeEvent<Object> event) {
// >>>            EmpleadosProvider.get().dataProvider.refresh();

            // becomes Dirty
            empleadoSelected.becomesDirty();
            updateWidgets();
        }
    }
    
    public EmpleadoREST getREST() {
        if (empleadoREST == null) {
            empleadoREST = new EmpleadoREST();

            empleadoREST.addRESTExecutedListener(new RestMethodExecutedListener());
        }
        return empleadoREST;
    }
    
    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.CREATE.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Empleado created = (Empleado) methodEvent.getResult();
// >>>                    dataProvider.getList().add(created);
                }
// >>>                onMethodExecuted(methodEvent); 
            } else if (EMethod.UPDATE.equals(methodEvent.getMethod())) {
//                // en methodEvent.getResult() va el Empleado recargado
//                // pero no requiere pasarlo al binding dado que es el mismo cambiado
//                // actualizar el dataProvider
//                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) { 
//                    Empleado empleadoUpdated = (Empleado) methodEvent.getResult();
//                    int idx = dataProvider.getList().indexOf(empleadoUpdated);
//                    dataProvider.getList().set(idx, empleadoUpdated);
//                }
// >>>                onMethodExecuted(methodEvent); 
            } else if (EMethod.FIND_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // re-carga el provider con el empleado reloaded
                    Empleado reloaded = (Empleado) methodEvent.getResult();
// >>>                    int idx = dataProvider.getList().indexOf(reloaded);
// >>>                    dataProvider.getList().set(idx, reloaded);
                    
                    empleadoSelected = reloaded;
        
                    updateWidgets();
        
                    empleadoBinder.setModel(empleadoSelected != null ? empleadoSelected : new Empleado());//, InitialState.FROM_MODEL, true);
        
                    jefeChosen.setUrlPhotoPath();
        
                    if (empleadoSelected != null) {
                        nombreTxtBox.setFocus(true);
                    }
                    
                }

                // en methodEvent.getResult() va el Empleado recargado para pasarlo al binding
// >>>                onMethodExecuted(methodEvent); // <-- usa el mismo methodEvent
            } 
            
        }
    }
    
    private class SaveClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {

            if (empleadoSelected == null) {
                Window.alert("EmpleadosEditorUI.SaveClickHandler empleadoSelected es NULL.");
                return;
            }
            
            if (isValid()) {
                boolean isNuevo = empleadoSelected == null || empleadoSelected.getId() == null || empleadoSelected.getId() <= 0;
                if (isNuevo) {
                    // add
// >>                    EmpleadosProvider.get().add(empleadoSelected);
                } else {
                    // update
// >>                    EmpleadosProvider.get().update(empleadoSelected);
                }
            }

        }
    }

    private boolean isValid() {
        boolean result = true;
        
        Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        Set<ConstraintViolation<Empleado>> violations = validator.validate(empleadoSelected);            
        if(!violations.isEmpty()){
            result = false;
            StringBuilder builder = new StringBuilder();
            for (ConstraintViolation<Empleado> violation : violations) {
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
            if (empleadoSelected == null) {
                Window.alert("EmpleadosEditorUI.SaveClickHandler empleadoSelected es NULL.");
                return;
            }
            boolean isNuevo = empleadoSelected == null || empleadoSelected.getId() == null || empleadoSelected.getId() <= 0;
            if (isNuevo) {
                setSelectedBean(null);
            } else {
                // cancelar update
// >>                EmpleadosProvider.get().reloadById(empleadoSelected.getId());
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

    public void setSelectedBean(Integer idEmpleadoBaseSelected) {
        
        idEmpleadoBaseSelected = idEmpleadoBaseSelected == null ? 0 : idEmpleadoBaseSelected;
        
        getREST().findEmpleadoById(idEmpleadoBaseSelected);
        
    }
    
    private void updateWidgets() {
        boolean empSelNotNull = empleadoSelected != null;
        
        this.setActive(empSelNotNull);

        if (empleadoSelected != null && (empleadoSelected.getId() == null || empleadoSelected.getId() <= 0)) {
            // cuando es nuevo, hacerlo dirty
            empleadoSelected.becomesDirty();
        }
        
        this.saveBtn.setEnabled(empSelNotNull && this.empleadoSelected.isDirty());
        this.cancelBtn.setEnabled(empSelNotNull && this.empleadoSelected.isDirty());
        
        this.txtStatus.setVisible(this.saveBtn.isEnabled());
        
        if (empSelNotNull) {
            String strStatus = empleadoSelected.getId() != null && empleadoSelected.getId() > 0 ? "Actualización" : "Inserción";
            this.txtStatus.setText(strStatus);
        }

    }
    
}
