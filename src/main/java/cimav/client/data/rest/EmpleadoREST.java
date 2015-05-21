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
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, "FindAll " + exception.getMessage());
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
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.SUCCESS, "findAll listo");
                    dbEvent.setResult(empleados);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "findAll empleadoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, error);
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
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.FAILURE, "FindAllBase " +  exception.getMessage());
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
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.SUCCESS, "findAddBase listo");
                    dbEvent.setResult(empleados);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "findAllBase empleadoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }

    public void findById(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado/" + id;
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.FAILURE, "FindById " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Empleado empleado = empleadoJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.SUCCESS, "findById listo");
                    dbEvent.setResult(empleado);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "findById empleadoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void add(Empleado empleado) {

        //BaseREST.setDateFormatPOST();
        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue empleadoJSONValue = empleadoJsonCodec.encode(empleado);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(empleadoJSONValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, "Add " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Empleado nuevoEmpleado = empleadoJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.SUCCESS, "create listo");
                    dbEvent.setResult(nuevoEmpleado);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "create empleadoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void update(Empleado empleado) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/empleado";
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue empleadoJSONValue = empleadoJsonCodec.encode(empleado);

        Resource rb = new Resource(url, headers); 
        rb.put().json(empleadoJSONValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, "Update " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    // No regresa nada
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.SUCCESS, "update listo");
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "update empleadoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }

    public void findAllByDepto(int idDepto) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado/by_depto/" + idDepto;
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BY_DEPTO, ETypeResult.FAILURE, "findAllByDepto " + exception.getMessage());
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
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BY_DEPTO, ETypeResult.SUCCESS, "findAllByDepto listo");
                    dbEvent.setResult(empleados);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "findAllByDepto empleadoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_ALL_BY_DEPTO, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
}
