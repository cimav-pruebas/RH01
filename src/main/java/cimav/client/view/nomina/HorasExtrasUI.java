/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.HoraExtra;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.HorasExtrasREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.cell.client.DatePickerCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.SafeHtmlCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.core.client.JsArrayMixed;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import static com.google.gwt.query.client.GQuery.window;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.DataGrid;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.cellview.client.RowHoverEvent;
import com.google.gwt.user.cellview.client.SafeHtmlHeader;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author juan.calderon
 */
public class HorasExtrasUI extends Composite {
    
    private static HorasExtrasUIUiBinder uiBinder = GWT.create(HorasExtrasUIUiBinder.class);
    
    interface HorasExtrasUIUiBinder extends UiBinder<Widget, HorasExtrasUI> {
    }
    
    @UiField
    HTMLPanel htmlPanel;
    
    @UiField(provided = true)
    DataGrid<HoraExtra> dataGrid;
    
    @UiField
    Anchor anchorPlus;
    
    private ListDataProvider<HoraExtra> provider;
    private HorasExtrasREST rest;
    
    private Integer idEmpleado;
    
    private DatePickerCell diaCell;
    private NomCantidadInputCell horasCell;
    
    public HorasExtrasUI() {
        
        this.buildGrid(); // antes del initWidget
        
        initWidget(uiBinder.createAndBindUi(this));
        
        anchorPlus.addClickHandler(new HorasExtrasUI.ClickPlus());
        
        Properties wnd = window.cast();
        wnd.setFunction("removeHoraExtra", new Function() {
            // relacionado con NomIconInputCell
            public void f() {
                JsArrayMixed args = arguments(0);
                String idHoraExtra = args.getString(0);
                getHorasExtrasREST().remove(idHoraExtra);
            }
        });
        
    }
    
    private void buildGrid() {

        List<HoraExtra> list = new ArrayList<>();
        provider = new ListDataProvider<>(list);
        dataGrid = new DataGrid<>(provider.getKeyProvider());
        dataGrid.getElement().setId("idDataGrid");

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin Horas Extras"));

        dataGrid.setPageSize(20);

        diaCell = new DatePickerCell(DateTimeFormat.getFormat(DateTimeFormat.PredefinedFormat.DATE_MEDIUM));
        horasCell = new NomCantidadInputCell();
        
        initTableColumns();

        // Add the CellList to the adapter in the database.
        provider.addDataDisplay(dataGrid);

        dataGrid.addRowHoverHandler(new RowHoverEvent.Handler() {
            @Override
            public void onRowHover(RowHoverEvent event) {
                TableRowElement rowEle = event.getHoveringRow();
                Element removeHoraExtraEle = rowEle.getElementsByTagName("a").getItem(0);
                if (event.isUnHover()) {
                    GQuery.$(removeHoraExtraEle).css(CSS.VISIBILITY.with(Style.Visibility.HIDDEN));
                } else {
                    GQuery.$(removeHoraExtraEle).css(CSS.VISIBILITY.with(Style.Visibility.VISIBLE));
                }
            }
        });

    }

    private class ClickPlus implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            
                HoraExtra nueva = new HoraExtra();
                nueva.setIdEmpleado(idEmpleado);
                nueva.setQuincena(21); // TODO referenciar a la Quincena global
                
