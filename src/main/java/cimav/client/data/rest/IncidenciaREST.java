/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.Incidencia;
import cimav.client.view.common.Ajax;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONValue;
import java.util.HashMap;
import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.JsonEncoderDecoder;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

/**
 *
 * @author juan.calderon
 */
public class IncidenciaREST extends BaseREST {
  
    public interface JsonCodec extends JsonEncoderDecoder<Incidencia> {}
    public JsonCodec jsonCodec = GWT.create(JsonCodec.class);
 
    public void create(Incidencia incidencia) {

        //BaseREST.setDateFormatPOST();
        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/incidencias";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue jsonValue = jsonCodec.encode(incidencia);
        
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
                    Incidencia nueva = jsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.SUCCESS, "create listo");
                    dbEvent.setResult(nueva);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "IncidenciaREST.CREATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void update(Incidencia incidencia) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/incidencias/" + incidencia.getId();
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue jsonValue = jsonCodec.encode(incidencia);

        Resource rb = new Resource(url, headers); 
        rb.put().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, "IncidenciaREST.UPDATE " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    // No regresa nada
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.SUCCESS, "IncidenciaREST.UPDATE listo");
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "IncidenciaREST.UPDATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }

    public void remove(String id) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/incidencias/" + id;
        
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
                    String error = "IncidenciaREST.remove " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
}
