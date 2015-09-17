/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.rest.CalculoREST;

/**
 *
 * @author juan.calderon
 */
public class Calculo {

    private CalculoREST calculoREST;
    
    public Calculo() {
    }
    
    public CalculoREST getREST() {
        if (calculoREST == null) {
            calculoREST = new CalculoREST();
        }
        return calculoREST;
    }
    
    public void calcular(int id) {
        getREST().calcular(id);
    }
    public void calcular(String ids) {
        getREST().calcular(ids);
    }
    
}
