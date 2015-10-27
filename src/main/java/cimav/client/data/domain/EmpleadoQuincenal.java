/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import org.fusesource.restygwt.client.Json;

/**
 *
 * @author juan.calderon
 */
public class EmpleadoQuincenal implements  Serializable {
    
    private Integer id;
    @Json(name = "idEmpleado")
    private Integer idEmpleado;
    
    // Faltas, Incapacidades, Ordinarios y Descanso se modifican junto
    // a las Incidencias
    private Integer faltas;
    @Json(name = "incapacidadHabiles")
    private Integer incapacidadHabiles;
    @Json(name = "incapacidadInhabiles")
    private Integer incapacidadInhabiles;
    private Integer ordinarios;
    private Integer descanso;
    
    // Si cumple años durante la quicena la PAnt es proporcional.
    // diasPAntUno corresponde a los dias con los años anteriores
    // diasPAntDos corresponde a los dias con los años nuevos
    @Json(name = "yearPAnt")
    private Integer yearPAnt;
    @Json(name = "monthsPAnt")
    private Integer monthsPAnt;
    @Json(name = "daysPAnt")
    private Integer daysPAnt;
    
    @Json(name = "diasPAntUno") 
    private Integer diasPAntUno;
    @Json(name = "dias_pant_dos")
    private Integer diasPAntDos;

    public EmpleadoQuincenal() {
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

    public Integer getFaltas() {
        return faltas;
    }

    public void setFaltas(Integer faltas) {
        this.faltas = faltas;
    }

    public Integer getIncapacidadHabiles() {
        return incapacidadHabiles;
    }

    public void setIncapacidadHabiles(Integer incapacidadHabiles) {
        this.incapacidadHabiles = incapacidadHabiles;
    }

    public Integer getIncapacidadInhabiles() {
        return incapacidadInhabiles;
    }

    public void setIncapacidadInhabiles(Integer incapacidadInhabiles) {
        this.incapacidadInhabiles = incapacidadInhabiles;
    }

    public Integer getOrdinarios() {
        return ordinarios;
    }

    public void setOrdinarios(Integer ordinarios) {
        this.ordinarios = ordinarios;
    }

    public Integer getDescanso() {
        return descanso;
    }

    public void setDescanso(Integer descanso) {
        this.descanso = descanso;
    }

    public Integer getTrabajados() {
        return ordinarios + descanso;
    }
    
    public Integer getYearPAnt() {
        return yearPAnt;
    }

    public void setYearPAnt(Integer yearPAnt) {
        this.yearPAnt = yearPAnt;
    }

    public Integer getMonthsPAnt() {
        return monthsPAnt;
    }

    public void setMonthsPAnt(Integer monthsPAnt) {
        this.monthsPAnt = monthsPAnt;
    }

    public Integer getDaysPAnt() {
        return daysPAnt;
    }

    public void setDaysPAnt(Integer daysPAnt) {
        this.daysPAnt = daysPAnt;
    }

    public Integer getDiasPAntUno() {
        return diasPAntUno;
    }

    public void setDiasPAntUno(Integer diasPAntUno) {
        this.diasPAntUno = diasPAntUno;
    }

    public Integer getDiasPAntDos() {
        return diasPAntDos;
    }

    public void setDiasPAntDos(Integer diasPAntDos) {
        this.diasPAntDos = diasPAntDos;
    }

    
}
