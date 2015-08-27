/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

/**
 *
 * @author juan.calderon
 */
public enum ETipoFalta {
    IE("IE", "INCAPACIDAD POR ENFERMEDAD", true),
    IM("IM", "INCAPACIDAD POR MATERNIDAD", true),
    IR("IR", "RIESGO DE TRABAJO", true),
    IT("IT", "ACCIDENTE TRAYECTO", true),
    AI("AI", "FALTA INJUSTIFICADA", true),
    AP("AP", "PERMISO SIN GOCE DE SUELDO", true),
    AS("AS", "SANCION POR RETARDOS", true),
    AE("AE", "PERMISO ECONOMICO", true),
    AM("AM", "PERMISO POR MATRIMONIO", true),
    AN("AN", "PERMISO POR NACIMIENTO HIJO", true),
    AF("AF", "PERMISO FALLACIMIENTO FAMILIAR", true),
    AH("AH", "PERMISO POR TRES HORAS", true);
    
    private String id;
    private String descripcion;
    private Boolean seCobra;

    private ETipoFalta(String id, String descripcion, Boolean seCobra) {
        this.id = id;
        this.descripcion = descripcion;
        this.seCobra = seCobra;
    }

    public static ETipoFalta get(String id) {
        ETipoFalta result = ETipoFalta.AI; // default
        for (ETipoFalta value : ETipoFalta.values()) {
            if (value.getId().equals(id)) {
                result = value;
                break;
            }
        }
        return result;
    }
    
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Boolean getSeCobra() {
        return seCobra;
    }

    public void setSeCobra(Boolean seCobra) {
        this.seCobra = seCobra;
    }

    
}
