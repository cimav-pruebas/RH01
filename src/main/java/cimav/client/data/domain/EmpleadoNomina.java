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
    @JsonIgnore 
    private EGrupo grupo;
    private Integer idGrupo;
    private Tabulador nivel;
    private Departamento departamento;
    @JsonIgnore private ESede sede;
    private Integer idSede;
    @JsonIgnore private EStatusEmpleado status;
    private Integer idStatus;

    private Date fechaAntiguedad;
    private List<Movimiento> movimientos;
    
    private Nomina nomina;
    
    private Double estimulosProductividad;
    
    private Integer pantDayEven;
    private Integer pantDayOdd;
    private Integer pantMonths;
    private Integer pantYears;
    
    public EmpleadoNomina() {
        super();
        this.estimulosProductividad = 0.00;
    }
    
    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
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

    public List<Movimiento> getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List<Movimiento> movimientos) {
        this.movimientos = movimientos;
    }

    public Nomina getNomina() {
        return nomina;
    }

    public void setNomina(Nomina nomina) {
        this.nomina = nomina;
    }

    public Double getEstimulosProductividad() {
        return estimulosProductividad;
    }

    public void setEstimulosProductividad(Double estimulosProductividad) {
        this.estimulosProductividad = estimulosProductividad;
    }

    /** 
     * Regresa la lista por TipoConcepto y TipoMovimiento
     * @param tipoConcepto
     * @param tipoMovimiento
     * @return 
     */
    public List<Movimiento> getMovimientos(ETipoConcepto tipoConcepto, ETipoMovimiento tipoMovimiento) {
        List<Movimiento> result = new ArrayList<>();
        for(Movimiento movimiento : movimientos) {
            if (movimiento.getConcepto().getTipoConcepto().equals(tipoConcepto) && movimiento.getConcepto().getTipoMovimiento().equals(tipoMovimiento)) {
                result.add(movimiento);
            }
        }
        return result;
    }

    /** 
     * Regresa la lista por TipoConcepto 
     * @param tipoConcepto
     * @return 
     */
    public List<Movimiento> getMovimientos(ETipoConcepto tipoConcepto) {
        List<Movimiento> result = new ArrayList<>();
        for(Movimiento movimiento : movimientos) {
            ETipoConcepto tipo = movimiento.getConcepto().getTipoConcepto();
            /*
            if (ETipoConcepto.REPERCUCION.equals(tipoConcepto)) {
                if (tipo.equals(ETipoConcepto.REPERCUCION) || tipo.equals(ETipoConcepto.INTERNO)) {
                    result.add(nominaQuincenal);
                }
            } else {
                if (tipo.equals(tipoConcepto)) {
                    result.add(nominaQuincenal);
                }
            }
            */
            if (tipo.equals(tipoConcepto)) {
                result.add(movimiento);
            }
        }
        return result;
    }
    
    public BigDecimal getTotalPercepciones() {
        List<Movimiento> percepciones = this.getMovimientos(ETipoConcepto.PERCEPCION);
        BigDecimal result = BigDecimal.ZERO;
        for(Movimiento percepcion : percepciones) {
            if (percepcion.getConcepto().getSuma()) {
                result = result.add(percepcion.getCantidad());
            }
        }
        return result;
    }
    
    public BigDecimal getTotalDeducciones() {
        List<Movimiento> deducciones = this.getMovimientos(ETipoConcepto.DEDUCCION);
        BigDecimal result = BigDecimal.ZERO;
        for(Movimiento deduccion : deducciones) {
            if (deduccion.getConcepto().getSuma()) {
                result = result.add(deduccion.getCantidad());
            }
        }
        return result;
    }

    
}
