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
public class NominaAsistenciasUI extends Composite {
    
    private static NominaAsistenciasUIUiBinder uiBinder = GWT.create(NominaAsistenciasUIUiBinder.class);
    
    interface NominaAsistenciasUIUiBinder extends UiBinder<Widget, NominaAsistenciasUI> {
    }
    
//    private FaltaREST faltaRest;
//    private EmpleadoNomina empleadoNomina;
//    
//    @UiField
//    IntegerBox spinAsistencias;
//    @UiField
//    IntegerBox spinFaltas;
//    @UiField
//    TextBox txtRazon;
    
    public NominaAsistenciasUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
//        spinAsistencias.getElement().setAttribute("style", "width:60px; height:inherit; text-align:right;");
//        spinAsistencias.getElement().setAttribute("type", "number");
//        spinAsistencias.getElement().setAttribute("step", "1");
//        spinAsistencias.getElement().setAttribute("min", "1");
//        spinAsistencias.getElement().setAttribute("max", "15");
//        spinAsistencias.getElement().setAttribute("value", "15");
//        spinAsistencias.getElement().setAttribute("disabled", "");
//        spinFaltas.getElement().setAttribute("style", "width:60px; height:inherit; text-align:right;");
//        spinFaltas.getElement().setAttribute("type", "number");
//        spinFaltas.getElement().setAttribute("step", "1");
//        spinFaltas.getElement().setAttribute("min", "1");
//        spinFaltas.getElement().setAttribute("max", "15");
//        spinFaltas.getElement().setAttribute("value", "0");
//        
//        spinFaltas.addChangeHandler(new ChangeHandler() {
//            @Override
//            public void onChange(ChangeEvent event) {
//                Integer faltas = spinFaltas.getValue();
//                if (faltas==null || faltas<0 || faltas>15) {
//                    spinFaltas.setValue(empleadoNomina.getFalta().getFaltas(), false);
//                } else {
//                    Integer asistencias = 15 - faltas;
//                    spinAsistencias.setValue(asistencias);
//                    
//                    empleadoNomina.getFalta().setFaltas(faltas);
//                            
//                    getREST().update(empleadoNomina.getFalta());
//                }
//            }
//        });
//        
//        txtRazon.addChangeHandler(new ChangeHandler() {
//            @Override
//            public void onChange(ChangeEvent event) {
//                empleadoNomina.getFalta().setRazon(txtRazon.getText());
//
//                getREST().update(empleadoNomina.getFalta());
//            }
//        });

    }

//    private FaltaREST getREST() {
//        if (faltaRest == null) {
//            faltaRest = new FaltaREST();
//        }
//        return faltaRest;
//    }
//    
//    public void setEmpleado(EmpleadoNomina empleado) {
//        if (empleado == null || empleado.getFalta() == null) {
//            return;
//        }
//        empleadoNomina = empleado;
//
//        Integer faltas = empleadoNomina.getFalta().getFaltas();
//        Integer asistencias = 15 - faltas;
//        this.spinAsistencias.setValue(asistencias);
//        spinFaltas.setValue(faltas, false);
//        txtRazon.setValue(empleadoNomina.getFalta().getRazon(), false);
//    }
    
}
