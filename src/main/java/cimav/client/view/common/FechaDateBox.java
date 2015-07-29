/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.user.datepicker.client.DateBox;

/**
 *
 * @author juan.calderon
 */
public class FechaDateBox extends DateBox {

    public FechaDateBox() {
        super();
        DateTimeFormat df = DateTimeFormat.getFormat("dd/MM/yyyy");
        DateBox.Format dtf = new DateBox.DefaultFormat(df);
        this.setFormat(dtf);
    }
    
}
