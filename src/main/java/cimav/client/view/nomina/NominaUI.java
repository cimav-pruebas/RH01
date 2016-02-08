/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.EmpleadoQuincenal;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.data.rest.NominaQuincenalREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NominaUI extends Composite {
    
    private static NominaUIUiBinder uiBinder = GWT.create(NominaUIUiBinder.class);
    
    interface NominaUIUiBinder extends UiBinder<Widget, NominaUI> {
    }
    
    @UiField
    Label totalLabel;
    @UiField
    Label antiguedadLabel;
    
    @UiField
    NominaMovimientosUI nominaPercepcionesUI;
    @UiField
    NominaMovimientosUI nominaDeduccionesUI;
    @UiField
    NominaSaldoUI nominaPercepcionesSaldoUI;
    @UiField
    NominaSaldoUI nominaDeduccionesSaldoUI;
    @UiField
    NominaFaltasUI nominaFaltasUI;
    @UiField
    HorasExtrasUI horasExtrasUI;

    @UiField
    NominaMovimientosUI nominaRepercucionesUI;
    @UiField
    NominaMovimientosUI nominaInternosUI;
    
    @UiField TabBadgeListItem tabPercepPorSaldo;
    @UiField TabBadgeListItem tabDeducPorSaldo;
    @UiField TabBadgeListItem tabDeducFaltas;
    @UiField TabBadgeListItem tabDeducHorasExtras;
    
    private EmpleadoNomina empleadoNominaLoaded;
    
    private EmpleadoREST empleadoREST;
    private NominaQuincenalREST nominaQuincenalREST;
    
    @UiField
    HorizontalPanel movimientosContenedor;
    
    public NominaUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
        PagosListener listener = new PagosListener();
        nominaPercepcionesSaldoUI.addPagosListener(listener);
        nominaDeduccionesSaldoUI.addPagosListener(listener);
        nominaFaltasUI.addFaltasListener(new FaltasListener());
        horasExtrasUI.addHorasExtrasListener(new HoraExtraListener());
    }
    
    @UiHandler({
        "tabPercepConceptos",   "tabPercepPorSaldo", 
        "tabDeducConceptos",    "tabDeducPorSaldo", 
        "tabDeducFaltas", "tabDeducHorasExtras",
        "tabRepercuciones", "tabInternos"})
    protected void onClick(ClickEvent e) {
        /* Todos los Tab Requieren el ReDraw */
        String str = e.getSource().toString();
        if (str.contains("tabPercepConceptos")) {
            nominaPercepcionesUI.dataGrid.redraw();
        } else if (str.contains("tabPercepPorSaldo")) {
            nominaPercepcionesSaldoUI.dataGrid.redraw();
        } else if (str.contains("tabDeducConceptos")) {
            nominaDeduccionesUI.dataGrid.redraw();
        } else if (str.contains("tabDeducPorSaldo")) {
            nominaDeduccionesSaldoUI.dataGrid.redraw();
        } else if (str.contains("tabDeducFaltas")) {
            nominaFaltasUI.dataGrid.redraw();
        } else if (str.contains("tabDeducHorasExtras")) {
            horasExtrasUI.dataGrid.redraw();
        } else if (str.contains("tabRepercuciones")) {
            nominaRepercucionesUI.dataGrid.redraw();
        } else if (str.contains("tabInternos")) {
            nominaInternosUI.dataGrid.redraw();
        }
    }
    
    @Override
    protected void onLoad() {
        super.onLoad(); //To change body of generated methods, choose Tools | Templates.
        String strClass  = ".cimav-client-view-nomina-NominaUI_NominaUIUiBinderImpl_GenCss_style-movimientos-contenedor";
        GQuery.$(strClass).$("td").attr("width", "50%");
    }   

    private EmpleadoREST getEmpleadosREST() {
        if (empleadoREST == null) {
            empleadoREST = new EmpleadoREST();

            empleadoREST.addRESTExecutedListener(new RestMethodExecutedListener());
        }
        return empleadoREST;
    }
    private class RestMethodExecutedListener implements BaseREST.RESTExecutedListener {

        @Override
        public void onRESTExecuted(MethodEvent methodEvent) {
            if (EMethod.FIND_EMPLEADO_NOMINA_BY_ID.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    
                    // re-carga el provider con el empleado reloaded
                    empleadoNominaLoaded = (EmpleadoNomina) methodEvent.getResult();
                    
                    List<NominaQuincenal> percepciones = new ArrayList<>();
                    List<NominaQuincenal> deducciones = new ArrayList<>();
                    List<NominaQuincenal> repercuciones = new ArrayList<>();
                    List<NominaQuincenal> internos = new ArrayList<>();
                    
                    if (empleadoNominaLoaded != null) {
                        percepciones.addAll(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.PERCEPCION));
                        deducciones.addAll(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.DEDUCCION));
                        repercuciones.addAll(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.REPERCUCION));
                        internos.addAll(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.INTERNO));
                    }
                    
                    nominaPercepcionesUI.setList(percepciones);
                    int percepSaldos = nominaPercepcionesSaldoUI.setEmpleado(empleadoNominaLoaded);
                            
                    nominaDeduccionesUI.setList(deducciones);
                    int deduccSaldos = nominaDeduccionesSaldoUI.setEmpleado(empleadoNominaLoaded);

                    EmpleadoQuincenal empleadoQuincenal = empleadoNominaLoaded != null ? empleadoNominaLoaded.getEmpleadoQuincenal() : null;
                    
                    int deducFaltas = nominaFaltasUI.setEmpleado(empleadoQuincenal);
                    Double horasExtras = horasExtrasUI.setEmpleado(empleadoQuincenal);
                            
                    BigDecimal totPercepciones = empleadoNominaLoaded != null ? empleadoNominaLoaded.getTotalPercepciones() : BigDecimal.ZERO;
                    BigDecimal totDeducciones = empleadoNominaLoaded != null ? empleadoNominaLoaded.getTotalDeducciones() : BigDecimal.ZERO;
                    BigDecimal total = totPercepciones.subtract(totDeducciones);
                    
                    totalLabel.setText(Utils.formatCurrency(total));

                    tabPercepPorSaldo.setCount(String.valueOf(percepSaldos));
                    tabDeducPorSaldo.setCount(String.valueOf(deduccSaldos));
                    tabDeducFaltas.setCount(String.valueOf(deducFaltas));
                    tabDeducHorasExtras.setCount(String.valueOf(horasExtras));
                    
                    nominaRepercucionesUI.setList(repercuciones);
                    nominaInternosUI.setList(internos);
                    
                    String antiguedad = empleadoNominaLoaded != null && empleadoQuincenal.getYearPAnt() != null ?
                            empleadoQuincenal.getYearPAnt() + " año(s), " + empleadoQuincenal.getMonthsPAnt() + " mes(es) y " + empleadoQuincenal.getDaysPAnt() + " día(s)"
                            : "Sin antigüedad";
                    antiguedadLabel.setText(antiguedad);
                    
                } else {
                    Window.alert("Error NominaUI: " + methodEvent.getReason());
                }
            } 
            
        }
    }
    
    private NominaQuincenalREST getNominaQuincenalREST() {
        if (nominaQuincenalREST == null) {
            nominaQuincenalREST = new NominaQuincenalREST();
            nominaQuincenalREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.FIND_BY_EMPLEADO_IDS.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            List<NominaQuincenal> list = (List<NominaQuincenal>) restEvent.getResult();
                            List<NominaQuincenal> percepciones = new ArrayList<>();
                            List<NominaQuincenal> deducciones = new ArrayList<>();
                            List<NominaQuincenal> repercuciones = new ArrayList<>();
                            List<NominaQuincenal> internos = new ArrayList<>();
                            BigDecimal total = BigDecimal.ZERO;
                            for(NominaQuincenal nq : list) {
                                if (ETipoConcepto.PERCEPCION.equals(nq.getConcepto().getTipoConcepto())) {
                                    percepciones.add(nq);
                                    if (nq.getConcepto().getSuma()) {
                                        total = total.add(nq.getCantidad());
                                    }
                                } else if (ETipoConcepto.DEDUCCION.equals(nq.getConcepto().getTipoConcepto())) {
                                    deducciones.add(nq);
                                    if (nq.getConcepto().getSuma()) {
                                        total = total.subtract(nq.getCantidad());
                                    }
                                } else if (ETipoConcepto.REPERCUCION.equals(nq.getConcepto().getTipoConcepto())) {
                                    repercuciones.add(nq);
                                }  else if (ETipoConcepto.INTERNO.equals(nq.getConcepto().getTipoConcepto())) {
                                    internos.add(nq);
                                }
                            }
                            nominaPercepcionesUI.setList(percepciones);
                            nominaDeduccionesUI.setList(deducciones);
                            nominaRepercucionesUI.setList(repercuciones);
                            nominaInternosUI.setList(internos);
                            
                            totalLabel.setText(Utils.formatCurrency(total));
                            
                        }
                    }
                }
            });
        }
        return nominaQuincenalREST;
    }
    
    public void setSelectedBean(Integer idEmpleadoBaseSelected) {
        // Viene desde la Lista de NominaBase
        
        idEmpleadoBaseSelected = idEmpleadoBaseSelected == null ? 0 : idEmpleadoBaseSelected;
        
        getEmpleadosREST().findEmpleadoNominaById(idEmpleadoBaseSelected);
        
    }

    public void setSelectedList(String ids) {
        
        getNominaQuincenalREST().findByEmpleadoIds(ids);
        
    }
    
    private class PagosListener implements NominaSaldoUI.PagosListener {
        @Override
        public void onPago(MethodEvent event) {
            if (EMethod.CREATE.equals(event.getMethod()) 
             || EMethod.UPDATE.equals(event.getMethod()) 
             || EMethod.DELETE.equals(event.getMethod())) {
                // Se creeo/modificó/borro un saldo/pago; reload al empleado
                NominaUI.this.setSelectedBean(empleadoNominaLoaded.getId());
            }
        }
    }
    private class FaltasListener implements NominaFaltasUI.FaltasListener {
        @Override
        public void onFalta(MethodEvent event) {
            if (EMethod.CREATE.equals(event.getMethod()) 
             || EMethod.UPDATE.equals(event.getMethod()) 
             || EMethod.DELETE.equals(event.getMethod())) {
                // Se creeo/modificó/borro una Falta; reload al empleado
                NominaUI.this.setSelectedBean(empleadoNominaLoaded.getId());
            }
        }
    }
    private class HoraExtraListener implements HorasExtrasUI.HorasExtrasListener {
        @Override
        public void onHoraExtra(MethodEvent event) {
            if (EMethod.CREATE.equals(event.getMethod()) 
             || EMethod.UPDATE.equals(event.getMethod()) 
             || EMethod.DELETE.equals(event.getMethod())) {
                // Se creeo/modificó/borro una HoraExtra; reload al empleado
                NominaUI.this.setSelectedBean(empleadoNominaLoaded.getId());
            }
        }
    }
    
}
