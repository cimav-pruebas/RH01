package org.jboss.errai.databinding.client;

import cimav.client.data.domain.BaseDomain;
import cimav.client.data.domain.Departamento;
import cimav.client.data.domain.EBanco;
import cimav.client.data.domain.EClinica;
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
import cimav.client.data.domain.ETipoSNI;
import cimav.client.data.domain.Empleado;
import cimav.client.data.domain.EmpleadoBase;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.EmpleadoQuincenal;
import cimav.client.data.domain.HoraExtra;
import cimav.client.data.domain.Incidencia;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.data.domain.Tabulador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.jboss.errai.databinding.client.api.InitialState;

public class BindableProxyLoaderImpl implements BindableProxyLoader { public void loadBindableProxies() {
    class cimav_client_data_domain_DepartamentoProxy extends Departamento implements BindableProxy {
      private BindableProxyAgent<Departamento> agent;
      public cimav_client_data_domain_DepartamentoProxy(InitialState initialState) {
        this(new Departamento(), initialState);
      }

      public cimav_client_data_domain_DepartamentoProxy(Departamento target, InitialState initialState) {
        agent = new BindableProxyAgent<Departamento>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
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
        clone.setId(agent.target.getId());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setName(agent.target.getName());
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setCode(agent.target.getCode());
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

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent("id", oldValue, id);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent("isDirty", oldValue, isDirty);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent("consecutivo", oldValue, consecutivo);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent("code", oldValue, code);
      }

      public Object get(String property) {
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("code")) {
          return getCode();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
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
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        throw new NonExistingPropertyException(property);
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
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_DepartamentoProxy((Departamento) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_DepartamentoProxy(state);
      }
    });
    class cimav_client_data_domain_BaseDomainProxy extends BaseDomain implements BindableProxy {
      private BindableProxyAgent<BaseDomain> agent;
      public cimav_client_data_domain_BaseDomainProxy(InitialState initialState) {
        this(new BaseDomain(), initialState);
      }

      public cimav_client_data_domain_BaseDomainProxy(BaseDomain target, InitialState initialState) {
        agent = new BindableProxyAgent<BaseDomain>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
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
        clone.setId(agent.target.getId());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setName(agent.target.getName());
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setCode(agent.target.getCode());
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

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent("id", oldValue, id);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent("isDirty", oldValue, isDirty);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent("consecutivo", oldValue, consecutivo);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent("code", oldValue, code);
      }

      public Object get(String property) {
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("code")) {
          return getCode();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
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
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        throw new NonExistingPropertyException(property);
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
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_BaseDomainProxy((BaseDomain) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_BaseDomainProxy(state);
      }
    });
    class cimav_client_data_domain_TabuladorProxy extends Tabulador implements BindableProxy {
      private BindableProxyAgent<Tabulador> agent;
      public cimav_client_data_domain_TabuladorProxy(InitialState initialState) {
        this(new Tabulador(), initialState);
      }

