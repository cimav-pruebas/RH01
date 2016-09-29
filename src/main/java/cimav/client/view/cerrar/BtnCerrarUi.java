/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.cerrar;

import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.CalculoREST;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.ButtonType;
import org.gwtbootstrap3.extras.toggleswitch.client.ui.ToggleSwitch;

/**
 *
 * @author calderon
 */
public class BtnCerrarUi extends Composite {
    
    private static BtnCerrarUiBinder uiBinder = GWT.create(BtnCerrarUiBinder.class);
    interface BtnCerrarUiBinder extends UiBinder<Widget, BtnCerrarUi> {
    }
    
    @UiField ToggleSwitch toggleSwitch;
    @UiField Button btnCerrar;
    
    public BtnCerrarUi() {
        initWidget(uiBinder.createAndBindUi(this));
        
        toggleSwitch.addValueChangeHandler(new ValueChangeHandler<Boolean>() {
            @Override
            public void onValueChange(ValueChangeEvent<Boolean> event) {
                btnCerrar.setEnabled(event.getValue());
                if (event.getValue()) {
                    btnCerrar.setType(ButtonType.DANGER);
                } else {
                    btnCerrar.setType(ButtonType.DEFAULT);
                }
            }
        });
        
    }
    
    public Button getBtnCerrar() {
        return btnCerrar;
    }
    
}
