/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author calderon
 */
public class EmpleadoNominaHisto implements  Serializable {
    
    private List<MovimientoHisto> movimientosHisto;

    public EmpleadoNominaHisto() {
        movimientosHisto = new ArrayList<>();
    }

    public List<MovimientoHisto> getMovimientosHisto() {
        return movimientosHisto;
    }

    public void setMovimientosHisto(List<MovimientoHisto> movimientosHisto) {
        this.movimientosHisto = movimientosHisto;
    }

    /** 
     * Regresa la lista por TipoConcepto y TipoMovimiento
     * @param tipoConcepto
     * @param tipoMovimiento
     * @return 
     */
    public List<MovimientoHisto> getMovimientos(ETipoConcepto tipoConcepto, ETipoMovimiento tipoMovimiento) {
        List<MovimientoHisto> result = new ArrayList<>();
        for(MovimientoHisto movimiento : movimientosHisto) {
            if (movimiento.getConcepto().getTipoConcepto().equals(tipoConcepto) && movimiento.getConcepto().getTipoMovimiento().equals(tipoMovimiento)) {
                result.add(movimiento);
            }
        }
        return result;
    }

    /** 
     * Regresa la lista por TipoConcepto 
     * @param tipoConcepto
     * @return 
     */
    public List<MovimientoHisto> getMovimientos(ETipoConcepto tipoConcepto) {
        List<MovimientoHisto> result = new ArrayList<>();
        for(MovimientoHisto movimiento : movimientosHisto) {
            ETipoConcepto tipo = movimiento.getConcepto().getTipoConcepto();
            /*
            if (ETipoConcepto.REPERCUCION.equals(tipoConcepto)) {
                if (tipo.equals(ETipoConcepto.REPERCUCION) || tipo.equals(ETipoConcepto.INTERNO)) {
                    result.add(nominaQuincenal);
                }
            } else {
                if (tipo.equals(tipoConcepto)) {
                    result.add(nominaQuincenal);
                }
            }
            */
            if (tipo.equals(tipoConcepto)) {
                result.add(movimiento);
            }
        }
        return result;
    }
    
    public BigDecimal getTotalPercepciones() {
        List<MovimientoHisto> percepciones = this.getMovimientos(ETipoConcepto.PERCEPCION);
        BigDecimal result = BigDecimal.ZERO;
        for(MovimientoHisto percepcion : percepciones) {
            if (percepcion.getConcepto().getSuma()) {
                result = result.add(percepcion.getCantidad());
            }
        }
        return result;
    }
    
    public BigDecimal getTotalDeducciones() {
        List<MovimientoHisto> deducciones = this.getMovimientos(ETipoConcepto.DEDUCCION);
        BigDecimal result = BigDecimal.ZERO;
        for(MovimientoHisto deduccion : deducciones) {
            if (deduccion.getConcepto().getSuma()) {
                result = result.add(deduccion.getCantidad());
            }
        }
        return result;
    }
    
}
