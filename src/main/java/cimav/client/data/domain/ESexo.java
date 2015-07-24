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
public enum ESexo {
    MASCULINO(0, "Masculino"),
    FEMENINO(1, "Femenino");
    
    private int id;
    private String nombre;

    private ESexo(int id, String nombre) {
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
    
    public static ESexo get(int id) {
        ESexo result = ESexo.MASCULINO; // default
        for (ESexo value : ESexo.values()) {
            if (value.getId() == id) {
                result = value;
                break;
            }
        }
        return result;
    }
}
