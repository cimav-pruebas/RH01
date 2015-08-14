/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.NominaQuincenal;
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
public class NominaQuincenalREST extends BaseREST {
    
    public interface JsonCodec extends JsonEncoderDecoder<NominaQuincenal> {}
    public JsonCodec jsonCodec = GWT.create(JsonCodec.class);
 
    public void update(NominaQuincenal nominaQuincenal) {

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
    
}
