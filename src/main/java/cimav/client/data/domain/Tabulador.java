/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.math.BigDecimal;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author juan.calderon
 */
@Bindable
public class Tabulador extends BaseDomain {

    private BigDecimal sueldo;
    private BigDecimal matDidacticos;
    private BigDecimal honorarios;
    private BigDecimal compGarantizada;
    private BigDecimal cargaAdmin;

    public BigDecimal getSueldo() {
        return sueldo;
    }

    public void setSueldo(BigDecimal sueldo) {
        this.sueldo = sueldo;
    }

    public BigDecimal getMatDidacticos() {
        return matDidacticos;
    }

    public void setMatDidacticos(BigDecimal matDidacticos) {
        this.matDidacticos = matDidacticos;
    }

    public BigDecimal getHonorarios() {
        return honorarios;
    }

    public void setHonorarios(BigDecimal honorarios) {
        this.honorarios = honorarios;
    }

    public BigDecimal getCompGarantizada() {
        return compGarantizada;
    }

    public void setCompGarantizada(BigDecimal compGarantizada) {
        this.compGarantizada = compGarantizada;
    }

    public BigDecimal getCargaAdmin() {
        return cargaAdmin;
    }

    public void setCargaAdmin(BigDecimal cargaAdmin) {
        this.cargaAdmin = cargaAdmin;
    }
    
}
