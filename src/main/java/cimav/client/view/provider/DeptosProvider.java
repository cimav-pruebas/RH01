/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Departamento;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.DepartamentoREST;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class DeptosProvider extends BaseProvider<Departamento> {
    
    private static DeptosProvider instance;

    private DepartamentoREST deptosREST;

    public static DeptosProvider get() {
        if (instance == null) {
            instance = new DeptosProvider();
        }
        return instance;
    }

    @Override
    public boolean matchFilter(Departamento value, String filter) {
        if (value == null) {
            return false;
        }
        if (filter == null || filter.trim().isEmpty()) {
            return true;
        }

        // ^.*\b(one|two|three)\b.*$    one, tow or three
        // ^(?=.*?\bone\b)(?=.*?\btwo\b)(?=.*?\bthree\b).*$ one, two AND three
        // ^(?=.*?one)(?=.*?two)(?=.*?three).*$ las palabras no tiene boundiry
        // la frase completa debe tener todos los terminos
        String pattern = "^";
        filter = filter.toLowerCase();
        String[] array = filter.split("\\s+");
        for (String term : array) {
            pattern = pattern + "(?=.*?" + term.trim() + ")";
        }
        pattern = pattern + ".+";

        String string = value.getName() + " " + value.getCode();
        string = string.toLowerCase();

        RegExp regExp = RegExp.compile(pattern);
        MatchResult matcher = regExp.exec(string);

        return matcher != null;
    }

    public DepartamentoREST getREST() {
        if (deptosREST == null) {
            deptosREST = new DepartamentoREST();

            deptosREST.addRESTExecutedListener(new RestMethodExecutedListener());
        }
        return deptosREST;
    }

    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_ALL.equals(methodEvent.getMethod())
                    || EMethod.FIND_ALL_BASE.equals(methodEvent.getMethod())) {
                
                // tumbar a todos. 
                dataProvider.getList().clear();
                
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // si no hubo problema, pasa la lista resultante al Provider
                    List<Departamento> deptos = (List<Departamento>) methodEvent.getResult();
                    dataProvider.getList().addAll(deptos);
                } else {
                    Window.alert("Falló cargada de departamentos: " + methodEvent.getReason());
                }
                
                // le avisa al EmpleadoUI
                onMethodExecuted(methodEvent);
                
            } else if (EMethod.CREATE.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Departamento created = (Departamento) methodEvent.getResult();
                    dataProvider.getList().add(created);
                }
                onMethodExecuted(methodEvent); 
            } else if (EMethod.UPDATE.equals(methodEvent.getMethod())) {
                onMethodExecuted(methodEvent); 
            } else if (EMethod.FIND_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // re-carga el provider con el empleado reloaded
                    Departamento reloaded = (Departamento) methodEvent.getResult();
                    int idx = dataProvider.getList().indexOf(reloaded);
                    dataProvider.getList().set(idx, reloaded);
                }

                // en methodEvent.getResult() va el Empleado recargado para pasarlo al binding
                onMethodExecuted(methodEvent); // <-- usa el mismo methodEvent
            }
            
        }
    }

    public void findAll() {
        this.getREST().findAllBase();
    }
    
    public void add(Departamento depto) {
        this.getREST().add(depto);
    }

    public void update(Departamento depto) {
        this.getREST().update(depto);
    }
    
    public void reloadById(int idDepto) {
        this.getREST().findById(idDepto);
    }
    
}
