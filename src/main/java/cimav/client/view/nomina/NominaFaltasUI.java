/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoIncidencia;
import cimav.client.data.domain.EmpleadoQuincenal;
import cimav.client.data.domain.Incidencia;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.IncidenciaREST;
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
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.query.client.Function;
import com.google.gwt.query.client.GQuery;
import static com.google.gwt.query.client.GQuery.window;
import com.google.gwt.query.client.Properties;
import com.google.gwt.query.client.css.CSS;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.text.shared.Renderer;
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
import com.google.gwt.user.client.ui.ValueListBox;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import org.gwtbootstrap3.client.ui.Anchor;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;

/**
 *
 * @author juan.calderon
 */
public class NominaFaltasUI extends Composite {
    
    private static NominaFaltasUIUiBinder uiBinder = GWT.create(NominaFaltasUIUiBinder.class);
    
    interface NominaFaltasUIUiBinder extends UiBinder<Widget, NominaFaltasUI> {
    }
    
    @UiField
    HTMLPanel htmlPanel;
    
    @UiField(provided = true)
    DataGrid<Incidencia> dataGrid;
    
    @UiField
    Anchor anchorPlus;
    
    private ListDataProvider<Incidencia> provider;
    private IncidenciaREST faltaREST;
    
    private final ValueListBox<ETipoIncidencia> faltaChosen;
    
    private Integer idEmpleado;
    
    private DatePickerCell fechaInicioCell;
    private NomIntegerInputCell diasCell;
    private NomTextInputCell folioCell;
            
