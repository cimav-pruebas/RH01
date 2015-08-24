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
import com.google.gwt.json.client.JSONValue;
import java.util.HashMap;
import org.fusesource.restygwt.client.JsonCallback;
import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.Resource;

/**
 *
 * @author juan.calderon
 */
public class CalculoREST extends BaseREST {

    public void calcular(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/calculo/" + id;

        HashMap<String, String> headers = new HashMap<>();
        headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");

        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR, ETypeResult.FAILURE, "CalculoREST.calcular " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR, ETypeResult.SUCCESS, "CalculoREST.calcular listo");
                    dbEvent.setResult(response);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "CalculoREST.calcular " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CALCULAR, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));
    }

}
