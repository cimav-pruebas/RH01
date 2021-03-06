/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.tabulador;

import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.Tabulador;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.TabuladorREST;
import com.arcbees.chosen.client.gwt.ChosenValueListBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.text.shared.Renderer;
//import com.google.common.base.Strings;
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
public class TabuladorChosen extends Composite {
    
    private static TabuladorChosenUiBinder uiBinder = GWT.create(TabuladorChosenUiBinder.class);
    
    interface TabuladorChosenUiBinder extends UiBinder<Widget, TabuladorChosen> {
    }
    
    private final TabuladorREST rest;
    
    @UiField
    HTMLPanel htmlPanel;
    private ChosenValueListBox<Tabulador> chosen;
    
    public TabuladorChosen() {
        initWidget(uiBinder.createAndBindUi(this));
        
        chosen = new ChosenValueListBox<>(new Renderer<Tabulador>() {
            @Override
            public String render(Tabulador object) {
                if (object == null) {
                    return "None";
                }
                
                //String result = Strings.padEnd(object.getCode(), 8, ' ');
                
//                JsArrayMixed args = JavaScriptObject.createArray().cast();
//                args.push(object.getCode());
//                String result = format("%1$-8s", args);
                
                String result = object.getCode();
                
                result = result + object.getName();
                
                return result;
            }

            @Override
            public void render(Tabulador object, Appendable appendable) throws IOException {
                if (object != null) {
                    String s = render(object);
                    appendable.append(s);
                }
            }
        });

        chosen.setWidth("400px");
        
        htmlPanel.add(chosen);
        
        rest = new TabuladorREST();
        rest.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
            @Override
            public void onRESTExecuted(MethodEvent restEvent) {
                if (restEvent.getMethod().equals(EMethod.FIND_ALL)) {
                    if (restEvent.getTypeResult().equals(ETypeResult.SUCCESS)) {
                        
                        List<Tabulador> tabuladores = (List<Tabulador>) restEvent.getResult();
                        tabuladores.add(null);
                        chosen.setAcceptableValues(tabuladores);
                        
                    } else {
                        Window.alert("Falló carga de Tabuladores");
                    }
                }
            }
        });
        rest.findAll();
    }
    
    public static native String format (String format, JsArrayMixed values) /*-{
        return vsprintf(format, values);
    }-*/;    
    
    public void setSelected(Tabulador value) {
        chosen.setValue(value, true);
    }
    
    public Tabulador getSelected() {
        return chosen.getValue();
    }
    
    public ChosenValueListBox getChosen() {
        return this.chosen;
    }

    
}
