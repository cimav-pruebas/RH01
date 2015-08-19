/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.Concepto;
import cimav.client.data.domain.ETipoConcepto;
import cimav.client.data.domain.ETipoMovimiento;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.NominaQuincenal;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.NominaQuincenalREST;
import cimav.client.view.catalogos.conceptos.ConceptosChosen;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.SimpleSafeHtmlRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author juan.calderon
 */
public class NominaSaldoUI extends Composite {
    
    private static NominaSaldoUIUiBinder uiBinder = GWT.create(NominaSaldoUIUiBinder.class);

    private int cont = 0;

    
    interface NominaSaldoUIUiBinder extends UiBinder<Widget, NominaSaldoUI> {
    }
    
    @UiField(provided = true) 
    DataGrid<NominaQuincenal> dataGrid;

    private int empleadoId;
    private ListDataProvider<NominaQuincenal> provider;
    private NominaQuincenalREST nominaQuincenalREST;
    
    private NomInputCell quincenasCell;
    private NomInputCell saldoCell;
    
    @UiField
    Anchor anchorPlus;
    
    private final ConceptosChosen conceptosChosen;

    private final ETipoConcepto tipoConcepto;
    private final ETipoMovimiento tipoMovimiento;

    @UiField
    HTMLPanel  htmlPanel;
    
    @UiConstructor
    public NominaSaldoUI(String idTipoConcepto, String idTipoMovimiento) {
    
        this.tipoConcepto = ETipoConcepto.get(idTipoConcepto);
        this.tipoMovimiento = ETipoMovimiento.get(idTipoMovimiento);
        
        this.buildGrid(); // antes del initWidget
        
        initWidget(uiBinder.createAndBindUi(this));
        
        conceptosChosen = new ConceptosChosen(this.tipoConcepto, this.tipoMovimiento);
        conceptosChosen.addStyleName("conceptos-chosen");
        htmlPanel.add(conceptosChosen);
                
        anchorPlus.addClickHandler(new ClickPlus());
    }

    private void buildGrid() {
        
        List<NominaQuincenal> nominaQuincenalList = new ArrayList<>();
        provider = new ListDataProvider<>(nominaQuincenalList);
        dataGrid = new DataGrid<>(provider.getKeyProvider());

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin movimientos"));

        dataGrid.setPageSize(20);

        quincenasCell = new NomInputCell();
        saldoCell = new NomInputCell();
        
        initTableColumns(); 
        
//        // Add the CellList to the adapter in the database.
        provider.addDataDisplay(dataGrid);
        
    }
    
    private class ClickPlus implements ClickHandler {
        @Override
        public void onClick(ClickEvent event) {
            boolean add = true;
            Concepto selected = conceptosChosen.getSelected();
            if (selected != null && selected.getId() != null && selected.getId() > 0 ) {
                for (NominaQuincenal nq : provider.getList()) {
                    if (nq.getConcepto().equals(selected)) {
                        add = false;
                        break;
                    }
                }
            } else {
                add = false;
            }
            if (add) {
                NominaQuincenal nuevo = new NominaQuincenal();
                nuevo.setCantidad(BigDecimal.ZERO);
                nuevo.setIdEmpleado(empleadoId);
                nuevo.setNumQuincenas(1);
                nuevo.setPagoPermanente(BigDecimal.ZERO);
                nuevo.setPagoUnico(BigDecimal.ZERO);
                nuevo.setSaldoRestante(BigDecimal.ZERO);
                nuevo.setConcepto(selected);
                
                // Crearlo en la DB
                getNominaQuincenalsREST().create(nuevo);
                
            }
        }
    }
    
