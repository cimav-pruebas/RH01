/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.data.domain.Concepto;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.EMethod;
import cimav.client.data.domain.Empleado;
import cimav.client.data.domain.EmpleadoBase;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.view.common.Ajax;
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
public class EmpleadoREST extends BaseREST {

    public interface EmpleadoJsonCodec extends JsonEncoderDecoder<Empleado> {}
    public EmpleadoJsonCodec empleadoJsonCodec = GWT.create(EmpleadoJsonCodec.class);
    
    public interface EmpleadoBaseJsonCodec extends JsonEncoderDecoder<EmpleadoBase> {}
    public EmpleadoBaseJsonCodec empleadoBaseJsonCodec = GWT.create(EmpleadoBaseJsonCodec.class);
    
    public interface EmpleadoNominaJsonCodec extends JsonEncoderDecoder<EmpleadoNomina> {}
    public EmpleadoNominaJsonCodec empleadoNominaJsonCodec = GWT.create(EmpleadoNominaJsonCodec.class);

    public interface JsonCodecConcepto extends JsonEncoderDecoder<Concepto> {}
    public JsonCodecConcepto jsonCodecConcepto = GWT.create(JsonCodecConcepto.class);
    
    public void findAllBase() {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado_base";
        
        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BASE_ALL, ETypeResult.FAILURE, "FindAll " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    List<EmpleadoBase> empleados = new ArrayList<>();
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        EmpleadoBase empleadoBase = empleadoBaseJsonCodec.decode(val);
                        empleados.add(empleadoBase);
                    }
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BASE_ALL, ETypeResult.SUCCESS, "findAll listo");
                    dbEvent.setResult(empleados);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "EmpleadoREST.FIND_BASE_ALL " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BASE_ALL, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void findEmpleadoById(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado/" + id;
        
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
                    Empleado empleado = empleadoJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.SUCCESS, "findById listo");
                    dbEvent.setResult(empleado);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "EmpleadoREST.FIND_BY_ID " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BY_ID, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void findBaseById(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado_base/" + id;
        
        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BASE_BY_ID, ETypeResult.FAILURE, "FindById " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Empleado empleado = empleadoJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BASE_BY_ID, ETypeResult.SUCCESS, "findById listo");
                    dbEvent.setResult(empleado);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "EmpleadoREST.FIND_BASE_BY_ID " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_BASE_BY_ID, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void findEmpleadoNominaById(int id) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado_nomina/" + id;
        
        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_EMPLEADO_NOMINA_BY_ID, ETypeResult.FAILURE, "FindById " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    EmpleadoNomina empleadoNomina = empleadoNominaJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_EMPLEADO_NOMINA_BY_ID, ETypeResult.SUCCESS, "findById listo");
                    dbEvent.setResult(empleadoNomina);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "EmpleadoREST.FIND_EMPLEADO_NOMINA_BY_ID " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_EMPLEADO_NOMINA_BY_ID, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
    
    public void add(Empleado empleado) {

        //BaseREST.setDateFormatPOST();
        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado";

        //Create a Jsonizer instance
        JSONValue empleadoJSONValue = empleadoJsonCodec.encode(empleado);
        
        Resource rb = new Resource(url, headers);
        rb.post().json(empleadoJSONValue).send(Ajax.jsonCall(new JsonCallback() {
            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, "Add " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    Empleado nuevoEmpleado = empleadoJsonCodec.decode(response);
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.SUCCESS, "create listo");
                    dbEvent.setResult(nuevoEmpleado);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "EmpleadoREST.CREATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.CREATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }
    
    public void update(Empleado empleado) {

        BaseREST.setDateFormatPOST();

        String url = BaseREST.URL_REST_BASE + "api/empleado";
        
        //Create a Jsonizer instance
        JSONValue empleadoJSONValue = empleadoJsonCodec.encode(empleado);

        Resource rb = new Resource(url, headers); 
        rb.put().json(empleadoJSONValue).send(Ajax.jsonCall(new JsonCallback() {
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
                    String error = "EmpleadoREST.UPDATE " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.UPDATE, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }
        }));

    }

    public void findBaseByIdDepto(int idDepto) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado_base/by_depto/" + idDepto;
        
        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_EMPLEADO_BASE_BY_ID_DEPTO, ETypeResult.FAILURE, "findAllByDepto " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                try {
                    List<EmpleadoBase> empleadosBase = new ArrayList<>();
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        EmpleadoBase empleadoBase = empleadoBaseJsonCodec.decode(val);
                        empleadosBase.add(empleadoBase);
                    }
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_EMPLEADO_BASE_BY_ID_DEPTO, ETypeResult.SUCCESS, "findAllByDepto listo");
                    dbEvent.setResult(empleadosBase);
                    onRESTExecuted(dbEvent);
                } catch (Exception e) {
                    String error = "EmpleadoREST.FIND_BASE_BY_ID_DEPTO " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_EMPLEADO_BASE_BY_ID_DEPTO, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }

    public void findPensionAlimenticia(int idEmpleado) {

        BaseREST.setDateFormatGET();

        String url = BaseREST.URL_REST_BASE + "api/empleado_nomina/pension_alimenticia/" + idEmpleado;
        
        Resource rb = new Resource(url, headers);
        rb.get().send(Ajax.jsonCall(new JsonCallback() {

            @Override
            public void onFailure(Method method, Throwable exception) {
                MethodEvent dbEvent = new MethodEvent(EMethod.FIND_PENSION_ALIMENTICA_BY_ID_EMPLEADO, ETypeResult.FAILURE, "findPensionAlimenticiaByIdEmpleado " + exception.getMessage());
                onRESTExecuted(dbEvent);
            }

            @Override
            public void onSuccess(Method method, JSONValue response) {
                List<Concepto> conceptos = new ArrayList<>();
                try {
                    JSONArray array = response.isArray();
                    for (int i = 0; i < array.size(); i++) {
                        JSONValue val = array.get(i);
                        
                        Concepto concepto  = (Concepto) jsonCodecConcepto.decode(val);
                        conceptos.add(concepto);
                    }
                    MethodEvent methodEvent = new MethodEvent(EMethod.FIND_PENSION_ALIMENTICA_BY_ID_EMPLEADO, ETypeResult.SUCCESS, "findPensionAlimenticiaByIdEmpleado");
                    methodEvent.setResult(conceptos);
                    onRESTExecuted(methodEvent);
                } catch (Exception e) {
                    String error = "findPensionAlimenticiaByIdEmpleado " + e.getMessage();
                    MethodEvent dbEvent = new MethodEvent(EMethod.FIND_PENSION_ALIMENTICA_BY_ID_EMPLEADO, ETypeResult.FAILURE, error);
                    onRESTExecuted(dbEvent);
                }
            }

        }));

    }
}
