/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

/**
 *
 * @author calderon
 */
public enum ECreditoInfoTipo {
    NONE(0, "No aplica"),
    VSM(1, "Veces salario mínimo"),
    FIJO(2, "Fijo");
    
    private int id;
    private String name;

    private ECreditoInfoTipo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public static ECreditoInfoTipo get(int id) {
        ECreditoInfoTipo result = ECreditoInfoTipo.NONE; // default
        for (ECreditoInfoTipo value : ECreditoInfoTipo.values()) {
            if (value.getId() == id) {
                result = value;
                break;
            }
        }
        return result;
    }
    
}
