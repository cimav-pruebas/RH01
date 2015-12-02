/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.conceptos;

import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.ETipoMovimiento;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.ConceptoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import org.gwtbootstrap3.client.ui.ValueListBox;

/**
 *
 * @author juan.calderon
 */
public class ConceptosChosen extends Composite {
    
    private static ConceptosChosenUiBinder uiBinder = GWT.create(ConceptosChosenUiBinder.class);
    
    interface ConceptosChosenUiBinder extends UiBinder<Widget, ConceptosChosen> {
    }

    private final ConceptoREST rest;
    
    @UiField HTMLPanel htmlPanel;
    
    private final ValueListBox<Concepto> chosen;
    
    private List<Concepto> conceptos;
    
    private ETipoConcepto tipoConcepto;
    private ETipoMovimiento tipoMovimiento;
    
    public ConceptosChosen(ETipoConcepto tipoConcepto, ETipoMovimiento tipoMovimiento) {
        
        this.tipoConcepto = tipoConcepto;
        this.tipoMovimiento = tipoMovimiento;
        
        initWidget(uiBinder.createAndBindUi(this));
        
        chosen = new ValueListBox<>(new Renderer<Concepto>() {
            @Override
            public String render(Concepto object) {
                if (object == null) {
                    return "Ninguno";
                }
                return object.getName();
            }
            @Override
            public void render(Concepto object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
                
        htmlPanel.add(chosen);
        
        rest = new ConceptoREST();
        
        rest.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
            @Override
            public void onRESTExecuted(MethodEvent restEvent) {
                if (restEvent.getMethod().equals(EMethod.FIND_ALL)) {
                    if (restEvent.getTypeResult().equals(ETypeResult.SUCCESS)) {
                        
                        conceptos = (List<Concepto>) restEvent.getResult();
                        // Agregar el Null como valor válido
                        // conceptos.add(null);
                        //chosen.setAcceptableValues(conceptos);
                        
                        setConceptosAceptables(); //tipoConcepto, tipoMovimiento);
                        
                    } else {
                        Window.alert("Falló carga de Conceptos");
                    }
                }
            }
        });
        rest.findAll();
        
    }
    
    private void setConceptosAceptables(/*ETipoConcepto tipoConcepto, ETipoMovimiento tipoMvto*/) {
        List<Concepto> acceptableConceptos = new ArrayList<>();
        if (this.conceptos == null) {
            this.conceptos = new ArrayList<>();
        }
        for (Concepto concepto : this.conceptos) {
            if(concepto != null && concepto.getTipoConcepto() != null && concepto.getTipoMovimiento() != null) {
                //if (concepto.getTipoConcepto().equals(this.tipoConcepto) && concepto.getTipoMovimiento().equals(this.tipoMovimiento)) {
                // que sea P o D y que no sea calculo
                if (concepto.getTipoConcepto().equals(this.tipoConcepto) && !ETipoMovimiento.CALCULO.equals(concepto.getTipoMovimiento())) {
                    acceptableConceptos.add(concepto);
                }
            }
        }
        Collections.sort(acceptableConceptos, new Comparator<Concepto>() {
            @Override
            public int compare(Concepto c1, Concepto c2) {
                return c1.getName().compareTo(c2.getName());
            }
        });
        chosen.setAcceptableValues(acceptableConceptos);
    }
    
    public void setSelected(Concepto value) {
        chosen.setValue(value, true);
    }
    
    public Concepto getSelected() {
        return chosen.getValue();
    }

    public ValueListBox<Concepto> getChosen() {
        return this.chosen;
    }

}
