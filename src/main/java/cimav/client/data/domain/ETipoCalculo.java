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
public enum ETipoCalculo {
    FIJO("F", "Fijo");
    
    private String id;
    private String name;

    private ETipoCalculo(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public static ETipoCalculo get(String id) {
        ETipoCalculo result = ETipoCalculo.FIJO; // default
        for (ETipoCalculo value : ETipoCalculo.values()) {
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
