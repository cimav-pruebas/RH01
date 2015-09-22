/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import cimav.client.view.Quincena;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juan.calderon
 */
public class Falta implements  Serializable {
    
    private Integer id;
    private Integer idEmpleado;
    private Integer dias;
    private Integer faltas;
    private Date fechaInicio;
    @JsonIgnore private ETipoFalta tipoFalta;
    private String idTipo;
    private String folio;

    public Falta() {
        fechaInicio = new Date();
        tipoFalta = ETipoFalta.AI;
        this.setDias(1);
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

    public Integer getDias() {
        return dias;
    }

    public final void setDias(Integer dias) {
        this.dias = dias;
        if (this.getTipoFalta().isIncapacidad()) {
            // TODO falta calcular faltas para incapacidades
            this.faltas = dias + 1;
        } else if (this.getTipoFalta().isFalta()) {
            // tantos días, tantas faltas
            this.faltas = dias;
        } else if (this.getTipoFalta().isPermiso()) {
            // no cuentan como faltas
            this.faltas = 0;
        }  
    }

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ETipoFalta getTipoFalta() {
        return tipoFalta;
    }

    public void setTipoFalta(ETipoFalta tipoFalta) {
        this.tipoFalta = tipoFalta;
        this.idTipo = tipoFalta != null ? tipoFalta.getId() : "XX";
    }

    public String getIdTipo() {
        return idTipo;
    }

    public void setIdTipo(String idTipo) {
        this.tipoFalta = ETipoFalta.get(idTipo);
        this.idTipo = idTipo;
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }
    

    
}
