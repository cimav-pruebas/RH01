/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import org.gwtbootstrap3.extras.datetimepicker.client.ui.DateTimePicker;
import org.gwtbootstrap3.extras.datetimepicker.client.ui.base.constants.DateTimePickerView;

/**
 *
 * @author juan.calderon
 */
public class FechaPicker extends DateTimePicker {

    public FechaPicker(String width) {
        this.setMinView(DateTimePickerView.MONTH);
        this.setFormat("dd/mm/yyyy");
        this.setShowTodayButton(true);
        this.setWidth(width);
    }   
    
    
}
