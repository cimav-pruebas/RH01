/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.data.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gwt.i18n.client.DateTimeFormat;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author juan.calderon
 */
public class Incidencia implements Serializable {
    
    private Integer id;
    private Integer idEmpleado;
    private Integer dias;
    private Integer diasHabiles;
    private Integer diasInhabiles;
    private Date fechaInicial;
    @JsonIgnore private ETipoIncidencia tipo;
    private String code;
    private String folio;
    private String clase;

    public Incidencia() {
        fechaInicial = new Date();
        tipo = ETipoIncidencia.AI;
        clase = tipo.getClase();
        folio = "";
        this.setDias(1);
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

    public Integer getDias() {
        return dias;
    }

    public final void setDias(Integer dias) {
        this.dias = dias;
    }

    public void ajustar() {

        // Fecha
        Date fechaInicioQuin = Quincena.get().getFechaInicio();
        Date fechaFinQuin = Quincena.get().getFechaFin();
        
        if (this.getTipo().isIncapacidad()) {
            // la fecha de inicio para Incapacidades tiene que ser mayor o igual al inicio de la quincena
            //boolean isAntes = this.fechaInicial.compareTo(fechaInicioQuin) < 0;
            if (this.fechaInicial.before(fechaInicioQuin)) {
                this.fechaInicial = fechaInicioQuin;
            }
        } else if (this.getTipo().isFalta()) {
            // la falta tiene que estar entre la fecha de inicio y la fecha de fin de la quincena
            //boolean isBetween = (this.fechaInicial.compareTo(fechaInicioQuin) >= 0) && (this.fechaInicial.compareTo(fechaFinQuin) <= 0); // incluye endpoints
            boolean isBetween = (this.fechaInicial.equals(fechaInicioQuin) || this.fechaInicial.after(fechaInicioQuin)) && 
                    (this.fechaInicial.equals(fechaFinQuin) || this.fechaInicial.before(fechaFinQuin));
            if (!isBetween) {
                // Si no esta, ajustarla a la fecha de inicio
                this.fechaInicial = Quincena.get().getFechaInicio();
            }
        } else if (this.getTipo().isPermiso()) {
            
        }
        
        // Dias
        if (this.getTipo().isIncapacidad()) {
            if (this.dias > 90) {
                // Máximo 90 incapacidades
                this.dias = 90;
            }
            Date fechaAux = this.fechaInicial;
            this.diasHabiles = 0;
            this.diasInhabiles = 0;
            while ( (fechaFinQuin.after(fechaAux) || fechaFinQuin.equals(fechaAux)) && ((this.diasHabiles + this.diasInhabiles) < this.dias) ) {
                // mientras no revase a la fecha fin
                // y mientras Habiles + Inhabibles no superen los dias totales
                DateTimeFormat format = DateTimeFormat.getFormat("c"); // try with "E" pattern also
                String dayOfWeek = format.format(fechaAux); //0 for sunday
                if (dayOfWeek.equals("0") || dayOfWeek.equals("6")) { 
                    this.diasInhabiles++;
                } else {
                    this.diasHabiles++;
                }
                // avanzar 1 día
                fechaAux = new Date(fechaAux.getTime() + TimeUnit.DAYS.toMillis( 1 ));
            }
        } else if (this.getTipo().isFalta()) {
            if (this.dias > 5) {
                // Máximo 5 faltas
                this.dias = 5;
            }
            if (this.dias > Quincena.get().getDiasOrdinarios()) {
                // Máximo igual a los ordinarios
                this.dias = Quincena.get().getDiasOrdinarios();
            }
            // todas la faltas capturadas son sobre dias hábiles; no hay faltas en días inhabiles o de asueto
            this.diasHabiles = this.dias;
            // no hay faltas en días de descanso
            this.diasInhabiles = 0;
        } else if (this.getTipo().isPermiso()) {
            // no cuentan como faltas
            //this.incidencias = 0;
        }  
    }
    
    public Date getFechaInicial() {
        return fechaInicial;
    }

    public void setFechaInicial(Date fechaInicial) {
        this.fechaInicial = fechaInicial;
    }

    public Integer getDiasHabiles() {
        return diasHabiles;
    }

    public void setDiasHabiles(Integer diasHabiles) {
        this.diasHabiles = diasHabiles;
    }

    public Integer getDiasInhabiles() {
        return diasInhabiles;
    }

    public void setDiasInhabiles(Integer diasInhabiles) {
        this.diasInhabiles = diasInhabiles;
    }
    
    public Integer getDiasRestantes() {
        // Dias que se pasan a la siguiente quincena
        return this.dias - (this.diasHabiles + this.diasInhabiles);
    }
    
    public ETipoIncidencia getTipo() {
        return tipo;
    }

    public void setTipo(ETipoIncidencia tipo) {
        this.tipo = tipo;
        this.code = tipo != null ? tipo.getId() : "XX";
        this.clase = this.tipo.getClase();
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.tipo = ETipoIncidencia.get(code);
        this.code = code;
        this.clase = this.tipo.getClase();
    }

    public String getFolio() {
        return folio;
    }

    public void setFolio(String folio) {
        this.folio = folio;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }
    
}