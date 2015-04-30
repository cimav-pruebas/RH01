package org.jboss.errai.databinding.client;

import cimav.client.data.domain.BaseDomain;
import cimav.client.data.domain.Departamento;
import cimav.client.data.domain.EBanco;
import cimav.client.data.domain.EClinica;
import cimav.client.data.domain.ESede;
import cimav.client.data.domain.EStatusEmpleado;
import cimav.client.data.domain.ETipoAntiguedad;
import cimav.client.data.domain.ETipoContrato;
import cimav.client.data.domain.ETipoEmpleado;
import cimav.client.data.domain.ETipoSNI;
import cimav.client.data.domain.Empleado;
import cimav.client.data.domain.Grupo;
import cimav.client.data.domain.Tabulador;
import java.util.Date;
import org.jboss.errai.databinding.client.api.InitialState;

public class BindableProxyLoaderImpl implements BindableProxyLoader { public void loadBindableProxies() {
    class cimav_client_data_domain_GrupoProxy extends Grupo implements BindableProxy {
      private BindableProxyAgent<Grupo> agent;
      public cimav_client_data_domain_GrupoProxy(InitialState initialState) {
        this(new Grupo(), initialState);
      }

      public cimav_client_data_domain_GrupoProxy(Grupo target, InitialState initialState) {
        agent = new BindableProxyAgent<Grupo>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.copyValues();
      }

      public BindableProxyAgent getAgent() {
        return agent;
      }

      public void updateWidgets() {
        agent.updateWidgetsAndFireEvents();
      }

      public Grupo unwrap() {
        return agent.target;
      }

      public Grupo deepUnwrap() {
        final Grupo clone = new Grupo();
        clone.setId(agent.target.getId());
        clone.setName(agent.target.getName());
        clone.setCode(agent.target.getCode());
        return clone;
      }

