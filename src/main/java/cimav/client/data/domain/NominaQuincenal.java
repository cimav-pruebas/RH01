/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;

/**
 *
 * @author juan.calderon
 */
public class NominaQuincenal implements  Serializable{

    private Integer id;
    private Integer numQuincenas;
    private Concepto concepto;
    private BigDecimal cantidad;

    private BigDecimal pago;
    private BigDecimal saldo;
    private Boolean permanente;
    
    private Integer idEmpleado;
    
    public NominaQuincenal() {
        this.idEmpleado = 0;
        this.numQuincenas = 1;
        this.cantidad = BigDecimal.ZERO;
        this.pago = BigDecimal.ZERO;
        this.saldo = BigDecimal.ZERO;
        this.permanente = Boolean.FALSE;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getNumQuincenas() {
        return numQuincenas;
    }

    public void setNumQuincenas(Integer numQuincenas) {
        this.numQuincenas = numQuincenas;
        
        this.setCantidad();
    }

    public Concepto getConcepto() {
        return concepto;
    }

    public void setConcepto(Concepto concepto) {
        this.concepto = concepto;
    }

    public BigDecimal getCantidad() {
        return cantidad;
    }

    public void setCantidad(BigDecimal cantidad) {
        this.cantidad = cantidad;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public BigDecimal getPago() {
        return pago;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;

        this.setCantidad();
    }
    
    private void setCantidad() {
        if (this.numQuincenas == null || this.numQuincenas <= 0) {
            // no pueden ser menos de 1 quincena
            this.numQuincenas = 1;
        }
        
        if (this.saldo == null || this.saldo.signum() == 0) {
            // los saldos negativos si pasan
            this.saldo = BigDecimal.ZERO;
            this.pago = BigDecimal.ZERO;
            return;
        }
        
        if (this.permanente) {
            // si es Permanente, NO prorratea el saldo entre las quincenas
            this.pago = this.saldo;
        } else {
            // si NO es Permanente, SI prorratea el saldo entre las quincenas
            this.pago = this.saldo.divide(new BigDecimal(this.numQuincenas), RoundingMode.HALF_UP);
        }
        
        this.cantidad = this.pago;
    }

    public Boolean getPermanente() {
        return permanente;
    }

    public void setPermanente(Boolean permanente) {
        this.permanente = permanente;
        this.setCantidad();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final NominaQuincenal other = (NominaQuincenal) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    
    @Override
    public String toString() {
        return "NominaQuincenal{" + "id=" + id + ", numQuincenas=" + numQuincenas + ", concepto=" + concepto + '}';
    }

    
}
