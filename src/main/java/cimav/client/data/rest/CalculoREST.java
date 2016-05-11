/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.Quincena;
import cimav.client.view.common.Ajax;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.core.client.GWT;
import com.google.gwt.json.client.JSONParser;
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
public class CalculoREST extends BaseREST {

    public interface QuincenaJsonCodec extends JsonEncoderDecoder<Quincena> {}
    public QuincenaJsonCodec quincenaJsonCodec = GWT.create(QuincenaJsonCodec.class);
    
    public void findQuincena() {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/quincena/";
        
        Resource rb = new Resource(url, headers);
        rb.get().user("ADMIN").password("admin").send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_QUINCENA, ETypeResult.FAILURE, "FIND_QUINCENA " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Quincena quincena = (Quincena) quincenaJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_QUINCENA, ETypeResult.SUCCESS, "FIND_QUINCENA listo");
                    dbEvent.setResult(quincena);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "FIND_QUINCENA >> " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_QUINCENA, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void calcular(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/calculo/" + id;

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR_ONE, ETypeResult.FAILURE, "CalculoREST.calcular " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR_ONE, ETypeResult.SUCCESS, "CalculoREST.calcular listo");
                    dbEvent.setResult(response);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "CalculoREST.calcular " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR_ONE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));
    }

    public void calcular(String ids) {
        
        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/calculo";

        JSONValue jsonValue =JSONParser.parseStrict(ids);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(jsonValue).send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR_ALL, ETypeResult.FAILURE, "CalculoREST.calcular " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR_ALL, ETypeResult.SUCCESS, "CalculoREST.calcular listo");
                    dbEvent.setResult(response);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "CalculoREST.calcular " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR_ALL, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));
    }
    
}
