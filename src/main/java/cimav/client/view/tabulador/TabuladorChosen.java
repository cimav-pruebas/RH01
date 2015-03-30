/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.tabulador;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Tabulador;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.TabuladorREST;
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
public class TabuladorChosen extends Composite {
    
    private static TabuladorChosenUiBinder uiBinder = GWT.create(TabuladorChosenUiBinder.class);
    
    interface TabuladorChosenUiBinder extends UiBinder<Widget, TabuladorChosen> {
    }
    
    private final TabuladorREST rest;
    
    @UiField
    HTMLPanel htmlPanel;
    private ValueListBox<Tabulador> chosen;
    
    public TabuladorChosen() {
        initWidget(uiBinder.createAndBindUi(this));
        
        chosen = new ValueListBox<>(new Renderer<Tabulador>() {
            @Override
            public String render(Tabulador object) {
                if (object == null) {
                    return "None";
                }
                return object.getName();
            }

            @Override
            public void render(Tabulador object, Appendable appendable) throws IOException {
                if (object != null) {
                    String s = render(object);
                    appendable.append(s);
                }
            }
        });

        htmlPanel.add(chosen);
        
        rest = new TabuladorREST();
        rest.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
            @Override
            public void onRESTExecuted(MethodEvent restEvent) {
                if (restEvent.getMethod().equals(EMethod.FIND_ALL_BASE)) {
                    if (restEvent.getTypeResult().equals(ETypeResult.SUCCESS)) {
                        
                        List<Tabulador> tabuladores = (List<Tabulador>) restEvent.getResult();
                        chosen.setAcceptableValues(tabuladores);
                        
                    } else {
                        Growl.growl("Falló la carga de Tabuladores");
                    }
                }
            }
        });
        rest.findAllBase();
        
    }
    
    public void setSelected(Tabulador value) {
        chosen.setValue(value, true);
    }
    
    public Tabulador getSelected() {
        return chosen.getValue();
    }
    
}
