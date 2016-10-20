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
import cimav.client.data.domain.Movimiento;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.NominaQuincenalREST;
import cimav.client.view.catalogos.conceptos.ConceptosChosen;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.ICustomDataGridResource;
import cimav.client.view.common.MethodEvent;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.CheckboxCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import static com.google.gwt.query.client.GQuery.window;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiConstructor;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.RowHoverEvent;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author juan.calderon
 */
public class NominaSaldoUI extends Composite {

    private static NominaSaldoUIUiBinder uiBinder = GWT.create(NominaSaldoUIUiBinder.class);

    interface NominaSaldoUIUiBinder extends UiBinder<Widget, NominaSaldoUI> {
    }

    @UiField(provided = true)
    DataGrid<Movimiento> dataGrid;

    private int empleadoId;
    private ListDataProvider<Movimiento> provider;
    private NominaQuincenalREST nominaQuincenalREST;

    private NomIntegerInputCell quincenasCell;
    private NomCantidadInputCell pagoCell;
    private NomCantidadInputCell saldoCell;
    private CheckboxCell permanenteCell;

    @UiField
    Anchor anchorPlus;

    private final ConceptosChosen conceptosChosen;

    private final ETipoConcepto tipoConcepto;

    @UiField
    HTMLPanel htmlPanel;
    
    @UiConstructor
    public NominaSaldoUI(String idTipoConcepto) {

        this.tipoConcepto = ETipoConcepto.get(idTipoConcepto);

        this.buildGrid(); // antes del initWidget

        initWidget(uiBinder.createAndBindUi(this));

        conceptosChosen = new ConceptosChosen(this.tipoConcepto, ETipoMovimiento.PAGO);
        conceptosChosen.addStyleName("movimientos-chosen");
        htmlPanel.add(conceptosChosen);

        anchorPlus.addClickHandler(new ClickPlus());

        Properties wnd = window.cast();
        wnd.setFunction("removeSaldo", new Function() {
            public void f() {
                JsArrayMixed args = arguments(0);
                String idSaldo = args.getString(0);
                getNominaQuincenalsREST().remove(idSaldo);
            }
        });

    }
    
    private void buildGrid() {
        
        List<Movimiento> nominaQuincenalList = new ArrayList<>();
        provider = new ListDataProvider<>(nominaQuincenalList);
        
        ICustomDataGridResource dataGridResource = GWT.create(ICustomDataGridResource.class);
        dataGridResource.dataGridStyle().ensureInjected();
        
        dataGrid = new DataGrid<>(60, dataGridResource);
        
        //dataGrid = new DataGrid<>(provider.getKeyProvider());

        dataGrid.getElement().setId("idDataGrid");

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin movimientos"));

        dataGrid.setPageSize(20);

        quincenasCell = new NomIntegerInputCell("24");
        saldoCell = new NomCantidadInputCell();
        pagoCell = new NomCantidadInputCell();
        permanenteCell = new CheckboxCell();

        initTableColumns();

        // Add the CellList to the adapter in the database.
        provider.addDataDisplay(dataGrid);

        dataGrid.addRowHoverHandler(new RowHoverEvent.Handler() {
            @Override
            public void onRowHover(RowHoverEvent event) {
                TableRowElement rowEle = event.getHoveringRow();
                Element removeSaldoEle = rowEle.getElementsByTagName("a").getItem(0);
                if (event.isUnHover()) {
                    GQuery.$(removeSaldoEle).css(CSS.VISIBILITY.with(Style.Visibility.HIDDEN));
                } else {
                    GQuery.$(removeSaldoEle).css(CSS.VISIBILITY.with(Style.Visibility.VISIBLE));
                }
            }
        });

    }

    private class ClickPlus implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {

