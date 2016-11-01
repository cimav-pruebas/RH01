package org.jboss.errai.databinding.client;

import cimav.client.data.domain.BaseDomain;
import cimav.client.data.domain.Departamento;
import cimav.client.data.domain.EBanco;
import cimav.client.data.domain.EClinica;
import cimav.client.data.domain.ECreditoInfoTipo;
import cimav.client.data.domain.EEdoCivil;
import cimav.client.data.domain.EGrupo;
import cimav.client.data.domain.ESede;
import cimav.client.data.domain.ESexo;
import cimav.client.data.domain.EStatusEmpleado;
import cimav.client.data.domain.ETipoAntiguedad;
import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.ETipoContrato;
import cimav.client.data.domain.ETipoEmpleado;
import cimav.client.data.domain.ETipoMovimiento;
import cimav.client.data.domain.ETipoPension;
import cimav.client.data.domain.ETipoSNI;
import cimav.client.data.domain.Empleado;
import cimav.client.data.domain.EmpleadoBase;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.Movimiento;
import cimav.client.data.domain.Nomina;
import cimav.client.data.domain.Tabulador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.jboss.errai.databinding.client.api.StateSync;

public class BindableProxyLoaderImpl implements BindableProxyLoader { public void loadBindableProxies() {
    class cimav_client_data_domain_TabuladorProxy extends Tabulador implements BindableProxy {
      private BindableProxyAgent<Tabulador> agent;
      public cimav_client_data_domain_TabuladorProxy() {
        this(new Tabulador());
      }

