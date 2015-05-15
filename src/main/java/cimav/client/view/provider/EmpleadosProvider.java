/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Empleado;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import com.google.gwt.core.shared.GWT;
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
public class EmpleadosProvider extends BaseProvider<Empleado> {

    private static EmpleadosProvider instance;

    private EmpleadoREST empleadoREST;

    public static EmpleadosProvider get() {
        if (instance == null) {
            instance = new EmpleadosProvider();
        }
        return instance;
    }

    @Override
    public boolean matchFilter(Empleado value, String filter) {
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

        String grupoStr = value.getGrupo() != null ? value.getGrupo().getCode() + " " + value.getGrupo().getName() : " ";
        String nivelStr = value.getNivel() != null ? value.getNivel().getCode() + " " + value.getNivel().getName() : " ";
        String sedeStr = value.getSede() != null ? value.getSede().getAbrev() + " " + value.getSede().getNombre(): " ";
        String deptoStr = value.getDepartamento() != null ? value.getDepartamento().getCode() + " " + value.getDepartamento().getName() : " ";

        String string
                = value.getName() + " " + value.getRfc() + " " + value.getCode() + " " + value.getCuentaCimav() + " " + grupoStr + " " + nivelStr + " " + sedeStr + " " + deptoStr;
        string = string.toLowerCase();

        RegExp regExp = RegExp.compile(pattern);
        MatchResult matcher = regExp.exec(string);

        return matcher != null;
    }

    public static final int ORDER_BY_NAME = 0;
    public static final int ORDER_BY_CODE = 1;
    public static final int ORDER_BY_GRUPO = 2;
    public static final int ORDER_BY_NIVEL = 3;

    @Override
    public void order(int orderBy) {
        switch (orderBy) {
            case ORDER_BY_NAME: {
                Collections.sort(dataProvider.getList(), new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado emp1, Empleado emp2) {
                        return emp1.getName().compareTo(emp2.getName());
                    }
                });
                break;
            }
            case ORDER_BY_CODE: {
                Collections.sort(dataProvider.getList(), new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado emp1, Empleado emp2) {
                        return emp1.getCode().compareTo(emp2.getCode());
                    }
                });
                break;
            }
            case ORDER_BY_GRUPO: {
                Collections.sort(dataProvider.getList(), new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado emp1, Empleado emp2) {
                        String grp1 = emp1.getGrupo() != null ? emp1.getGrupo().getCode() : "";
                        String grp2 = emp2.getGrupo() != null ? emp2.getGrupo().getCode() : "";
                        return grp1.compareTo(grp2);
                    }
                });
                break;
            }
            case ORDER_BY_NIVEL: {
                Collections.sort(dataProvider.getList(), new Comparator<Empleado>() {
                    @Override
                    public int compare(Empleado emp1, Empleado emp2) {
                        String niv1 = emp1.getNivel() != null ? emp1.getNivel().getCode() : "";
                        String niv2 = emp2.getNivel() != null ? emp2.getNivel().getCode() : "";
                        return niv1.compareTo(niv2);
                    }
                });
                break;
            }
        }
    }

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
            if (EMethod.FIND_ALL.equals(methodEvent.getMethod())) {
                
                // tumbar a todos. 
                dataProvider.getList().clear();
                
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // si no hubo problema, pasa la lista resultante al Provider
                    List<Empleado> empleados = (List<Empleado>) methodEvent.getResult();
                    dataProvider.getList().addAll(empleados);
                    
                    for(Empleado emp : empleados) {
                        GWT.log(">>>> " + emp.toString());
                    }
                    
                } else {
                    Window.alert("Falló cargada de empleados: " + methodEvent.getReason());
                }
                
                // le avisa al EmpleadoUI
                onMethodExecuted(methodEvent);
                
            } else if (EMethod.CREATE.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    Empleado created = (Empleado) methodEvent.getResult();
                    dataProvider.getList().add(created);
                }
                onMethodExecuted(methodEvent); 
            } else if (EMethod.UPDATE.equals(methodEvent.getMethod())) {
//                // en methodEvent.getResult() va el Empleado recargado
//                // pero no requiere pasarlo al binding dado que es el mismo cambiado
//                // actualizar el dataProvider
//                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) { 
//                    Empleado empleadoUpdated = (Empleado) methodEvent.getResult();
//                    int idx = dataProvider.getList().indexOf(empleadoUpdated);
//                    dataProvider.getList().set(idx, empleadoUpdated);
//                }
                onMethodExecuted(methodEvent); 
            } else if (EMethod.FIND_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    // re-carga el provider con el empleado reloaded
                    Empleado reloaded = (Empleado) methodEvent.getResult();
                    int idx = dataProvider.getList().indexOf(reloaded);
                    dataProvider.getList().set(idx, reloaded);
                }

                // en methodEvent.getResult() va el Empleado recargado para pasarlo al binding
                onMethodExecuted(methodEvent); // <-- usa el mismo methodEvent
            }
            
        }
    }

    public void findAll() {
        this.getREST().findAll();
    }
    
    public void add(Empleado empleado) {
        this.getREST().add(empleado);
    }

    public void update(Empleado empleado) {
        this.getREST().update(empleado);
    }
    
    public void reloadById(int idEmpleado) {
        this.getREST().findById(idEmpleado);
    }
    
//    public void delete(Empleado empleado) {
//        if (empleado == null || empleado.getId() <= 0) {
//            Growl.growl("Empleado nulo");
//        } else {
//            this.getREST().delete(empleado.getId());
//        }
//    }

}