            boolean add = true;
            Concepto selected = conceptosChosen.getSelected();
            if (selected != null && selected.getId() != null && selected.getId() > 0) {
                for (Movimiento nq : provider.getList()) {
                    if (nq.getConcepto().equals(selected)) {
                        add = false;
                        break;
                    }
                }
            } else {
                add = false;
            }
            if (add) {
                Movimiento nuevo = new Movimiento();
                // 1ero el concepto para saber si es [P, D, R, I], [P]
                nuevo.setConcepto(selected);
                nuevo.setIdEmpleado(empleadoId);
                
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
                            onPago(restEvent);

                        } else {
                            Growl.growl("Falló creación del pago. " + restEvent.getReason());
                        }
                    } else if (EMethod.UPDATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            onPago(restEvent);
                        } else {
                            Growl.growl("Falló actualización del pago. " + restEvent.getReason());
                        }
                    } else if (EMethod.DELETE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {

                            onPago(restEvent);

                        } else {
                            Growl.growl("Falló eliminación del pago. " + restEvent.getReason());
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

        // id + icon remove
        Column<Movimiento, String> iconCol = new Column<Movimiento, String>(new NomIconInputCell(NomIconInputCell.SALDO)) {
            @Override
            public String getValue(Movimiento object) {
                return "" + object.getId();
            }
        };
        Header<String> forzarFooter = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                return "  ";
            }
        };
        dataGrid.addColumn(iconCol, new SafeHtmlHeader(SafeHtmlUtils.fromString("")), forzarFooter);
        dataGrid.setColumnWidth(iconCol, 16, Style.Unit.PX);

        // Concepto
        Column<Movimiento, String> conceptoCol = new Column<Movimiento, String>((new TextCell())) {
            @Override
            public String getValue(Movimiento object) {
                Concepto concepto = object.getConcepto();
                return concepto.getName();
            }
        };
        dataGrid.addColumn(conceptoCol, "Concepto");
        dataGrid.setColumnWidth(conceptoCol, 100, Style.Unit.PCT);

