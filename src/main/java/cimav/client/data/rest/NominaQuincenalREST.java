/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.Movimiento;
import cimav.client.view.common.Ajax;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONArray;
import com.google.gwt.json.client.JSONParser;
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
public class NominaQuincenalREST extends BaseREST {
    
    public interface JsonCodec extends JsonEncoderDecoder<Movimiento> {}
    public JsonCodec jsonCodec = GWT.create(JsonCodec.class);
 
    public void findByEmpleadoIds(String empleadoIds) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/nomina_quincenal/find_by_empleado_ids";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        JSONValue jsonValue =JSONParser.parseStrict(empleadoIds);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_EMPLEADO_IDS, ETypeResult.FAILURE, "NominaQuincenalREST.FIND_BY_EMPLEADO_IDS " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    List<Movimiento> result = new ArrayList<>();
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        Movimiento nq = jsonCodec.decode(val);
                        result.add(nq);
                    }
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_EMPLEADO_IDS, ETypeResult.SUCCESS, "NominaQuincenalREST.FIND_BY_EMPLEADO_IDS listo");
                    dbEvent.setResult(result);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "NominaQuincenalREST.FIND_BY_EMPLEADO_IDS " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_EMPLEADO_IDS, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void create(Movimiento nominaQuincenal) {

        //BaseREST.setDateFormatPOST();
        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/nomina_quincenal";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue jsonValue = jsonCodec.encode(nominaQuincenal);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, "Create " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Movimiento nuevo = jsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.SUCCESS, "create listo");
                    dbEvent.setResult(nuevo);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "NominaQuincenalREST.CREATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void update(Movimiento nominaQuincenal) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/nomina_quincenal/" + nominaQuincenal.getId();
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue jsonValue = jsonCodec.encode(nominaQuincenal);

        Resource rb = new Resource(url, headers); 
        rb.put().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {
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
                    String error = "NominaQuincenalREST.UPDATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void remove(String id) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/nomina_quincenal/" + id;
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers); 
        rb.delete().send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.FAILURE, "remove " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    // No regresa nada
                    MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.SUCCESS, "remove listo");
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "NominaQuincenalREST.remove " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
}
