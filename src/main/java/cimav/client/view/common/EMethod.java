/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

/**
 *
 * @author juan.calderon
 */
public enum EMethod {

    // Full Objects
    FIND_ALL,
    FIND_BY_ID,
    CREATE,
    UPDATE,
    DELETE,
    CANCEL,
    
    // Base Objects
    FIND_BASE_ALL,
    FIND_BASE_BY_ID,
       
    // Empleado
    FIND_EMPLEADO_BASE_BY_ID_DEPTO,
    // EmpleadoNomina
    FIND_EMPLEADO_NOMINA_BY_ID,

    //Movimientos
    UPDATE_MOVIMIENTO,
    
    // CALCULO
    CALCULAR_ONE,
    CALCULAR_ALL
}
