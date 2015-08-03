/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.io.Serializable;
import java.math.BigDecimal;
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
