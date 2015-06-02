/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.tabulador;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author juan.calderon
 */
public class TabuladoresUi extends Composite {
    
    private static TabuladoresUiUiBinder uiBinder = GWT.create(TabuladoresUiUiBinder.class);
    
    interface TabuladoresUiUiBinder extends UiBinder<Widget, TabuladoresUi> {
    }
    
    public TabuladoresUi() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
