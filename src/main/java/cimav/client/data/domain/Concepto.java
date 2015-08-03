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
    private ETipoMovimiento tipoMovimiento;
    @Json(name = "idTipoMvto")
    private String idTipoMovimiento;

    @JsonIgnore
    private ETipoCalculo tipoCalculo;
    private String idTipoCalculo;

    public Concepto() {
        super();
        this.tipoMovimiento = ETipoMovimiento.PERCEPTION;
        this.tipoCalculo = ETipoCalculo.FIJO;
    }
    
    public ETipoMovimiento getTipoMovimiento() {
        return tipoMovimiento;
    }

    public void setTipoMovimiento(ETipoMovimiento tipoMovimiento) {
        this.tipoMovimiento = tipoMovimiento;
        this.idTipoMovimiento = tipoMovimiento != null ? tipoMovimiento.getId() : "X";
    }

    public String getIdTipoMovimiento() {
        return idTipoMovimiento;
    }

    public void setIdTipoMovimiento(String idTipoMovimiento) {
        this.tipoMovimiento = ETipoMovimiento.get(idTipoMovimiento);
        this.idTipoMovimiento = idTipoMovimiento;
    }
    
    public ETipoCalculo getTipoCalculo() {
        return tipoCalculo;
    }

    public void setTipoCalculo(ETipoCalculo tipoCalculo) {
        this.tipoCalculo = tipoCalculo;
        this.idTipoCalculo = tipoCalculo != null ? tipoCalculo.getId() : "Y";
    }

    public String getIdTipoCalculo() {
        return idTipoCalculo;
    }

    public void setIdTipoCalculo(String idTipoCalculo) {
        this.tipoCalculo = ETipoCalculo.get(idTipoCalculo);
        this.idTipoCalculo = idTipoCalculo;
    }

}