//        // Permanente
//        Column<Movimiento, Boolean> permanenteCol = new Column<Movimiento, Boolean>(permanenteCell) {
//            @Override
//            public Boolean getValue(Movimiento object) {
//                Boolean result = object.getPermanente();
//                return result;
//            }
//        };
//        permanenteCol.setFieldUpdater(new FieldUpdater<Movimiento, Boolean>() {
//            @Override
//            public void update(int index, Movimiento object, Boolean value) {
//                try {
//                    object.setPermanente(value);
//                    getNominaQuincenalsREST().update(object);
//                } catch (Exception e) {
//
//                }
//                saldoCell.clearViewData(object);
//                int absRowIndex = index;
//                dataGrid.redrawRow(absRowIndex);
//            }
//        });
//        permanenteCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_CENTER);
////        TextHeader permanenteHeader = new TextHeader("");
////        permanenteHeader.setHeaderStyleNames("permanenteHeader");
//        SafeHtmlBuilder permanenteHeader = new SafeHtmlBuilder();
//        permanenteHeader.appendHtmlConstant("<span clas='permanenteHeader fa fa-anchor' style=''><i class='icon-anchor'></i></span>");
//        dataGrid.addColumn(permanenteCol, permanenteHeader.toSafeHtml());
//        dataGrid.setColumnWidth(permanenteCol, 20, Style.Unit.PX);
        
         // Pago
         /*
        Column<Movimiento, SafeHtml> pagoCol = new Column<Movimiento, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(Movimiento object) {
                BigDecimal pago = object == null || object.getPago() == null ? BigDecimal.ZERO : object.getPago();
                Utils.formatCantidad(pago);
                SafeHtmlBuilder a = new SafeHtmlBuilder();
               if (object.getPermanente()) {
                   a.appendHtmlConstant("<span style='padding-right:3px; '>" + Utils.formatCantidad(pago) + "</span>");
                } else {
                   a.appendHtmlConstant("<span style='padding-right:3px; color: grey; font-style: italic;'>" + Utils.formatCantidad(pago) + "</span>");
                }
		return a.toSafeHtml();            
            }
        };
        pagoCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(pagoCol, "Pago");
        dataGrid.setColumnWidth(pagoCol, 90, Style.Unit.PCT);
        */
         
        // Pago 
        Column<Movimiento, String> pagoCol = new Column<Movimiento, String>(pagoCell) {
            @Override
            public String getValue(Movimiento object) {
                BigDecimal nuevoPago = object == null || object.getPago()== null ? BigDecimal.ZERO : object.getPago();

                boolean pagoEsMayorASaldo = object.getSaldo().signum() > 0 &&  object.getSaldo().subtract(nuevoPago).signum() < 0;
                pagoCell.setAsNegative(pagoEsMayorASaldo);

                return Utils.formatCantidad(nuevoPago);
            }
        };
        pagoCol.setFieldUpdater(new FieldUpdater<Movimiento, String>() {
            @Override
            public void update(int index, Movimiento object, String value) {
                BigDecimal nuevoPago;
                try {
                    nuevoPago = new BigDecimal(value.trim());
                    object.setPago(nuevoPago);
                    getNominaQuincenalsREST().update(object);
                } catch (Exception e) {

                }
                saldoCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        pagoCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(pagoCol, "Pago");
        dataGrid.setColumnWidth(pagoCol, 90, Style.Unit.PX);
        
        // Saldo 
        Column<Movimiento, String> saldoCol = new Column<Movimiento, String>(saldoCell) {
            @Override
            public String getValue(Movimiento object) {
                BigDecimal result = object == null || object.getSaldo() == null ? BigDecimal.ZERO : object.getSaldo();
                return Utils.formatCantidad(result);
            }
        };
        saldoCol.setFieldUpdater(new FieldUpdater<Movimiento, String>() {
            @Override
            public void update(int index, Movimiento object, String value) {
                BigDecimal nuevoSaldo;
                try {
                    nuevoSaldo = new BigDecimal(value.trim());
                    object.setSaldo(nuevoSaldo);
                    getNominaQuincenalsREST().update(object);
                } catch (Exception e) {

                }
                saldoCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        saldoCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(saldoCol, "Saldo");
        dataGrid.setColumnWidth(saldoCol, 110, Style.Unit.PX);

//        // Quincenas/Veces
//        Column<Movimiento, String> quincenasCol = new Column<Movimiento, String>(quincenasCell) {
//            @Override
//            public String getValue(Movimiento object) {
//                Integer result = object == null || object.getNumQuincenas() == null ? 0 : object.getNumQuincenas();
//                return Integer.toString(result);
//            }
//        };
//        quincenasCol.setFieldUpdater(new FieldUpdater<Movimiento, String>() {
//            @Override
//            public void update(int index, Movimiento object, String value) {
//                    // Push the changes into the MyDTO. At this point, you could send an
//                // asynchronous request to the server to update the database.
//                // value es el valor capturado
//                // si es valido, pasar al object y a la DB
//                // NOTA requiere de un Ajax con icono chiquito
//                // Tiene su propio loading el DataGrid
//
//            // Intenta convertir el value a Integer
//                // si falla lo deja como estaba
//                // si no falla lo actualiza
//                Integer numQuin = 0;
//                try {
//                    numQuin = Integer.parseInt(value.trim());
//                    object.setNumQuincenas(numQuin);
//
//                    getNominaQuincenalsREST().update(object);
//
//                } catch (NumberFormatException | NullPointerException e) {
//                }
//                quincenasCell.clearViewData(object);
//                int absRowIndex = index;
//                dataGrid.redrawRow(absRowIndex);
//            }
//        });
//        quincenasCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
//        dataGrid.addColumn(quincenasCol,  "Veces");
//        dataGrid.setColumnWidth(quincenasCol, 68, Style.Unit.PX);

    }

    public int setEmpleado(EmpleadoNomina empleado) {
        List<Movimiento> result = new ArrayList<>();
        if (empleado != null) {
            this.empleadoId = empleado.getId();
            result.addAll(empleado.getMovimientos(this.tipoConcepto, ETipoMovimiento.PAGO));
        }
        provider.setList(result);
        return result.size();
    }

    // <editor-fold defaultstate="collapsed" desc="interface PagosListener"> 
    public interface PagosListener extends java.util.EventListener {

        void onPago(MethodEvent event);
    }
    private final ArrayList listeners = new ArrayList();

    public void addPagosListener(PagosListener listener) {
        listeners.add(listener);
    }

    public void removePagosListener(PagosListener listener) {
        listeners.remove(listener);
    }

    public void onPago(MethodEvent restEvent) {
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            PagosListener listener = (PagosListener) it.next();
            listener.onPago(restEvent);
        }
    }
    // </editor-fold>

}
