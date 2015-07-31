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
    
    public void findAll() {

        BaseREST.setDateFormatGET();
        String url = BaseREST.URL_REST_BASE + "api/departamento";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, exception.getMessage());
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
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.SUCCESS, "");
                    methodEvent.setResult(departamentos);
                    onRESTExecuted(methodEvent);
                } catch (Exception e) {
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_ALL, ETypeResult.FAILURE, e.getMessage());
                    onRESTExecuted(methodEvent);
                }
            }

        }));

    }
    
    
    public void findById(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/departamento/" + id;
        
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
                    Departamento departamento = (Departamento) jsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.SUCCESS, "findById listo");
                    dbEvent.setResult(departamento);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "findById departamentoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void add(Departamento departamento) {

        //BaseREST.setDateFormatPOST();
        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/departamento";

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue departamentoJSONValue = jsonCodec.encode(departamento);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(departamentoJSONValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, "Add " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Departamento nuevoDepartamento = (Departamento) jsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.SUCCESS, "create listo");
                    dbEvent.setResult(nuevoDepartamento);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "create departamentoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void update(Departamento departamento) {

        BaseREST.setDateFormatPOST();

        int id = departamento != null && departamento.getId() != null ? departamento.getId() : 0;
        String url = BaseREST.URL_REST_BASE + "api/departamento/" + id;
        
        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        //Create a Jsonizer instance
        JSONValue departamentoJSONValue = jsonCodec.encode(departamento);

        Resource rb = new Resource(url, headers); 
        rb.put().json(departamentoJSONValue).send(Ajax.jsonCall(new JsonCallback() {
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
                    String error = "update departamentoJsonCodec >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
}

