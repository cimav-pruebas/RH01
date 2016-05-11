/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.rest;

import cimav.client.view.common.MethodEvent;
import com.googlecode.gwt.crypto.bouncycastle.util.encoders.Base64;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.fusesource.restygwt.client.Defaults;
import org.fusesource.restygwt.client.Resource;

/**
 *
 * @author juan.calderon
 */
public class BaseREST {
    
    public static final String URL_REST_BASE = Defaults.getServiceRoot().replace("/rh", "/rh_rest"); // Para cuando el server esta embedded
    //private static final String URL_REST = "http://201.174.72.60:8080/RestRH01-0.1.50/" + "api/empleado/base";
    //public static final String URL_REST_BASE = "http://calderas.cimav.edu.mx:8080/rest_rh/";
    //public static final String URL_REST_BASE = "http://10.0.4.40:8080/rest_rh/"; 
    //public static final String URL_REST_BASE = "http://localhost:8080/RestRH01/";
    
/*    private static HashMap<String, String> headers;
    
    public static HashMap<String, String> getHeaders() {
        if (headers == null) {
            headers = new HashMap<>();
            headers.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
            headers.put("UserLogged", "ElUsuarioLogeado");
        }
        return headers;
    }
    */
    
    public static void setDateFormatGET() {
        org.fusesource.restygwt.client.Defaults.setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
    }
    
    public static void setDateFormatPOST() {
        org.fusesource.restygwt.client.Defaults.setDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    }

//    private static String encodedCredentials = null;
//    private static String getEncodedCredentials() {
//        if (encodedCredentials == null){
//            String credentials =  "ADMIN:admin";
//            try {
//                encodedCredentials = new String(Base64.encode(credentials.getBytes()), "UTF-8");
//            } catch (UnsupportedEncodingException ex) {
//                Logger.getLogger(BaseREST.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//        return encodedCredentials;
//    }
    
//    public static HashMap<String, String> headers;     
//    static {
//        headers.put("Content-Type", "application/json; charset=utf-8");
//        String credentials =  "ADMIN:admin";
//        try {
//            String encodedCredentials = new String(Base64.encode(credentials.getBytes()), "UTF-8");
//            headers.put("Authorization", encodedCredentials);
//        } catch (UnsupportedEncodingException ex) {
//            Logger.getLogger(BaseREST.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    
    public static final HashMap<String, String> headers = initHeader();     
    private static HashMap<String, String> initHeader() {
        HashMap<String, String> h = new HashMap<>();     
        h.put(Resource.HEADER_CONTENT_TYPE, "application/json; charset=utf-8");
        h.put("Authorization", "QURNSU5fUk9MRTphZG1pbg==");
        return  h;
    }
    
//    private static BaseREST instance;
//
//    public static BaseREST get() {
//        if (instance == null) {
//            instance = new BaseREST();
//        }
//        return instance;
//    }
    
    // <editor-fold defaultstate="collapsed" desc="interface RESTExecutedListener"> 
    public interface RESTExecutedListener extends java.util.EventListener {
        void onRESTExecuted(MethodEvent restEvent);
    }
    private final ArrayList listeners = new ArrayList();
    public void addRESTExecutedListener(RESTExecutedListener listener) {
        listeners.add(listener);
    }
    public void removeRESTExecutedListener(RESTExecutedListener listener) {
        listeners.remove(listener);
    }
    public void onRESTExecuted(MethodEvent restEvent) {
        for(Iterator it = listeners.iterator(); it.hasNext();) {
            RESTExecutedListener listener = (RESTExecutedListener) it.next();
            listener.onRESTExecuted(restEvent);
        }
    }
    // </editor-fold>
    
}
