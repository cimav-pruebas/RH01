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
public enum ETipoPension {
    SIN(0, "Sin pensión"),
    PORCEN_NETO(1, "Sobre neto"),
    PORCEN_PERCEPCIONES(2, "Sobre total de percepciones"),
    PORCEN_CONCEPTOS(3, "Sobre conceptos seleccionados");
    
    private int id;
    private String nombre;

    private ETipoPension(int id, String nombre) {
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
    
    public static ETipoPension get(int id) {
        ETipoPension result = ETipoPension.SIN; // default
        for (ETipoPension value : ETipoPension.values()) {
            if (value.getId() == id) {
                result = value;
                break;
            }
        }
        return result;
    }
}
