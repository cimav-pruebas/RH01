/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import org.gwtbootstrap3.extras.slider.client.ui.Slider;

/**
 *
 * @author juan.calderon
 */
public class NominaAsistenciasUI extends Composite {
    
    private static NominaAsistenciasUIUiBinder uiBinder = GWT.create(NominaAsistenciasUIUiBinder.class);
    
    interface NominaAsistenciasUIUiBinder extends UiBinder<Widget, NominaAsistenciasUI> {
    }
    
    @UiField
    Slider sliderAsistencias;
    
    public NominaAsistenciasUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
        sliderAsistencias.setEnabled(true);
        sliderAsistencias.setValue(13.0);
    }
    
//    @UiHandler("sliderAsistencias")
//    void onChange(ValueChangeEvent<Double> event) {
////        Integer value = event.getValue().intValue();
////        
////        int asistencias = value;
////        int faltas = 15 - value;
//        
//        //empleadoNominaLoaded.get
//        
//    }

}
