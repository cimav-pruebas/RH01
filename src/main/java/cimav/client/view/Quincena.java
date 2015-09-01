/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view;

import java.util.Date;

/**
 *
 * @author juan.calderon
 */
public class Quincena {
    
    private static Date fechaFin;
    private static int diaFin;
    private static Date fechaInicio;

    public static Date getFechaFin() {
        // Calendar.set(year + 1900, month, date) or GregorianCalendar(year + 1900, month, date).
        
        if (fechaFin == null) {
            fechaFin = new Date();
            int d = fechaFin.getDay();
            if (d <= 15) {
                //CalendarUtil
                diaFin = 15;
            } else {
                diaFin = 30;
            }
            fechaFin.setDate(diaFin);
        }
        return fechaFin;
    }

    public static int getDiaFin() {
        return diaFin;
    }
    
    
}
