/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.common.MethodEvent;
import cimav.client.common.ETypeResult;
import cimav.client.common.EMethod;
import cimav.client.data.domain.Empleado;
import cimav.client.common.Ajax;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONValue;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

/**
 *
 * @author juan.calderon
 */
public class EmpleadoREST extends BaseREST {

    public interface EmpleadoJsonCodec extends JsonEncoderDecoder<Empleado> {}
    public EmpleadoJsonCodec empleadoJsonCodec = GWT.create(EmpleadoJsonCodec.class);
    
    public void findAll() {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado";
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    List<Empleado> empleados = new ArrayList<>();
                    JSONArray array = response.isArray();
                    for (int i = 0; i < 10; /*array.size();*/ i++) {
                        JSONValue val = array.get(i);
                        Empleado empleado = empleadoJsonCodec.decode(val);
                        empleados.add(empleado);
                    }
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.SUCCESS, "");
                    dbEvent.setResult(empleados);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, e.getMessage());
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void findAllBase() {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado/base";
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.FAILURE, exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    List<Empleado> empleados = new ArrayList<>();
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        Empleado empleado = empleadoJsonCodec.decode(val);
                        empleados.add(empleado);
                    }
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.SUCCESS, "");
                    dbEvent.setResult(empleados);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.FAILURE, e.getMessage());
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }

    // </editor-fold>
}