    public NominaFaltasUI() {
        
        this.buildGrid(); // antes del initWidget
        
        initWidget(uiBinder.createAndBindUi(this));
        
        faltaChosen = new org.gwtbootstrap3.client.ui.ValueListBox<>(new Renderer<ETipoIncidencia>() {
            @Override
            public String render(ETipoIncidencia object) {
                if (object == null) {
                    return "None";
                }
                return object.getDescripcion();
            }

            @Override
            public void render(ETipoIncidencia object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<ETipoIncidencia> tipos = Arrays.asList(ETipoIncidencia.values());
        faltaChosen.setValue(ETipoIncidencia.AI); //default
        faltaChosen.setAcceptableValues(tipos);
        faltaChosen.addStyleName("movimientos-chosen");
        
        htmlPanel.add(faltaChosen);
        
        anchorPlus.addClickHandler(new ClickPlus());
        
        Properties wnd = window.cast();
        wnd.setFunction("removeFalta", new Function() {
            public void f() {
                JsArrayMixed args = arguments(0);
                String idFalta = args.getString(0);
                getIncidenciasREST().remove(idFalta);
            }
        });
        
    }
    private void buildGrid() {

        List<Incidencia> list = new ArrayList<>();
        provider = new ListDataProvider<>(list);
        dataGrid = new DataGrid<>(provider.getKeyProvider());
        dataGrid.getElement().setId("idDataGrid");

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin incidencias"));

        dataGrid.setPageSize(20);

        fechaInicioCell = new DatePickerCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM));
        diasCell = new NomIntegerInputCell("80");
        folioCell = new NomTextInputCell();
        
        initTableColumns();

//        // Add the CellList to the adapter in the database.
        provider.addDataDisplay(dataGrid);

        dataGrid.addRowHoverHandler(new RowHoverEvent.Handler() {
            @Override
            public void onRowHover(RowHoverEvent event) {
                TableRowElement rowEle = event.getHoveringRow();
                Element removeFaltaEle = rowEle.getElementsByTagName("a").getItem(0);
                if (event.isUnHover()) {
                    GQuery.$(removeFaltaEle).css(CSS.VISIBILITY.with(Style.Visibility.HIDDEN));
                } else {
                    GQuery.$(removeFaltaEle).css(CSS.VISIBILITY.with(Style.Visibility.VISIBLE));
                }
            }
        });

    }

    private class ClickPlus implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
            
            boolean add = true;
            ETipoIncidencia selected = faltaChosen.getValue();
            if (selected != null && selected.getId() != null && !selected.getId().isEmpty()) {
                for (Incidencia f : provider.getList()) {
                    if (f.getTipo().equals(selected)) {
                        add = false;
                        break;
                    }
                }
            } else {
                add = false;
            }
            if (add) {
                Incidencia nueva = new Incidencia();
                nueva.setIdEmpleado(idEmpleado);
                nueva.setTipo(selected);
                nueva.setFechaInicial(new Date());
                nueva.setDias(1);
                
                nueva.ajustar();
                
                // Crearla en la DB
                getIncidenciasREST().create(nueva);
            }
        }
    }
    
    private IncidenciaREST getIncidenciasREST() {
        if (faltaREST == null) {
            faltaREST = new IncidenciaREST();
            faltaREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.CREATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            onFalta(restEvent);

                        } else {
                            Growl.growl("Falló creación de la falta. " + restEvent.getReason());
                        }
                    } else if (EMethod.UPDATE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            onFalta(restEvent);

                        } else {
                            Growl.growl("Falló actualización de la falta. " + restEvent.getReason());
                        }
                    }  else if (EMethod.DELETE.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {

                            onFalta(restEvent);

                        } else {
                            Growl.growl("Falló eliminación de la falta. " + restEvent.getReason());
                        }
                    }
                }
            });
        }
        return faltaREST;
    }
    
    private void initTableColumns() {

        // id + icon remove
        Column<Incidencia, String> iconCol = new Column<Incidencia, String>(new NomIconInputCell(NomIconInputCell.FALTA)) {
            @Override
            public String getValue(Incidencia object) {
                return "" + object.getId();
            }
        };
        dataGrid.addColumn(iconCol, "");
        dataGrid.setColumnWidth(iconCol, 16, Style.Unit.PX);
        
        // Id+Tipo
        Column<Incidencia, SafeHtml> tipoCol = new Column<Incidencia, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(Incidencia object) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<div style='outline-style:none; white-space: nowrap;'><strong style='font-size: 12px; padding-right: 3px;'>" 
                        + object.getCode()+ "</strong> " + "<span>"+object.getTipo().getDescripcion()+"</span></div>");
                return sb.toSafeHtml();
            }
        };
        dataGrid.addColumn(tipoCol, "Tipo");
        dataGrid.setColumnWidth(tipoCol, 60, Style.Unit.PCT);
        
        // Fecha
        Column<Incidencia, Date> fechaCol = new Column<Incidencia, Date>(fechaInicioCell) {
            @Override
            public Date getValue(Incidencia object) {
                return object.getFechaInicial();
            }
        };
        fechaCol.setFieldUpdater(new FieldUpdater<Incidencia, Date>() {
            @Override
            public void update(int index, Incidencia object, Date value) {
                try {
                    object.setFechaInicial(value);
                    
                    object.ajustar();
                    
                    getIncidenciasREST().update(object);
                } catch (Exception ex) {

                }
                fechaInicioCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        dataGrid.addColumn(fechaCol, "Fecha");
        dataGrid.setColumnWidth(fechaCol, 120, Style.Unit.PX);

        // Dias
        Column<Incidencia, String> diasCol = new Column<Incidencia, String>(diasCell) {
            @Override
            public String getValue(Incidencia object) {
                Integer result = object == null || object.getDias() == null ? 0 : object.getDias();
                return Integer.toString(result);
            }
        };
        diasCol.setFieldUpdater(new FieldUpdater<Incidencia, String>() {
            @Override
            public void update(int index, Incidencia object, String value) {
                try {
                    Integer dias = Integer.parseInt(value);
                    object.setDias(dias);
                    
                    object.ajustar();
                    getIncidenciasREST().update(object);
                } catch (Exception ex) {

                }
                diasCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });
        diasCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(diasCol, "Días");
        dataGrid.setColumnWidth(diasCol, 68, Style.Unit.PX);

        // Dias Habiles
        Column<Incidencia, SafeHtml> diasHabilesCols = new Column<Incidencia, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(Incidencia object) {
                Integer result = object == null || object.getDiasHabiles()== null ? 0 : object.getDiasHabiles();
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<div style='outline-style:none; white-space: nowrap;'><strong style='font-size: 12px; padding-right: 6px;'>" 
                        + Integer.toString(result)+ "</strong></div>");
                return sb.toSafeHtml();
            }
        };
        diasHabilesCols.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(diasHabilesCols, new SafeHtmlHeader(SafeHtmlUtils.fromString("Hábiles")));
        dataGrid.setColumnWidth(diasHabilesCols, 68, Style.Unit.PX);

        // Dias Inhabiles
        Column<Incidencia, SafeHtml> diasInhabiles = new Column<Incidencia, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(Incidencia object) {
                Integer result = object == null || object.getDiasInhabiles()== null ? 0 : object.getDiasInhabiles();
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<div style='outline-style:none; white-space: nowrap;'><strong style='font-size: 12px; padding-right: 6px;'>" 
                        + Integer.toString(result)+ "</strong></div>");
                return sb.toSafeHtml();
            }
        };
        diasInhabiles.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(diasInhabiles, new SafeHtmlHeader(SafeHtmlUtils.fromString("Inhábiles")));
        dataGrid.setColumnWidth(diasInhabiles, 68, Style.Unit.PX);

        // Dias Restantes
        Column<Incidencia, SafeHtml> diasRestantesCol = new Column<Incidencia, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(Incidencia object) {
                Integer result = object == null || object.getDiasRestantes()== null ? 0 : object.getDiasRestantes();
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<div style='outline-style:none; white-space: nowrap;'><strong style='font-size: 12px; padding-right: 6px;'>" 
                        + Integer.toString(result)+ "</strong></div>");
                return sb.toSafeHtml();
            }
        };
        diasRestantesCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(diasRestantesCol, new SafeHtmlHeader(SafeHtmlUtils.fromString("Restantes")));
        dataGrid.setColumnWidth(diasRestantesCol, 68, Style.Unit.PX);
        
        // Folio
        Column<Incidencia, String> folioCol = new Column<Incidencia, String>(folioCell) {
            @Override
            public String getValue(Incidencia object) {
                return object.getFolio();
            }
        };
        folioCol.setFieldUpdater(new FieldUpdater<Incidencia, String>() {
            @Override
            public void update(int index, Incidencia object, String value) {
                try {
                    object.setFolio(value);
                    getIncidenciasREST().update(object);
                } catch (Exception ex) {

                }
                folioCell.clearViewData(object);
                int absRowIndex = index;
                dataGrid.redrawRow(absRowIndex);
            }
        });        
        Header<String> forzarFooter = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                return "  ";
            }
        };
        dataGrid.addColumn(folioCol, new SafeHtmlHeader(SafeHtmlUtils.fromString("Folio")), forzarFooter);
        dataGrid.setColumnWidth(folioCol, 40, Style.Unit.PCT);

    }

    public int setEmpleado(EmpleadoQuincenal empleadoQuincenal) {
        List<Incidencia> result = new ArrayList<>();
        if (empleadoQuincenal != null) {
            this.idEmpleado = empleadoQuincenal.getIdEmpleado();
            result = empleadoQuincenal.getIncidencias();
            Collections.sort(result, new Comparator<Incidencia>() {
                @Override
                public int compare(Incidencia f1, Incidencia f2) {
                    //return f1.getFechaInicial().compareTo(f2.getFechaInicial());
                    return f1.getId().compareTo(f2.getId());
                }
            });
        }
        provider.setList(result);
        return result.size();
    }

    // <editor-fold defaultstate="collapsed" desc="interface FaltasListener"> 
    public interface FaltasListener extends java.util.EventListener {

        void onFalta(MethodEvent event);
    }
    private final ArrayList listeners = new ArrayList();

    public void addFaltasListener(FaltasListener listener) {
        listeners.add(listener);
    }

    public void removeFaltasListener(FaltasListener listener) {
        listeners.remove(listener);
    }

    public void onFalta(MethodEvent restEvent) {
        int y = 1;
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            FaltasListener listener = (FaltasListener) it.next();
            listener.onFalta(restEvent);
        }
    }
    // </editor-fold>
    
}