    private NominaQuincenalREST getNominaQuincenalsREST() {
        if (nominaQuincenalREST == null) {
            nominaQuincenalREST = new NominaQuincenalREST();

            nominaQuincenalREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.CREATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            
                            NominaQuincenal nomQuinNueva = (NominaQuincenal) restEvent.getResult();
                            
                            // hasta estar correctamente en la DB, pasarlo al provider
                            provider.getList().add(nomQuinNueva);
                        } else {
                            Growl.growl("Falló creación del movimiento. " + restEvent.getReason());
                        }
                    } else if (EMethod.UPDATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            
                        }  else {
                            Growl.growl("Falló actualización del movimiento. " + restEvent.getReason());
                        }
                    }
                }
            });
        }
        return nominaQuincenalREST;
    }
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns() {

        // Concepto
        Column<NominaQuincenal, String> conceptoCol = new Column<NominaQuincenal, String>((new TextCell())) {
            @Override
            public String getValue(NominaQuincenal object) {
                Concepto concepto = object.getConcepto();
                //return concepto.getCode() + " " + concepto.getIdTipoConcepto()+ " " +  concepto.getIdTipoMovimiento()+ " " + concepto.getName();
                return concepto.getName();
            }
        };
        dataGrid.addColumn(conceptoCol, "Concepto");
        dataGrid.setColumnWidth(conceptoCol, 100, Style.Unit.PCT);

        // Saldo Descuento
        Column<NominaQuincenal, String> descuentoCol = new Column<NominaQuincenal, String>(new EditTextCell()) {
            @Override
            public String getValue(NominaQuincenal object) {
                BigDecimal result = object == null || object.getSaldoDescuento() == null ? BigDecimal.ZERO : object.getSaldoDescuento();
                return Utils.formatCantidad(result);
            }
        };
        descuentoCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(descuentoCol,  "Descuento");
        dataGrid.setColumnWidth(descuentoCol, 90, Style.Unit.PX);
        
        // Saldo Restante
        Column<NominaQuincenal, String> restanteCol = new Column<NominaQuincenal, String>(saldoCell) {
                    @Override
                    public String getValue(NominaQuincenal object) {
                        BigDecimal result = object == null || object.getSaldoRestante() == null ? BigDecimal.ZERO : object.getSaldoRestante();
                        return Utils.formatCantidad(result);
                    }
                };
        restanteCol.setFieldUpdater(new FieldUpdater<NominaQuincenal, String>() {
            @Override
            public void update(int index, NominaQuincenal object, String value) {
                BigDecimal nuevoSaldo;
                try {
                    nuevoSaldo = new BigDecimal(value.trim());
                    object.setSaldoRestante(nuevoSaldo);
                    getNominaQuincenalsREST().update(object);
                } catch (Exception e) {
                    
                }
                saldoCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        restanteCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(restanteCol,  "Saldo");
        dataGrid.setColumnWidth(restanteCol, 90, Style.Unit.PX);
        
        // Quincenas
        Column<NominaQuincenal, String> quincenasCol = new Column<NominaQuincenal, String>(quincenasCell) {
            @Override
            public String getValue(NominaQuincenal object) {
                Integer result = object == null || object.getNumQuincenas() == null ? 0 : object.getNumQuincenas();
                return Integer.toString(result);
            }
        };
        quincenasCol.setFieldUpdater(new FieldUpdater<NominaQuincenal, String>() {
            @Override
            public void update(int index, NominaQuincenal object, String value) {
                        // Push the changes into the MyDTO. At this point, you could send an
                // asynchronous request to the server to update the database.
                // value es el valor capturado
                // si es valido, pasar al object y a la DB
                // NOTA requiere de un Ajax con icono chiquito
                // Tiene su propio loading el DataGrid

                // Intenta convertir el value a Integer
                // si falla lo deja como estaba
                // si no falla lo actualiza
                Integer numQuin = 0;
                try {
                    numQuin = Integer.parseInt(value.trim());
                    object.setNumQuincenas(numQuin);
                    
                    getNominaQuincenalsREST().update(object);
                    
                    GWT.log("Contador: " + cont++);
                } catch (NumberFormatException | NullPointerException e) {
                }
                quincenasCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        quincenasCol.setHorizontalAlignment( HasHorizontalAlignment.ALIGN_RIGHT);
        Header<String> forzarFooter = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                    return "  ";
            }
        };
        //dataGrid.addColumn(quincenasCol,  "Quincenas");
        dataGrid.addColumn(quincenasCol,  new SafeHtmlHeader(SafeHtmlUtils.fromString("Veces")), forzarFooter);
        dataGrid.setColumnWidth(quincenasCol, 60, Style.Unit.PX);
        
    }
    
    public void setEmpleado(EmpleadoNomina empleado) {
        this.empleadoId = empleado.getId();
        List<NominaQuincenal> result = empleado.getNominaQuincenalCollection(this.tipoConcepto, this.tipoMovimiento);
        provider.setList(result);
    }
    
//    public void setList(List<NominaQuincenal> percepciones) {
//        
//        provider.setList(percepciones);
//    }

    
//                    MethodEvent movimiento = new MethodEvent(EMethod.UPDATE_MOVIMIENTO, ETypeResult.SUCCESS, "");
//                    movimiento.setResult(object);
//                    onMovimiento(movimiento);
                    
    
//    // <editor-fold defaultstate="collapsed" desc="interface MovimientosListener"> 
//    public interface MovimientosListener extends java.util.EventListener {
//        void onMovimiento(MethodEvent event);
//    }
//    private final ArrayList listeners = new ArrayList();
//    public void addMovimientosListener(MovimientosListener listener) {
//        listeners.add(listener);
//    }
//    public void removeMovimientosListener(MovimientosListener listener) {
//        listeners.remove(listener);
//    }
//    public void onMovimiento(MethodEvent restEvent) {
//        for(Iterator it = listeners.iterator(); it.hasNext();) {
//            MovimientosListener listener = (MovimientosListener) it.next();
//            listener.onMovimiento(restEvent);
//        }
//    }
//    // </editor-fold>

//    public void setIdTipoConcepto(String idTipoConcepto) {
//        this.idTipoConcepto = idTipoConcepto;
//    }
//
//    public void setIdTipoMovimiento(String idTipoMovimiento) {
//        this.idTipoMovimiento = idTipoMovimiento;
//    }

    
}
