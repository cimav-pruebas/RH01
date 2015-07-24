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
public enum EEdoCivil {
    SOLTERO(0, "Soltero"),
    CASADO(1, "Casado"),
    DIVORCIADO(2, "Divorciado"),
    VIUDO(3, "Viudo"),
    UNION_LIBRE(4, "Unión libre");
    
    private int id;
    private String nombre;

    private EEdoCivil(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    public static EEdoCivil get(int id) {
        EEdoCivil result = EEdoCivil.SOLTERO; // default
        for (EEdoCivil value : EEdoCivil.values()) {
            if (value.getId() == id) {
                result = value;
                break;
            }
        }
        return result;
    }
}
