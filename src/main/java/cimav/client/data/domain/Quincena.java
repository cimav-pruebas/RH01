/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author juan.calderon
 */
public class Quincena implements Serializable {
    
    private Integer year;
    private Integer mes;
    private Integer quincena;
    private Integer diaInicio;
    private Integer diaFin;
    private Integer diaFinCalendario;
    private Date fechaInicio;
    private Date fechaFin;
    private Date fechaFinCalendario;
    private Integer dias;
    private Integer diasOrdinarios;
    private Integer diasDescanso;
    private Integer diasAsueto;
    private Integer diasImss;
    
    public Quincena() {
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getFechaFinCalendario() {
        return fechaFinCalendario;
    }

    public void setFechaFinCalendario(Date fechaFinCalendario) {
        this.fechaFinCalendario = fechaFinCalendario;
    }

    public Integer getDias() {
        return dias;
    }

    public void setDias(Integer dias) {
        this.dias = dias;
    }

    public Integer getDiasOrdinarios() {
        return diasOrdinarios;
    }

    public void setDiasOrdinarios(Integer diasOrdinarios) {
        this.diasOrdinarios = diasOrdinarios;
    }

    public Integer getDiasDescanso() {
        return diasDescanso;
    }

    public void setDiasDescanso(Integer diasDescanso) {
        this.diasDescanso = diasDescanso;
    }

    public Integer getDiasAsueto() {
        return diasAsueto;
    }

    public void setDiasAsueto(Integer diasAsueto) {
        this.diasAsueto = diasAsueto;
    }

    public Integer getDiasImss() {
        return diasImss;
    }

    public void setDiasImss(Integer diasImss) {
        this.diasImss = diasImss;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMes() {
        return mes;
    }

    public void setMes(Integer mes) {
        this.mes = mes;
    }

    public Integer getDiaInicio() {
        return diaInicio;
    }

    public void setDiaInicio(Integer diaInicio) {
        this.diaInicio = diaInicio;
    }

    public Integer getDiaFin() {
        return diaFin;
    }

    public void setDiaFin(Integer diaFin) {
        this.diaFin = diaFin;
    }

    public Integer getDiaFinCalendario() {
        return diaFinCalendario;
    }

    public void setDiaFinCalendario(Integer diaFinCalendario) {
        this.diaFinCalendario = diaFinCalendario;
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.quincena);
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
        final Quincena other = (Quincena) obj;
        if (!Objects.equals(this.quincena, other.quincena)) {
            return false;
        }
        return true;
    }

    
    
}
