/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

import cimav.client.data.domain.EmpleadoHisto;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.user.client.Window;
import java.util.List;

/**
 *
 * @author calderon
 */
public class EmpleadosHistoProvider extends BaseProvider<EmpleadoHisto> {

//    /* Borrar por compatiblidad */
//    private static EmpleadosBaseProvider instance;
//    public static EmpleadosBaseProvider get() {
//        if (instance == null) {
//            instance = new EmpleadosBaseProvider();
//        }
//        return instance;
//    }
    
    private EmpleadoREST empleadoREST;
    
    public EmpleadoREST getREST() {
        if (empleadoREST == null) {
            empleadoREST = new EmpleadoREST();

            empleadoREST.addRESTExecutedListener(new RestMethodExecutedListener());
        }
        return empleadoREST;
    }

    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_EMPLEADO_HISTO_BY_ID_EMPLEADO.equals(methodEvent.getMethod())) {
                
                // tumbar a todos. 
                dataProvider.getList().clear();
                
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // si no hubo problema, pasa la lista resultante al Provider
                    List<EmpleadoHisto> empleados = (List<EmpleadoHisto>) methodEvent.getResult();
                    dataProvider.getList().addAll(empleados);
                } else {
                    Window.alert("EmpleadosHistoProvider.java Falló cargada de empleados: " + methodEvent.getReason());
                }
                
                // le avisa al EmpleadoHistoUI
                onMethodExecuted(methodEvent);
            }
        }
    }

    public void findHistoByIdEmpleado(int idEmpleado) {
        this.getREST().findHistoByIdEmpleado(idEmpleado);
    }
    
}

