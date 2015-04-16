/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.grupo;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Grupo;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.GrupoREST;
import com.google.gwt.core.client.GWT;
import com.google.gwt.text.shared.Renderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import java.io.IOException;
import java.util.List;
import org.gwtbootstrap3.client.ui.ValueListBox;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author juan.calderon
 */
public class GrupoChosen extends Composite {

    private static GrupoChosenUiBinder uiBinder = GWT.create(GrupoChosenUiBinder.class);

    interface GrupoChosenUiBinder extends UiBinder<Widget, GrupoChosen> {
    }

    private final GrupoREST grupoREST;
    
    @UiField
    HTMLPanel htmlPanel;
    private ValueListBox<Grupo> chosen;

    public GrupoChosen() {
        initWidget(uiBinder.createAndBindUi(this));

        chosen = new ValueListBox<>(new Renderer<Grupo>() {
            @Override
            public String render(Grupo object) {
                if (object == null) {
                    return "None";
                }
                return object.getName();
            }

            @Override
            public void render(Grupo object, Appendable appendable) throws IOException {
                if (object != null) {
                    String s = render(object);
                    appendable.append(s);
                }
            }
        });

        htmlPanel.add(chosen);
        
        grupoREST = new GrupoREST();
        grupoREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
            @Override
            public void onRESTExecuted(MethodEvent restEvent) {
                if (restEvent.getMethod().equals(EMethod.FIND_ALL_BASE)) {
                    if (restEvent.getTypeResult().equals(ETypeResult.SUCCESS)) {
                        
                        List<Grupo> grupos = (List<Grupo>) restEvent.getResult();
                        // Agregar el Null como valor válido
                        //grupos.add(null);
                        chosen.setAcceptableValues(grupos);
                        
                    } else {
                        Growl.growl("Falló la carga de Grupos");
                    }
                }
            }
        });
        grupoREST.findAllBase();
        
    }
    
    public void setSelected(Grupo value) {
        chosen.setValue(value, true);
    }
    
    public Grupo getSelected() {
        return chosen.getValue();
    }
}
