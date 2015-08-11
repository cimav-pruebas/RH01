/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author juan.calderon
 */
public class ConceptosPorSaldo extends Composite {
    
    private static ConceptosPorSaldoUiBinder uiBinder = GWT.create(ConceptosPorSaldoUiBinder.class);
    
    interface ConceptosPorSaldoUiBinder extends UiBinder<Widget, ConceptosPorSaldo> {
    }
    
    public ConceptosPorSaldo() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
