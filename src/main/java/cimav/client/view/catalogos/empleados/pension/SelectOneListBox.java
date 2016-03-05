/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados.pension;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

/**
 *
 * @author juan.calderon
 */
public class SelectOneListBox extends Composite {
    
    private static SelectOneListBoxUiBinder uiBinder = GWT.create(SelectOneListBoxUiBinder.class);
    
    interface SelectOneListBoxUiBinder extends UiBinder<Widget, SelectOneListBox> {
    }
    
    public SelectOneListBox() {
        initWidget(uiBinder.createAndBindUi(this));
    }
}
