/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.Concepto;
import cimav.client.view.common.Ajax;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
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
public class ConceptoREST extends BaseREST {
    
    public interface JsonCodec extends JsonEncoderDecoder<Concepto> {}
    public JsonEncoderDecoder jsonCodec = GWT.create(JsonCodec.class);
    
    //private HashMap<String, String> headers = BaseREST.getHeader();
    
    public void findAll() {

        BaseREST.setDateFormatGET();
        String url = BaseREST.URL_REST_BASE + "api/concepto";

        Resource rb = new Resource(url, BaseREST.headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, exception.getMessage());
                onRESTExecuted(methodEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                List<Concepto> conceptos = new ArrayList<>();
                try {
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        
                        Concepto concepto  = (Concepto) jsonCodec.decode(val);
                        conceptos.add(concepto);
                    }
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.SUCCESS, "");
                    methodEvent.setResult(conceptos);
                    onRESTExecuted(methodEvent);
                } catch (Exception e) {
                    String error = "findAll Conceptos >> " + e.getMessage();
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, error);
                    onRESTExecuted(methodEvent);
                }
            }

        }));

    }
    
    
    public void findById(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/concepto/" + id;
        
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
                    Concepto concepto = (Concepto) jsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.SUCCESS, "findById listo");
                    dbEvent.setResult(concepto);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "findById Conceptos >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
}
