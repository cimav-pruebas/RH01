/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.util.Date;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author juan.calderon
 */
@Bindable
public class EmpleadoBase extends BaseDomain implements Serializable {
    
    private String cuentaCimav;
    private String urlPhoto;
    @JsonIgnore 
    private EGrupo grupo;
    private Integer idGrupo;
    private Tabulador nivel;
    private Departamento departamento;
    @JsonIgnore private ESede sede;
    private Integer idSede;
    @JsonIgnore private EStatusEmpleado status;
    private Integer idStatus;
    private Double estimulosProductividad;

    private Date fechaAntiguedad;
    
    private Integer pantDayEven;
    private Integer pantDayOdd;
    private Integer pantMonths;
    private Integer pantYears;
    
    public EmpleadoBase() {
        super();
        
        this.status = EStatusEmpleado.ACTIVO;
        this.sede = ESede.CHIHUAHUA;
    }
    
    public String getUrlPhoto() {
        String cteUrlPhoto = "http://cimav.edu.mx/foto/";
        String varUrlPhoto = this.cuentaCimav == null || this.cuentaCimav.trim().isEmpty() 
                ? cteUrlPhoto + "default" : cteUrlPhoto + this.cuentaCimav;
        //return this.urlPhoto;
        return varUrlPhoto;
    }

    public void setUrlPhoto(String urlPhoto) {
        this.urlPhoto = urlPhoto;
    }
    
    public String getCuentaCimav() {
        return cuentaCimav;
    }

    public void setCuentaCimav(String cuentaCimav) {
        this.urlPhoto = "http://cimav.edu.mx/foto/" + cuentaCimav;
        this.cuentaCimav = cuentaCimav;
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

    public Tabulador getNivel() {
        return nivel;
    }

    public void setNivel(Tabulador nivel) {
        this.nivel = nivel;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }

    public Double getEstimulosProductividad() {
        return estimulosProductividad;
    }

    public void setEstimulosProductividad(Double estimulosProductividad) {
        this.estimulosProductividad = estimulosProductividad;
    }

    public Integer getPantDayEven() {
        return pantDayEven;
    }

    public void setPantDayEven(Integer pantDayEven) {
        this.pantDayEven = pantDayEven;
    }

    public Integer getPantDayOdd() {
        return pantDayOdd;
    }

    public void setPantDayOdd(Integer pantDayOdd) {
        this.pantDayOdd = pantDayOdd;
    }

    public Integer getPantMonths() {
        return pantMonths;
    }

    public void setPantMonths(Integer pantMonths) {
        this.pantMonths = pantMonths;
    }

    public Integer getPantYears() {
        return pantYears;
    }

    public void setPantYears(Integer pantYears) {
        this.pantYears = pantYears;
    }

    
}
