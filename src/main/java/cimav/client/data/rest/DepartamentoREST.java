/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.common.Ajax;
import cimav.client.common.EMethod;
import cimav.client.common.ETypeResult;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Departamento;
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
public class DepartamentoREST extends BaseREST {
    
    public interface JsonCodec extends JsonEncoderDecoder<Departamento> {
    }
    
    public JsonEncoderDecoder jsonCodec = GWT.create(JsonCodec.class);
    
    public void findAllBase() {

        BaseREST.setDateFormatGET();
        String url = BaseREST.URL_REST_BASE + "api/departamento";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.FAILURE, exception.getMessage());
                onRESTExecuted(methodEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                List<Departamento> departamentos = new ArrayList<>();
                try {
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        
                        Departamento departamento  = (Departamento) jsonCodec.decode(val);
                        departamentos.add(departamento);
                    }
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.SUCCESS, "");
                    methodEvent.setResult(departamentos);
                    onRESTExecuted(methodEvent);
                } catch (Exception e) {
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL_BASE, ETypeResult.FAILURE, e.getMessage());
                    onRESTExecuted(methodEvent);
                }
            }

        }));

    }
    
}

