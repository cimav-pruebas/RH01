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
    IE("IE", "INCAPACIDAD POR ENFERMEDAD",      0),
    IM("IM", "INCAPACIDAD POR MATERNIDAD",      0),
    IR("IR", "RIESGO DE TRABAJO",               0),
    IT("IT", "ACCIDENTE TRAYECTO",              0),
    AI("AI", "FALTA INJUSTIFICADA",             1),
    AP("AP", "PERMISO SIN GOCE DE SUELDO",      1),
    AS("AS", "SANCION POR RETARDOS",            1),
    AE("AE", "PERMISO ECONOMICO",               2),
    AM("AM", "PERMISO POR MATRIMONIO",          2),
    AN("AN", "PERMISO POR NACIMIENTO HIJO",     2),
    AF("AF", "PERMISO FALLACIMIENTO FAMILIAR",  2),
    AH("AH", "PERMISO POR TRES HORAS",          2);
    
    private final String id;
    private final String descripcion;
    private final Integer clase; // 0 Incapacidad, 1 Falta, 2 Permiso

    private ETipoFalta(String id, String descripcion, Integer clase) {
        this.id = id;
        this.descripcion = descripcion;
        this.clase = clase;
    }

    @Override
    public String toString() {
        return this.id + " " + this.descripcion;
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

    public String getDescripcion() {
        return descripcion;
    }

    public Integer getClase() {
        return clase;
    }
    
    public Boolean isIncapacidad() {
        return this.clase == 0;
    }
    public Boolean isFalta() {
        return this.clase == 1;
    }
    public Boolean isPermiso() {
        return this.clase == 2;
    }
    
}
