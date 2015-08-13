/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.ETipoMovimiento;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.EmpleadoREST;
import static cimav.client.view.MainUI.OPT_DEPARTAMENTOS;
import static cimav.client.view.MainUI.OPT_NOMINA;
import static cimav.client.view.MainUI.OPT_PERSONAL;
import static cimav.client.view.MainUI.OPT_TABULADOR;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.SelectionEvent;
import com.google.gwt.event.logical.shared.SelectionHandler;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import java.math.BigDecimal;
import org.gwtbootstrap3.client.ui.NavTabs;
import org.gwtbootstrap3.client.ui.TabContent;
import org.gwtbootstrap3.client.ui.TabPane;

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
    NominaPercepcionesUI nominaPercepcionesUI;
    @UiField
    NominaSaldoUI nominaSaldoUI;

    private EmpleadoREST empleadoREST;
    
    public NominaUI() {
        initWidget(uiBinder.createAndBindUi(this));
        
    }
    
    @UiHandler({"tabItemConceptos", "tabItemPorSaldo", "tabItemPorPago"})
    protected void onClick(ClickEvent e) {
        GWT.log("XXXX> " + e.getSource());
        String str = e.getSource().toString();
        if (str.contains("tabConceptosId")) {
            nominaPercepcionesUI.dataGridPercepciones.redraw();
        } else if (str.contains("tabPorSaldoId")) {
            nominaSaldoUI.dataGrid.redraw();
        } else if (str.contains("tabPorPeriodoId")) {
            
        } 
    }
    
//    private class ActionListener implements NominaPercepcionesUI.ActionListener {
//        @Override
//        public void onActionEditor(MethodEvent restEvent) {
//            if (EMethod.SUMAR_TOTAL.equals(restEvent.getMethod())) {
//                if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
//                    totalLabel.setText("15,000");
//                }
//            }
//        }
//    }
    
    @Override
    protected void onLoad() {
        super.onLoad(); //To change body of generated methods, choose Tools | Templates.

        GQuery cqFront = GQuery.$(".face.front");
        
        cqFront.attr("style","padding: 0; ");
        
//        cqFront.$(".trigger").remove();
        
        
//        /* Replazar el style del icono del Flip Card*/
//        cqFront.$(".trigger").attr("class","fa fa-pencil").attr("style",
//            //" border: 1px solid blueviolet; " +
//            " cursor: pointer; " +
//            " position: absolute; " +
//            " bottom: 5px; " +
//            " left: 5px; " +
//            " z-index: 300; " 
//        );
        
//        cqFront.append("<i id='noManches' class='fa fa-plus' style=' cursor: pointer;  position: absolute;  bottom: 5px;  left: 5px;  z-index: 300; '></i>");
//        cqFront.append("<i class='fa fa-minus' style=' cursor: pointer;  position: absolute;  bottom: 5px;  left: 25px;  z-index: 300; '></i>");
//        cqFront.append("<i class='fa fa-pencil' style=' cursor: pointer;  position: absolute;  bottom: 5px;  left: 45px;  z-index: 300; '></i>");
        
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
                    EmpleadoNomina empleadoNominaLoaded = (EmpleadoNomina) methodEvent.getResult();
                    
                    nominaPercepcionesUI.setList(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.PERCEPCION));
                    nominaSaldoUI.setList(empleadoNominaLoaded.getNominaQuincenalCollection(ETipoConcepto.PERCEPCION, ETipoMovimiento.SALDO));
                            
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
    
}
