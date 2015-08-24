/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.util.Objects;

/**
 *
 * @author juan.calderon
 */
public enum EGrupo {
    AYA(1, "AYA", "ADMINISTRATIVOS Y DE APOYO"),
    CYT(2, "CYT", "CIENTIFICOS Y TECNOLOGICOS"),
    MMS(3, "MMS", "MANDOS MEDIOS Y SUPERIORES"),
    HON(4, "HON", "HONORARIOS ASIMILABLES A SUELDO");
    
    private Integer id;
    private String code;
    private String name;
    
    private EGrupo(Integer id, String code, String name) {
        this.id = id;
        this.code = code;
        this.name = name;
    }
    
    public static EGrupo get(Integer id) {
        EGrupo result = EGrupo.AYA; // default
        for (EGrupo value : EGrupo.values()) {
            if (Objects.equals(value.getId(), id)) {
                result = value;
                break;
            }
        }
        return result;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
}