      public cimav_client_data_domain_TabuladorProxy(Tabulador target) {
        agent = new BindableProxyAgent<Tabulador>(this, target);
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("matDidacticos", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("cargaAdmin", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("sueldo", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("honorarios", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("compGarantizada", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("this", new PropertyType(Tabulador.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Tabulador unwrap() {
        return agent.target;
      }

      public Tabulador deepUnwrap() {
        final Tabulador clone = new Tabulador();
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setMatDidacticos(agent.target.getMatDidacticos());
        clone.setCode(agent.target.getCode());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setCargaAdmin(agent.target.getCargaAdmin());
        clone.setName(agent.target.getName());
        clone.setSueldo(agent.target.getSueldo());
        clone.setHonorarios(agent.target.getHonorarios());
        clone.setId(agent.target.getId());
        clone.setCompGarantizada(agent.target.getCompGarantizada());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_TabuladorProxy) {
          obj = ((cimav_client_data_domain_TabuladorProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent(false, "consecutivo", oldValue, consecutivo);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public BigDecimal getMatDidacticos() {
        return agent.target.getMatDidacticos();
      }

      public void setMatDidacticos(BigDecimal matDidacticos) {
        BigDecimal oldValue = agent.target.getMatDidacticos();
        agent.target.setMatDidacticos(matDidacticos);
        agent.updateWidgetsAndFireEvent(false, "matDidacticos", oldValue, matDidacticos);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent(false, "code", oldValue, code);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent(false, "isDirty", oldValue, isDirty);
      }

      public BigDecimal getCargaAdmin() {
        return agent.target.getCargaAdmin();
      }

      public void setCargaAdmin(BigDecimal cargaAdmin) {
        BigDecimal oldValue = agent.target.getCargaAdmin();
        agent.target.setCargaAdmin(cargaAdmin);
        agent.updateWidgetsAndFireEvent(false, "cargaAdmin", oldValue, cargaAdmin);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent(false, "name", oldValue, name);
      }

      public BigDecimal getSueldo() {
        return agent.target.getSueldo();
      }

      public void setSueldo(BigDecimal sueldo) {
        BigDecimal oldValue = agent.target.getSueldo();
        agent.target.setSueldo(sueldo);
        agent.updateWidgetsAndFireEvent(false, "sueldo", oldValue, sueldo);
      }

      public BigDecimal getHonorarios() {
        return agent.target.getHonorarios();
      }

      public void setHonorarios(BigDecimal honorarios) {
        BigDecimal oldValue = agent.target.getHonorarios();
        agent.target.setHonorarios(honorarios);
        agent.updateWidgetsAndFireEvent(false, "honorarios", oldValue, honorarios);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent(false, "id", oldValue, id);
      }

      public BigDecimal getCompGarantizada() {
        return agent.target.getCompGarantizada();
      }

      public void setCompGarantizada(BigDecimal compGarantizada) {
        BigDecimal oldValue = agent.target.getCompGarantizada();
        agent.target.setCompGarantizada(compGarantizada);
        agent.updateWidgetsAndFireEvent(false, "compGarantizada", oldValue, compGarantizada);
      }

      public Object get(String property) {
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("matDidacticos")) {
          return getMatDidacticos();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("cargaAdmin")) {
          return getCargaAdmin();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("sueldo")) {
          return getSueldo();
        }
        if (property.equals("honorarios")) {
          return getHonorarios();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("compGarantizada")) {
          return getCompGarantizada();
        }
        if (property.equals("this")) {
          return agent.target;
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("matDidacticos")) {
          agent.target.setMatDidacticos((BigDecimal) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("cargaAdmin")) {
          agent.target.setCargaAdmin((BigDecimal) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("sueldo")) {
          agent.target.setSueldo((BigDecimal) value);
          return;
        }
        if (property.equals("honorarios")) {
          agent.target.setHonorarios((BigDecimal) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("compGarantizada")) {
          agent.target.setCompGarantizada((BigDecimal) value);
          return;
        }
        if (property.equals("this")) {
          agent.target = (Tabulador) value;
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public Map getBeanProperties() {
        final Map props = new HashMap(agent.propertyTypes);
        props.remove("this");
        return Collections.unmodifiableMap(props);
      }

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public void becomesDirty() {
        agent.target.becomesDirty();
        agent.updateWidgetsAndFireEvents();
      }

      public void cleanDirty() {
        agent.target.cleanDirty();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(Tabulador.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model) {
        return new cimav_client_data_domain_TabuladorProxy((Tabulador) model);
      }
      public BindableProxy getBindableProxy() {
        return new cimav_client_data_domain_TabuladorProxy();
      }
    });
    class cimav_client_data_domain_DepartamentoProxy extends Departamento implements BindableProxy {
      private BindableProxyAgent<Departamento> agent;
      public cimav_client_data_domain_DepartamentoProxy() {
        this(new Departamento());
      }

      public cimav_client_data_domain_DepartamentoProxy(Departamento target) {
        agent = new BindableProxyAgent<Departamento>(this, target);
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("this", new PropertyType(Departamento.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Departamento unwrap() {
        return agent.target;
      }

      public Departamento deepUnwrap() {
        final Departamento clone = new Departamento();
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setCode(agent.target.getCode());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setName(agent.target.getName());
        clone.setId(agent.target.getId());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_DepartamentoProxy) {
          obj = ((cimav_client_data_domain_DepartamentoProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent(false, "consecutivo", oldValue, consecutivo);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent(false, "code", oldValue, code);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent(false, "isDirty", oldValue, isDirty);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent(false, "name", oldValue, name);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent(false, "id", oldValue, id);
      }

      public Object get(String property) {
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("this")) {
          return agent.target;
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("this")) {
          agent.target = (Departamento) value;
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public Map getBeanProperties() {
        final Map props = new HashMap(agent.propertyTypes);
        props.remove("this");
        return Collections.unmodifiableMap(props);
      }

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public void becomesDirty() {
        agent.target.becomesDirty();
        agent.updateWidgetsAndFireEvents();
      }

      public void cleanDirty() {
        agent.target.cleanDirty();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(Departamento.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model) {
        return new cimav_client_data_domain_DepartamentoProxy((Departamento) model);
      }
      public BindableProxy getBindableProxy() {
        return new cimav_client_data_domain_DepartamentoProxy();
      }
    });
    class cimav_client_data_domain_EmpleadoProxy extends Empleado implements BindableProxy {
      private BindableProxyAgent<Empleado> agent;
      public cimav_client_data_domain_EmpleadoProxy() {
        this(new Empleado());
      }

      public cimav_client_data_domain_EmpleadoProxy(Empleado target) {
        agent = new BindableProxyAgent<Empleado>(this, target);
        agent.propertyTypes.put("idBanco", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pensionTipo", new PropertyType(ETipoPension.class, false, false));
        agent.propertyTypes.put("grupo", new PropertyType(EGrupo.class, false, false));
        agent.propertyTypes.put("jefe", new PropertyType(EmpleadoBase.class, true, false));
        agent.propertyTypes.put("tipoEmpleado", new PropertyType(ETipoEmpleado.class, false, false));
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pantMonths", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("fechaFinContrato", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("pantDayEven", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("direccionColonia", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idEdoCivil", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pensionBeneficiario", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("telefono", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("curp", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("tipoAntiguedad", new PropertyType(ETipoAntiguedad.class, false, false));
        agent.propertyTypes.put("direccionCalle", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("direccionCP", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("pensionPorcen", new PropertyType(Double.class, false, false));
        agent.propertyTypes.put("pantYears", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("banco", new PropertyType(EBanco.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("rfc", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idTipoContrato", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("fechaIngreso", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("edoCivil", new PropertyType(EEdoCivil.class, false, false));
        agent.propertyTypes.put("imss", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("clinica", new PropertyType(EClinica.class, false, false));
        agent.propertyTypes.put("cuentaBanco", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("retCreditoInfonavitValor", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("idGrupo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("apellidoPaterno", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaBaja", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("pensionIdBanco", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("emailPersonal", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaInicioContrato", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("fechaNacimiento", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("estimulosProductividad", new PropertyType(Double.class, false, false));
        agent.propertyTypes.put("tipoSNI", new PropertyType(ETipoSNI.class, false, false));
        agent.propertyTypes.put("nombre", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("apellidoMaterno", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("numSni", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("retCreditoInfonavitTipo", new PropertyType(ECreditoInfoTipo.class, false, false));
        agent.propertyTypes.put("idClinica", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idSexo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("fechaSni", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("idTipoEmpleado", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("tipoContrato", new PropertyType(ETipoContrato.class, false, false));
        agent.propertyTypes.put("pensionIdTipo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idSede", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("idTipoSni", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("retCreditoInfonavitIdTipo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pensionCuenta", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("pantDayOdd", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idTipoAntiguedad", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("sexo", new PropertyType(ESexo.class, false, false));
        agent.propertyTypes.put("porcenSegSeparacionInd", new PropertyType(Double.class, false, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("pensionBanco", new PropertyType(EBanco.class, false, false));
        agent.propertyTypes.put("this", new PropertyType(Empleado.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Empleado unwrap() {
        return agent.target;
      }

      public Empleado deepUnwrap() {
        final Empleado clone = new Empleado();
        clone.setIdBanco(agent.target.getIdBanco());
        clone.setPensionTipo(agent.target.getPensionTipo());
        clone.setGrupo(agent.target.getGrupo());
        if (agent.target.getJefe() instanceof BindableProxy) {
          clone.setJefe((EmpleadoBase) ((BindableProxy) getJefe()).deepUnwrap());
        } else {
          clone.setJefe(agent.target.getJefe());
        }
        clone.setTipoEmpleado(agent.target.getTipoEmpleado());
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setPantMonths(agent.target.getPantMonths());
        clone.setFechaFinContrato(agent.target.getFechaFinContrato());
        clone.setPantDayEven(agent.target.getPantDayEven());
        clone.setDireccionColonia(agent.target.getDireccionColonia());
        clone.setIdEdoCivil(agent.target.getIdEdoCivil());
        clone.setPensionBeneficiario(agent.target.getPensionBeneficiario());
        clone.setId(agent.target.getId());
        clone.setTelefono(agent.target.getTelefono());
        clone.setCurp(agent.target.getCurp());
        clone.setTipoAntiguedad(agent.target.getTipoAntiguedad());
        clone.setDireccionCalle(agent.target.getDireccionCalle());
        clone.setDireccionCP(agent.target.getDireccionCP());
        clone.setPensionPorcen(agent.target.getPensionPorcen());
        clone.setPantYears(agent.target.getPantYears());
        clone.setBanco(agent.target.getBanco());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        clone.setRfc(agent.target.getRfc());
        clone.setIdTipoContrato(agent.target.getIdTipoContrato());
        clone.setFechaIngreso(agent.target.getFechaIngreso());
        clone.setEdoCivil(agent.target.getEdoCivil());
        clone.setImss(agent.target.getImss());
        clone.setClinica(agent.target.getClinica());
        clone.setCuentaBanco(agent.target.getCuentaBanco());
        clone.setName(agent.target.getName());
        clone.setRetCreditoInfonavitValor(agent.target.getRetCreditoInfonavitValor());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
        clone.setStatus(agent.target.getStatus());
        clone.setIdGrupo(agent.target.getIdGrupo());
        clone.setApellidoPaterno(agent.target.getApellidoPaterno());
        clone.setFechaBaja(agent.target.getFechaBaja());
        clone.setPensionIdBanco(agent.target.getPensionIdBanco());
        clone.setEmailPersonal(agent.target.getEmailPersonal());
        clone.setCode(agent.target.getCode());
        clone.setFechaInicioContrato(agent.target.getFechaInicioContrato());
        clone.setFechaNacimiento(agent.target.getFechaNacimiento());
        clone.setEstimulosProductividad(agent.target.getEstimulosProductividad());
        clone.setTipoSNI(agent.target.getTipoSNI());
        clone.setNombre(agent.target.getNombre());
        clone.setApellidoMaterno(agent.target.getApellidoMaterno());
        clone.setNumSni(agent.target.getNumSni());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        clone.setRetCreditoInfonavitTipo(agent.target.getRetCreditoInfonavitTipo());
        clone.setIdClinica(agent.target.getIdClinica());
        clone.setIdSexo(agent.target.getIdSexo());
        clone.setFechaSni(agent.target.getFechaSni());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setIdTipoEmpleado(agent.target.getIdTipoEmpleado());
        clone.setTipoContrato(agent.target.getTipoContrato());
        clone.setPensionIdTipo(agent.target.getPensionIdTipo());
        clone.setIdSede(agent.target.getIdSede());
        clone.setIdTipoSni(agent.target.getIdTipoSni());
        clone.setSede(agent.target.getSede());
        clone.setRetCreditoInfonavitIdTipo(agent.target.getRetCreditoInfonavitIdTipo());
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setPensionCuenta(agent.target.getPensionCuenta());
        clone.setPantDayOdd(agent.target.getPantDayOdd());
        clone.setIdTipoAntiguedad(agent.target.getIdTipoAntiguedad());
        if (agent.target.getDepartamento() instanceof BindableProxy) {
          clone.setDepartamento((Departamento) ((BindableProxy) getDepartamento()).deepUnwrap());
        } else {
          clone.setDepartamento(agent.target.getDepartamento());
        }
        clone.setSexo(agent.target.getSexo());
        clone.setPorcenSegSeparacionInd(agent.target.getPorcenSegSeparacionInd());
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        clone.setPensionBanco(agent.target.getPensionBanco());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_EmpleadoProxy) {
          obj = ((cimav_client_data_domain_EmpleadoProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Integer getIdBanco() {
        return agent.target.getIdBanco();
      }

      public void setIdBanco(Integer idBanco) {
        Integer oldValue = agent.target.getIdBanco();
        agent.target.setIdBanco(idBanco);
        agent.updateWidgetsAndFireEvent(false, "idBanco", oldValue, idBanco);
      }

      public ETipoPension getPensionTipo() {
        return agent.target.getPensionTipo();
      }

      public void setPensionTipo(ETipoPension pensionTipo) {
        ETipoPension oldValue = agent.target.getPensionTipo();
        agent.target.setPensionTipo(pensionTipo);
        agent.updateWidgetsAndFireEvent(false, "pensionTipo", oldValue, pensionTipo);
      }

      public EGrupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(EGrupo grupo) {
        EGrupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent(false, "grupo", oldValue, grupo);
      }

      public EmpleadoBase getJefe() {
        return agent.target.getJefe();
      }

      public void setJefe(EmpleadoBase jefe) {
        if (agent.binders.containsKey("jefe")) {
          jefe = (EmpleadoBase) agent.binders.get("jefe").setModel(jefe, StateSync.FROM_MODEL, true);
        }
        EmpleadoBase oldValue = agent.target.getJefe();
        agent.target.setJefe(jefe);
        agent.updateWidgetsAndFireEvent(false, "jefe", oldValue, jefe);
      }

      public ETipoEmpleado getTipoEmpleado() {
        return agent.target.getTipoEmpleado();
      }

      public void setTipoEmpleado(ETipoEmpleado tipoEmpleado) {
        ETipoEmpleado oldValue = agent.target.getTipoEmpleado();
        agent.target.setTipoEmpleado(tipoEmpleado);
        agent.updateWidgetsAndFireEvent(false, "tipoEmpleado", oldValue, tipoEmpleado);
      }

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent(false, "idStatus", oldValue, idStatus);
      }

      public Integer getPantMonths() {
        return agent.target.getPantMonths();
      }

      public void setPantMonths(Integer pantMonths) {
        Integer oldValue = agent.target.getPantMonths();
        agent.target.setPantMonths(pantMonths);
        agent.updateWidgetsAndFireEvent(false, "pantMonths", oldValue, pantMonths);
      }

      public Date getFechaFinContrato() {
        return agent.target.getFechaFinContrato();
      }

      public void setFechaFinContrato(Date fechaFinContrato) {
        Date oldValue = agent.target.getFechaFinContrato();
        agent.target.setFechaFinContrato(fechaFinContrato);
        agent.updateWidgetsAndFireEvent(false, "fechaFinContrato", oldValue, fechaFinContrato);
      }

      public Integer getPantDayEven() {
        return agent.target.getPantDayEven();
      }

      public void setPantDayEven(Integer pantDayEven) {
        Integer oldValue = agent.target.getPantDayEven();
        agent.target.setPantDayEven(pantDayEven);
        agent.updateWidgetsAndFireEvent(false, "pantDayEven", oldValue, pantDayEven);
      }

      public String getDireccionColonia() {
        return agent.target.getDireccionColonia();
      }

      public void setDireccionColonia(String direccionColonia) {
        String oldValue = agent.target.getDireccionColonia();
        agent.target.setDireccionColonia(direccionColonia);
        agent.updateWidgetsAndFireEvent(false, "direccionColonia", oldValue, direccionColonia);
      }

      public Integer getIdEdoCivil() {
        return agent.target.getIdEdoCivil();
      }

      public void setIdEdoCivil(Integer idEdoCivil) {
        Integer oldValue = agent.target.getIdEdoCivil();
        agent.target.setIdEdoCivil(idEdoCivil);
        agent.updateWidgetsAndFireEvent(false, "idEdoCivil", oldValue, idEdoCivil);
      }

      public String getPensionBeneficiario() {
        return agent.target.getPensionBeneficiario();
      }

      public void setPensionBeneficiario(String pensionBeneficiario) {
        String oldValue = agent.target.getPensionBeneficiario();
        agent.target.setPensionBeneficiario(pensionBeneficiario);
        agent.updateWidgetsAndFireEvent(false, "pensionBeneficiario", oldValue, pensionBeneficiario);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent(false, "id", oldValue, id);
      }

      public String getTelefono() {
        return agent.target.getTelefono();
      }

      public void setTelefono(String telefono) {
        String oldValue = agent.target.getTelefono();
        agent.target.setTelefono(telefono);
        agent.updateWidgetsAndFireEvent(false, "telefono", oldValue, telefono);
      }

      public String getCurp() {
        return agent.target.getCurp();
      }

      public void setCurp(String curp) {
        String oldValue = agent.target.getCurp();
        agent.target.setCurp(curp);
        agent.updateWidgetsAndFireEvent(false, "curp", oldValue, curp);
      }

      public ETipoAntiguedad getTipoAntiguedad() {
        return agent.target.getTipoAntiguedad();
      }

      public void setTipoAntiguedad(ETipoAntiguedad tipoAntiguedad) {
        ETipoAntiguedad oldValue = agent.target.getTipoAntiguedad();
        agent.target.setTipoAntiguedad(tipoAntiguedad);
        agent.updateWidgetsAndFireEvent(false, "tipoAntiguedad", oldValue, tipoAntiguedad);
      }

      public String getDireccionCalle() {
        return agent.target.getDireccionCalle();
      }

      public void setDireccionCalle(String direccionCalle) {
        String oldValue = agent.target.getDireccionCalle();
        agent.target.setDireccionCalle(direccionCalle);
        agent.updateWidgetsAndFireEvent(false, "direccionCalle", oldValue, direccionCalle);
      }

      public String getDireccionCP() {
        return agent.target.getDireccionCP();
      }

      public void setDireccionCP(String direccionCP) {
        String oldValue = agent.target.getDireccionCP();
        agent.target.setDireccionCP(direccionCP);
        agent.updateWidgetsAndFireEvent(false, "direccionCP", oldValue, direccionCP);
      }

      public Double getPensionPorcen() {
        return agent.target.getPensionPorcen();
      }

      public void setPensionPorcen(Double pensionPorcen) {
        Double oldValue = agent.target.getPensionPorcen();
        agent.target.setPensionPorcen(pensionPorcen);
        agent.updateWidgetsAndFireEvent(false, "pensionPorcen", oldValue, pensionPorcen);
      }

      public Integer getPantYears() {
        return agent.target.getPantYears();
      }

      public void setPantYears(Integer pantYears) {
        Integer oldValue = agent.target.getPantYears();
        agent.target.setPantYears(pantYears);
        agent.updateWidgetsAndFireEvent(false, "pantYears", oldValue, pantYears);
      }

      public EBanco getBanco() {
        return agent.target.getBanco();
      }

      public void setBanco(EBanco banco) {
        EBanco oldValue = agent.target.getBanco();
        agent.target.setBanco(banco);
        agent.updateWidgetsAndFireEvent(false, "banco", oldValue, banco);
      }

      public String getCuentaCimav() {
        return agent.target.getCuentaCimav();
      }

      public void setCuentaCimav(String cuentaCimav) {
        String oldValue = agent.target.getCuentaCimav();
        agent.target.setCuentaCimav(cuentaCimav);
        agent.updateWidgetsAndFireEvent(false, "cuentaCimav", oldValue, cuentaCimav);
      }

      public String getRfc() {
        return agent.target.getRfc();
      }

      public void setRfc(String rfc) {
        String oldValue = agent.target.getRfc();
        agent.target.setRfc(rfc);
        agent.updateWidgetsAndFireEvent(false, "rfc", oldValue, rfc);
      }

      public Integer getIdTipoContrato() {
        return agent.target.getIdTipoContrato();
      }

      public void setIdTipoContrato(Integer idTipoContrato) {
        Integer oldValue = agent.target.getIdTipoContrato();
        agent.target.setIdTipoContrato(idTipoContrato);
        agent.updateWidgetsAndFireEvent(false, "idTipoContrato", oldValue, idTipoContrato);
      }

      public Date getFechaIngreso() {
        return agent.target.getFechaIngreso();
      }

      public void setFechaIngreso(Date fechaIngreso) {
        Date oldValue = agent.target.getFechaIngreso();
        agent.target.setFechaIngreso(fechaIngreso);
        agent.updateWidgetsAndFireEvent(false, "fechaIngreso", oldValue, fechaIngreso);
      }

      public EEdoCivil getEdoCivil() {
        return agent.target.getEdoCivil();
      }

      public void setEdoCivil(EEdoCivil edoCivil) {
        EEdoCivil oldValue = agent.target.getEdoCivil();
        agent.target.setEdoCivil(edoCivil);
        agent.updateWidgetsAndFireEvent(false, "edoCivil", oldValue, edoCivil);
      }

      public String getImss() {
        return agent.target.getImss();
      }

      public void setImss(String imss) {
        String oldValue = agent.target.getImss();
        agent.target.setImss(imss);
        agent.updateWidgetsAndFireEvent(false, "imss", oldValue, imss);
      }

      public EClinica getClinica() {
        return agent.target.getClinica();
      }

      public void setClinica(EClinica clinica) {
        EClinica oldValue = agent.target.getClinica();
        agent.target.setClinica(clinica);
        agent.updateWidgetsAndFireEvent(false, "clinica", oldValue, clinica);
      }

      public String getCuentaBanco() {
        return agent.target.getCuentaBanco();
      }

      public void setCuentaBanco(String cuentaBanco) {
        String oldValue = agent.target.getCuentaBanco();
        agent.target.setCuentaBanco(cuentaBanco);
        agent.updateWidgetsAndFireEvent(false, "cuentaBanco", oldValue, cuentaBanco);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent(false, "name", oldValue, name);
      }

      public BigDecimal getRetCreditoInfonavitValor() {
        return agent.target.getRetCreditoInfonavitValor();
      }

      public void setRetCreditoInfonavitValor(BigDecimal retCreditoInfonavitValor) {
        BigDecimal oldValue = agent.target.getRetCreditoInfonavitValor();
        agent.target.setRetCreditoInfonavitValor(retCreditoInfonavitValor);
        agent.updateWidgetsAndFireEvent(false, "retCreditoInfonavitValor", oldValue, retCreditoInfonavitValor);
      }

      public Tabulador getNivel() {
        return agent.target.getNivel();
      }

      public void setNivel(Tabulador nivel) {
        if (agent.binders.containsKey("nivel")) {
          nivel = (Tabulador) agent.binders.get("nivel").setModel(nivel, StateSync.FROM_MODEL, true);
        }
        Tabulador oldValue = agent.target.getNivel();
        agent.target.setNivel(nivel);
        agent.updateWidgetsAndFireEvent(false, "nivel", oldValue, nivel);
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public void setStatus(EStatusEmpleado status) {
        EStatusEmpleado oldValue = agent.target.getStatus();
        agent.target.setStatus(status);
        agent.updateWidgetsAndFireEvent(false, "status", oldValue, status);
      }

      public Integer getIdGrupo() {
        return agent.target.getIdGrupo();
      }

      public void setIdGrupo(Integer idGrupo) {
        Integer oldValue = agent.target.getIdGrupo();
        agent.target.setIdGrupo(idGrupo);
        agent.updateWidgetsAndFireEvent(false, "idGrupo", oldValue, idGrupo);
      }

      public String getApellidoPaterno() {
        return agent.target.getApellidoPaterno();
      }

      public void setApellidoPaterno(String apellidoPaterno) {
        String oldValue = agent.target.getApellidoPaterno();
        agent.target.setApellidoPaterno(apellidoPaterno);
        agent.updateWidgetsAndFireEvent(false, "apellidoPaterno", oldValue, apellidoPaterno);
      }

      public Date getFechaBaja() {
        return agent.target.getFechaBaja();
      }

      public void setFechaBaja(Date fechaBaja) {
        Date oldValue = agent.target.getFechaBaja();
        agent.target.setFechaBaja(fechaBaja);
        agent.updateWidgetsAndFireEvent(false, "fechaBaja", oldValue, fechaBaja);
      }

      public Integer getPensionIdBanco() {
        return agent.target.getPensionIdBanco();
      }

      public void setPensionIdBanco(Integer pensionIdBanco) {
        Integer oldValue = agent.target.getPensionIdBanco();
        agent.target.setPensionIdBanco(pensionIdBanco);
        agent.updateWidgetsAndFireEvent(false, "pensionIdBanco", oldValue, pensionIdBanco);
      }

      public String getEmailPersonal() {
        return agent.target.getEmailPersonal();
      }

      public void setEmailPersonal(String emailPersonal) {
        String oldValue = agent.target.getEmailPersonal();
        agent.target.setEmailPersonal(emailPersonal);
        agent.updateWidgetsAndFireEvent(false, "emailPersonal", oldValue, emailPersonal);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent(false, "code", oldValue, code);
      }

      public Date getFechaInicioContrato() {
        return agent.target.getFechaInicioContrato();
      }

      public void setFechaInicioContrato(Date fechaInicioContrato) {
        Date oldValue = agent.target.getFechaInicioContrato();
        agent.target.setFechaInicioContrato(fechaInicioContrato);
        agent.updateWidgetsAndFireEvent(false, "fechaInicioContrato", oldValue, fechaInicioContrato);
      }

      public Date getFechaNacimiento() {
        return agent.target.getFechaNacimiento();
      }

      public void setFechaNacimiento(Date fechaNacimiento) {
        Date oldValue = agent.target.getFechaNacimiento();
        agent.target.setFechaNacimiento(fechaNacimiento);
        agent.updateWidgetsAndFireEvent(false, "fechaNacimiento", oldValue, fechaNacimiento);
      }

      public Double getEstimulosProductividad() {
        return agent.target.getEstimulosProductividad();
      }

      public void setEstimulosProductividad(Double estimulosProductividad) {
        Double oldValue = agent.target.getEstimulosProductividad();
        agent.target.setEstimulosProductividad(estimulosProductividad);
        agent.updateWidgetsAndFireEvent(false, "estimulosProductividad", oldValue, estimulosProductividad);
      }

      public ETipoSNI getTipoSNI() {
        return agent.target.getTipoSNI();
      }

      public void setTipoSNI(ETipoSNI tipoSNI) {
        ETipoSNI oldValue = agent.target.getTipoSNI();
        agent.target.setTipoSNI(tipoSNI);
        agent.updateWidgetsAndFireEvent(false, "tipoSNI", oldValue, tipoSNI);
      }

      public String getNombre() {
        return agent.target.getNombre();
      }

      public void setNombre(String nombre) {
        String oldValue = agent.target.getNombre();
        agent.target.setNombre(nombre);
        agent.updateWidgetsAndFireEvent(false, "nombre", oldValue, nombre);
      }

      public String getApellidoMaterno() {
        return agent.target.getApellidoMaterno();
      }

      public void setApellidoMaterno(String apellidoMaterno) {
        String oldValue = agent.target.getApellidoMaterno();
        agent.target.setApellidoMaterno(apellidoMaterno);
        agent.updateWidgetsAndFireEvent(false, "apellidoMaterno", oldValue, apellidoMaterno);
      }

      public String getNumSni() {
        return agent.target.getNumSni();
      }

      public void setNumSni(String numSni) {
        String oldValue = agent.target.getNumSni();
        agent.target.setNumSni(numSni);
        agent.updateWidgetsAndFireEvent(false, "numSni", oldValue, numSni);
      }

      public String getUrlPhoto() {
        return agent.target.getUrlPhoto();
      }

      public void setUrlPhoto(String urlPhoto) {
        String oldValue = agent.target.getUrlPhoto();
        agent.target.setUrlPhoto(urlPhoto);
        agent.updateWidgetsAndFireEvent(false, "urlPhoto", oldValue, urlPhoto);
      }

      public ECreditoInfoTipo getRetCreditoInfonavitTipo() {
        return agent.target.getRetCreditoInfonavitTipo();
      }

      public void setRetCreditoInfonavitTipo(ECreditoInfoTipo retCreditoInfonavitTipo) {
        ECreditoInfoTipo oldValue = agent.target.getRetCreditoInfonavitTipo();
        agent.target.setRetCreditoInfonavitTipo(retCreditoInfonavitTipo);
        agent.updateWidgetsAndFireEvent(false, "retCreditoInfonavitTipo", oldValue, retCreditoInfonavitTipo);
      }

      public Integer getIdClinica() {
        return agent.target.getIdClinica();
      }

      public void setIdClinica(Integer idClinica) {
        Integer oldValue = agent.target.getIdClinica();
        agent.target.setIdClinica(idClinica);
        agent.updateWidgetsAndFireEvent(false, "idClinica", oldValue, idClinica);
      }

      public Integer getIdSexo() {
        return agent.target.getIdSexo();
      }

      public void setIdSexo(Integer idSexo) {
        Integer oldValue = agent.target.getIdSexo();
        agent.target.setIdSexo(idSexo);
        agent.updateWidgetsAndFireEvent(false, "idSexo", oldValue, idSexo);
      }

      public Date getFechaSni() {
        return agent.target.getFechaSni();
      }

      public void setFechaSni(Date fechaSni) {
        Date oldValue = agent.target.getFechaSni();
        agent.target.setFechaSni(fechaSni);
        agent.updateWidgetsAndFireEvent(false, "fechaSni", oldValue, fechaSni);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent(false, "isDirty", oldValue, isDirty);
      }

      public Integer getIdTipoEmpleado() {
        return agent.target.getIdTipoEmpleado();
      }

      public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        Integer oldValue = agent.target.getIdTipoEmpleado();
        agent.target.setIdTipoEmpleado(idTipoEmpleado);
        agent.updateWidgetsAndFireEvent(false, "idTipoEmpleado", oldValue, idTipoEmpleado);
      }

      public ETipoContrato getTipoContrato() {
        return agent.target.getTipoContrato();
      }

      public void setTipoContrato(ETipoContrato tipoContrato) {
        ETipoContrato oldValue = agent.target.getTipoContrato();
        agent.target.setTipoContrato(tipoContrato);
        agent.updateWidgetsAndFireEvent(false, "tipoContrato", oldValue, tipoContrato);
      }

      public Integer getPensionIdTipo() {
        return agent.target.getPensionIdTipo();
      }

      public void setPensionIdTipo(Integer pensionIdTipo) {
        Integer oldValue = agent.target.getPensionIdTipo();
        agent.target.setPensionIdTipo(pensionIdTipo);
        agent.updateWidgetsAndFireEvent(false, "pensionIdTipo", oldValue, pensionIdTipo);
      }

      public Integer getIdSede() {
        return agent.target.getIdSede();
      }

      public void setIdSede(Integer idSede) {
        Integer oldValue = agent.target.getIdSede();
        agent.target.setIdSede(idSede);
        agent.updateWidgetsAndFireEvent(false, "idSede", oldValue, idSede);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public Integer getIdTipoSni() {
        return agent.target.getIdTipoSni();
      }

      public void setIdTipoSni(Integer idTipoSni) {
        Integer oldValue = agent.target.getIdTipoSni();
        agent.target.setIdTipoSni(idTipoSni);
        agent.updateWidgetsAndFireEvent(false, "idTipoSni", oldValue, idTipoSni);
      }

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent(false, "sede", oldValue, sede);
      }

      public Integer getRetCreditoInfonavitIdTipo() {
        return agent.target.getRetCreditoInfonavitIdTipo();
      }

      public void setRetCreditoInfonavitIdTipo(Integer retCreditoInfonavitIdTipo) {
        Integer oldValue = agent.target.getRetCreditoInfonavitIdTipo();
        agent.target.setRetCreditoInfonavitIdTipo(retCreditoInfonavitIdTipo);
        agent.updateWidgetsAndFireEvent(false, "retCreditoInfonavitIdTipo", oldValue, retCreditoInfonavitIdTipo);
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent(false, "consecutivo", oldValue, consecutivo);
      }

      public String getPensionCuenta() {
        return agent.target.getPensionCuenta();
      }

      public void setPensionCuenta(String pensionCuenta) {
        String oldValue = agent.target.getPensionCuenta();
        agent.target.setPensionCuenta(pensionCuenta);
        agent.updateWidgetsAndFireEvent(false, "pensionCuenta", oldValue, pensionCuenta);
      }

      public Integer getPantDayOdd() {
        return agent.target.getPantDayOdd();
      }

      public void setPantDayOdd(Integer pantDayOdd) {
        Integer oldValue = agent.target.getPantDayOdd();
        agent.target.setPantDayOdd(pantDayOdd);
        agent.updateWidgetsAndFireEvent(false, "pantDayOdd", oldValue, pantDayOdd);
      }

      public Integer getIdTipoAntiguedad() {
        return agent.target.getIdTipoAntiguedad();
      }

      public void setIdTipoAntiguedad(Integer idTipoAntiguedad) {
        Integer oldValue = agent.target.getIdTipoAntiguedad();
        agent.target.setIdTipoAntiguedad(idTipoAntiguedad);
        agent.updateWidgetsAndFireEvent(false, "idTipoAntiguedad", oldValue, idTipoAntiguedad);
      }

      public Departamento getDepartamento() {
        return agent.target.getDepartamento();
      }

      public void setDepartamento(Departamento departamento) {
        if (agent.binders.containsKey("departamento")) {
          departamento = (Departamento) agent.binders.get("departamento").setModel(departamento, StateSync.FROM_MODEL, true);
        }
        Departamento oldValue = agent.target.getDepartamento();
        agent.target.setDepartamento(departamento);
        agent.updateWidgetsAndFireEvent(false, "departamento", oldValue, departamento);
      }

      public ESexo getSexo() {
        return agent.target.getSexo();
      }

      public void setSexo(ESexo sexo) {
        ESexo oldValue = agent.target.getSexo();
        agent.target.setSexo(sexo);
        agent.updateWidgetsAndFireEvent(false, "sexo", oldValue, sexo);
      }

      public Double getPorcenSegSeparacionInd() {
        return agent.target.getPorcenSegSeparacionInd();
      }

      public void setPorcenSegSeparacionInd(Double porcenSegSeparacionInd) {
        Double oldValue = agent.target.getPorcenSegSeparacionInd();
        agent.target.setPorcenSegSeparacionInd(porcenSegSeparacionInd);
        agent.updateWidgetsAndFireEvent(false, "porcenSegSeparacionInd", oldValue, porcenSegSeparacionInd);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent(false, "fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public EBanco getPensionBanco() {
        return agent.target.getPensionBanco();
      }

      public void setPensionBanco(EBanco pensionBanco) {
        EBanco oldValue = agent.target.getPensionBanco();
        agent.target.setPensionBanco(pensionBanco);
        agent.updateWidgetsAndFireEvent(false, "pensionBanco", oldValue, pensionBanco);
      }

      public Object get(String property) {
        if (property.equals("idBanco")) {
          return getIdBanco();
        }
        if (property.equals("pensionTipo")) {
          return getPensionTipo();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("jefe")) {
          return getJefe();
        }
        if (property.equals("tipoEmpleado")) {
          return getTipoEmpleado();
        }
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("pantMonths")) {
          return getPantMonths();
        }
        if (property.equals("fechaFinContrato")) {
          return getFechaFinContrato();
        }
        if (property.equals("pantDayEven")) {
          return getPantDayEven();
        }
        if (property.equals("direccionColonia")) {
          return getDireccionColonia();
        }
        if (property.equals("idEdoCivil")) {
          return getIdEdoCivil();
        }
        if (property.equals("pensionBeneficiario")) {
          return getPensionBeneficiario();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("telefono")) {
          return getTelefono();
        }
        if (property.equals("curp")) {
          return getCurp();
        }
        if (property.equals("tipoAntiguedad")) {
          return getTipoAntiguedad();
        }
        if (property.equals("direccionCalle")) {
          return getDireccionCalle();
        }
        if (property.equals("direccionCP")) {
          return getDireccionCP();
        }
        if (property.equals("pensionPorcen")) {
          return getPensionPorcen();
        }
        if (property.equals("pantYears")) {
          return getPantYears();
        }
        if (property.equals("banco")) {
          return getBanco();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("rfc")) {
          return getRfc();
        }
        if (property.equals("idTipoContrato")) {
          return getIdTipoContrato();
        }
        if (property.equals("fechaIngreso")) {
          return getFechaIngreso();
        }
        if (property.equals("edoCivil")) {
          return getEdoCivil();
        }
        if (property.equals("imss")) {
          return getImss();
        }
        if (property.equals("clinica")) {
          return getClinica();
        }
        if (property.equals("cuentaBanco")) {
          return getCuentaBanco();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("retCreditoInfonavitValor")) {
          return getRetCreditoInfonavitValor();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("idGrupo")) {
          return getIdGrupo();
        }
        if (property.equals("apellidoPaterno")) {
          return getApellidoPaterno();
        }
        if (property.equals("fechaBaja")) {
          return getFechaBaja();
        }
        if (property.equals("pensionIdBanco")) {
          return getPensionIdBanco();
        }
        if (property.equals("emailPersonal")) {
          return getEmailPersonal();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("fechaInicioContrato")) {
          return getFechaInicioContrato();
        }
        if (property.equals("fechaNacimiento")) {
          return getFechaNacimiento();
        }
        if (property.equals("estimulosProductividad")) {
          return getEstimulosProductividad();
        }
        if (property.equals("tipoSNI")) {
          return getTipoSNI();
        }
        if (property.equals("nombre")) {
          return getNombre();
        }
        if (property.equals("apellidoMaterno")) {
          return getApellidoMaterno();
        }
        if (property.equals("numSni")) {
          return getNumSni();
        }
        if (property.equals("urlPhoto")) {
          return getUrlPhoto();
        }
        if (property.equals("retCreditoInfonavitTipo")) {
          return getRetCreditoInfonavitTipo();
        }
        if (property.equals("idClinica")) {
          return getIdClinica();
        }
        if (property.equals("idSexo")) {
          return getIdSexo();
        }
        if (property.equals("fechaSni")) {
          return getFechaSni();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("idTipoEmpleado")) {
          return getIdTipoEmpleado();
        }
        if (property.equals("tipoContrato")) {
          return getTipoContrato();
        }
        if (property.equals("pensionIdTipo")) {
          return getPensionIdTipo();
        }
        if (property.equals("idSede")) {
          return getIdSede();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("idTipoSni")) {
          return getIdTipoSni();
        }
        if (property.equals("sede")) {
          return getSede();
        }
        if (property.equals("retCreditoInfonavitIdTipo")) {
          return getRetCreditoInfonavitIdTipo();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("pensionCuenta")) {
          return getPensionCuenta();
        }
        if (property.equals("pantDayOdd")) {
          return getPantDayOdd();
        }
        if (property.equals("idTipoAntiguedad")) {
          return getIdTipoAntiguedad();
        }
        if (property.equals("departamento")) {
          return getDepartamento();
        }
        if (property.equals("sexo")) {
          return getSexo();
        }
        if (property.equals("porcenSegSeparacionInd")) {
          return getPorcenSegSeparacionInd();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("pensionBanco")) {
          return getPensionBanco();
        }
        if (property.equals("this")) {
          return agent.target;
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("idBanco")) {
          agent.target.setIdBanco((Integer) value);
          return;
        }
        if (property.equals("pensionTipo")) {
          agent.target.setPensionTipo((ETipoPension) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((EGrupo) value);
          return;
        }
        if (property.equals("jefe")) {
          agent.target.setJefe((EmpleadoBase) value);
          return;
        }
        if (property.equals("tipoEmpleado")) {
          agent.target.setTipoEmpleado((ETipoEmpleado) value);
          return;
        }
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("pantMonths")) {
          agent.target.setPantMonths((Integer) value);
          return;
        }
        if (property.equals("fechaFinContrato")) {
          agent.target.setFechaFinContrato((Date) value);
          return;
        }
        if (property.equals("pantDayEven")) {
          agent.target.setPantDayEven((Integer) value);
          return;
        }
        if (property.equals("direccionColonia")) {
          agent.target.setDireccionColonia((String) value);
          return;
        }
        if (property.equals("idEdoCivil")) {
          agent.target.setIdEdoCivil((Integer) value);
          return;
        }
        if (property.equals("pensionBeneficiario")) {
          agent.target.setPensionBeneficiario((String) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("telefono")) {
          agent.target.setTelefono((String) value);
          return;
        }
        if (property.equals("curp")) {
          agent.target.setCurp((String) value);
          return;
        }
        if (property.equals("tipoAntiguedad")) {
          agent.target.setTipoAntiguedad((ETipoAntiguedad) value);
          return;
        }
        if (property.equals("direccionCalle")) {
          agent.target.setDireccionCalle((String) value);
          return;
        }
        if (property.equals("direccionCP")) {
          agent.target.setDireccionCP((String) value);
          return;
        }
        if (property.equals("pensionPorcen")) {
          agent.target.setPensionPorcen((Double) value);
          return;
        }
        if (property.equals("pantYears")) {
          agent.target.setPantYears((Integer) value);
          return;
        }
        if (property.equals("banco")) {
          agent.target.setBanco((EBanco) value);
          return;
        }
        if (property.equals("cuentaCimav")) {
          agent.target.setCuentaCimav((String) value);
          return;
        }
        if (property.equals("rfc")) {
          agent.target.setRfc((String) value);
          return;
        }
        if (property.equals("idTipoContrato")) {
          agent.target.setIdTipoContrato((Integer) value);
          return;
        }
        if (property.equals("fechaIngreso")) {
          agent.target.setFechaIngreso((Date) value);
          return;
        }
        if (property.equals("edoCivil")) {
          agent.target.setEdoCivil((EEdoCivil) value);
          return;
        }
        if (property.equals("imss")) {
          agent.target.setImss((String) value);
          return;
        }
        if (property.equals("clinica")) {
          agent.target.setClinica((EClinica) value);
          return;
        }
        if (property.equals("cuentaBanco")) {
          agent.target.setCuentaBanco((String) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("retCreditoInfonavitValor")) {
          agent.target.setRetCreditoInfonavitValor((BigDecimal) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        if (property.equals("status")) {
          agent.target.setStatus((EStatusEmpleado) value);
          return;
        }
        if (property.equals("idGrupo")) {
          agent.target.setIdGrupo((Integer) value);
          return;
        }
        if (property.equals("apellidoPaterno")) {
          agent.target.setApellidoPaterno((String) value);
          return;
        }
        if (property.equals("fechaBaja")) {
          agent.target.setFechaBaja((Date) value);
          return;
        }
        if (property.equals("pensionIdBanco")) {
          agent.target.setPensionIdBanco((Integer) value);
          return;
        }
        if (property.equals("emailPersonal")) {
          agent.target.setEmailPersonal((String) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("fechaInicioContrato")) {
          agent.target.setFechaInicioContrato((Date) value);
          return;
        }
        if (property.equals("fechaNacimiento")) {
          agent.target.setFechaNacimiento((Date) value);
          return;
        }
        if (property.equals("estimulosProductividad")) {
          agent.target.setEstimulosProductividad((Double) value);
          return;
        }
        if (property.equals("tipoSNI")) {
          agent.target.setTipoSNI((ETipoSNI) value);
          return;
        }
        if (property.equals("nombre")) {
          agent.target.setNombre((String) value);
          return;
        }
        if (property.equals("apellidoMaterno")) {
          agent.target.setApellidoMaterno((String) value);
          return;
        }
        if (property.equals("numSni")) {
          agent.target.setNumSni((String) value);
          return;
        }
        if (property.equals("urlPhoto")) {
          agent.target.setUrlPhoto((String) value);
          return;
        }
        if (property.equals("retCreditoInfonavitTipo")) {
          agent.target.setRetCreditoInfonavitTipo((ECreditoInfoTipo) value);
          return;
        }
        if (property.equals("idClinica")) {
          agent.target.setIdClinica((Integer) value);
          return;
        }
        if (property.equals("idSexo")) {
          agent.target.setIdSexo((Integer) value);
          return;
        }
        if (property.equals("fechaSni")) {
          agent.target.setFechaSni((Date) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("idTipoEmpleado")) {
          agent.target.setIdTipoEmpleado((Integer) value);
          return;
        }
        if (property.equals("tipoContrato")) {
          agent.target.setTipoContrato((ETipoContrato) value);
          return;
        }
        if (property.equals("pensionIdTipo")) {
          agent.target.setPensionIdTipo((Integer) value);
          return;
        }
        if (property.equals("idSede")) {
          agent.target.setIdSede((Integer) value);
          return;
        }
        if (property.equals("idTipoSni")) {
          agent.target.setIdTipoSni((Integer) value);
          return;
        }
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
          return;
        }
        if (property.equals("retCreditoInfonavitIdTipo")) {
          agent.target.setRetCreditoInfonavitIdTipo((Integer) value);
          return;
        }
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("pensionCuenta")) {
          agent.target.setPensionCuenta((String) value);
          return;
        }
        if (property.equals("pantDayOdd")) {
          agent.target.setPantDayOdd((Integer) value);
          return;
        }
        if (property.equals("idTipoAntiguedad")) {
          agent.target.setIdTipoAntiguedad((Integer) value);
          return;
        }
        if (property.equals("departamento")) {
          agent.target.setDepartamento((Departamento) value);
          return;
        }
        if (property.equals("sexo")) {
          agent.target.setSexo((ESexo) value);
          return;
        }
        if (property.equals("porcenSegSeparacionInd")) {
          agent.target.setPorcenSegSeparacionInd((Double) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("pensionBanco")) {
          agent.target.setPensionBanco((EBanco) value);
          return;
        }
        if (property.equals("this")) {
          agent.target = (Empleado) value;
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public Map getBeanProperties() {
        final Map props = new HashMap(agent.propertyTypes);
        props.remove("this");
        return Collections.unmodifiableMap(props);
      }

      public EmpleadoBase toBase() {
        final EmpleadoBase returnValue = agent.target.toBase();
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public void becomesDirty() {
        agent.target.becomesDirty();
        agent.updateWidgetsAndFireEvents();
      }

      public void cleanDirty() {
        agent.target.cleanDirty();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(Empleado.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model) {
        return new cimav_client_data_domain_EmpleadoProxy((Empleado) model);
      }
      public BindableProxy getBindableProxy() {
        return new cimav_client_data_domain_EmpleadoProxy();
      }
    });
    class cimav_client_data_domain_EmpleadoNominaProxy extends EmpleadoNomina implements BindableProxy {
      private BindableProxyAgent<EmpleadoNomina> agent;
      public cimav_client_data_domain_EmpleadoNominaProxy() {
        this(new EmpleadoNomina());
      }

      public cimav_client_data_domain_EmpleadoNominaProxy(EmpleadoNomina target) {
        agent = new BindableProxyAgent<EmpleadoNomina>(this, target);
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("totalDeducciones", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("estimulosProductividad", new PropertyType(Double.class, false, false));
        agent.propertyTypes.put("grupo", new PropertyType(EGrupo.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pantMonths", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("nomina", new PropertyType(Nomina.class, false, false));
        agent.propertyTypes.put("pantDayEven", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idSede", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("pantYears", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("movimientos", new PropertyType(List.class, false, true));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("totalPercepciones", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("pantDayOdd", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("idGrupo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("this", new PropertyType(EmpleadoNomina.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public EmpleadoNomina unwrap() {
        return agent.target;
      }

      public EmpleadoNomina deepUnwrap() {
        final EmpleadoNomina clone = new EmpleadoNomina();
        clone.setCode(agent.target.getCode());
        clone.setEstimulosProductividad(agent.target.getEstimulosProductividad());
        clone.setGrupo(agent.target.getGrupo());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setPantMonths(agent.target.getPantMonths());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setNomina(agent.target.getNomina());
        clone.setPantDayEven(agent.target.getPantDayEven());
        clone.setIdSede(agent.target.getIdSede());
        clone.setId(agent.target.getId());
        clone.setSede(agent.target.getSede());
        clone.setPantYears(agent.target.getPantYears());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        if (agent.target.getMovimientos() != null) {
          final List movimientosClone = new ArrayList();
          for (Object movimientosElem : agent.target.getMovimientos()) {
            if (movimientosElem instanceof BindableProxy) {
              movimientosClone.add(((BindableProxy) movimientosElem).deepUnwrap());
            } else {
              movimientosClone.add(movimientosElem);
            }
          }
          clone.setMovimientos(movimientosClone);
        }
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setPantDayOdd(agent.target.getPantDayOdd());
        clone.setName(agent.target.getName());
        if (agent.target.getDepartamento() instanceof BindableProxy) {
          clone.setDepartamento((Departamento) ((BindableProxy) getDepartamento()).deepUnwrap());
        } else {
          clone.setDepartamento(agent.target.getDepartamento());
        }
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
        clone.setStatus(agent.target.getStatus());
        clone.setIdGrupo(agent.target.getIdGrupo());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_EmpleadoNominaProxy) {
          obj = ((cimav_client_data_domain_EmpleadoNominaProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent(false, "code", oldValue, code);
      }

      public BigDecimal getTotalDeducciones() {
        return agent.target.getTotalDeducciones();
      }

      public Double getEstimulosProductividad() {
        return agent.target.getEstimulosProductividad();
      }

      public void setEstimulosProductividad(Double estimulosProductividad) {
        Double oldValue = agent.target.getEstimulosProductividad();
        agent.target.setEstimulosProductividad(estimulosProductividad);
        agent.updateWidgetsAndFireEvent(false, "estimulosProductividad", oldValue, estimulosProductividad);
      }

      public EGrupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(EGrupo grupo) {
        EGrupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent(false, "grupo", oldValue, grupo);
      }

      public String getUrlPhoto() {
        return agent.target.getUrlPhoto();
      }

      public void setUrlPhoto(String urlPhoto) {
        String oldValue = agent.target.getUrlPhoto();
        agent.target.setUrlPhoto(urlPhoto);
        agent.updateWidgetsAndFireEvent(false, "urlPhoto", oldValue, urlPhoto);
      }

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent(false, "idStatus", oldValue, idStatus);
      }

      public Integer getPantMonths() {
        return agent.target.getPantMonths();
      }

      public void setPantMonths(Integer pantMonths) {
        Integer oldValue = agent.target.getPantMonths();
        agent.target.setPantMonths(pantMonths);
        agent.updateWidgetsAndFireEvent(false, "pantMonths", oldValue, pantMonths);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent(false, "isDirty", oldValue, isDirty);
      }

      public Nomina getNomina() {
        return agent.target.getNomina();
      }

      public void setNomina(Nomina nomina) {
        Nomina oldValue = agent.target.getNomina();
        agent.target.setNomina(nomina);
        agent.updateWidgetsAndFireEvent(false, "nomina", oldValue, nomina);
      }

      public Integer getPantDayEven() {
        return agent.target.getPantDayEven();
      }

      public void setPantDayEven(Integer pantDayEven) {
        Integer oldValue = agent.target.getPantDayEven();
        agent.target.setPantDayEven(pantDayEven);
        agent.updateWidgetsAndFireEvent(false, "pantDayEven", oldValue, pantDayEven);
      }

      public Integer getIdSede() {
        return agent.target.getIdSede();
      }

      public void setIdSede(Integer idSede) {
        Integer oldValue = agent.target.getIdSede();
        agent.target.setIdSede(idSede);
        agent.updateWidgetsAndFireEvent(false, "idSede", oldValue, idSede);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent(false, "id", oldValue, id);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent(false, "sede", oldValue, sede);
      }

      public Integer getPantYears() {
        return agent.target.getPantYears();
      }

      public void setPantYears(Integer pantYears) {
        Integer oldValue = agent.target.getPantYears();
        agent.target.setPantYears(pantYears);
        agent.updateWidgetsAndFireEvent(false, "pantYears", oldValue, pantYears);
      }

      public String getCuentaCimav() {
        return agent.target.getCuentaCimav();
      }

      public void setCuentaCimav(String cuentaCimav) {
        String oldValue = agent.target.getCuentaCimav();
        agent.target.setCuentaCimav(cuentaCimav);
        agent.updateWidgetsAndFireEvent(false, "cuentaCimav", oldValue, cuentaCimav);
      }

      public List getMovimientos() {
        return agent.target.getMovimientos();
      }

      public void setMovimientos(List<Movimiento> movimientos) {
        List<Movimiento> oldValue = agent.target.getMovimientos();
        movimientos = agent.ensureBoundListIsProxied("movimientos", movimientos);
        agent.target.setMovimientos(movimientos);
        agent.updateWidgetsAndFireEvent(true, "movimientos", oldValue, movimientos);
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent(false, "consecutivo", oldValue, consecutivo);
      }

      public BigDecimal getTotalPercepciones() {
        return agent.target.getTotalPercepciones();
      }

      public Integer getPantDayOdd() {
        return agent.target.getPantDayOdd();
      }

      public void setPantDayOdd(Integer pantDayOdd) {
        Integer oldValue = agent.target.getPantDayOdd();
        agent.target.setPantDayOdd(pantDayOdd);
        agent.updateWidgetsAndFireEvent(false, "pantDayOdd", oldValue, pantDayOdd);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent(false, "name", oldValue, name);
      }

      public Departamento getDepartamento() {
        return agent.target.getDepartamento();
      }

      public void setDepartamento(Departamento departamento) {
        if (agent.binders.containsKey("departamento")) {
          departamento = (Departamento) agent.binders.get("departamento").setModel(departamento, StateSync.FROM_MODEL, true);
        }
        Departamento oldValue = agent.target.getDepartamento();
        agent.target.setDepartamento(departamento);
        agent.updateWidgetsAndFireEvent(false, "departamento", oldValue, departamento);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent(false, "fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public Tabulador getNivel() {
        return agent.target.getNivel();
      }

      public void setNivel(Tabulador nivel) {
        if (agent.binders.containsKey("nivel")) {
          nivel = (Tabulador) agent.binders.get("nivel").setModel(nivel, StateSync.FROM_MODEL, true);
        }
        Tabulador oldValue = agent.target.getNivel();
        agent.target.setNivel(nivel);
        agent.updateWidgetsAndFireEvent(false, "nivel", oldValue, nivel);
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public void setStatus(EStatusEmpleado status) {
        EStatusEmpleado oldValue = agent.target.getStatus();
        agent.target.setStatus(status);
        agent.updateWidgetsAndFireEvent(false, "status", oldValue, status);
      }

      public Integer getIdGrupo() {
        return agent.target.getIdGrupo();
      }

      public void setIdGrupo(Integer idGrupo) {
        Integer oldValue = agent.target.getIdGrupo();
        agent.target.setIdGrupo(idGrupo);
        agent.updateWidgetsAndFireEvent(false, "idGrupo", oldValue, idGrupo);
      }

      public Object get(String property) {
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("totalDeducciones")) {
          return getTotalDeducciones();
        }
        if (property.equals("estimulosProductividad")) {
          return getEstimulosProductividad();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("urlPhoto")) {
          return getUrlPhoto();
        }
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("pantMonths")) {
          return getPantMonths();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("nomina")) {
          return getNomina();
        }
        if (property.equals("pantDayEven")) {
          return getPantDayEven();
        }
        if (property.equals("idSede")) {
          return getIdSede();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("sede")) {
          return getSede();
        }
        if (property.equals("pantYears")) {
          return getPantYears();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("movimientos")) {
          return getMovimientos();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("totalPercepciones")) {
          return getTotalPercepciones();
        }
        if (property.equals("pantDayOdd")) {
          return getPantDayOdd();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("departamento")) {
          return getDepartamento();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("idGrupo")) {
          return getIdGrupo();
        }
        if (property.equals("this")) {
          return agent.target;
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("estimulosProductividad")) {
          agent.target.setEstimulosProductividad((Double) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((EGrupo) value);
          return;
        }
        if (property.equals("urlPhoto")) {
          agent.target.setUrlPhoto((String) value);
          return;
        }
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("pantMonths")) {
          agent.target.setPantMonths((Integer) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("nomina")) {
          agent.target.setNomina((Nomina) value);
          return;
        }
        if (property.equals("pantDayEven")) {
          agent.target.setPantDayEven((Integer) value);
          return;
        }
        if (property.equals("idSede")) {
          agent.target.setIdSede((Integer) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
          return;
        }
        if (property.equals("pantYears")) {
          agent.target.setPantYears((Integer) value);
          return;
        }
        if (property.equals("cuentaCimav")) {
          agent.target.setCuentaCimav((String) value);
          return;
        }
        if (property.equals("movimientos")) {
          agent.target.setMovimientos((List<Movimiento>) value);
          return;
        }
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("pantDayOdd")) {
          agent.target.setPantDayOdd((Integer) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("departamento")) {
          agent.target.setDepartamento((Departamento) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        if (property.equals("status")) {
          agent.target.setStatus((EStatusEmpleado) value);
          return;
        }
        if (property.equals("idGrupo")) {
          agent.target.setIdGrupo((Integer) value);
          return;
        }
        if (property.equals("this")) {
          agent.target = (EmpleadoNomina) value;
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public Map getBeanProperties() {
        final Map props = new HashMap(agent.propertyTypes);
        props.remove("this");
        return Collections.unmodifiableMap(props);
      }

      public List getMovimientos(ETipoConcepto a0, ETipoMovimiento a1) {
        final List returnValue = agent.target.getMovimientos(a0, a1);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public List getMovimientos(ETipoConcepto a0) {
        final List returnValue = agent.target.getMovimientos(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public void becomesDirty() {
        agent.target.becomesDirty();
        agent.updateWidgetsAndFireEvents();
      }

      public void cleanDirty() {
        agent.target.cleanDirty();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(EmpleadoNomina.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model) {
        return new cimav_client_data_domain_EmpleadoNominaProxy((EmpleadoNomina) model);
      }
      public BindableProxy getBindableProxy() {
        return new cimav_client_data_domain_EmpleadoNominaProxy();
      }
    });
    class cimav_client_data_domain_BaseDomainProxy extends BaseDomain implements BindableProxy {
      private BindableProxyAgent<BaseDomain> agent;
      public cimav_client_data_domain_BaseDomainProxy() {
        this(new BaseDomain());
      }

      public cimav_client_data_domain_BaseDomainProxy(BaseDomain target) {
        agent = new BindableProxyAgent<BaseDomain>(this, target);
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("this", new PropertyType(BaseDomain.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public BaseDomain unwrap() {
        return agent.target;
      }

      public BaseDomain deepUnwrap() {
        final BaseDomain clone = new BaseDomain();
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setCode(agent.target.getCode());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setName(agent.target.getName());
        clone.setId(agent.target.getId());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_BaseDomainProxy) {
          obj = ((cimav_client_data_domain_BaseDomainProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent(false, "consecutivo", oldValue, consecutivo);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent(false, "code", oldValue, code);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent(false, "isDirty", oldValue, isDirty);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent(false, "name", oldValue, name);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent(false, "id", oldValue, id);
      }

      public Object get(String property) {
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("this")) {
          return agent.target;
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("this")) {
          agent.target = (BaseDomain) value;
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public Map getBeanProperties() {
        final Map props = new HashMap(agent.propertyTypes);
        props.remove("this");
        return Collections.unmodifiableMap(props);
      }

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public void becomesDirty() {
        agent.target.becomesDirty();
        agent.updateWidgetsAndFireEvents();
      }

      public void cleanDirty() {
        agent.target.cleanDirty();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(BaseDomain.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model) {
        return new cimav_client_data_domain_BaseDomainProxy((BaseDomain) model);
      }
      public BindableProxy getBindableProxy() {
        return new cimav_client_data_domain_BaseDomainProxy();
      }
    });
    class cimav_client_data_domain_EmpleadoBaseProxy extends EmpleadoBase implements BindableProxy {
      private BindableProxyAgent<EmpleadoBase> agent;
      public cimav_client_data_domain_EmpleadoBaseProxy() {
        this(new EmpleadoBase());
      }

      public cimav_client_data_domain_EmpleadoBaseProxy(EmpleadoBase target) {
        agent = new BindableProxyAgent<EmpleadoBase>(this, target);
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("estimulosProductividad", new PropertyType(Double.class, false, false));
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("grupo", new PropertyType(EGrupo.class, false, false));
        agent.propertyTypes.put("pantYears", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pantMonths", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("pantDayEven", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("pantDayOdd", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("idSede", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("idGrupo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("this", new PropertyType(EmpleadoBase.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getBindableProxyAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public EmpleadoBase unwrap() {
        return agent.target;
      }

      public EmpleadoBase deepUnwrap() {
        final EmpleadoBase clone = new EmpleadoBase();
        clone.setCode(agent.target.getCode());
        clone.setEstimulosProductividad(agent.target.getEstimulosProductividad());
        clone.setSede(agent.target.getSede());
        clone.setGrupo(agent.target.getGrupo());
        clone.setPantYears(agent.target.getPantYears());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setPantMonths(agent.target.getPantMonths());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setPantDayEven(agent.target.getPantDayEven());
        clone.setPantDayOdd(agent.target.getPantDayOdd());
        clone.setName(agent.target.getName());
        if (agent.target.getDepartamento() instanceof BindableProxy) {
          clone.setDepartamento((Departamento) ((BindableProxy) getDepartamento()).deepUnwrap());
        } else {
          clone.setDepartamento(agent.target.getDepartamento());
        }
        clone.setIdSede(agent.target.getIdSede());
        clone.setId(agent.target.getId());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        clone.setStatus(agent.target.getStatus());
        clone.setIdGrupo(agent.target.getIdGrupo());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_EmpleadoBaseProxy) {
          obj = ((cimav_client_data_domain_EmpleadoBaseProxy) obj).unwrap();
        }
        return agent.target.equals(obj);
      }

      public int hashCode() {
        return agent.target.hashCode();
      }

      public String toString() {
        return agent.target.toString();
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent(false, "code", oldValue, code);
      }

      public Double getEstimulosProductividad() {
        return agent.target.getEstimulosProductividad();
      }

      public void setEstimulosProductividad(Double estimulosProductividad) {
        Double oldValue = agent.target.getEstimulosProductividad();
        agent.target.setEstimulosProductividad(estimulosProductividad);
        agent.updateWidgetsAndFireEvent(false, "estimulosProductividad", oldValue, estimulosProductividad);
      }

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent(false, "sede", oldValue, sede);
      }

      public EGrupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(EGrupo grupo) {
        EGrupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent(false, "grupo", oldValue, grupo);
      }

      public Integer getPantYears() {
        return agent.target.getPantYears();
      }

      public void setPantYears(Integer pantYears) {
        Integer oldValue = agent.target.getPantYears();
        agent.target.setPantYears(pantYears);
        agent.updateWidgetsAndFireEvent(false, "pantYears", oldValue, pantYears);
      }

      public String getCuentaCimav() {
        return agent.target.getCuentaCimav();
      }

      public void setCuentaCimav(String cuentaCimav) {
        String oldValue = agent.target.getCuentaCimav();
        agent.target.setCuentaCimav(cuentaCimav);
        agent.updateWidgetsAndFireEvent(false, "cuentaCimav", oldValue, cuentaCimav);
      }

      public String getUrlPhoto() {
        return agent.target.getUrlPhoto();
      }

      public void setUrlPhoto(String urlPhoto) {
        String oldValue = agent.target.getUrlPhoto();
        agent.target.setUrlPhoto(urlPhoto);
        agent.updateWidgetsAndFireEvent(false, "urlPhoto", oldValue, urlPhoto);
      }

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent(false, "idStatus", oldValue, idStatus);
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent(false, "consecutivo", oldValue, consecutivo);
      }

      public Integer getPantMonths() {
        return agent.target.getPantMonths();
      }

      public void setPantMonths(Integer pantMonths) {
        Integer oldValue = agent.target.getPantMonths();
        agent.target.setPantMonths(pantMonths);
        agent.updateWidgetsAndFireEvent(false, "pantMonths", oldValue, pantMonths);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent(false, "isDirty", oldValue, isDirty);
      }

      public Integer getPantDayEven() {
        return agent.target.getPantDayEven();
      }

      public void setPantDayEven(Integer pantDayEven) {
        Integer oldValue = agent.target.getPantDayEven();
        agent.target.setPantDayEven(pantDayEven);
        agent.updateWidgetsAndFireEvent(false, "pantDayEven", oldValue, pantDayEven);
      }

      public Integer getPantDayOdd() {
        return agent.target.getPantDayOdd();
      }

      public void setPantDayOdd(Integer pantDayOdd) {
        Integer oldValue = agent.target.getPantDayOdd();
        agent.target.setPantDayOdd(pantDayOdd);
        agent.updateWidgetsAndFireEvent(false, "pantDayOdd", oldValue, pantDayOdd);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent(false, "name", oldValue, name);
      }

      public Departamento getDepartamento() {
        return agent.target.getDepartamento();
      }

      public void setDepartamento(Departamento departamento) {
        if (agent.binders.containsKey("departamento")) {
          departamento = (Departamento) agent.binders.get("departamento").setModel(departamento, StateSync.FROM_MODEL, true);
        }
        Departamento oldValue = agent.target.getDepartamento();
        agent.target.setDepartamento(departamento);
        agent.updateWidgetsAndFireEvent(false, "departamento", oldValue, departamento);
      }

      public Integer getIdSede() {
        return agent.target.getIdSede();
      }

      public void setIdSede(Integer idSede) {
        Integer oldValue = agent.target.getIdSede();
        agent.target.setIdSede(idSede);
        agent.updateWidgetsAndFireEvent(false, "idSede", oldValue, idSede);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent(false, "id", oldValue, id);
      }

      public Tabulador getNivel() {
        return agent.target.getNivel();
      }

      public void setNivel(Tabulador nivel) {
        if (agent.binders.containsKey("nivel")) {
          nivel = (Tabulador) agent.binders.get("nivel").setModel(nivel, StateSync.FROM_MODEL, true);
        }
        Tabulador oldValue = agent.target.getNivel();
        agent.target.setNivel(nivel);
        agent.updateWidgetsAndFireEvent(false, "nivel", oldValue, nivel);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent(false, "fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public void setStatus(EStatusEmpleado status) {
        EStatusEmpleado oldValue = agent.target.getStatus();
        agent.target.setStatus(status);
        agent.updateWidgetsAndFireEvent(false, "status", oldValue, status);
      }

      public Integer getIdGrupo() {
        return agent.target.getIdGrupo();
      }

      public void setIdGrupo(Integer idGrupo) {
        Integer oldValue = agent.target.getIdGrupo();
        agent.target.setIdGrupo(idGrupo);
        agent.updateWidgetsAndFireEvent(false, "idGrupo", oldValue, idGrupo);
      }

      public Object get(String property) {
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("estimulosProductividad")) {
          return getEstimulosProductividad();
        }
        if (property.equals("sede")) {
          return getSede();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("pantYears")) {
          return getPantYears();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("urlPhoto")) {
          return getUrlPhoto();
        }
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("pantMonths")) {
          return getPantMonths();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("pantDayEven")) {
          return getPantDayEven();
        }
        if (property.equals("pantDayOdd")) {
          return getPantDayOdd();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("departamento")) {
          return getDepartamento();
        }
        if (property.equals("idSede")) {
          return getIdSede();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("idGrupo")) {
          return getIdGrupo();
        }
        if (property.equals("this")) {
          return agent.target;
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("estimulosProductividad")) {
          agent.target.setEstimulosProductividad((Double) value);
          return;
        }
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((EGrupo) value);
          return;
        }
        if (property.equals("pantYears")) {
          agent.target.setPantYears((Integer) value);
          return;
        }
        if (property.equals("cuentaCimav")) {
          agent.target.setCuentaCimav((String) value);
          return;
        }
        if (property.equals("urlPhoto")) {
          agent.target.setUrlPhoto((String) value);
          return;
        }
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("pantMonths")) {
          agent.target.setPantMonths((Integer) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("pantDayEven")) {
          agent.target.setPantDayEven((Integer) value);
          return;
        }
        if (property.equals("pantDayOdd")) {
          agent.target.setPantDayOdd((Integer) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("departamento")) {
          agent.target.setDepartamento((Departamento) value);
          return;
        }
        if (property.equals("idSede")) {
          agent.target.setIdSede((Integer) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("status")) {
          agent.target.setStatus((EStatusEmpleado) value);
          return;
        }
        if (property.equals("idGrupo")) {
          agent.target.setIdGrupo((Integer) value);
          return;
        }
        if (property.equals("this")) {
          agent.target = (EmpleadoBase) value;
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public Map getBeanProperties() {
        final Map props = new HashMap(agent.propertyTypes);
        props.remove("this");
        return Collections.unmodifiableMap(props);
      }

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public void becomesDirty() {
        agent.target.becomesDirty();
        agent.updateWidgetsAndFireEvents();
      }

      public void cleanDirty() {
        agent.target.cleanDirty();
        agent.updateWidgetsAndFireEvents();
      }
    }
    BindableProxyFactory.addBindableProxy(EmpleadoBase.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model) {
        return new cimav_client_data_domain_EmpleadoBaseProxy((EmpleadoBase) model);
      }
      public BindableProxy getBindableProxy() {
        return new cimav_client_data_domain_EmpleadoBaseProxy();
      }
    });
  }
}