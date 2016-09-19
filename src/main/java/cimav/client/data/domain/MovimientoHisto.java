/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.math.BigDecimal;

/**
 *
 * @author calderon
 */
public class MovimientoHisto {
    
    private Integer id;
    private Integer idEmpleado;
    private Concepto concepto;
    private BigDecimal cantidad;
    private BigDecimal cantidadEmpresa;

    private Short year;
    private Short quincena;

    public MovimientoHisto() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
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

    public BigDecimal getCantidadEmpresa() {
        return cantidadEmpresa;
    }

    public void setCantidadEmpresa(BigDecimal cantidadEmpresa) {
        this.cantidadEmpresa = cantidadEmpresa;
    }

    public Short getYear() {
        return year;
    }

    public void setYear(Short year) {
        this.year = year;
    }

    public Short getQuincena() {
        return quincena;
    }

    public void setQuincena(Short quincena) {
        this.quincena = quincena;
    }

    
}
