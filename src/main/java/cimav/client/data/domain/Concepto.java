/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.fusesource.restygwt.client.Json;

/**
 *
 * @author juan.calderon
 */
public class Concepto extends BaseDomain {
    
    @JsonIgnore
    private ETipoConcepto tipoConcepto;
    @Json(name = "idTipoConcepto")
    private String idTipoConcepto;

    @JsonIgnore
    private ETipoMovimiento tipoMovimiento;
    @Json(name = "idTipoMovimiento")
    private String idTipoMovimiento;

    public Concepto() {
        super();
        this.tipoConcepto = ETipoConcepto.PERCEPCION;
    }
    
    public ETipoConcepto getTipoConcepto() {
        return tipoConcepto;
    }

    public void setTipoConcepto(ETipoConcepto tipoConcepto) {
        this.tipoConcepto = tipoConcepto;
        this.idTipoConcepto = tipoConcepto != null ? tipoConcepto.getId() : "X";
    }

    public String getIdTipoConcepto() {
        return idTipoConcepto;
    }

    public void setIdTipoConcepto(String idTipoConcepto) {
        this.tipoConcepto = ETipoConcepto.get(idTipoConcepto);
        this.idTipoConcepto = idTipoConcepto;
    }

    public ETipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(ETipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
        this.idTipoMovimiento = tipoMovimiento != null ? tipoMovimiento.getId() : "Z";
    }

    public String getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(String idTipoMovimiento) {
        this.tipoMovimiento = ETipoMovimiento.get(idTipoMovimiento);
        this.idTipoMovimiento = idTipoMovimiento;
    }
    
}
