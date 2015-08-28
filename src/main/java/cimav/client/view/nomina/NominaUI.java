/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.math.BigDecimal;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

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
    NominaMovimientosUI nominaPercepcionesUI;
    @UiField
    NominaMovimientosUI nominaDeduccionesUI;
    @UiField
    NominaSaldoUI nominaPercepcionesSaldoUI;
    @UiField
    NominaSaldoUI nominaDeduccionesSaldoUI;
    
    @UiField
    Button btnCalcular;
    
    private EmpleadoNomina empleadoNominaLoaded;
    
    private EmpleadoREST empleadoREST;
    
    @UiField
    HorizontalPanel movimientosContenedor;
    
    public NominaUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
        btnCalcular.addClickHandler(new CalcularClick());
        
        MovimientosListener listener = new MovimientosListener();
        nominaPercepcionesSaldoUI.addMovimientosListener(listener);
        nominaDeduccionesSaldoUI.addMovimientosListener(listener);

    }
    
    @UiHandler({"tabPercepConceptos", "tabPercepPorSaldo", "tabDeducConceptos", "tabDeducPorSaldo", "tabDeducPorPago"})
    protected void onClick(ClickEvent e) {
        String str = e.getSource().toString();
        if (str.contains("tabPercepConceptos")) {
            nominaPercepcionesUI.dataGrid.redraw();
        } else if (str.contains("tabPercepPorSaldo")) {
            nominaPercepcionesSaldoUI.dataGrid.redraw();
        } else if (str.contains("tabDeducConceptos")) {
            nominaDeduccionesUI.dataGrid.redraw();
        } else if (str.contains("tabDeducPorSaldo")) {
            nominaDeduccionesSaldoUI.dataGrid.redraw();
        } else if (str.contains("tabDeducPorPeriodo")) {
            
        }
    }
    
    private class CalcularClick implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            
            Calculo calculo = new Calculo(empleadoNominaLoaded.getId());
            
            calculo.getREST().addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.CALCULAR.equals(restEvent.getMethod())) {
                        if(ETypeResult.SUCCESS.equals((restEvent.getTypeResult()))) {
                            Growl.growl(empleadoNominaLoaded.getCode() + " calculado");
                        } else {
                            Growl.growl(empleadoNominaLoaded.getCode() + " fallo cálculo");
                        }
                        NominaUI.this.setSelectedBean(empleadoNominaLoaded.getId());
                    }
                }
            });
            
            calculo.calcular();
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
                    
                    nominaPercepcionesUI.setList(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.PERCEPCION));
                    nominaPercepcionesSaldoUI.setEmpleado(empleadoNominaLoaded);
                            
                    nominaDeduccionesUI.setList(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.DEDUCCION));
                    nominaDeduccionesSaldoUI.setEmpleado(empleadoNominaLoaded);
                            
                    BigDecimal totPercepciones = empleadoNominaLoaded.getTotalPercepciones();
                    BigDecimal totDeducciones = empleadoNominaLoaded.getTotalDeducciones();
                    BigDecimal total = totPercepciones.subtract(totDeducciones);
                    
                    totalLabel.setText(Utils.formatCurrency(total));
                    
                } else {
                    
                }
            } 
            
        }
    }
    
    public void setSelectedBean(Integer idEmpleadoBaseSelected) {
        // Viene desde la Lista de NominaBase
        
        idEmpleadoBaseSelected = idEmpleadoBaseSelected == null ? 0 : idEmpleadoBaseSelected;
        
        getEmpleadosREST().findEmpleadoNominaById(idEmpleadoBaseSelected);
     
    }
    
    private class MovimientosListener implements NominaSaldoUI.MovimientosListener {
        @Override
        public void onMovimiento(MethodEvent event) {
            if (EMethod.CREATE.equals(event.getMethod()) 
                    || EMethod.UPDATE.equals(event.getMethod()) 
                    || EMethod.DELETE.equals(event.getMethod())) {
                // Se creeo/modificó/borro un saldo; reload al empleado
                NominaUI.this.setSelectedBean(empleadoNominaLoaded.getId());
            }
        }
    }
    
}
