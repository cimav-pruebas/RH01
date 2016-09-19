/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nominahisto;

import cimav.client.view.nomina.*;
import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.EmpleadoNominaHisto;
import cimav.client.data.domain.MovimientoHisto;
import cimav.client.data.domain.Quincena;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.data.rest.NominaQuincenalREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavPills;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
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
public class NominaHistoUI extends Composite {
    
    private static NominaHistoUIUiBinder uiBinder = GWT.create(NominaHistoUIUiBinder.class);
    
    interface NominaHistoUIUiBinder extends UiBinder<Widget, NominaHistoUI> {
    }
    
    @UiField
    Label totalLabel;
    
    @UiField
    NominaMovimientosHistoUI nominaPercepcionesUI;
    @UiField
    NominaMovimientosHistoUI nominaDeduccionesUI;
    @UiField
    NominaRepercucionesHistoUI nominaRepercucionesUI;
    @UiField
    NominaMovimientosHistoUI nominaInternosUI;
    
    private EmpleadoNominaHisto empleadoNominaLoaded;
    
    private EmpleadoREST empleadoREST;
    private NominaQuincenalREST nominaQuincenalREST;
    
    @UiField
    HorizontalPanel movimientosContenedor;
    
    @UiField
    NavPills navQuincenas;
    
    private Integer quincenaSelected = 0;
    private Integer idEmpleadoBaseSelected = 0;
    
    public NominaHistoUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
        navQuincenas.getElement().setAttribute("style", "padding: 8px; border-bottom:1px solid lightgray;");
        int quinActual = Quincena.get().getQuincena();
        for(int i=1; i <= 24; i++) {
            NavLink navLink = new NavLink("" + i);
            navLink.setName("" + i);
            if (i>=quinActual) {
                navLink.setDisabled(true);
                navLink.getAnchor().getElement().setAttribute("style", "border-radius: 100%; font-size: 18px; color:lightgray");
            } else {
                navLink.setDisabled(false);
                navLink.getAnchor().getElement().setAttribute("style", "border-radius: 100%; font-size: 18px;");
            }
            navLink.addClickHandler((ClickEvent event) -> {
                // Pone todos los sub-menus en DesActivados
                for (int j = 0; j < navQuincenas.getWidgetCount(); j++) {
                    Widget widgetNavLink = navQuincenas.getWidget(j);
                    if (widgetNavLink instanceof NavLink) {
                        NavLink navLink1 = (NavLink) widgetNavLink;
                        navLink1.setActive(false);
                    }
                }
                // Activa el clickeado
                IconAnchor src = (IconAnchor) event.getSource();
                NavLink navLink2 = (NavLink) src.getParent();
                navLink2.setActive(true);
                
                quincenaSelected = Integer.valueOf(navLink2.getName());
                
                setSelectedBean(idEmpleadoBaseSelected);
            });
            navQuincenas.add(navLink);
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
            if (EMethod.FIND_EMPLEADO_NOMINA_HISTO_BY_ID_YEAR_QUINCENA.equals(methodEvent.getMethod())) {
                if (ETypeResult.SUCCESS.equals(methodEvent.getTypeResult())) {
                    
                    // re-carga el provider con el empleado reloaded
                    empleadoNominaLoaded = (EmpleadoNominaHisto) methodEvent.getResult();
                    
                    List<MovimientoHisto> percepciones = new ArrayList<>();
                    List<MovimientoHisto> deducciones = new ArrayList<>();
                    List<MovimientoHisto> repercuciones = new ArrayList<>();
                    List<MovimientoHisto> internos = new ArrayList<>();
                    
                    if (empleadoNominaLoaded != null) {
                        percepciones.addAll(empleadoNominaLoaded.getMovimientos(ETipoConcepto.PERCEPCION));
                        deducciones.addAll(empleadoNominaLoaded.getMovimientos(ETipoConcepto.DEDUCCION));
                        repercuciones.addAll(empleadoNominaLoaded.getMovimientos(ETipoConcepto.REPERCUCION));
                        internos.addAll(empleadoNominaLoaded.getMovimientos(ETipoConcepto.INTERNO));
                    }
                    
                    nominaPercepcionesUI.setList(percepciones);
                    nominaDeduccionesUI.setList(deducciones);

                    //NominaHisto nomina = empleadoNominaLoaded != null ? empleadoNominaLoaded.getNomina() : null;
                    
                    BigDecimal totPercepciones = empleadoNominaLoaded != null ? empleadoNominaLoaded.getTotalPercepciones() : BigDecimal.ZERO;
                    BigDecimal totDeducciones = empleadoNominaLoaded != null ? empleadoNominaLoaded.getTotalDeducciones() : BigDecimal.ZERO;
                    BigDecimal total = totPercepciones.subtract(totDeducciones);
                    
                    totalLabel.setText(Utils.formatCurrency(total));

                    nominaRepercucionesUI.setList(repercuciones);
                    nominaInternosUI.setList(internos);
                    
                } else {
                    Window.alert("Error NominaUI: " + methodEvent.getReason());
                }
            } 
            
        }
    }
    /*
    private NominaQuincenalREST getNominaQuincenalREST() {
        if (nominaQuincenalREST == null) {
            nominaQuincenalREST = new NominaQuincenalREST();
            nominaQuincenalREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.FIND_BY_EMPLEADO_IDS.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            List<Movimiento> list = (List<Movimiento>) restEvent.getResult();
                            List<Movimiento> percepciones = new ArrayList<>();
                            List<Movimiento> deducciones = new ArrayList<>();
                            List<Movimiento> repercuciones = new ArrayList<>();
                            List<Movimiento> internos = new ArrayList<>();
                            BigDecimal total = BigDecimal.ZERO;
                            for(Movimiento nq : list) {
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
  */  
    public void setSelectedBean(Integer idEmpleadoBaseSel) {
        // Viene desde la Lista de NominaBase
        
        idEmpleadoBaseSelected = idEmpleadoBaseSel == null ? 0 : idEmpleadoBaseSel;
        
        getEmpleadosREST().findEmpleadoNominaHistoById(idEmpleadoBaseSelected, quincenaSelected);
        
    }

    public void setSelectedList(String ids) {
        
      //  getNominaQuincenalREST().findByEmpleadoIds(ids);
        
    }
    
    
}
