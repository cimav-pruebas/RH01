/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

import cimav.client.data.domain.EmpleadoBase;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.regexp.shared.MatchResult;
import com.google.gwt.regexp.shared.RegExp;
import com.google.gwt.user.client.Window;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class EmpleadosBaseProvider extends BaseProvider<EmpleadoBase> {

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

    @Override
    public boolean matchFilter(EmpleadoBase value, String filter) {
        if (value == null) {
            return false;
        }
        if (filter == null || filter.trim().isEmpty()) {
            return true;
        }

        boolean hasAya = filter.toLowerCase().contains("a:"); 
        if (hasAya) {
            filter = filter.replace("a:", "");
        }
        boolean hasHon = filter.toLowerCase().contains("h:"); 
        if (hasHon) {
            filter = filter.replace("h:", "");
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

        String grupoStr = value.getGrupo() != null ? value.getGrupo().getCode() + " " + value.getGrupo().getName() : " ";
        String codeGrupolStr = value.getGrupo() != null ? value.getGrupo().getCode() : " ";
        String nivelStr = value.getNivel() != null ? value.getNivel().getCode() + " " + value.getNivel().getName() : " ";
        String sedeStr = value.getSede() != null ? value.getSede().getAbrev() + " " + value.getSede().getNombre(): " ";
        String deptoStr = value.getDepartamento() != null ? value.getDepartamento().getCode() + " " + value.getDepartamento().getName() : " ";

        String string = value.getName() /*+ " " + value.getRfc()*/ + " " + value.getCode() + " " + value.getCuentaCimav() + " " + grupoStr + " " + nivelStr + " " + sedeStr + " " + deptoStr;
        string = string.toLowerCase();

        RegExp regExp = RegExp.compile(pattern);
        MatchResult matcher = regExp.exec(string);

        boolean result = matcher != null;
        
        if (hasAya) {
            result = result && (codeGrupolStr.toLowerCase().contains("aya"));
        }
        if (hasHon) {
            result = result && (codeGrupolStr.toLowerCase().contains("hon"));
        }
        
        return result;
    }

    public static final int ORDER_BY_NAME = 0;
    public static final int ORDER_BY_CODE = 1;
    public static final int ORDER_BY_GRUPO = 2;
    public static final int ORDER_BY_NIVEL = 3;

    @Override
    public void order(int orderBy) {
        switch (orderBy) {
            case ORDER_BY_NAME: {
                Collections.sort(dataProvider.getList(), new Comparator<EmpleadoBase>() {
                    @Override
                    public int compare(EmpleadoBase emp1, EmpleadoBase emp2) {
                        return emp1.getName().compareTo(emp2.getName());
                    }
                });
                break;
            }
            case ORDER_BY_CODE: {
                Collections.sort(dataProvider.getList(), new Comparator<EmpleadoBase>() {
                    @Override
                    public int compare(EmpleadoBase emp1, EmpleadoBase emp2) {
                        return emp1.getCode().compareTo(emp2.getCode());
                    }
                });
                break;
            }
            case ORDER_BY_GRUPO: {
                Collections.sort(dataProvider.getList(), new Comparator<EmpleadoBase>() {
                    @Override
                    public int compare(EmpleadoBase emp1, EmpleadoBase emp2) {
                        String grp1 = emp1.getGrupo() != null ? emp1.getGrupo().getCode() : "";
                        String grp2 = emp2.getGrupo() != null ? emp2.getGrupo().getCode() : "";
                        return grp1.compareTo(grp2);
                    }
                });
                break;
            }
            case ORDER_BY_NIVEL: {
                Collections.sort(dataProvider.getList(), new Comparator<EmpleadoBase>() {
                    @Override
                    public int compare(EmpleadoBase emp1, EmpleadoBase emp2) {
                        String niv1 = emp1.getNivel() != null ? emp1.getNivel().getCode() : "";
                        String niv2 = emp2.getNivel() != null ? emp2.getNivel().getCode() : "";
                        return niv1.compareTo(niv2);
                    }
                });
                break;
            }
        }
    }

    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_BASE_ALL.equals(methodEvent.getMethod())) {
                
                // tumbar a todos. 
                dataProvider.getList().clear();
                
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // si no hubo problema, pasa la lista resultante al Provider
                    List<EmpleadoBase> empleados = (List<EmpleadoBase>) methodEvent.getResult();
                    dataProvider.getList().addAll(empleados);
                } else {
                    Window.alert("EmpleadosBaseProvider.java Falló cargada de empleados: " + methodEvent.getReason());
                }
                
                // le avisa al EmpleadoUI
                onMethodExecuted(methodEvent);
                
            } else if (EMethod.FIND_BASE_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // re-carga el provider con el empleado reloaded
                    EmpleadoBase reloaded = (EmpleadoBase) methodEvent.getResult();
                    int idx = dataProvider.getList().indexOf(reloaded);
                    dataProvider.getList().set(idx, reloaded);
                }

                // en methodEvent.getResult() va el Empleado recargado para pasarlo al binding
                onMethodExecuted(methodEvent); // <-- usa el mismo methodEvent
            }  else if (EMethod.FIND_EMPLEADO_BASE_BY_ID_DEPTO.equals(methodEvent.getMethod())) {
                // tumbar a todos. 
                dataProvider.getList().clear();
                
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // si no hubo problema, pasa la lista resultante al Provider
                    List<EmpleadoBase> empleados = (List<EmpleadoBase>) methodEvent.getResult();
                    dataProvider.getList().addAll(empleados);
                } else {
                    Window.alert("EmpleadosBaseProvider.java. Falló cargada de empleados by_depto: " + methodEvent.getReason());
                }
                
                onMethodExecuted(methodEvent);
            } 
            
        }
    }

    public void findAllBase() {
        this.getREST().findAllBase();
    }
    
    public void reloadBaseById(int idEmpleado) {
        this.getREST().findBaseById(idEmpleado);
    }
    
    public void findAllBaseByDepto(int idDepto) {
        this.getREST().findBaseByIdDepto(idDepto);
    }
    
}
