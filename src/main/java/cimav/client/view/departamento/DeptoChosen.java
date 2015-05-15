/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.departamento;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Departamento;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.DepartamentoREST;
import com.arcbees.chosen.client.gwt.ChosenValueListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.io.IOException;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class DeptoChosen extends Composite {
    
    private static DeptoChosenUiBinder uiBinder = GWT.create(DeptoChosenUiBinder.class);
    
    interface DeptoChosenUiBinder extends UiBinder<Widget, DeptoChosen> {
    }
    
    private final DepartamentoREST deptoREST;
    
    @UiField HTMLPanel htmlPanel;
    private ChosenValueListBox<Departamento> chosen;
    
    public DeptoChosen() {
        initWidget(uiBinder.createAndBindUi(this));
        
        chosen = new ChosenValueListBox<>(new Renderer<Departamento>() {
            @Override
            public String render(Departamento object) {
                if (object == null) {
                    return "None";
                }
                return object.getName();
            }
            @Override
            public void render(Departamento object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
                
        htmlPanel.add(chosen);
        
        deptoREST = new DepartamentoREST();
        
        deptoREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
            @Override
            public void onRESTExecuted(MethodEvent restEvent) {
                if (restEvent.getMethod().equals(EMethod.FIND_ALL_BASE)) {
                    if (restEvent.getTypeResult().equals(ETypeResult.SUCCESS)) {
                        
                        List<Departamento> deptos = (List<Departamento>) restEvent.getResult();
                        // Agregar el Null como valor válido
                        deptos.add(null);
                        chosen.setAcceptableValues(deptos);
                        
                    } else {
                        Window.alert("Falló carga de Departamentos");
                    }
                }
            }
        });
        deptoREST.findAllBase();

    }
    
    public void setSelected(Departamento value) {
        chosen.setValue(value, true);
    }
    
    public Departamento getSelected() {
        return chosen.getValue();
    }

    public ChosenValueListBox<Departamento> getChosen() {
        return this.chosen;
    }

}
