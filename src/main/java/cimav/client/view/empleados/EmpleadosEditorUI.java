/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.empleados;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.EBanco;
import cimav.client.data.domain.EClinica;
import cimav.client.data.domain.ESede;
import cimav.client.data.domain.EStatusEmpleado;
import cimav.client.data.domain.Empleado;
import cimav.client.view.empleados.credito.CreditoInputGroup;
import cimav.client.view.departamento.DeptoChosen;
import cimav.client.view.empleados.jefe.JefeChosen;
import cimav.client.view.empleados.sni.TipoSNIChosen;
import cimav.client.view.empleados.tipoantiguedad.TipoAntiguedadChosen;
import cimav.client.view.empleados.tipocontrato.TipoContratoChosen;
import cimav.client.view.empleados.tipoempleado.TipoEmpleadoChosen;
import cimav.client.view.grupo.GrupoChosen;
import cimav.client.view.provider.EmpleadosProvider;
import cimav.client.view.tabulador.TabuladorChosen;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.user.datepicker.client.DateBox;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.TextBox;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
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
    Button saveBtn;
    @UiField
    Button cancelBtn;

    // general
    private final TextBox nombreTxtBox;
    private final TextBox paternoTxtBox;
    private final TextBox maternoTxtBox;
    private final TextBox rfcTxtBox;
    private final TextBox curpTxtBox;
    private final TextBox imssTxtBox;
    private final ValueListBox<EClinica> imssClinicaChosen;
    private final CreditoInputGroup creditoInputGroup;
    private final ValueListBox<EBanco> bancoChosen;
    private final TextBox cuentaBancoTxtBox;
    private final TextBox cuentaCimavTxtBox;
    // laboral
    private final ValueListBox<ESede> sedeChosen;
    private final DeptoChosen deptoChosen;
    private final DateBox fechaIngresoDatePicker = new DateBox();
    private final DateBox fechaContratoFinDatePicker = new DateBox();
    private final DateBox fechaContratoInicioDatePicker = new DateBox();
    private final DateBox fechaBajaDatePicker = new DateBox();
    private final DateBox fechaAntiguedadDatePicker = new DateBox();
    private final DateBox fechaSNIDatePicker = new DateBox();
    private final ValueListBox<EStatusEmpleado> statusEmpladoChose;
    private final JefeChosen jefeChosen;
    private final GrupoChosen grupoChosen;
    private final TabuladorChosen tabuladorChosen;
    private final TipoEmpleadoChosen tipoEmpleadoChosen;
    private final TipoContratoChosen tipoContratoChosen;
    private final TipoAntiguedadChosen tipoAntiguedadChosen;
    private final TipoSNIChosen tipoSniChosen;
    private final TextBox numSNITxtBox;

    // Model & DataBinder
    private Empleado empleadoSelected;
    private DataBinder<Empleado> empleadoBinder;

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

        creditoInputGroup = new CreditoInputGroup();
        creditoInputGroup.setWidth("244px");
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

        flexEditorGeneral.setWidget(8, 0, new HTML(htmlRowSpc));
        flexEditorGeneral.setHTML(9, 0, "Crédito");
        flexEditorGeneral.setWidget(10, 0, creditoInputGroup);

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

//        editor.setHTML(2, 0, "Descripción");
//        cellFormatter.setColSpan(2, 0, 2);
//        cellFormatter.setHorizontalAlignment(2, 0, HasHorizontalAlignment.ALIGN_LEFT);
//        editor.setWidget(3, 0, txt3);
//        cellFormatter.setColSpan(3, 0, 2);
        saveBtn.addClickHandler(new SaveClickHandler());
        cancelBtn.addClickHandler(new CancelClickHandler());

        EmpleadosProvider.get().addMethodExecutedListener(new ProviderMethodExecutedListener());