                // Crearla en la DB
                getHorasExtrasREST().create(nueva);
        }
    }
    
    private HorasExtrasREST getHorasExtrasREST() {
        if (rest == null) {
            rest = new HorasExtrasREST();
            rest.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.CREATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            onHoraExtra(restEvent);

                        } else {
                            Growl.growl("Fallá creación de la hora extra. " + restEvent.getReason());
                        }
                    } else if (EMethod.UPDATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {

                            onHoraExtra(restEvent);

                        } else {
                            Growl.growl("Fallá actualización de la hora extra. " + restEvent.getReason());
                        }
                    }  else if (EMethod.DELETE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {

                            onHoraExtra(restEvent);

                        } else {
                            Growl.growl("Fallá eliminación de la hora extra. " + restEvent.getReason());
                        }
                    }
                }
            });
        }
        return rest;
    }
    
    private void initTableColumns() {

        // id + icon remove
        Column<HoraExtra, String> iconCol = new Column<HoraExtra, String>(new NomIconInputCell(NomIconInputCell.FALTA)) {
            @Override
            public String getValue(HoraExtra object) {
                return "" + object.getId();
            }
        };
        dataGrid.addColumn(iconCol, "");
        dataGrid.setColumnWidth(iconCol, 16, Style.Unit.PX);
        
        // Semana
        Column<HoraExtra, SafeHtml> semanaCol = new Column<HoraExtra, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(HoraExtra object) {
                SafeHtmlBuilder a = new SafeHtmlBuilder();
                a.appendHtmlConstant("<span>" + object.getWeekOfYear() + "</span>");
//                if (object.getConcepto().getSuma()) {
//                    a.appendHtmlConstant("<span>" + object.getConcepto().getName() + "</span>");
//                } else {
//                    a.appendHtmlConstant("<span style='color: grey; font-style: italic;'>" + object.getConcepto().getName() + "</span>");
//                }
		return a.toSafeHtml();            
            }
        };
        dataGrid.addColumn(semanaCol, "Semana");
        dataGrid.setColumnWidth(semanaCol, 120, Style.Unit.PCT);
        
        // Fecha
        Column<HoraExtra, Date> diaCol = new Column<HoraExtra, Date>(diaCell) {
            @Override
            public Date getValue(HoraExtra object) {
                return object.getDia();
            }
        };
        diaCol.setFieldUpdater(new FieldUpdater<HoraExtra, Date>() {
            @Override
            public void update(int index, HoraExtra object, Date value) {
                try {
                    object.setDia(value);
                    
                    //object.ajustar();
                    
                    getHorasExtrasREST().update(object);
                } catch (Exception ex) {

                }
                diaCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        dataGrid.addColumn(diaCol, "Día (fecha)");
        dataGrid.setColumnWidth(diaCol, 120, Style.Unit.PX);

        // Horas
        Column<HoraExtra, String> horasCol = new Column<HoraExtra, String>(horasCell) {
            @Override
            public String getValue(HoraExtra object) {
                Double result = object == null || object.getHoras() == null ? 0 : object.getHoras();
                return Double.toString(result);
            }
        };
        horasCol.setFieldUpdater(new FieldUpdater<HoraExtra, String>() {
            @Override
            public void update(int index, HoraExtra object, String value) {
                try {
                    Double horas = Double.parseDouble(value);
                    object.setHoras(horas);
                    
                    //object.ajustar();
                    
                    getHorasExtrasREST().update(object);
                } catch (Exception ex) {

                }
                horasCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        horasCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        Header<String> forzarFooter = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                return "  ";
            }
        };
        dataGrid.addColumn(horasCol, new SafeHtmlHeader(SafeHtmlUtils.fromString("Horas")), forzarFooter);
        dataGrid.setColumnWidth(horasCol, 68, Style.Unit.PX);

    }

    public Double setEmpleado(EmpleadoNomina empleado) {
        List<HoraExtra> horas = new ArrayList<>();
        if (empleado != null) {
            this.idEmpleado = empleado.getId();
            if (empleado.getHorasExtras() != null) {
                horas.addAll(empleado.getHorasExtras());
            }
//            Collections.sort(result, new Comparator<Incidencia>() {
//                @Override
//                public int compare(Incidencia f1, Incidencia f2) {
//                    //return f1.getFechaInicial().compareTo(f2.getFechaInicial());
//                    return f1.getId().compareTo(f2.getId());
//                }
//            });
        }
        Double result = 0.00;
        for(HoraExtra he : horas) {
            result = result + he.getHoras();
        }
        provider.setList(horas);
        return result;
    }

    // <editor-fold defaultstate="collapsed" desc="interface HorasExtrasListener"> 
    public interface HorasExtrasListener extends java.util.EventListener {

        void onHoraExtra(MethodEvent event);
    }
    private final ArrayList listeners = new ArrayList();

    public void addHorasExtrasListener(HorasExtrasListener listener) {
        listeners.add(listener);
    }

    public void removeHorasExtrasListener(HorasExtrasListener listener) {
        listeners.remove(listener);
    }

    public void onHoraExtra(MethodEvent restEvent) {
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            HorasExtrasListener listener = (HorasExtrasListener) it.next();
            listener.onHoraExtra(restEvent);
        }
    }
    // </editor-fold>
    
    
}
