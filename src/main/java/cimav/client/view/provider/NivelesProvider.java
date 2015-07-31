/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.Tabulador;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.TabuladorREST;
import com.google.gwt.user.client.Window;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NivelesProvider extends BaseProvider<Tabulador> {
    
    private static NivelesProvider instance;

    private TabuladorREST tabuladorREST;

    public static NivelesProvider get() {
        if (instance == null) {
            instance = new NivelesProvider();
        }
        return instance;
    }

    public TabuladorREST getTabuladorREST() {
        if (tabuladorREST == null) {
            tabuladorREST = new TabuladorREST();

            tabuladorREST.addRESTExecutedListener(new RestMethodExecutedListener());
        }
        return tabuladorREST;
    }

    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_ALL.equals(methodEvent.getMethod())) {
                
                // tumbar a todos. 
                dataProvider.getList().clear();
                
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // si no hubo problema, pasa la lista resultante al Provider
                    List<Tabulador> niveles = (List<Tabulador>) methodEvent.getResult();
                    dataProvider.getList().addAll(niveles);
                } else {
                    Window.alert("Falló cargada de Niveles: " + methodEvent.getReason());
                }
                
                // le avisa al EmpleadoUI
                onMethodExecuted(methodEvent);
                
            } 
            
        }
    }

    public void findAll() {
        this.getTabuladorREST().findAll();
    }
    
}

