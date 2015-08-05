/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author juan.calderon
 */
@Bindable
public class EmpleadoNomina extends BaseDomain implements Serializable{
    
    private String cuentaCimav;
    private String urlPhoto;
    private Grupo grupo;
    private Tabulador nivel;
    private Departamento departamento;
    @JsonIgnore private ESede sede;
    private Integer idSede;
    @JsonIgnore private EStatusEmpleado status;
    private Integer idStatus;

    private Date fechaAntiguedad;
    private List<NominaQuincenal> nominaQuincenalCollection;

    public EmpleadoNomina() {
        super();
    }

    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }
 
    public String getUrlPhoto() {
        String cteUrlPhoto = "http://cimav.edu.mx/foto/";
        String varUrlPhoto = this.cuentaCimav == null || this.cuentaCimav.trim().isEmpty() 
                ? cteUrlPhoto + "default" : cteUrlPhoto + this.cuentaCimav;
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

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
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

    public List<NominaQuincenal> getNominaQuincenalCollection() {
        return nominaQuincenalCollection;
    }

    public void setNominaQuincenalCollection(List<NominaQuincenal> nominaQuincenalCollection) {
        this.nominaQuincenalCollection = nominaQuincenalCollection;
    }
    
    /* Sumas */
    
    public List<NominaQuincenal> getNominaQuincenalCollectionByTipoMovimiento(ETipoMovimiento tipoMovimiento) {
        List<NominaQuincenal> result = new ArrayList<>();
        for(NominaQuincenal nominaQuincenal : nominaQuincenalCollection) {
            if (nominaQuincenal.getConcepto().getTipoMovimiento().equals(tipoMovimiento)) {
                result.add(nominaQuincenal);
            }
        }
        return result;
    }
    
    public BigDecimal getTotalPercepciones() {
        List<NominaQuincenal> percepciones = this.getNominaQuincenalCollectionByTipoMovimiento(ETipoMovimiento.PERCEPTION);
        BigDecimal result = BigDecimal.ZERO;
        for(NominaQuincenal percepcion : percepciones) {
            result = result.add(percepcion.getCantidad());
        }
        return result;
    }
    
    public BigDecimal getTotalDeducciones() {
        List<NominaQuincenal> deducciones = this.getNominaQuincenalCollectionByTipoMovimiento(ETipoMovimiento.DEDUCCION);
        BigDecimal result = BigDecimal.ZERO;
        for(NominaQuincenal deduccion : deducciones) {
            result = result.add(deduccion.getCantidad());
        }
        return result;
    }
    
//    public BigDecimal getTotal() {
//        BigDecimal result = this.getTotalPercepciones().subtract(this.getTotalDeducciones());
//        return result;
//    }
    
}
