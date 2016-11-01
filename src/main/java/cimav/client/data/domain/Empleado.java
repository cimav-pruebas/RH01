/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gwt.user.datepicker.client.CalendarUtil;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.NotBlank;
import org.jboss.errai.databinding.client.api.Bindable;

/**
 *
 * @author juan.calderon
 */
@Bindable
public class Empleado extends BaseDomain implements Serializable { 
    
    private String cuentaCimav;
    private String urlPhoto;

    @JsonIgnore 
    private EGrupo grupo;
    private Integer idGrupo;
    
    private Tabulador nivel;
    private Departamento departamento;
    
    @JsonIgnore 
    private ESede sede;
    private Integer idSede;
    
    @JsonIgnore 
    private EStatusEmpleado status;
    private Integer idStatus;
    
    private String rfc;
    private String curp;
    private String cuentaBanco;
    private String imss;
    
    @NotBlank(message = "Nombre no puede estar vacío")
    @Size(min = 3, max = 40, message = "Nombre debe tener entre 3 y 40 caracteres")
    private String nombre;

    private String apellidoPaterno;
    
    private String apellidoMaterno;
    
    @JsonIgnore 
    private EClinica clinica;
    private Integer idClinica;

    @JsonIgnore 
    private EBanco banco;
    private Integer idBanco;
    
//    private Grupo grupo;
//    private Tabulador nivel;
//    private Departamento departamento;
    
//    @JsonIgnore private ESede sede;
//    private Integer idSede;
    
//    @JsonIgnore private EStatusEmpleado status;
//    private Integer idStatus;
    
    @JsonIgnore 
    private ETipoEmpleado tipoEmpleado;
    private Integer idTipoEmpleado;
    
    @JsonIgnore private 
    ETipoContrato tipoContrato;
    private Integer idTipoContrato;
    
    @JsonIgnore 
    private ETipoAntiguedad tipoAntiguedad;
    private Integer idTipoAntiguedad;
    
    @JsonIgnore 
    private ETipoSNI tipoSNI;
    private Integer idTipoSni;

    private EmpleadoBase jefe;
    
    private Date fechaIngreso;
    private Date fechaInicioContrato;
    private Date fechaFinContrato;
    private Date fechaBaja;
    private Date fechaAntiguedad;
    private Date fechaSni;
    private String numSni;

    private Date fechaNacimiento;
    
    @JsonIgnore 
    private ESexo sexo;
    private Integer idSexo;
    
    @JsonIgnore 
    private EEdoCivil edoCivil;
    private Integer idEdoCivil;
    
    private String direccionCalle;
    private String direccionColonia;
    private String direccionCP;
    private String telefono;
    private String emailPersonal;
    
    private Double estimulosProductividad;
    private Double porcenSegSeparacionInd;
    
    @JsonIgnore 
    private ETipoPension pensionTipo;
    private Integer pensionIdTipo;
    private Double pensionPorcen;
    private String pensionBeneficiario;
    @JsonIgnore 
    private EBanco pensionBanco;
    private Integer pensionIdBanco;
    private String pensionCuenta;
    
    private Integer pantDayEven;
    private Integer pantDayOdd;
    private Integer pantMonths;
    private Integer pantYears;
    
    @JsonIgnore 
    private ECreditoInfoTipo retCreditoInfonavitTipo;
    private Integer retCreditoInfonavitIdTipo;
    private BigDecimal retCreditoInfonavitValor;
    
    public Empleado() {
        super();
        
//        this.status = EStatusEmpleado.SABATICO;
        this.tipoEmpleado = ETipoEmpleado.NORMAL;
        this.tipoContrato = ETipoContrato.DETERMINADO;
        
        Date today = new Date();
        this.fechaIngreso = today;
        this.fechaInicioContrato = today;
        Date oneYear = new Date(); // un año después
        CalendarUtil.addMonthsToDate(oneYear, 12);
        this.fechaFinContrato = oneYear;
        
        this.tipoAntiguedad = ETipoAntiguedad.ADMINISTRATIVA;
        this.tipoSNI = ETipoSNI.NO_APLICA;
        
//        this.sede = ESede.CHIHUAHUA;
        
        this.porcenSegSeparacionInd = 0.00;
        
        retCreditoInfonavitValor = BigDecimal.ZERO;
        retCreditoInfonavitTipo = ECreditoInfoTipo.NONE;
    }

