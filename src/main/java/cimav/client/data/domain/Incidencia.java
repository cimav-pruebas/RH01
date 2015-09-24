/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author juan.calderon
 */
public class Incidencia implements Serializable {
    
    private Integer id;
    private Integer idEmpleado;
    private Integer dias;
    private Integer incidencias;
    private Date fechaInicio;
    @JsonIgnore private ETipoIncidencia tipo;
    private String code;
    private String folio;
    private String clase;

    public Incidencia() {
        fechaInicio = new Date();
        tipo = ETipoIncidencia.AI;
        clase = tipo.getClase();
        folio = "";
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
        if (this.getTipo().isIncapacidad()) {
            // TODO falta calcular faltas para incapacidades
            this.incidencias = dias + 1;
        } else if (this.getTipo().isFalta()) {
            // tantos días, tantas faltas
            this.incidencias = dias;
        } else if (this.getTipo().isPermiso()) {
            // no cuentan como faltas
            this.incidencias = 0;
        }  
    }

    public Integer getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(Integer incidencias) {
        this.incidencias = incidencias;
    }
    
    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public ETipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(ETipoIncidencia tipo) {
        this.tipo = tipo;
        this.code = tipo != null ? tipo.getId() : "XX";
        this.clase = this.tipo.getClase();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.tipo = ETipoIncidencia.get(code);
        this.code = code;
        this.clase = this.tipo.getClase();
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
    
}