/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.cerrar;

import com.github.gwtbootstrap.client.ui.Modal;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.client.ui.Button;

/**
 *
 * @author juan.calderon
 */
public class CerrarUI extends Composite {
    
    private static CerrarUIUiBinder uiBinder = GWT.create(CerrarUIUiBinder.class);
    
    interface CerrarUIUiBinder extends UiBinder<Widget, CerrarUI> {
    }
    
    @UiField Modal modal;
    @UiField Button btnOK;
    
    public CerrarUI() {
        initWidget(uiBinder.createAndBindUi(this));

        btnOK.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
            }
        });
        
        modal.getElement().setAttribute("style", "width: 800px; margin: auto; height: 400px");
    }
    
    public Modal getModal() {
        return modal;
    } 
}