//        EmpleadoValueHandle valueChange = new EmpleadoValueHandle();
//        nombreTxtBox.addValueChangeHandler(valueChange);
//        paternoTxtBox.addValueChangeHandler(valueChange);

        DateTimeFormat df = DateTimeFormat.getFormat("dd/MM/yyyy");
        DateBox.Format dtf = new DateBox.DefaultFormat(df);
        fechaIngresoDatePicker.setFormat(dtf);
        fechaSNIDatePicker.setFormat(dtf);
        fechaContratoFinDatePicker.setFormat(dtf);
        fechaContratoInicioDatePicker.setFormat(dtf);
        fechaBajaDatePicker.setFormat(dtf);
        fechaAntiguedadDatePicker.setFormat(dtf);

        // deshabilitado
        //this.setActive(false);
        
        //MyCustomDateConverter mcdc = new MyCustomDateConverter();
        try {
            empleadoBinder = DataBinder.forType(Empleado.class);
            empleadoSelected = empleadoBinder
                    .bind(nombreTxtBox, "nombre")
                    .bind(paternoTxtBox, "apellidoPaterno")
                    .bind(maternoTxtBox, "apellidoMaterno")
                    .bind(rfcTxtBox, "rfc")
                    .bind(curpTxtBox, "curp")
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
                    .getModel();

            empleadoBinder.addPropertyChangeHandler(new BinderPropertyChange());

        } catch (Exception e) {
            GWT.log(e.getMessage());
        }
    }

    private class BinderPropertyChange implements PropertyChangeHandler<Object> {
        @Override
        public void onPropertyChange(PropertyChangeEvent<Object> event) {
            GWT.log("<<<>>> " + event.getPropertyName() + " >> " + event.getOldValue() + " >> " + event.getNewValue());

            EmpleadosProvider.get().dataProvider.refresh();

            // becomes Dirty
            empleadoSelected.becomesDirty();
            updateWidgets();
        }
    }
    
//@DefaultConverter
//public class MyCustomDateConverter implements Converter<Date, String> {
// 
//  private static final String DATE_FORMAT = "DD_MM_YYYY";
// 
//  @Override
//  public Date toModelValue(String widgetValue) {
//      Date result = DateTimeFormat.getFormat(DATE_FORMAT).parse(widgetValue);
//      return result;
//  }
// 
//  @Override
//  public String toWidgetValue(Date modelValue) {
//    return DateTimeFormat.getFormat(DATE_FORMAT).format((Date) modelValue);
//  }
//}    
    private class SaveClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {

            if (empleadoSelected == null) {
                // TODO Alert empleadoBean is Null
                return;
            }
            
            boolean isNuevo = empleadoSelected == null || empleadoSelected.getId() == null || empleadoSelected.getId() <= 0;
            if (isNuevo) {
                EmpleadosProvider.get().add(empleadoSelected);
            } else {
                // update
                EmpleadosProvider.get().update(empleadoSelected);
            }

        }
    }

    private class CancelClickHandler implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            if (empleadoSelected == null) {
                // TODO Alert empleadoBean is Null
                return;
            }
            boolean isNuevo = empleadoSelected == null || empleadoSelected.getId() == null || empleadoSelected.getId() <= 0;
            if (isNuevo) {
            } else {
                // cancelar update
                EmpleadosProvider.get().reloadById(empleadoSelected.getId());
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

    private class ProviderMethodExecutedListener implements EmpleadosProvider.MethodExecutedListener {

        @Override
        public void onMethodExecuted(MethodEvent methodEvent) {
            if (EMethod.UPDATE.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Growl.growl("Registro actualizado");
                    
                    // Si actualizó bien:
                    // toma el Bean
                    //int idx = EmpleadosProvider.get().getDataProvider().getList().indexOf(empleadoSelected);
                    // limpia el Bean
                    empleadoSelected.cleanDirty();
                    // lo pasa al Provider
//                    EmpleadosProvider.get().getDataProvider().getList().set(idx, empleadoSelected);
                    
                } else {
                    Growl.growl("Falló actualización");
                }
                
                updateWidgets();
                
            } else if (EMethod.FIND_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Growl.growl("Registro cancelado");
                    Empleado empCancelado = (Empleado) methodEvent.getResult();
                    // recarga el empleado desde la DB
                    // viene clean
                    // lo asigna al Bean
                    setSelectedBean(empCancelado);
                    
                } else {
                    Growl.growl("Falló cancelación");
                }
                
                updateWidgets();
            }

        }
    }

////            String urlPhoto = "http://cimav.edu.mx/foto/" + empleadoBean.getCuentaCimav();
////            empleadoBean.setUrlPhoto(urlPhoto);
    
    public void setSelectedBean(Empleado empleadoSelected) {
        this.empleadoSelected = empleadoSelected;
        
        this.updateWidgets();
        
        this.empleadoBinder.setModel(this.empleadoSelected != null ? this.empleadoSelected : new Empleado());//, InitialState.FROM_MODEL, true);
    }
    
    private void updateWidgets() {
        this.setActive(empleadoSelected != null);
        
        this.cancelBtn.setEnabled(empleadoSelected != null && this.empleadoSelected.isDirty());
        this.saveBtn.setEnabled(empleadoSelected != null && this.empleadoSelected.isDirty());
    }
    
}
