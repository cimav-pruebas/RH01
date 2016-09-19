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
 * @author calderon
 */
public class EmpleadoHisto extends BaseDomain implements Serializable {
    
    private static final long serialVersionUID = 1L;

    @JsonIgnore 
    private EGrupo grupo;
    private Integer idGrupo; //idGrupo
    
    @JsonIgnore 
    private EStatusEmpleado status;
    private Integer idStatus; //idStatus
    
    @JsonIgnore 
    private ESede sede;
    private Integer idSede; //idSede
    
    private String nivelCode; //nivelCode
    
    private Departamento departamento;

    @JsonIgnore 
    private ETipoEmpleado tipoEmpleado;
    private Integer idTipoEmpleado;
    
    private Integer idEmpleado; //idEmpleado
    
    @JsonIgnore 
    private ETipoAntiguedad tipoAntiguedad;
    private Integer idTipoAntiguedad; //idTipoAntiguedad

    private Date fechaAntiguedad; //fechaAntiguedad
    private Date fechaIngreso; //fechaIngreso
    private Date fechaBaja; //fechaBaja (muchos nulos)
    private Double estimulosProductividad;   //estimulosProductividad 
    
    private String antiguedad; //antiguedad
    
    private Integer year;
    private Integer quincena;

    public EmpleadoHisto() {
    }
    
    public ESede getSede() {
        return sede;
    }

    public void setSede(ESede sede) {
        this.sede = sede;
        this.idSede = sede != null ? sede.getId() : 0;
    }

    public Integer getIdSede() {
        return idSede;
    }

    public void setIdSede(Integer idSede) {
        this.sede = ESede.get(idSede);
        this.idSede = idSede;
    }

    public EStatusEmpleado getStatus() {
        return status;
    }

    public void setStatus(EStatusEmpleado status) {
        this.status = status;
        this.idStatus = status != null ? status.getId() : 0;
    }

    public Integer getIdStatus() {
        return idStatus;
    }

    public void setIdStatus(Integer idStatus) {
        this.status = EStatusEmpleado.get(idStatus);
        this.idStatus = idStatus;
    }

    public EGrupo getGrupo() {
        return grupo;
    }

    public void setGrupo(EGrupo grupo) {
        this.grupo = grupo;
        this.idGrupo = grupo != null ? grupo.getId() : 0;
    }

    public Integer getIdGrupo() {
        return idGrupo;
    }

    public void setIdGrupo(Integer idGrupo) {
        this.grupo = EGrupo.get(idGrupo);
        this.idGrupo = idGrupo;
    }
    
    public ETipoEmpleado getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(ETipoEmpleado tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
        this.idTipoEmpleado = tipoEmpleado != null ? tipoEmpleado.getId() : 0;
    }

    public Integer getIdTipoEmpleado() {
        return idTipoEmpleado;
    }

    public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        this.tipoEmpleado = ETipoEmpleado.get(idTipoEmpleado);
        this.idTipoEmpleado = idTipoEmpleado;
    }
    
    public ETipoAntiguedad getTipoAntiguedad() {
        return tipoAntiguedad;
    }

    public void setTipoAntiguedad(ETipoAntiguedad tipoAntiguedad) {
        this.tipoAntiguedad = tipoAntiguedad;
        this.idTipoAntiguedad = tipoAntiguedad != null ? tipoAntiguedad.getId() : 0;
    }

    public Integer getIdTipoAntiguedad() {
        return idTipoAntiguedad;
    }

    public void setIdTipoAntiguedad(Integer idTipoAntiguedad) {
        this.tipoAntiguedad = ETipoAntiguedad.get(idTipoAntiguedad);
        this.idTipoAntiguedad = idTipoAntiguedad;
    }

    public String getNivelCode() {
        return nivelCode;
    }

    public void setNivelCode(String nivelCode) {
        this.nivelCode = nivelCode;
    }

    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Double getEstimulosProductividad() {
        return estimulosProductividad;
    }

    public void setEstimulosProductividad(Double estimulosProductividad) {
        this.estimulosProductividad = estimulosProductividad;
    }

    public String getAntiguedad() {
        return antiguedad;
    }

    public void setAntiguedad(String antiguedad) {
        this.antiguedad = antiguedad;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }
    
    
}
