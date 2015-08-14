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

    private BigDecimal pagoUnico;
    private BigDecimal pagoPermanente;
    private BigDecimal saldoDescuento;
    private BigDecimal saldoRestante;
    
    private Integer idEmpleado;

    public NominaQuincenal() {
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
        
        int quin = this.numQuincenas > 0 ? this.numQuincenas : 1;
        
        if (concepto != null && ETipoMovimiento.SALDO.equals(concepto.getTipoMovimiento())) {
            this.saldoDescuento = this.saldoRestante.divide(new BigDecimal(quin), RoundingMode.HALF_UP);
            this.cantidad = this.saldoDescuento;
        } else if (concepto != null && ETipoMovimiento.PAGO.equals(concepto.getTipoMovimiento())) {
        } 
        
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

    public BigDecimal getPagoUnico() {
        return pagoUnico;
    }

    public void setPagoUnico(BigDecimal pagoUnico) {
        if (pagoUnico == null || pagoUnico.signum() == -1) {
            pagoUnico = BigDecimal.ZERO;
        }
        
        this.pagoUnico = pagoUnico;
        
        if (concepto != null && ETipoMovimiento.PAGO.equals(concepto.getTipoMovimiento())) {
            this.cantidad = this.pagoUnico;
        }
    }

    public BigDecimal getPagoPermanente() {
        return pagoPermanente;
    }

    public void setPagoPermanente(BigDecimal pagoPermanente) {
        if (pagoPermanente == null || pagoPermanente.signum() == -1) {
            pagoPermanente = BigDecimal.ZERO;
        }
        
        this.pagoPermanente = pagoPermanente;
        
        if (concepto != null && ETipoMovimiento.PAGO.equals(concepto.getTipoMovimiento())) {
            // si hay Pago Unico, se cobra el Pago unico
            if (this.pagoUnico != null && this.pagoUnico.signum() == 1) {
                this.cantidad = this.pagoUnico;
            } else {
                this.cantidad = this.pagoPermanente;
            }
        }
        
        this.pagoPermanente = pagoPermanente;
    }

    public BigDecimal getSaldoDescuento() {
        return saldoDescuento;
    }

//    public void setSaldoDescuento(BigDecimal saldoDescuento) {
//        this.saldoDescuento = saldoDescuento;
//    }

    public BigDecimal getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(BigDecimal saldoRestante) {
        this.saldoRestante = saldoRestante;
        
        int quin = this.numQuincenas > 0 ? this.numQuincenas : 1;
        this.saldoDescuento = this.saldoRestante.divide(new BigDecimal(quin), RoundingMode.HALF_UP);
        
        if (concepto != null && ETipoMovimiento.SALDO.equals(concepto.getTipoMovimiento())) {
            this.cantidad = this.saldoDescuento;
        }
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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
