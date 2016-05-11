/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.HoraExtra;
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
public class HorasExtrasREST extends BaseREST {
  
    public interface JsonCodec extends JsonEncoderDecoder<HoraExtra> {}
    public JsonCodec jsonCodec = GWT.create(JsonCodec.class);
    
    public void create(HoraExtra horaExtra) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/horas_extras";

        //Create a Jsonizer instance
        JSONValue jsonValue = jsonCodec.encode(horaExtra);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, "HorasExtrasREST Create " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    BaseREST.setDateFormatGET();
                    HoraExtra nueva = jsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.SUCCESS, "HorasExtrasREST create listo");
                    dbEvent.setResult(nueva);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "HoraExtraREST.CREATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void update(HoraExtra horaExtra) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/horas_extras/" + horaExtra.getId();
        
        //Create a Jsonizer instance
        JSONValue jsonValue = jsonCodec.encode(horaExtra);

        Resource rb = new Resource(url, headers); 
        rb.put().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, "HorasExtrasREST.UPDATE " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    // No regresa nada
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.SUCCESS, "HorasExtrasREST.UPDATE listo");
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "HorasExtrasREST.UPDATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }

    public void remove(String id) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/horas_extras/" + id;
        
        Resource rb = new Resource(url, headers); 
        rb.delete().send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.FAILURE, "HorasExtrasREST remove " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    // No regresa nada
                    MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.SUCCESS, "HorasExtrasREST remove listo");
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "HorasExtrasREST.remove " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.DELETE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
}