      public cimav_client_data_domain_TabuladorProxy(Tabulador target, InitialState initialState) {
        agent = new BindableProxyAgent<Tabulador>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("compGarantizada", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("cargaAdmin", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("sueldo", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("honorarios", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("matDidacticos", new PropertyType(BigDecimal.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
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
        clone.setId(agent.target.getId());
        clone.setCompGarantizada(agent.target.getCompGarantizada());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setCargaAdmin(agent.target.getCargaAdmin());
        clone.setName(agent.target.getName());
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setSueldo(agent.target.getSueldo());
        clone.setCode(agent.target.getCode());
        clone.setHonorarios(agent.target.getHonorarios());
        clone.setMatDidacticos(agent.target.getMatDidacticos());
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

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent("id", oldValue, id);
      }

      public BigDecimal getCompGarantizada() {
        return agent.target.getCompGarantizada();
      }

      public void setCompGarantizada(BigDecimal compGarantizada) {
        BigDecimal oldValue = agent.target.getCompGarantizada();
        agent.target.setCompGarantizada(compGarantizada);
        agent.updateWidgetsAndFireEvent("compGarantizada", oldValue, compGarantizada);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent("isDirty", oldValue, isDirty);
      }

      public BigDecimal getCargaAdmin() {
        return agent.target.getCargaAdmin();
      }

      public void setCargaAdmin(BigDecimal cargaAdmin) {
        BigDecimal oldValue = agent.target.getCargaAdmin();
        agent.target.setCargaAdmin(cargaAdmin);
        agent.updateWidgetsAndFireEvent("cargaAdmin", oldValue, cargaAdmin);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent("consecutivo", oldValue, consecutivo);
      }

      public BigDecimal getSueldo() {
        return agent.target.getSueldo();
      }

      public void setSueldo(BigDecimal sueldo) {
        BigDecimal oldValue = agent.target.getSueldo();
        agent.target.setSueldo(sueldo);
        agent.updateWidgetsAndFireEvent("sueldo", oldValue, sueldo);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent("code", oldValue, code);
      }

      public BigDecimal getHonorarios() {
        return agent.target.getHonorarios();
      }

      public void setHonorarios(BigDecimal honorarios) {
        BigDecimal oldValue = agent.target.getHonorarios();
        agent.target.setHonorarios(honorarios);
        agent.updateWidgetsAndFireEvent("honorarios", oldValue, honorarios);
      }

      public BigDecimal getMatDidacticos() {
        return agent.target.getMatDidacticos();
      }

      public void setMatDidacticos(BigDecimal matDidacticos) {
        BigDecimal oldValue = agent.target.getMatDidacticos();
        agent.target.setMatDidacticos(matDidacticos);
        agent.updateWidgetsAndFireEvent("matDidacticos", oldValue, matDidacticos);
      }

      public Object get(String property) {
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("compGarantizada")) {
          return getCompGarantizada();
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
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("sueldo")) {
          return getSueldo();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("honorarios")) {
          return getHonorarios();
        }
        if (property.equals("matDidacticos")) {
          return getMatDidacticos();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("compGarantizada")) {
          agent.target.setCompGarantizada((BigDecimal) value);
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
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("sueldo")) {
          agent.target.setSueldo((BigDecimal) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("honorarios")) {
          agent.target.setHonorarios((BigDecimal) value);
          return;
        }
        if (property.equals("matDidacticos")) {
          agent.target.setMatDidacticos((BigDecimal) value);
          return;
        }
        throw new NonExistingPropertyException(property);
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
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_TabuladorProxy((Tabulador) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_TabuladorProxy(state);
      }
    });
    class cimav_client_data_domain_EmpleadoNominaProxy extends EmpleadoNomina implements BindableProxy {
      private BindableProxyAgent<EmpleadoNomina> agent;
      public cimav_client_data_domain_EmpleadoNominaProxy(InitialState initialState) {
        this(new EmpleadoNomina(), initialState);
      }

      public cimav_client_data_domain_EmpleadoNominaProxy(EmpleadoNomina target, InitialState initialState) {
        agent = new BindableProxyAgent<EmpleadoNomina>(this, target, initialState);
        agent.propertyTypes.put("totalDeducciones", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("grupo", new PropertyType(EGrupo.class, false, false));
        agent.propertyTypes.put("idSede", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("empleadoQuincenal", new PropertyType(EmpleadoQuincenal.class, false, false));
        agent.propertyTypes.put("incidencias", new PropertyType(List.class, false, true));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idGrupo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("horasExtras", new PropertyType(List.class, false, true));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("nominaQuincenalCollection", new PropertyType(List.class, false, true));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("totalPercepciones", new PropertyType(BigDecimal.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
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
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setGrupo(agent.target.getGrupo());
        clone.setIdSede(agent.target.getIdSede());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setEmpleadoQuincenal(agent.target.getEmpleadoQuincenal());
        if (agent.target.getIncidencias() != null) {
          final List incidenciasClone = new ArrayList();
          for (Object incidenciasElem : agent.target.getIncidencias()) {
            if (incidenciasElem instanceof BindableProxy) {
              incidenciasClone.add(((BindableProxy) incidenciasElem).deepUnwrap());
            } else {
              incidenciasClone.add(incidenciasElem);
            }
          }
          clone.setIncidencias(incidenciasClone);
        }
        clone.setStatus(agent.target.getStatus());
        clone.setSede(agent.target.getSede());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        clone.setIdGrupo(agent.target.getIdGrupo());
        clone.setCode(agent.target.getCode());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        if (agent.target.getHorasExtras() != null) {
          final List horasExtrasClone = new ArrayList();
          for (Object horasExtrasElem : agent.target.getHorasExtras()) {
            if (horasExtrasElem instanceof BindableProxy) {
              horasExtrasClone.add(((BindableProxy) horasExtrasElem).deepUnwrap());
            } else {
              horasExtrasClone.add(horasExtrasElem);
            }
          }
          clone.setHorasExtras(horasExtrasClone);
        }
        clone.setId(agent.target.getId());
        if (agent.target.getDepartamento() instanceof BindableProxy) {
          clone.setDepartamento((Departamento) ((BindableProxy) getDepartamento()).deepUnwrap());
        } else {
          clone.setDepartamento(agent.target.getDepartamento());
        }
        if (agent.target.getNominaQuincenalCollection() != null) {
          final List nominaQuincenalCollectionClone = new ArrayList();
          for (Object nominaQuincenalCollectionElem : agent.target.getNominaQuincenalCollection()) {
            if (nominaQuincenalCollectionElem instanceof BindableProxy) {
              nominaQuincenalCollectionClone.add(((BindableProxy) nominaQuincenalCollectionElem).deepUnwrap());
            } else {
              nominaQuincenalCollectionClone.add(nominaQuincenalCollectionElem);
            }
          }
          clone.setNominaQuincenalCollection(nominaQuincenalCollectionClone);
        }
        clone.setName(agent.target.getName());
        clone.setConsecutivo(agent.target.getConsecutivo());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
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

      public BigDecimal getTotalDeducciones() {
        return agent.target.getTotalDeducciones();
      }

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent("idStatus", oldValue, idStatus);
      }

      public EGrupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(EGrupo grupo) {
        EGrupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent("grupo", oldValue, grupo);
      }

      public Integer getIdSede() {
        return agent.target.getIdSede();
      }

      public void setIdSede(Integer idSede) {
        Integer oldValue = agent.target.getIdSede();
        agent.target.setIdSede(idSede);
        agent.updateWidgetsAndFireEvent("idSede", oldValue, idSede);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent("isDirty", oldValue, isDirty);
      }

      public EmpleadoQuincenal getEmpleadoQuincenal() {
        return agent.target.getEmpleadoQuincenal();
      }

      public void setEmpleadoQuincenal(EmpleadoQuincenal empleadoQuincenal) {
        EmpleadoQuincenal oldValue = agent.target.getEmpleadoQuincenal();
        agent.target.setEmpleadoQuincenal(empleadoQuincenal);
        agent.updateWidgetsAndFireEvent("empleadoQuincenal", oldValue, empleadoQuincenal);
      }

      public List getIncidencias() {
        return agent.target.getIncidencias();
      }

      public void setIncidencias(List<Incidencia> incidencias) {
        List<Incidencia> oldValue = agent.target.getIncidencias();
        incidencias = agent.ensureBoundListIsProxied("incidencias", incidencias);
        agent.target.setIncidencias(incidencias);
        agent.updateWidgetsAndFireEvent("incidencias", oldValue, incidencias);
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public void setStatus(EStatusEmpleado status) {
        EStatusEmpleado oldValue = agent.target.getStatus();
        agent.target.setStatus(status);
        agent.updateWidgetsAndFireEvent("status", oldValue, status);
      }

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent("sede", oldValue, sede);
      }

      public String getUrlPhoto() {
        return agent.target.getUrlPhoto();
      }

      public void setUrlPhoto(String urlPhoto) {
        String oldValue = agent.target.getUrlPhoto();
        agent.target.setUrlPhoto(urlPhoto);
        agent.updateWidgetsAndFireEvent("urlPhoto", oldValue, urlPhoto);
      }

      public Integer getIdGrupo() {
        return agent.target.getIdGrupo();
      }

      public void setIdGrupo(Integer idGrupo) {
        Integer oldValue = agent.target.getIdGrupo();
        agent.target.setIdGrupo(idGrupo);
        agent.updateWidgetsAndFireEvent("idGrupo", oldValue, idGrupo);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent("code", oldValue, code);
      }

      public String getCuentaCimav() {
        return agent.target.getCuentaCimav();
      }

      public void setCuentaCimav(String cuentaCimav) {
        String oldValue = agent.target.getCuentaCimav();
        agent.target.setCuentaCimav(cuentaCimav);
        agent.updateWidgetsAndFireEvent("cuentaCimav", oldValue, cuentaCimav);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent("fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public List getHorasExtras() {
        return agent.target.getHorasExtras();
      }

      public void setHorasExtras(List<HoraExtra> horasExtras) {
        List<HoraExtra> oldValue = agent.target.getHorasExtras();
        horasExtras = agent.ensureBoundListIsProxied("horasExtras", horasExtras);
        agent.target.setHorasExtras(horasExtras);
        agent.updateWidgetsAndFireEvent("horasExtras", oldValue, horasExtras);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent("id", oldValue, id);
      }

      public Departamento getDepartamento() {
        return agent.target.getDepartamento();
      }

      public void setDepartamento(Departamento departamento) {
        if (agent.binders.containsKey("departamento")) {
          departamento = (Departamento) agent.binders.get("departamento").setModel(departamento, InitialState.FROM_MODEL, true);
        }
        Departamento oldValue = agent.target.getDepartamento();
        agent.target.setDepartamento(departamento);
        agent.updateWidgetsAndFireEvent("departamento", oldValue, departamento);
      }

      public List getNominaQuincenalCollection() {
        return agent.target.getNominaQuincenalCollection();
      }

      public void setNominaQuincenalCollection(List<NominaQuincenal> nominaQuincenalCollection) {
        List<NominaQuincenal> oldValue = agent.target.getNominaQuincenalCollection();
        nominaQuincenalCollection = agent.ensureBoundListIsProxied("nominaQuincenalCollection", nominaQuincenalCollection);
        agent.target.setNominaQuincenalCollection(nominaQuincenalCollection);
        agent.updateWidgetsAndFireEvent("nominaQuincenalCollection", oldValue, nominaQuincenalCollection);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public BigDecimal getTotalPercepciones() {
        return agent.target.getTotalPercepciones();
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent("consecutivo", oldValue, consecutivo);
      }

      public Tabulador getNivel() {
        return agent.target.getNivel();
      }

      public void setNivel(Tabulador nivel) {
        if (agent.binders.containsKey("nivel")) {
          nivel = (Tabulador) agent.binders.get("nivel").setModel(nivel, InitialState.FROM_MODEL, true);
        }
        Tabulador oldValue = agent.target.getNivel();
        agent.target.setNivel(nivel);
        agent.updateWidgetsAndFireEvent("nivel", oldValue, nivel);
      }

      public Object get(String property) {
        if (property.equals("totalDeducciones")) {
          return getTotalDeducciones();
        }
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("idSede")) {
          return getIdSede();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("empleadoQuincenal")) {
          return getEmpleadoQuincenal();
        }
        if (property.equals("incidencias")) {
          return getIncidencias();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("sede")) {
          return getSede();
        }
        if (property.equals("urlPhoto")) {
          return getUrlPhoto();
        }
        if (property.equals("idGrupo")) {
          return getIdGrupo();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("horasExtras")) {
          return getHorasExtras();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("departamento")) {
          return getDepartamento();
        }
        if (property.equals("nominaQuincenalCollection")) {
          return getNominaQuincenalCollection();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("totalPercepciones")) {
          return getTotalPercepciones();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((EGrupo) value);
          return;
        }
        if (property.equals("idSede")) {
          agent.target.setIdSede((Integer) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("empleadoQuincenal")) {
          agent.target.setEmpleadoQuincenal((EmpleadoQuincenal) value);
          return;
        }
        if (property.equals("incidencias")) {
          agent.target.setIncidencias((List<Incidencia>) value);
          return;
        }
        if (property.equals("status")) {
          agent.target.setStatus((EStatusEmpleado) value);
          return;
        }
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
          return;
        }
        if (property.equals("urlPhoto")) {
          agent.target.setUrlPhoto((String) value);
          return;
        }
        if (property.equals("idGrupo")) {
          agent.target.setIdGrupo((Integer) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("cuentaCimav")) {
          agent.target.setCuentaCimav((String) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("horasExtras")) {
          agent.target.setHorasExtras((List<HoraExtra>) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("departamento")) {
          agent.target.setDepartamento((Departamento) value);
          return;
        }
        if (property.equals("nominaQuincenalCollection")) {
          agent.target.setNominaQuincenalCollection((List<NominaQuincenal>) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        throw new NonExistingPropertyException(property);
      }

      public List getNominaQuincenalCollection(ETipoConcepto a0, ETipoMovimiento a1) {
        final List returnValue = agent.target.getNominaQuincenalCollection(a0, a1);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
      }

      public List getNominaQuincenalCollection(ETipoConcepto a0) {
        final List returnValue = agent.target.getNominaQuincenalCollection(a0);
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
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_EmpleadoNominaProxy((EmpleadoNomina) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_EmpleadoNominaProxy(state);
      }
    });
    class cimav_client_data_domain_EmpleadoBaseProxy extends EmpleadoBase implements BindableProxy {
      private BindableProxyAgent<EmpleadoBase> agent;
      public cimav_client_data_domain_EmpleadoBaseProxy(InitialState initialState) {
        this(new EmpleadoBase(), initialState);
      }

      public cimav_client_data_domain_EmpleadoBaseProxy(EmpleadoBase target, InitialState initialState) {
        agent = new BindableProxyAgent<EmpleadoBase>(this, target, initialState);
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("grupo", new PropertyType(EGrupo.class, false, false));
        agent.propertyTypes.put("idSede", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("empleadoQuincenal", new PropertyType(EmpleadoQuincenal.class, false, false));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idGrupo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
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
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setGrupo(agent.target.getGrupo());
        clone.setIdSede(agent.target.getIdSede());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setEmpleadoQuincenal(agent.target.getEmpleadoQuincenal());
        clone.setStatus(agent.target.getStatus());
        clone.setSede(agent.target.getSede());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        clone.setIdGrupo(agent.target.getIdGrupo());
        clone.setCode(agent.target.getCode());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        clone.setId(agent.target.getId());
        if (agent.target.getDepartamento() instanceof BindableProxy) {
          clone.setDepartamento((Departamento) ((BindableProxy) getDepartamento()).deepUnwrap());
        } else {
          clone.setDepartamento(agent.target.getDepartamento());
        }
        clone.setName(agent.target.getName());
        clone.setConsecutivo(agent.target.getConsecutivo());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
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

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent("idStatus", oldValue, idStatus);
      }

      public EGrupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(EGrupo grupo) {
        EGrupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent("grupo", oldValue, grupo);
      }

      public Integer getIdSede() {
        return agent.target.getIdSede();
      }

      public void setIdSede(Integer idSede) {
        Integer oldValue = agent.target.getIdSede();
        agent.target.setIdSede(idSede);
        agent.updateWidgetsAndFireEvent("idSede", oldValue, idSede);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent("isDirty", oldValue, isDirty);
      }

      public EmpleadoQuincenal getEmpleadoQuincenal() {
        return agent.target.getEmpleadoQuincenal();
      }

      public void setEmpleadoQuincenal(EmpleadoQuincenal empleadoQuincenal) {
        EmpleadoQuincenal oldValue = agent.target.getEmpleadoQuincenal();
        agent.target.setEmpleadoQuincenal(empleadoQuincenal);
        agent.updateWidgetsAndFireEvent("empleadoQuincenal", oldValue, empleadoQuincenal);
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public void setStatus(EStatusEmpleado status) {
        EStatusEmpleado oldValue = agent.target.getStatus();
        agent.target.setStatus(status);
        agent.updateWidgetsAndFireEvent("status", oldValue, status);
      }

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent("sede", oldValue, sede);
      }

      public String getUrlPhoto() {
        return agent.target.getUrlPhoto();
      }

      public void setUrlPhoto(String urlPhoto) {
        String oldValue = agent.target.getUrlPhoto();
        agent.target.setUrlPhoto(urlPhoto);
        agent.updateWidgetsAndFireEvent("urlPhoto", oldValue, urlPhoto);
      }

      public Integer getIdGrupo() {
        return agent.target.getIdGrupo();
      }

      public void setIdGrupo(Integer idGrupo) {
        Integer oldValue = agent.target.getIdGrupo();
        agent.target.setIdGrupo(idGrupo);
        agent.updateWidgetsAndFireEvent("idGrupo", oldValue, idGrupo);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent("code", oldValue, code);
      }

      public String getCuentaCimav() {
        return agent.target.getCuentaCimav();
      }

      public void setCuentaCimav(String cuentaCimav) {
        String oldValue = agent.target.getCuentaCimav();
        agent.target.setCuentaCimav(cuentaCimav);
        agent.updateWidgetsAndFireEvent("cuentaCimav", oldValue, cuentaCimav);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent("fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent("id", oldValue, id);
      }

      public Departamento getDepartamento() {
        return agent.target.getDepartamento();
      }

      public void setDepartamento(Departamento departamento) {
        if (agent.binders.containsKey("departamento")) {
          departamento = (Departamento) agent.binders.get("departamento").setModel(departamento, InitialState.FROM_MODEL, true);
        }
        Departamento oldValue = agent.target.getDepartamento();
        agent.target.setDepartamento(departamento);
        agent.updateWidgetsAndFireEvent("departamento", oldValue, departamento);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent("consecutivo", oldValue, consecutivo);
      }

      public Tabulador getNivel() {
        return agent.target.getNivel();
      }

      public void setNivel(Tabulador nivel) {
        if (agent.binders.containsKey("nivel")) {
          nivel = (Tabulador) agent.binders.get("nivel").setModel(nivel, InitialState.FROM_MODEL, true);
        }
        Tabulador oldValue = agent.target.getNivel();
        agent.target.setNivel(nivel);
        agent.updateWidgetsAndFireEvent("nivel", oldValue, nivel);
      }

      public Object get(String property) {
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("idSede")) {
          return getIdSede();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("empleadoQuincenal")) {
          return getEmpleadoQuincenal();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("sede")) {
          return getSede();
        }
        if (property.equals("urlPhoto")) {
          return getUrlPhoto();
        }
        if (property.equals("idGrupo")) {
          return getIdGrupo();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("departamento")) {
          return getDepartamento();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((EGrupo) value);
          return;
        }
        if (property.equals("idSede")) {
          agent.target.setIdSede((Integer) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("empleadoQuincenal")) {
          agent.target.setEmpleadoQuincenal((EmpleadoQuincenal) value);
          return;
        }
        if (property.equals("status")) {
          agent.target.setStatus((EStatusEmpleado) value);
          return;
        }
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
          return;
        }
        if (property.equals("urlPhoto")) {
          agent.target.setUrlPhoto((String) value);
          return;
        }
        if (property.equals("idGrupo")) {
          agent.target.setIdGrupo((Integer) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("cuentaCimav")) {
          agent.target.setCuentaCimav((String) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("departamento")) {
          agent.target.setDepartamento((Departamento) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        throw new NonExistingPropertyException(property);
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
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_EmpleadoBaseProxy((EmpleadoBase) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_EmpleadoBaseProxy(state);
      }
    });
    class cimav_client_data_domain_EmpleadoProxy extends Empleado implements BindableProxy {
      private BindableProxyAgent<Empleado> agent;
      public cimav_client_data_domain_EmpleadoProxy(InitialState initialState) {
        this(new Empleado(), initialState);
      }

      public cimav_client_data_domain_EmpleadoProxy(Empleado target, InitialState initialState) {
        agent = new BindableProxyAgent<Empleado>(this, target, initialState);
        agent.propertyTypes.put("idSede", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("cuentaBanco", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idTipoEmpleado", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("jefe", new PropertyType(EmpleadoBase.class, true, false));
        agent.propertyTypes.put("sexo", new PropertyType(ESexo.class, false, false));
        agent.propertyTypes.put("idTipoContrato", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("fechaNacimiento", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("idClinica", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("direccionCP", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("curp", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("grupo", new PropertyType(EGrupo.class, false, false));
        agent.propertyTypes.put("fechaInicioContrato", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("fechaIngreso", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("tipoAntiguedad", new PropertyType(ETipoAntiguedad.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idEdoCivil", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("tipoEmpleado", new PropertyType(ETipoEmpleado.class, false, false));
        agent.propertyTypes.put("clinica", new PropertyType(EClinica.class, false, false));
        agent.propertyTypes.put("edoCivil", new PropertyType(EEdoCivil.class, false, false));
        agent.propertyTypes.put("direccionCalle", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("isDirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("apellidoPaterno", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("imss", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaFinContrato", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("rfc", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("nombre", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaSni", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("fechaBaja", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("dirty", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("apellidoMaterno", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("banco", new PropertyType(EBanco.class, false, false));
        agent.propertyTypes.put("emailPersonal", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("numSni", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("direccionColonia", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("tipoSNI", new PropertyType(ETipoSNI.class, false, false));
        agent.propertyTypes.put("idGrupo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("telefono", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("tipoContrato", new PropertyType(ETipoContrato.class, false, false));
        agent.propertyTypes.put("idBanco", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idSexo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("consecutivo", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idTipoSni", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("idTipoAntiguedad", new PropertyType(Integer.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
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
        clone.setIdSede(agent.target.getIdSede());
        clone.setCuentaBanco(agent.target.getCuentaBanco());
        clone.setIdTipoEmpleado(agent.target.getIdTipoEmpleado());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        if (agent.target.getJefe() instanceof BindableProxy) {
          clone.setJefe((EmpleadoBase) ((BindableProxy) getJefe()).deepUnwrap());
        } else {
          clone.setJefe(agent.target.getJefe());
        }
        clone.setSexo(agent.target.getSexo());
        clone.setIdTipoContrato(agent.target.getIdTipoContrato());
        clone.setFechaNacimiento(agent.target.getFechaNacimiento());
        clone.setIdClinica(agent.target.getIdClinica());
        clone.setDireccionCP(agent.target.getDireccionCP());
        clone.setCurp(agent.target.getCurp());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setGrupo(agent.target.getGrupo());
        clone.setFechaInicioContrato(agent.target.getFechaInicioContrato());
        clone.setStatus(agent.target.getStatus());
        clone.setFechaIngreso(agent.target.getFechaIngreso());
        clone.setTipoAntiguedad(agent.target.getTipoAntiguedad());
        clone.setCode(agent.target.getCode());
        clone.setIdEdoCivil(agent.target.getIdEdoCivil());
        clone.setTipoEmpleado(agent.target.getTipoEmpleado());
        clone.setClinica(agent.target.getClinica());
        clone.setEdoCivil(agent.target.getEdoCivil());
        clone.setDireccionCalle(agent.target.getDireccionCalle());
        clone.setIsDirty(agent.target.getIsDirty());
        clone.setSede(agent.target.getSede());
        clone.setApellidoPaterno(agent.target.getApellidoPaterno());
        clone.setImss(agent.target.getImss());
        clone.setFechaFinContrato(agent.target.getFechaFinContrato());
        clone.setRfc(agent.target.getRfc());
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        clone.setId(agent.target.getId());
        clone.setNombre(agent.target.getNombre());
        clone.setFechaSni(agent.target.getFechaSni());
        clone.setFechaBaja(agent.target.getFechaBaja());
        if (agent.target.getDepartamento() instanceof BindableProxy) {
          clone.setDepartamento((Departamento) ((BindableProxy) getDepartamento()).deepUnwrap());
        } else {
          clone.setDepartamento(agent.target.getDepartamento());
        }
        clone.setName(agent.target.getName());
        clone.setApellidoMaterno(agent.target.getApellidoMaterno());
        clone.setBanco(agent.target.getBanco());
        clone.setEmailPersonal(agent.target.getEmailPersonal());
        clone.setNumSni(agent.target.getNumSni());
        clone.setDireccionColonia(agent.target.getDireccionColonia());
        clone.setTipoSNI(agent.target.getTipoSNI());
        clone.setIdGrupo(agent.target.getIdGrupo());
        clone.setTelefono(agent.target.getTelefono());
        clone.setTipoContrato(agent.target.getTipoContrato());
        clone.setIdBanco(agent.target.getIdBanco());
        clone.setIdSexo(agent.target.getIdSexo());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        clone.setConsecutivo(agent.target.getConsecutivo());
        clone.setIdTipoSni(agent.target.getIdTipoSni());
        clone.setIdTipoAntiguedad(agent.target.getIdTipoAntiguedad());
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

      public Integer getIdSede() {
        return agent.target.getIdSede();
      }

      public void setIdSede(Integer idSede) {
        Integer oldValue = agent.target.getIdSede();
        agent.target.setIdSede(idSede);
        agent.updateWidgetsAndFireEvent("idSede", oldValue, idSede);
      }

      public String getCuentaBanco() {
        return agent.target.getCuentaBanco();
      }

      public void setCuentaBanco(String cuentaBanco) {
        String oldValue = agent.target.getCuentaBanco();
        agent.target.setCuentaBanco(cuentaBanco);
        agent.updateWidgetsAndFireEvent("cuentaBanco", oldValue, cuentaBanco);
      }

      public Integer getIdTipoEmpleado() {
        return agent.target.getIdTipoEmpleado();
      }

      public void setIdTipoEmpleado(Integer idTipoEmpleado) {
        Integer oldValue = agent.target.getIdTipoEmpleado();
        agent.target.setIdTipoEmpleado(idTipoEmpleado);
        agent.updateWidgetsAndFireEvent("idTipoEmpleado", oldValue, idTipoEmpleado);
      }

      public String getUrlPhoto() {
        return agent.target.getUrlPhoto();
      }

      public void setUrlPhoto(String urlPhoto) {
        String oldValue = agent.target.getUrlPhoto();
        agent.target.setUrlPhoto(urlPhoto);
        agent.updateWidgetsAndFireEvent("urlPhoto", oldValue, urlPhoto);
      }

      public EmpleadoBase getJefe() {
        return agent.target.getJefe();
      }

      public void setJefe(EmpleadoBase jefe) {
        if (agent.binders.containsKey("jefe")) {
          jefe = (EmpleadoBase) agent.binders.get("jefe").setModel(jefe, InitialState.FROM_MODEL, true);
        }
        EmpleadoBase oldValue = agent.target.getJefe();
        agent.target.setJefe(jefe);
        agent.updateWidgetsAndFireEvent("jefe", oldValue, jefe);
      }

      public ESexo getSexo() {
        return agent.target.getSexo();
      }

      public void setSexo(ESexo sexo) {
        ESexo oldValue = agent.target.getSexo();
        agent.target.setSexo(sexo);
        agent.updateWidgetsAndFireEvent("sexo", oldValue, sexo);
      }

      public Integer getIdTipoContrato() {
        return agent.target.getIdTipoContrato();
      }

      public void setIdTipoContrato(Integer idTipoContrato) {
        Integer oldValue = agent.target.getIdTipoContrato();
        agent.target.setIdTipoContrato(idTipoContrato);
        agent.updateWidgetsAndFireEvent("idTipoContrato", oldValue, idTipoContrato);
      }

      public Date getFechaNacimiento() {
        return agent.target.getFechaNacimiento();
      }

      public void setFechaNacimiento(Date fechaNacimiento) {
        Date oldValue = agent.target.getFechaNacimiento();
        agent.target.setFechaNacimiento(fechaNacimiento);
        agent.updateWidgetsAndFireEvent("fechaNacimiento", oldValue, fechaNacimiento);
      }

      public Integer getIdClinica() {
        return agent.target.getIdClinica();
      }

      public void setIdClinica(Integer idClinica) {
        Integer oldValue = agent.target.getIdClinica();
        agent.target.setIdClinica(idClinica);
        agent.updateWidgetsAndFireEvent("idClinica", oldValue, idClinica);
      }

      public String getDireccionCP() {
        return agent.target.getDireccionCP();
      }

      public void setDireccionCP(String direccionCP) {
        String oldValue = agent.target.getDireccionCP();
        agent.target.setDireccionCP(direccionCP);
        agent.updateWidgetsAndFireEvent("direccionCP", oldValue, direccionCP);
      }

      public String getCurp() {
        return agent.target.getCurp();
      }

      public void setCurp(String curp) {
        String oldValue = agent.target.getCurp();
        agent.target.setCurp(curp);
        agent.updateWidgetsAndFireEvent("curp", oldValue, curp);
      }

      public Tabulador getNivel() {
        return agent.target.getNivel();
      }

      public void setNivel(Tabulador nivel) {
        if (agent.binders.containsKey("nivel")) {
          nivel = (Tabulador) agent.binders.get("nivel").setModel(nivel, InitialState.FROM_MODEL, true);
        }
        Tabulador oldValue = agent.target.getNivel();
        agent.target.setNivel(nivel);
        agent.updateWidgetsAndFireEvent("nivel", oldValue, nivel);
      }

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent("idStatus", oldValue, idStatus);
      }

      public EGrupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(EGrupo grupo) {
        EGrupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent("grupo", oldValue, grupo);
      }

      public Date getFechaInicioContrato() {
        return agent.target.getFechaInicioContrato();
      }

      public void setFechaInicioContrato(Date fechaInicioContrato) {
        Date oldValue = agent.target.getFechaInicioContrato();
        agent.target.setFechaInicioContrato(fechaInicioContrato);
        agent.updateWidgetsAndFireEvent("fechaInicioContrato", oldValue, fechaInicioContrato);
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public void setStatus(EStatusEmpleado status) {
        EStatusEmpleado oldValue = agent.target.getStatus();
        agent.target.setStatus(status);
        agent.updateWidgetsAndFireEvent("status", oldValue, status);
      }

      public Date getFechaIngreso() {
        return agent.target.getFechaIngreso();
      }

      public void setFechaIngreso(Date fechaIngreso) {
        Date oldValue = agent.target.getFechaIngreso();
        agent.target.setFechaIngreso(fechaIngreso);
        agent.updateWidgetsAndFireEvent("fechaIngreso", oldValue, fechaIngreso);
      }

      public ETipoAntiguedad getTipoAntiguedad() {
        return agent.target.getTipoAntiguedad();
      }

      public void setTipoAntiguedad(ETipoAntiguedad tipoAntiguedad) {
        ETipoAntiguedad oldValue = agent.target.getTipoAntiguedad();
        agent.target.setTipoAntiguedad(tipoAntiguedad);
        agent.updateWidgetsAndFireEvent("tipoAntiguedad", oldValue, tipoAntiguedad);
      }

      public String getCode() {
        return agent.target.getCode();
      }

      public void setCode(String code) {
        String oldValue = agent.target.getCode();
        agent.target.setCode(code);
        agent.updateWidgetsAndFireEvent("code", oldValue, code);
      }

      public Integer getIdEdoCivil() {
        return agent.target.getIdEdoCivil();
      }

      public void setIdEdoCivil(Integer idEdoCivil) {
        Integer oldValue = agent.target.getIdEdoCivil();
        agent.target.setIdEdoCivil(idEdoCivil);
        agent.updateWidgetsAndFireEvent("idEdoCivil", oldValue, idEdoCivil);
      }

      public ETipoEmpleado getTipoEmpleado() {
        return agent.target.getTipoEmpleado();
      }

      public void setTipoEmpleado(ETipoEmpleado tipoEmpleado) {
        ETipoEmpleado oldValue = agent.target.getTipoEmpleado();
        agent.target.setTipoEmpleado(tipoEmpleado);
        agent.updateWidgetsAndFireEvent("tipoEmpleado", oldValue, tipoEmpleado);
      }

      public EClinica getClinica() {
        return agent.target.getClinica();
      }

      public void setClinica(EClinica clinica) {
        EClinica oldValue = agent.target.getClinica();
        agent.target.setClinica(clinica);
        agent.updateWidgetsAndFireEvent("clinica", oldValue, clinica);
      }

      public EEdoCivil getEdoCivil() {
        return agent.target.getEdoCivil();
      }

      public void setEdoCivil(EEdoCivil edoCivil) {
        EEdoCivil oldValue = agent.target.getEdoCivil();
        agent.target.setEdoCivil(edoCivil);
        agent.updateWidgetsAndFireEvent("edoCivil", oldValue, edoCivil);
      }

      public String getDireccionCalle() {
        return agent.target.getDireccionCalle();
      }

      public void setDireccionCalle(String direccionCalle) {
        String oldValue = agent.target.getDireccionCalle();
        agent.target.setDireccionCalle(direccionCalle);
        agent.updateWidgetsAndFireEvent("direccionCalle", oldValue, direccionCalle);
      }

      public Boolean getIsDirty() {
        return agent.target.getIsDirty();
      }

      public void setIsDirty(Boolean isDirty) {
        Boolean oldValue = agent.target.getIsDirty();
        agent.target.setIsDirty(isDirty);
        agent.updateWidgetsAndFireEvent("isDirty", oldValue, isDirty);
      }

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent("sede", oldValue, sede);
      }

      public String getApellidoPaterno() {
        return agent.target.getApellidoPaterno();
      }

      public void setApellidoPaterno(String apellidoPaterno) {
        String oldValue = agent.target.getApellidoPaterno();
        agent.target.setApellidoPaterno(apellidoPaterno);
        agent.updateWidgetsAndFireEvent("apellidoPaterno", oldValue, apellidoPaterno);
      }

      public String getImss() {
        return agent.target.getImss();
      }

      public void setImss(String imss) {
        String oldValue = agent.target.getImss();
        agent.target.setImss(imss);
        agent.updateWidgetsAndFireEvent("imss", oldValue, imss);
      }

      public Date getFechaFinContrato() {
        return agent.target.getFechaFinContrato();
      }

      public void setFechaFinContrato(Date fechaFinContrato) {
        Date oldValue = agent.target.getFechaFinContrato();
        agent.target.setFechaFinContrato(fechaFinContrato);
        agent.updateWidgetsAndFireEvent("fechaFinContrato", oldValue, fechaFinContrato);
      }

      public String getRfc() {
        return agent.target.getRfc();
      }

      public void setRfc(String rfc) {
        String oldValue = agent.target.getRfc();
        agent.target.setRfc(rfc);
        agent.updateWidgetsAndFireEvent("rfc", oldValue, rfc);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent("fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public Integer getId() {
        return agent.target.getId();
      }

      public void setId(Integer id) {
        Integer oldValue = agent.target.getId();
        agent.target.setId(id);
        agent.updateWidgetsAndFireEvent("id", oldValue, id);
      }

      public String getNombre() {
        return agent.target.getNombre();
      }

      public void setNombre(String nombre) {
        String oldValue = agent.target.getNombre();
        agent.target.setNombre(nombre);
        agent.updateWidgetsAndFireEvent("nombre", oldValue, nombre);
      }

      public Date getFechaSni() {
        return agent.target.getFechaSni();
      }

      public void setFechaSni(Date fechaSni) {
        Date oldValue = agent.target.getFechaSni();
        agent.target.setFechaSni(fechaSni);
        agent.updateWidgetsAndFireEvent("fechaSni", oldValue, fechaSni);
      }

      public Date getFechaBaja() {
        return agent.target.getFechaBaja();
      }

      public void setFechaBaja(Date fechaBaja) {
        Date oldValue = agent.target.getFechaBaja();
        agent.target.setFechaBaja(fechaBaja);
        agent.updateWidgetsAndFireEvent("fechaBaja", oldValue, fechaBaja);
      }

      public Departamento getDepartamento() {
        return agent.target.getDepartamento();
      }

      public void setDepartamento(Departamento departamento) {
        if (agent.binders.containsKey("departamento")) {
          departamento = (Departamento) agent.binders.get("departamento").setModel(departamento, InitialState.FROM_MODEL, true);
        }
        Departamento oldValue = agent.target.getDepartamento();
        agent.target.setDepartamento(departamento);
        agent.updateWidgetsAndFireEvent("departamento", oldValue, departamento);
      }

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
      }

      public Boolean isDirty() {
        return agent.target.isDirty();
      }

      public String getApellidoMaterno() {
        return agent.target.getApellidoMaterno();
      }

      public void setApellidoMaterno(String apellidoMaterno) {
        String oldValue = agent.target.getApellidoMaterno();
        agent.target.setApellidoMaterno(apellidoMaterno);
        agent.updateWidgetsAndFireEvent("apellidoMaterno", oldValue, apellidoMaterno);
      }

      public EBanco getBanco() {
        return agent.target.getBanco();
      }

      public void setBanco(EBanco banco) {
        EBanco oldValue = agent.target.getBanco();
        agent.target.setBanco(banco);
        agent.updateWidgetsAndFireEvent("banco", oldValue, banco);
      }

      public String getEmailPersonal() {
        return agent.target.getEmailPersonal();
      }

      public void setEmailPersonal(String emailPersonal) {
        String oldValue = agent.target.getEmailPersonal();
        agent.target.setEmailPersonal(emailPersonal);
        agent.updateWidgetsAndFireEvent("emailPersonal", oldValue, emailPersonal);
      }

      public String getNumSni() {
        return agent.target.getNumSni();
      }

      public void setNumSni(String numSni) {
        String oldValue = agent.target.getNumSni();
        agent.target.setNumSni(numSni);
        agent.updateWidgetsAndFireEvent("numSni", oldValue, numSni);
      }

      public String getDireccionColonia() {
        return agent.target.getDireccionColonia();
      }

      public void setDireccionColonia(String direccionColonia) {
        String oldValue = agent.target.getDireccionColonia();
        agent.target.setDireccionColonia(direccionColonia);
        agent.updateWidgetsAndFireEvent("direccionColonia", oldValue, direccionColonia);
      }

      public ETipoSNI getTipoSNI() {
        return agent.target.getTipoSNI();
      }

      public void setTipoSNI(ETipoSNI tipoSNI) {
        ETipoSNI oldValue = agent.target.getTipoSNI();
        agent.target.setTipoSNI(tipoSNI);
        agent.updateWidgetsAndFireEvent("tipoSNI", oldValue, tipoSNI);
      }

      public Integer getIdGrupo() {
        return agent.target.getIdGrupo();
      }

      public void setIdGrupo(Integer idGrupo) {
        Integer oldValue = agent.target.getIdGrupo();
        agent.target.setIdGrupo(idGrupo);
        agent.updateWidgetsAndFireEvent("idGrupo", oldValue, idGrupo);
      }

      public String getTelefono() {
        return agent.target.getTelefono();
      }

      public void setTelefono(String telefono) {
        String oldValue = agent.target.getTelefono();
        agent.target.setTelefono(telefono);
        agent.updateWidgetsAndFireEvent("telefono", oldValue, telefono);
      }

      public ETipoContrato getTipoContrato() {
        return agent.target.getTipoContrato();
      }

      public void setTipoContrato(ETipoContrato tipoContrato) {
        ETipoContrato oldValue = agent.target.getTipoContrato();
        agent.target.setTipoContrato(tipoContrato);
        agent.updateWidgetsAndFireEvent("tipoContrato", oldValue, tipoContrato);
      }

      public Integer getIdBanco() {
        return agent.target.getIdBanco();
      }

      public void setIdBanco(Integer idBanco) {
        Integer oldValue = agent.target.getIdBanco();
        agent.target.setIdBanco(idBanco);
        agent.updateWidgetsAndFireEvent("idBanco", oldValue, idBanco);
      }

      public Integer getIdSexo() {
        return agent.target.getIdSexo();
      }

      public void setIdSexo(Integer idSexo) {
        Integer oldValue = agent.target.getIdSexo();
        agent.target.setIdSexo(idSexo);
        agent.updateWidgetsAndFireEvent("idSexo", oldValue, idSexo);
      }

      public String getCuentaCimav() {
        return agent.target.getCuentaCimav();
      }

      public void setCuentaCimav(String cuentaCimav) {
        String oldValue = agent.target.getCuentaCimav();
        agent.target.setCuentaCimav(cuentaCimav);
        agent.updateWidgetsAndFireEvent("cuentaCimav", oldValue, cuentaCimav);
      }

      public Integer getConsecutivo() {
        return agent.target.getConsecutivo();
      }

      public void setConsecutivo(Integer consecutivo) {
        Integer oldValue = agent.target.getConsecutivo();
        agent.target.setConsecutivo(consecutivo);
        agent.updateWidgetsAndFireEvent("consecutivo", oldValue, consecutivo);
      }

      public Integer getIdTipoSni() {
        return agent.target.getIdTipoSni();
      }

      public void setIdTipoSni(Integer idTipoSni) {
        Integer oldValue = agent.target.getIdTipoSni();
        agent.target.setIdTipoSni(idTipoSni);
        agent.updateWidgetsAndFireEvent("idTipoSni", oldValue, idTipoSni);
      }

      public Integer getIdTipoAntiguedad() {
        return agent.target.getIdTipoAntiguedad();
      }

      public void setIdTipoAntiguedad(Integer idTipoAntiguedad) {
        Integer oldValue = agent.target.getIdTipoAntiguedad();
        agent.target.setIdTipoAntiguedad(idTipoAntiguedad);
        agent.updateWidgetsAndFireEvent("idTipoAntiguedad", oldValue, idTipoAntiguedad);
      }

      public Object get(String property) {
        if (property.equals("idSede")) {
          return getIdSede();
        }
        if (property.equals("cuentaBanco")) {
          return getCuentaBanco();
        }
        if (property.equals("idTipoEmpleado")) {
          return getIdTipoEmpleado();
        }
        if (property.equals("urlPhoto")) {
          return getUrlPhoto();
        }
        if (property.equals("jefe")) {
          return getJefe();
        }
        if (property.equals("sexo")) {
          return getSexo();
        }
        if (property.equals("idTipoContrato")) {
          return getIdTipoContrato();
        }
        if (property.equals("fechaNacimiento")) {
          return getFechaNacimiento();
        }
        if (property.equals("idClinica")) {
          return getIdClinica();
        }
        if (property.equals("direccionCP")) {
          return getDireccionCP();
        }
        if (property.equals("curp")) {
          return getCurp();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("fechaInicioContrato")) {
          return getFechaInicioContrato();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("fechaIngreso")) {
          return getFechaIngreso();
        }
        if (property.equals("tipoAntiguedad")) {
          return getTipoAntiguedad();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("idEdoCivil")) {
          return getIdEdoCivil();
        }
        if (property.equals("tipoEmpleado")) {
          return getTipoEmpleado();
        }
        if (property.equals("clinica")) {
          return getClinica();
        }
        if (property.equals("edoCivil")) {
          return getEdoCivil();
        }
        if (property.equals("direccionCalle")) {
          return getDireccionCalle();
        }
        if (property.equals("isDirty")) {
          return getIsDirty();
        }
        if (property.equals("sede")) {
          return getSede();
        }
        if (property.equals("apellidoPaterno")) {
          return getApellidoPaterno();
        }
        if (property.equals("imss")) {
          return getImss();
        }
        if (property.equals("fechaFinContrato")) {
          return getFechaFinContrato();
        }
        if (property.equals("rfc")) {
          return getRfc();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("id")) {
          return getId();
        }
        if (property.equals("nombre")) {
          return getNombre();
        }
        if (property.equals("fechaSni")) {
          return getFechaSni();
        }
        if (property.equals("fechaBaja")) {
          return getFechaBaja();
        }
        if (property.equals("departamento")) {
          return getDepartamento();
        }
        if (property.equals("name")) {
          return getName();
        }
        if (property.equals("dirty")) {
          return isDirty();
        }
        if (property.equals("apellidoMaterno")) {
          return getApellidoMaterno();
        }
        if (property.equals("banco")) {
          return getBanco();
        }
        if (property.equals("emailPersonal")) {
          return getEmailPersonal();
        }
        if (property.equals("numSni")) {
          return getNumSni();
        }
        if (property.equals("direccionColonia")) {
          return getDireccionColonia();
        }
        if (property.equals("tipoSNI")) {
          return getTipoSNI();
        }
        if (property.equals("idGrupo")) {
          return getIdGrupo();
        }
        if (property.equals("telefono")) {
          return getTelefono();
        }
        if (property.equals("tipoContrato")) {
          return getTipoContrato();
        }
        if (property.equals("idBanco")) {
          return getIdBanco();
        }
        if (property.equals("idSexo")) {
          return getIdSexo();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("consecutivo")) {
          return getConsecutivo();
        }
        if (property.equals("idTipoSni")) {
          return getIdTipoSni();
        }
        if (property.equals("idTipoAntiguedad")) {
          return getIdTipoAntiguedad();
        }
        throw new NonExistingPropertyException(property);
      }

      public void set(String property, Object value) {
        if (property.equals("idSede")) {
          agent.target.setIdSede((Integer) value);
          return;
        }
        if (property.equals("cuentaBanco")) {
          agent.target.setCuentaBanco((String) value);
          return;
        }
        if (property.equals("idTipoEmpleado")) {
          agent.target.setIdTipoEmpleado((Integer) value);
          return;
        }
        if (property.equals("urlPhoto")) {
          agent.target.setUrlPhoto((String) value);
          return;
        }
        if (property.equals("jefe")) {
          agent.target.setJefe((EmpleadoBase) value);
          return;
        }
        if (property.equals("sexo")) {
          agent.target.setSexo((ESexo) value);
          return;
        }
        if (property.equals("idTipoContrato")) {
          agent.target.setIdTipoContrato((Integer) value);
          return;
        }
        if (property.equals("fechaNacimiento")) {
          agent.target.setFechaNacimiento((Date) value);
          return;
        }
        if (property.equals("idClinica")) {
          agent.target.setIdClinica((Integer) value);
          return;
        }
        if (property.equals("direccionCP")) {
          agent.target.setDireccionCP((String) value);
          return;
        }
        if (property.equals("curp")) {
          agent.target.setCurp((String) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((EGrupo) value);
          return;
        }
        if (property.equals("fechaInicioContrato")) {
          agent.target.setFechaInicioContrato((Date) value);
          return;
        }
        if (property.equals("status")) {
          agent.target.setStatus((EStatusEmpleado) value);
          return;
        }
        if (property.equals("fechaIngreso")) {
          agent.target.setFechaIngreso((Date) value);
          return;
        }
        if (property.equals("tipoAntiguedad")) {
          agent.target.setTipoAntiguedad((ETipoAntiguedad) value);
          return;
        }
        if (property.equals("code")) {
          agent.target.setCode((String) value);
          return;
        }
        if (property.equals("idEdoCivil")) {
          agent.target.setIdEdoCivil((Integer) value);
          return;
        }
        if (property.equals("tipoEmpleado")) {
          agent.target.setTipoEmpleado((ETipoEmpleado) value);
          return;
        }
        if (property.equals("clinica")) {
          agent.target.setClinica((EClinica) value);
          return;
        }
        if (property.equals("edoCivil")) {
          agent.target.setEdoCivil((EEdoCivil) value);
          return;
        }
        if (property.equals("direccionCalle")) {
          agent.target.setDireccionCalle((String) value);
          return;
        }
        if (property.equals("isDirty")) {
          agent.target.setIsDirty((Boolean) value);
          return;
        }
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
          return;
        }
        if (property.equals("apellidoPaterno")) {
          agent.target.setApellidoPaterno((String) value);
          return;
        }
        if (property.equals("imss")) {
          agent.target.setImss((String) value);
          return;
        }
        if (property.equals("fechaFinContrato")) {
          agent.target.setFechaFinContrato((Date) value);
          return;
        }
        if (property.equals("rfc")) {
          agent.target.setRfc((String) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("id")) {
          agent.target.setId((Integer) value);
          return;
        }
        if (property.equals("nombre")) {
          agent.target.setNombre((String) value);
          return;
        }
        if (property.equals("fechaSni")) {
          agent.target.setFechaSni((Date) value);
          return;
        }
        if (property.equals("fechaBaja")) {
          agent.target.setFechaBaja((Date) value);
          return;
        }
        if (property.equals("departamento")) {
          agent.target.setDepartamento((Departamento) value);
          return;
        }
        if (property.equals("name")) {
          agent.target.setName((String) value);
          return;
        }
        if (property.equals("apellidoMaterno")) {
          agent.target.setApellidoMaterno((String) value);
          return;
        }
        if (property.equals("banco")) {
          agent.target.setBanco((EBanco) value);
          return;
        }
        if (property.equals("emailPersonal")) {
          agent.target.setEmailPersonal((String) value);
          return;
        }
        if (property.equals("numSni")) {
          agent.target.setNumSni((String) value);
          return;
        }
        if (property.equals("direccionColonia")) {
          agent.target.setDireccionColonia((String) value);
          return;
        }
        if (property.equals("tipoSNI")) {
          agent.target.setTipoSNI((ETipoSNI) value);
          return;
        }
        if (property.equals("idGrupo")) {
          agent.target.setIdGrupo((Integer) value);
          return;
        }
        if (property.equals("telefono")) {
          agent.target.setTelefono((String) value);
          return;
        }
        if (property.equals("tipoContrato")) {
          agent.target.setTipoContrato((ETipoContrato) value);
          return;
        }
        if (property.equals("idBanco")) {
          agent.target.setIdBanco((Integer) value);
          return;
        }
        if (property.equals("idSexo")) {
          agent.target.setIdSexo((Integer) value);
          return;
        }
        if (property.equals("cuentaCimav")) {
          agent.target.setCuentaCimav((String) value);
          return;
        }
        if (property.equals("consecutivo")) {
          agent.target.setConsecutivo((Integer) value);
          return;
        }
        if (property.equals("idTipoSni")) {
          agent.target.setIdTipoSni((Integer) value);
          return;
        }
        if (property.equals("idTipoAntiguedad")) {
          agent.target.setIdTipoAntiguedad((Integer) value);
          return;
        }
        throw new NonExistingPropertyException(property);
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
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_EmpleadoProxy((Empleado) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_EmpleadoProxy(state);
      }
    });
  }
}