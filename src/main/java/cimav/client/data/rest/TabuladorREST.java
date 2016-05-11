/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.view.common.Ajax;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.data.domain.Tabulador;
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
public class TabuladorREST extends BaseREST {
    
    public interface JsonCodec extends JsonEncoderDecoder<Tabulador> {
    }
    
    public JsonEncoderDecoder jsonCodec = GWT.create(JsonCodec.class);
    
    public void findAll() {

        BaseREST.setDateFormatGET();
        String url = BaseREST.URL_REST_BASE + "api/tabulador";
         
        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, exception.getMessage());
                onRESTExecuted(methodEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                List<Tabulador> tabuladores = new ArrayList<>();
                try {
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        
                        Tabulador tabulador  = (Tabulador) jsonCodec.decode(val);
                        tabuladores.add(tabulador);
                    }
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.SUCCESS, "");
                    methodEvent.setResult(tabuladores);
                    onRESTExecuted(methodEvent);
                } catch (Exception e) {
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, e.getMessage());
                    onRESTExecuted(methodEvent);
                }
            }

        }));

    }
    
}