    public EmpleadoBase toBase() {
        EmpleadoBase result = new EmpleadoBase();
        result.setId(this.getId());
        result.setCode(this.getCode());
        result.setName(this.getApellidoPaterno() + " " + this.getApellidoMaterno() + " " + this.getNombre());
        result.setConsecutivo(this.getConsecutivo());
        result.setCuentaCimav(this.getCuentaCimav());
        result.setUrlPhoto(this.getUrlPhoto());
        result.setGrupo(this.getGrupo());
        result.setDepartamento(this.getDepartamento());
        result.setSede(this.getSede());
        result.setStatus(this.getStatus());
        result.setNivel(this.getNivel());
        result.setEstimulosProductividad(this.getEstimulosProductividad());
        return result;
    }
    
    public EBanco getBanco() {
        return banco;
    }

    public void setBanco(EBanco banco) {
        this.banco = banco;
        this.idBanco = banco != null ? banco.getId() : 0;
    }

    public Integer getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(Integer idBanco) {
        this.banco = EBanco.get(idBanco);
        this.idBanco = idBanco;
    }
    
//    public String getUrlPhoto() {
//        String cteUrlPhoto = "http://cimav.edu.mx/foto/";
//        String varUrlPhoto = this.cuentaCimav == null || this.cuentaCimav.trim().isEmpty() 
//                ? cteUrlPhoto + "default" : cteUrlPhoto + this.cuentaCimav;
//        return this.urlPhoto;
//    }
//
//    public void setUrlPhoto(String urlPhoto) {
//        this.urlPhoto = urlPhoto;
//    }
//
//    public Grupo getGrupo() {
//        return grupo;
//    }
//
//    public void setGrupo(Grupo grupo) {
//        this.grupo = grupo;
//    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

//    public Tabulador getNivel() {
//        return nivel;
//    }
//
//    public void setNivel(Tabulador nivel) {
//        this.nivel = nivel;
//    }
//
//    public Departamento getDepartamento() {
//        return departamento;
//    }
//
//    public void setDepartamento(Departamento departamento) {
//        this.departamento = departamento;
//    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public EClinica getClinica() {
        return clinica;
    }

    public void setClinica(EClinica clinica) {
        this.clinica = clinica;
        this.idClinica = clinica != null ? clinica.getId() : 0;
    }

    public Integer getIdClinica() {
        return idClinica;
    }

    public void setIdClinica(Integer idClinica) {
        this.clinica = EClinica.get(idClinica);
        this.idClinica = idClinica;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

//    public String getCuentaCimav() {
//        return cuentaCimav;
//    }
//
//    public void setCuentaCimav(String cuentaCimav) {
//        this.urlPhoto = "http://cimav.edu.mx/foto/" + cuentaCimav;
//        this.cuentaCimav = cuentaCimav;
//    }

    public String getCuentaBanco() {
        return cuentaBanco;
    }

    public void setCuentaBanco(String cuentaBanco) {
        this.cuentaBanco = cuentaBanco;
    }

    public String getImss() {
        return imss;
    }

    public void setImss(String imss) {
        this.imss = imss;
    }

//    public ESede getSede() {
//        return sede;
//    }
//
//    public void setSede(ESede sede) {
//        this.sede = sede;
//        this.idSede = sede != null ? sede.getId() : 0;
//    }
//
//    public Integer getIdSede() {
//        return idSede;
//    }
//
//    public void setIdSede(Integer idSede) {
//        this.sede = ESede.get(idSede);
//        this.idSede = idSede;
//    }
//
//    public EStatusEmpleado getStatus() {
//        return status;
//    }
//
//    public void setStatus(EStatusEmpleado status) {
//        this.status = status;
//        this.idStatus = status != null ? status.getId() : 0;
//    }
//
//    public Integer getIdStatus() {
//        return idStatus;
//    }
//
//    public void setIdStatus(Integer idStatus) {
//        this.status = EStatusEmpleado.get(idStatus);
//        this.idStatus = idStatus;
//    }

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

    public ETipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(ETipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
        this.idTipoContrato = tipoContrato != null ? tipoContrato.getId() : 0;
    }

    public Integer getIdTipoContrato() {
        return idTipoContrato;
    }

    public void setIdTipoContrato(Integer idTipoContrato) {
        this.tipoContrato = ETipoContrato.get(idTipoContrato);
       this.idTipoContrato = idTipoContrato;
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

    public ETipoSNI getTipoSNI() {
        return tipoSNI;
    }

    public void setTipoSNI(ETipoSNI tipoSNI) {
        this.tipoSNI = tipoSNI;
        this.idTipoSni = tipoSNI != null ? tipoSNI.getId() : 0;
    }

    public Integer getIdTipoSni() {
        return idTipoSni;
    }

    public void setIdTipoSni(Integer idTipoSni) {
        this.tipoSNI = ETipoSNI.get(idTipoSni);
        this.idTipoSni = idTipoSni;
    }

    public EmpleadoBase getJefe() {
        return jefe;
    }

    public void setJefe(EmpleadoBase jefe) {
        this.jefe = jefe;
    }

    public String getNumSni() {
        return numSni;
    }

    public void setNumSni(String numSni) {
        this.numSni = numSni;
    }

    public Date getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(Date fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public Date getFechaInicioContrato() {
        return fechaInicioContrato;
    }

    public void setFechaInicioContrato(Date fechaInicioContrato) {
        this.fechaInicioContrato = fechaInicioContrato;
    }

    public Date getFechaFinContrato() {
        return fechaFinContrato;
    }

    public void setFechaFinContrato(Date fechaFinContrato) {
        this.fechaFinContrato = fechaFinContrato;
    }

    public Date getFechaBaja() {
        return fechaBaja;
    }

    public void setFechaBaja(Date fechaBaja) {
        this.fechaBaja = fechaBaja;
    }

    public Date getFechaAntiguedad() {
        return fechaAntiguedad;
    }

    public void setFechaAntiguedad(Date fechaAntiguedad) {
        this.fechaAntiguedad = fechaAntiguedad;
    }

    public Date getFechaSni() {
        return fechaSni;
    }

    public void setFechaSni(Date fechaSni) {
        this.fechaSni = fechaSni;
    }

    public String getDireccionCalle() {
        return direccionCalle;
    }

    public void setDireccionCalle(String direccionCalle) {
        this.direccionCalle = direccionCalle;
    }

    public String getDireccionColonia() {
        return direccionColonia;
    }

    public void setDireccionColonia(String direccionColonia) {
        this.direccionColonia = direccionColonia;
    }

    public String getDireccionCP() {
        return direccionCP;
    }

    public void setDireccionCP(String direccionCP) {
        this.direccionCP = direccionCP;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmailPersonal() {
        return emailPersonal;
    }

    public void setEmailPersonal(String emailPersonal) {
        this.emailPersonal = emailPersonal;
    }

    public EEdoCivil getEdoCivil() {
        return edoCivil;
    }

    public void setEdoCivil(EEdoCivil edoCivil) {
        this.edoCivil = edoCivil;
        this.idEdoCivil = edoCivil != null ? edoCivil.getId() : 0;
    }

    public Integer getIdEdoCivil() {
        return idEdoCivil;
    }

    public void setIdEdoCivil(Integer idEdoCivil) {
        this.edoCivil = EEdoCivil.get(idEdoCivil);
        this.idEdoCivil = idEdoCivil;
    }
    
    public ESexo getSexo() {
        return sexo;
    }

    public void setSexo(ESexo sexo) {
        this.sexo = sexo;
        this.idSexo = sexo != null ? sexo.getId() : 0;
    }

    public Integer getIdSexo() {
        return idSexo;
    }

    public void setIdSexo(Integer idSexo) {
        this.sexo = ESexo.get(idSexo);
        this.idSexo = idSexo;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

        public String getUrlPhoto() {
        String cteUrlPhoto = "http://cimav.edu.mx/foto/";
        String varUrlPhoto = this.cuentaCimav == null || this.cuentaCimav.trim().isEmpty() 
                ? cteUrlPhoto + "default" : cteUrlPhoto + this.cuentaCimav;
        return this.urlPhoto;
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

    public Double getPorcenSegSeparacionInd() {
        return porcenSegSeparacionInd;
    }

    public void setPorcenSegSeparacionInd(Double porcenSegSeparacionInd) {
        this.porcenSegSeparacionInd = porcenSegSeparacionInd;
    }

    public ETipoPension getPensionTipo() {
        return pensionTipo;
    }

    public void setPensionTipo(ETipoPension pensionTipo) {
        this.pensionTipo = pensionTipo;
        this.pensionIdTipo = pensionTipo != null ? pensionTipo.getId() : 0;
    }

    public Integer getPensionIdTipo() {
        return pensionIdTipo;
    }

    public void setPensionIdTipo(Integer pensionIdTipo) {
        this.pensionTipo = ETipoPension.get(pensionIdTipo);
        this.pensionIdTipo = pensionIdTipo;
    }

    public Double getPensionPorcen() {
        return pensionPorcen;
    }

    public void setPensionPorcen(Double pensionPorcen) {
        this.pensionPorcen = pensionPorcen;
    }

    public String getPensionBeneficiario() {
        return pensionBeneficiario;
    }

    public void setPensionBeneficiario(String pensionBeneficiario) {
        this.pensionBeneficiario = pensionBeneficiario;
    }

    public EBanco getPensionBanco() {
        return pensionBanco;
    }

    public void setPensionBanco(EBanco pensionBanco) {
        this.pensionBanco = pensionBanco;
        this.pensionIdBanco = pensionBanco != null ? pensionBanco.getId() : 0;
    }

    public Integer getPensionIdBanco() {
        return pensionIdBanco;
    }

    public void setPensionIdBanco(Integer pensionIdBanco) {
        this.pensionBanco = EBanco.get(pensionIdBanco);
        this.pensionIdBanco = pensionIdBanco;
    }

    public String getPensionCuenta() {
        return pensionCuenta;
    }

    public void setPensionCuenta(String pensionCuenta) {
        this.pensionCuenta = pensionCuenta;
    }

    public ECreditoInfoTipo getRetCreditoInfonavitTipo() {
        return retCreditoInfonavitTipo;
    }

    public void setRetCreditoInfonavitTipo(ECreditoInfoTipo retCreditoInfonavitTipo) {
        this.retCreditoInfonavitTipo = retCreditoInfonavitTipo;
        this.retCreditoInfonavitIdTipo = retCreditoInfonavitTipo != null ? retCreditoInfonavitTipo.getId() : 0;
    }

    public Integer getRetCreditoInfonavitIdTipo() {
        return retCreditoInfonavitIdTipo;
    }

    public void setRetCreditoInfonavitIdTipo(Integer retCreditoInfonavitIdTipo) {
        this.retCreditoInfonavitTipo = ECreditoInfoTipo.get(retCreditoInfonavitIdTipo);
        this.retCreditoInfonavitIdTipo = retCreditoInfonavitIdTipo;
    }

    public BigDecimal getRetCreditoInfonavitValor() {
        return retCreditoInfonavitValor;
    }

    public void setRetCreditoInfonavitValor(BigDecimal retCreditoInfonavitValor) {
        this.retCreditoInfonavitValor = retCreditoInfonavitValor;
    }
    
    
}
