/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados.pension;

import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.ETipoPension;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.ProgressBar;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.gwtbootstrap3.client.ui.constants.ButtonSize;
import org.gwtbootstrap3.client.ui.constants.IconType;
import org.gwtbootstrap3.client.ui.html.Div;
import org.gwtbootstrap3.client.ui.html.Span;

/**
 *
 * @author juan.calderon
 */
public class PensionUI extends Composite {
    
    private static PensionUIUiBinder uiBinder = GWT.create(PensionUIUiBinder.class);
    
    interface PensionUIUiBinder extends UiBinder<Widget, PensionUI> {
    }
    
    @UiField
    Span divPanel;
    @UiField
    Div divPickList;
    @UiField
    Modal modal;
    
    @UiField
    ProgressBar pb;
    @UiField
    Button btnT;
    
    private final Button btnModal;
    
    private final ValueListBox<ETipoPension> pensionTipo;
    
    public PensionUI() {
        initWidget(uiBinder.createAndBindUi(this));

        btnT.addClickHandler(new ClickHandler() {

            @Override
            public void onClick(ClickEvent event) {
                Double  n = pb.getPercent() + 3.00;
                if (n > 99.0) {
                    n = 0.00;
                }
                pb.setPercent(n);
            }
        });
        
        pensionTipo = new ValueListBox<>(new Renderer<ETipoPension>() {
            @Override
            public String render(ETipoPension object) {
                if (object == null) {
                    return "None";
                }
                return object.getNombre();
            }

            @Override
            public void render(ETipoPension object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<ETipoPension> tiposPension = Arrays.asList(ETipoPension.values());
        pensionTipo.setValue(ETipoPension.SIN); //default
        pensionTipo.setAcceptableValues(tiposPension);
        pensionTipo.getElement().setAttribute("style", "width: inherit;");
        
        modal.getElement().setAttribute("style", "width: 800px; margin: auto; height: 400px");
        
        btnModal = new Button();
        btnModal.setIcon(IconType.GEAR);
        btnModal.setSize(ButtonSize.SMALL);
        btnModal.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                modal.show();
            }
        });

        divPanel.add(pensionTipo);
        divPanel.add(btnModal);
        
        List<Concepto> conceptos = new ArrayList<>();
        Concepto concepto1 = new Concepto();
        concepto1.setCode("HOY");
        concepto1.setName("El dias en que etamos.");
        concepto1.setIdTipoConcepto("X");
        concepto1.setIdTipoMovimiento("Y");
        Concepto concepto2 = new Concepto();
        concepto2.setCode("TOMORROW");
        concepto2.setName("MÃ±ana es finde semana,");
        concepto2.setIdTipoConcepto("A");
        concepto2.setIdTipoMovimiento("B");
        conceptos.add(concepto2);
        Concepto concepto3 = new Concepto();
        concepto3.setCode("YESTERDAY");
        concepto3.setName("Ayer fue jueves");
        concepto3.setIdTipoConcepto("I");
        concepto3.setIdTipoMovimiento("J");
        conceptos.add(concepto3);
        
        //divPickList.add(pickListW);
        
        EmpleadoREST empleadoREST = new EmpleadoREST();
        empleadoREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
            @Override
            public void onRESTExecuted(MethodEvent restEvent) {
                if (EMethod.FIND_PENSION_ALIMENTICA_BY_ID_EMPLEADO.equals(restEvent.getMethod())) {
                    if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                        List<Concepto> conceptos = (List<Concepto>) restEvent.getResult();
                    }
                }
            }
        });
        empleadoREST.findPensionAlimenticia(91);
    }

    public ValueListBox<ETipoPension> get() {
        return pensionTipo;
    }
}
