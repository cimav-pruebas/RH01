/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.google.gwt.user.datepicker.client.CalendarUtil;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

/**
 *
 * @author juan.calderon
 */
public class HoraExtra implements Serializable {
    
    private Integer id;
    private Integer idEmpleado;
    private Date dia;
    private Double horas;
    private Integer quincena;
    private Integer weekOfYear;

    public HoraExtra() {
        this.dia = new Date();
        this.horas = 0.00;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdEmpleado() {
        return idEmpleado;
    }

    public void setIdEmpleado(Integer idEmpleado) {
        this.idEmpleado = idEmpleado;
    }

    public Date getDia() {
        return dia;
    }

    public void setDia(Date dia) {
        this.dia = dia;
    }

    public Double getHoras() {
        return horas;
    }

    public void setHoras(Double horas) {
        this.horas = horas;
    }

    public Integer getQuincena() {
        return quincena;
    }

    public void setQuincena(Integer quincena) {
        this.quincena = quincena;
    }

    public Integer getWeekOfYear() {
        return weekOfYear;
    }

    public void setWeekOfYear(Integer weekOfYear) {
        this.weekOfYear = weekOfYear;
    }
    
    
    
}
