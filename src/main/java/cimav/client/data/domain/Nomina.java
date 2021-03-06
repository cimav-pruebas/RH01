/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.io.Serializable;
import java.util.List;
import org.fusesource.restygwt.client.Json;

/**
 *
 * @author juan.calderon
 */
public class Nomina implements  Serializable {
    
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
    
//    // Si cumple años durante la quicena la PAnt es proporcional.
//    // diasPAntUno corresponde a los dias con los años anteriores
//    // diasPAntDos corresponde a los dias con los años nuevos
//    @Json(name = "yearPAnt")
//    private Integer yearPAnt;
//    @Json(name = "monthsPAnt")
//    private Integer monthsPAnt;
//    @Json(name = "daysPAnt")
//    private Integer daysPAnt;
//    
//    @Json(name = "diasPAntUno") 
//    private Integer diasPAntUno;
//    @Json(name = "dias_pant_dos")
//    private Integer diasPAntDos;

    private List<Incidencia> incidencias;
    
    private List<HoraExtra> horasExtras;
    
    public Nomina() {
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
    
    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    }

    public List<HoraExtra> getHorasExtras() {
        return horasExtras;
    }

    public void setHorasExtras(List<HoraExtra> horasExtras) {
        this.horasExtras = horasExtras;
    }


    
}