      public boolean equals(Object obj) {
        if (obj instanceof cimav_client_data_domain_GrupoProxy) {
          obj = ((cimav_client_data_domain_GrupoProxy) obj).unwrap();
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

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
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
        if (property.equals("name")) {
          return getName();
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
        if (property.equals("name")) {
          agent.target.setName((String) value);
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
    }
    BindableProxyFactory.addBindableProxy(Grupo.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_GrupoProxy((Grupo) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_GrupoProxy(state);
      }
    });
    class cimav_client_data_domain_DepartamentoProxy extends Departamento implements BindableProxy {
      private BindableProxyAgent<Departamento> agent;
      public cimav_client_data_domain_DepartamentoProxy(InitialState initialState) {
        this(new Departamento(), initialState);
      }

      public cimav_client_data_domain_DepartamentoProxy(Departamento target, InitialState initialState) {
        agent = new BindableProxyAgent<Departamento>(this, target, initialState);
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
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
        clone.setName(agent.target.getName());
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

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
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
        if (property.equals("name")) {
          return getName();
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
        if (property.equals("name")) {
          agent.target.setName((String) value);
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
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
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
        clone.setName(agent.target.getName());
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

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
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
        if (property.equals("name")) {
          return getName();
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
        if (property.equals("name")) {
          agent.target.setName((String) value);
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
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
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
        clone.setName(agent.target.getName());
        clone.setCode(agent.target.getCode());
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

      public String getName() {
        return agent.target.getName();
      }

      public void setName(String name) {
        String oldValue = agent.target.getName();
        agent.target.setName(name);
        agent.updateWidgetsAndFireEvent("name", oldValue, name);
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
        if (property.equals("name")) {
          return getName();
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
        if (property.equals("name")) {
          agent.target.setName((String) value);
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
    }
    BindableProxyFactory.addBindableProxy(Tabulador.class, new BindableProxyProvider() {
      public BindableProxy getBindableProxy(Object model, InitialState state) {
        return new cimav_client_data_domain_TabuladorProxy((Tabulador) model, state);
      }
      public BindableProxy getBindableProxy(InitialState state) {
        return new cimav_client_data_domain_TabuladorProxy(state);
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
        agent.propertyTypes.put("sede", new PropertyType(ESede.class, false, false));
        agent.propertyTypes.put("idTipoEmpleado", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("urlPhoto", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("jefe", new PropertyType(Empleado.class, true, false));
        agent.propertyTypes.put("apellidoPaterno", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("imss", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaFinContrato", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("idTipoContrato", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("fechaAntiguedad", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("rfc", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("idClinica", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("id", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("nombre", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("fechaSni", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("fechaBaja", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("departamento", new PropertyType(Departamento.class, true, false));
        agent.propertyTypes.put("name", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("apellidoMaterno", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("curp", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("nivel", new PropertyType(Tabulador.class, true, false));
        agent.propertyTypes.put("grupo", new PropertyType(Grupo.class, true, false));
        agent.propertyTypes.put("banco", new PropertyType(EBanco.class, false, false));
        agent.propertyTypes.put("idStatus", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("fechaInicioContrato", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("status", new PropertyType(EStatusEmpleado.class, false, false));
        agent.propertyTypes.put("numSni", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("tipoSNI", new PropertyType(ETipoSNI.class, false, false));
        agent.propertyTypes.put("tipoContrato", new PropertyType(ETipoContrato.class, false, false));
        agent.propertyTypes.put("idBanco", new PropertyType(Integer.class, false, false));
        agent.propertyTypes.put("hasCredito", new PropertyType(Boolean.class, false, false));
        agent.propertyTypes.put("numCredito", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("tipoAntiguedad", new PropertyType(ETipoAntiguedad.class, false, false));
        agent.propertyTypes.put("fechaIngreso", new PropertyType(Date.class, false, false));
        agent.propertyTypes.put("code", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("cuentaCimav", new PropertyType(String.class, false, false));
        agent.propertyTypes.put("tipoEmpleado", new PropertyType(ETipoEmpleado.class, false, false));
        agent.propertyTypes.put("clinica", new PropertyType(EClinica.class, false, false));
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
        clone.setSede(agent.target.getSede());
        clone.setIdTipoEmpleado(agent.target.getIdTipoEmpleado());
        clone.setUrlPhoto(agent.target.getUrlPhoto());
        if (agent.target.getJefe() instanceof BindableProxy) {
          clone.setJefe((Empleado) ((BindableProxy) getJefe()).deepUnwrap());
        } else {
          clone.setJefe(agent.target.getJefe());
        }
        clone.setApellidoPaterno(agent.target.getApellidoPaterno());
        clone.setImss(agent.target.getImss());
        clone.setFechaFinContrato(agent.target.getFechaFinContrato());
        clone.setIdTipoContrato(agent.target.getIdTipoContrato());
        clone.setFechaAntiguedad(agent.target.getFechaAntiguedad());
        clone.setRfc(agent.target.getRfc());
        clone.setIdClinica(agent.target.getIdClinica());
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
        clone.setCurp(agent.target.getCurp());
        if (agent.target.getNivel() instanceof BindableProxy) {
          clone.setNivel((Tabulador) ((BindableProxy) getNivel()).deepUnwrap());
        } else {
          clone.setNivel(agent.target.getNivel());
        }
        if (agent.target.getGrupo() instanceof BindableProxy) {
          clone.setGrupo((Grupo) ((BindableProxy) getGrupo()).deepUnwrap());
        } else {
          clone.setGrupo(agent.target.getGrupo());
        }
        clone.setBanco(agent.target.getBanco());
        clone.setIdStatus(agent.target.getIdStatus());
        clone.setFechaInicioContrato(agent.target.getFechaInicioContrato());
        clone.setStatus(agent.target.getStatus());
        clone.setNumSni(agent.target.getNumSni());
        clone.setTipoSNI(agent.target.getTipoSNI());
        clone.setTipoContrato(agent.target.getTipoContrato());
        clone.setIdBanco(agent.target.getIdBanco());
        clone.setHasCredito(agent.target.getHasCredito());
        clone.setNumCredito(agent.target.getNumCredito());
        clone.setTipoAntiguedad(agent.target.getTipoAntiguedad());
        clone.setFechaIngreso(agent.target.getFechaIngreso());
        clone.setCode(agent.target.getCode());
        clone.setCuentaCimav(agent.target.getCuentaCimav());
        clone.setTipoEmpleado(agent.target.getTipoEmpleado());
        clone.setClinica(agent.target.getClinica());
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

      public ESede getSede() {
        return agent.target.getSede();
      }

      public void setSede(ESede sede) {
        ESede oldValue = agent.target.getSede();
        agent.target.setSede(sede);
        agent.updateWidgetsAndFireEvent("sede", oldValue, sede);
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

      public Empleado getJefe() {
        return agent.target.getJefe();
      }

      public void setJefe(Empleado jefe) {
        if (agent.binders.containsKey("jefe")) {
          jefe = (Empleado) agent.binders.get("jefe").setModel(jefe, InitialState.FROM_MODEL, true);
        }
        Empleado oldValue = agent.target.getJefe();
        agent.target.setJefe(jefe);
        agent.updateWidgetsAndFireEvent("jefe", oldValue, jefe);
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

      public Integer getIdTipoContrato() {
        return agent.target.getIdTipoContrato();
      }

      public void setIdTipoContrato(Integer idTipoContrato) {
        Integer oldValue = agent.target.getIdTipoContrato();
        agent.target.setIdTipoContrato(idTipoContrato);
        agent.updateWidgetsAndFireEvent("idTipoContrato", oldValue, idTipoContrato);
      }

      public Date getFechaAntiguedad() {
        return agent.target.getFechaAntiguedad();
      }

      public void setFechaAntiguedad(Date fechaAntiguedad) {
        Date oldValue = agent.target.getFechaAntiguedad();
        agent.target.setFechaAntiguedad(fechaAntiguedad);
        agent.updateWidgetsAndFireEvent("fechaAntiguedad", oldValue, fechaAntiguedad);
      }

      public String getRfc() {
        return agent.target.getRfc();
      }

      public void setRfc(String rfc) {
        String oldValue = agent.target.getRfc();
        agent.target.setRfc(rfc);
        agent.updateWidgetsAndFireEvent("rfc", oldValue, rfc);
      }

      public Integer getIdClinica() {
        return agent.target.getIdClinica();
      }

      public void setIdClinica(Integer idClinica) {
        Integer oldValue = agent.target.getIdClinica();
        agent.target.setIdClinica(idClinica);
        agent.updateWidgetsAndFireEvent("idClinica", oldValue, idClinica);
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

      public String getApellidoMaterno() {
        return agent.target.getApellidoMaterno();
      }

      public void setApellidoMaterno(String apellidoMaterno) {
        String oldValue = agent.target.getApellidoMaterno();
        agent.target.setApellidoMaterno(apellidoMaterno);
        agent.updateWidgetsAndFireEvent("apellidoMaterno", oldValue, apellidoMaterno);
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

      public Grupo getGrupo() {
        return agent.target.getGrupo();
      }

      public void setGrupo(Grupo grupo) {
        if (agent.binders.containsKey("grupo")) {
          grupo = (Grupo) agent.binders.get("grupo").setModel(grupo, InitialState.FROM_MODEL, true);
        }
        Grupo oldValue = agent.target.getGrupo();
        agent.target.setGrupo(grupo);
        agent.updateWidgetsAndFireEvent("grupo", oldValue, grupo);
      }

      public EBanco getBanco() {
        return agent.target.getBanco();
      }

      public void setBanco(EBanco banco) {
        EBanco oldValue = agent.target.getBanco();
        agent.target.setBanco(banco);
        agent.updateWidgetsAndFireEvent("banco", oldValue, banco);
      }

      public Integer getIdStatus() {
        return agent.target.getIdStatus();
      }

      public void setIdStatus(Integer idStatus) {
        Integer oldValue = agent.target.getIdStatus();
        agent.target.setIdStatus(idStatus);
        agent.updateWidgetsAndFireEvent("idStatus", oldValue, idStatus);
      }

      public Date getFechaInicioContrato() {
        return agent.target.getFechaInicioContrato();
      }

      public EStatusEmpleado getStatus() {
        return agent.target.getStatus();
      }

      public String getNumSni() {
        return agent.target.getNumSni();
      }

      public void setNumSni(String numSni) {
        String oldValue = agent.target.getNumSni();
        agent.target.setNumSni(numSni);
        agent.updateWidgetsAndFireEvent("numSni", oldValue, numSni);
      }

      public ETipoSNI getTipoSNI() {
        return agent.target.getTipoSNI();
      }

      public ETipoContrato getTipoContrato() {
        return agent.target.getTipoContrato();
      }

      public Integer getIdBanco() {
        return agent.target.getIdBanco();
      }

      public void setIdBanco(Integer idBanco) {
        Integer oldValue = agent.target.getIdBanco();
        agent.target.setIdBanco(idBanco);
        agent.updateWidgetsAndFireEvent("idBanco", oldValue, idBanco);
      }

      public Boolean getHasCredito() {
        return agent.target.getHasCredito();
      }

      public void setHasCredito(Boolean hasCredito) {
        Boolean oldValue = agent.target.getHasCredito();
        agent.target.setHasCredito(hasCredito);
        agent.updateWidgetsAndFireEvent("hasCredito", oldValue, hasCredito);
      }

      public String getNumCredito() {
        return agent.target.getNumCredito();
      }

      public void setNumCredito(String numCredito) {
        String oldValue = agent.target.getNumCredito();
        agent.target.setNumCredito(numCredito);
        agent.updateWidgetsAndFireEvent("numCredito", oldValue, numCredito);
      }

      public ETipoAntiguedad getTipoAntiguedad() {
        return agent.target.getTipoAntiguedad();
      }

      public Date getFechaIngreso() {
        return agent.target.getFechaIngreso();
      }

      public void setFechaIngreso(Date fechaIngreso) {
        Date oldValue = agent.target.getFechaIngreso();
        agent.target.setFechaIngreso(fechaIngreso);
        agent.updateWidgetsAndFireEvent("fechaIngreso", oldValue, fechaIngreso);
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

      public ETipoEmpleado getTipoEmpleado() {
        return agent.target.getTipoEmpleado();
      }

      public EClinica getClinica() {
        return agent.target.getClinica();
      }

      public void setClinica(EClinica clinica) {
        EClinica oldValue = agent.target.getClinica();
        agent.target.setClinica(clinica);
        agent.updateWidgetsAndFireEvent("clinica", oldValue, clinica);
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
        if (property.equals("sede")) {
          return getSede();
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
        if (property.equals("apellidoPaterno")) {
          return getApellidoPaterno();
        }
        if (property.equals("imss")) {
          return getImss();
        }
        if (property.equals("fechaFinContrato")) {
          return getFechaFinContrato();
        }
        if (property.equals("idTipoContrato")) {
          return getIdTipoContrato();
        }
        if (property.equals("fechaAntiguedad")) {
          return getFechaAntiguedad();
        }
        if (property.equals("rfc")) {
          return getRfc();
        }
        if (property.equals("idClinica")) {
          return getIdClinica();
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
        if (property.equals("apellidoMaterno")) {
          return getApellidoMaterno();
        }
        if (property.equals("curp")) {
          return getCurp();
        }
        if (property.equals("nivel")) {
          return getNivel();
        }
        if (property.equals("grupo")) {
          return getGrupo();
        }
        if (property.equals("banco")) {
          return getBanco();
        }
        if (property.equals("idStatus")) {
          return getIdStatus();
        }
        if (property.equals("fechaInicioContrato")) {
          return getFechaInicioContrato();
        }
        if (property.equals("status")) {
          return getStatus();
        }
        if (property.equals("numSni")) {
          return getNumSni();
        }
        if (property.equals("tipoSNI")) {
          return getTipoSNI();
        }
        if (property.equals("tipoContrato")) {
          return getTipoContrato();
        }
        if (property.equals("idBanco")) {
          return getIdBanco();
        }
        if (property.equals("hasCredito")) {
          return getHasCredito();
        }
        if (property.equals("numCredito")) {
          return getNumCredito();
        }
        if (property.equals("tipoAntiguedad")) {
          return getTipoAntiguedad();
        }
        if (property.equals("fechaIngreso")) {
          return getFechaIngreso();
        }
        if (property.equals("code")) {
          return getCode();
        }
        if (property.equals("cuentaCimav")) {
          return getCuentaCimav();
        }
        if (property.equals("tipoEmpleado")) {
          return getTipoEmpleado();
        }
        if (property.equals("clinica")) {
          return getClinica();
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
        if (property.equals("sede")) {
          agent.target.setSede((ESede) value);
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
          agent.target.setJefe((Empleado) value);
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
        if (property.equals("idTipoContrato")) {
          agent.target.setIdTipoContrato((Integer) value);
          return;
        }
        if (property.equals("fechaAntiguedad")) {
          agent.target.setFechaAntiguedad((Date) value);
          return;
        }
        if (property.equals("rfc")) {
          agent.target.setRfc((String) value);
          return;
        }
        if (property.equals("idClinica")) {
          agent.target.setIdClinica((Integer) value);
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
        if (property.equals("curp")) {
          agent.target.setCurp((String) value);
          return;
        }
        if (property.equals("nivel")) {
          agent.target.setNivel((Tabulador) value);
          return;
        }
        if (property.equals("grupo")) {
          agent.target.setGrupo((Grupo) value);
          return;
        }
        if (property.equals("banco")) {
          agent.target.setBanco((EBanco) value);
          return;
        }
        if (property.equals("idStatus")) {
          agent.target.setIdStatus((Integer) value);
          return;
        }
        if (property.equals("numSni")) {
          agent.target.setNumSni((String) value);
          return;
        }
        if (property.equals("idBanco")) {
          agent.target.setIdBanco((Integer) value);
          return;
        }
        if (property.equals("hasCredito")) {
          agent.target.setHasCredito((Boolean) value);
          return;
        }
        if (property.equals("numCredito")) {
          agent.target.setNumCredito((String) value);
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
        if (property.equals("clinica")) {
          agent.target.setClinica((EClinica) value);
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

      public int compareTo(BaseDomain a0) {
        final int returnValue = agent.target.compareTo(a0);
        agent.updateWidgetsAndFireEvents();
        return returnValue;
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
