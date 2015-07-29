/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

/**
 *
 * @author juan.calderon
 */
public class MethodEvent {
    
    private EMethod method;
    private ETypeResult typeResult;
    private String reason;

    private Object result;

    public MethodEvent(EMethod method, ETypeResult typeResult, String reason) {
        this.method = method;
        this.typeResult = typeResult;
        this.reason = reason;
        this.result = null;
    }

    public EMethod getMethod() {
        return method;
    }

    public void setMethod(EMethod method) {
        this.method = method;
    }

    public ETypeResult getTypeResult() {
        return typeResult;
    }

    public void setTypeResult(ETypeResult typeResult) {
        this.typeResult = typeResult;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

}
