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
public enum ETipoIncidencia {
    IE("IE", "INCAPACIDAD POR ENFERMEDAD",      "I"),
    IM("IM", "INCAPACIDAD POR MATERNIDAD",      "I"),
    IR("IR", "RIESGO DE TRABAJO",               "I"),
    IT("IT", "ACCIDENTE TRAYECTO",              "I"),
    AI("AI", "FALTA INJUSTIFICADA",             "F"),
    AP("AP", "PERMISO SIN GOCE DE SUELDO",      "F"),
    AS("AS", "SANCION POR RETARDOS",            "F"),
    AE("AE", "PERMISO ECONOMICO",               "P"),
    AM("AM", "PERMISO POR MATRIMONIO",          "P"),
    AN("AN", "PERMISO POR NACIMIENTO HIJO",     "P"),
    AF("AF", "PERMISO FALLACIMIENTO FAMILIAR",  "P"),
    AH("AH", "PERMISO POR TRES HORAS",          "P");
    
    public static String FALTA          = "F";
    public static String INCAPACIDAD    = "I";
    public static String PERMISO        = "P";
    
    private final String id;
    private final String descripcion;
    private final String clase; // 0 Incapacidad, 1 Falta, 2 Permiso

    private ETipoIncidencia(String id, String descripcion, String clase) {
        this.id = id;
        this.descripcion = descripcion;
        this.clase = clase;
    }

    @Override
    public String toString() {
        return this.id + " " + this.descripcion;
    }
    
    public static ETipoIncidencia get(String id) {
        ETipoIncidencia result = ETipoIncidencia.AI; // default
        for (ETipoIncidencia value : ETipoIncidencia.values()) {
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

    public String getClase() {
        return clase;
    }

    public Boolean isFalta() {
        return FALTA.equals(clase);
    }
    public Boolean isIncapacidad() {
        return INCAPACIDAD.equals(clase);
    }
    public Boolean isPermiso() {
        return PERMISO.equals(clase);
    }
    
}
