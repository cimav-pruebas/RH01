/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoFalta;
import cimav.client.data.domain.EmpleadoNomina;
import cimav.client.data.domain.Falta;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.FaltaREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
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
    DataGrid<Falta> dataGrid;
    
    @UiField
    Anchor anchorPlus;
    
    private ListDataProvider<Falta> provider;
    private FaltaREST faltaREST;
    
    private final ValueListBox<ETipoFalta> faltaChosen;
    
    private Integer idEmpleado;
    
    public NominaFaltasUI() {
        
        this.buildGrid(); // antes del initWidget
        
        initWidget(uiBinder.createAndBindUi(this));
        
        
        faltaChosen = new org.gwtbootstrap3.client.ui.ValueListBox<>(new Renderer<ETipoFalta>() {
            @Override
            public String render(ETipoFalta object) {
                if (object == null) {
                    return "None";
                }
                return object.getDescripcion();
            }

            @Override
            public void render(ETipoFalta object, Appendable appendable) throws IOException {
                String s = render(object);
                appendable.append(s);
            }
        });
        List<ETipoFalta> tipos = Arrays.asList(ETipoFalta.values());
        faltaChosen.setValue(ETipoFalta.AI); //default
        faltaChosen.setAcceptableValues(tipos);
        faltaChosen.addStyleName("movimientos-chosen");
        
        htmlPanel.add(faltaChosen);
        
        anchorPlus.addClickHandler(new ClickPlus());
        
        Properties wnd = window.cast();
        wnd.setFunction("removeFalta", new Function() {
            public void f() {
                JsArrayMixed args = arguments(0);
                String idFalta = args.getString(0);
                getREST().remove(idFalta);
            }
        });

        
    }
    private void buildGrid() {

        List<Falta> list = new ArrayList<>();
        provider = new ListDataProvider<>(list);
        dataGrid = new DataGrid<>(provider.getKeyProvider());
        dataGrid.getElement().setId("idDataGrid");

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin faltas"));

        dataGrid.setPageSize(20);

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
            ETipoFalta selected = faltaChosen.getValue();
            if (selected != null && selected.getId() != null && !selected.getId().isEmpty()) {
                for (Falta f : provider.getList()) {
                    if (f.getTipoFalta().equals(selected)) {
                        add = false;
                        break;
                    }
                }
            } else {
                add = false;
            }
            if (add) {
                Falta nueva = new Falta();
                nueva.setIdEmpleado(idEmpleado);
                nueva.setTipoFalta(selected);
                // Crearla en la DB
                getREST().create(nueva);
            }
        }
    }
    
    private FaltaREST getREST() {
        if (faltaREST == null) {
            faltaREST = new FaltaREST();
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
        Column<Falta, String> iconCol = new Column<Falta, String>(new NomIconInputCell(NomIconInputCell.FALTA)) {
            @Override
            public String getValue(Falta object) {
                return "" + object.getId();
            }
        };
        dataGrid.addColumn(iconCol, "");
        dataGrid.setColumnWidth(iconCol, 16, Style.Unit.PX);
        
        // Id+Tipo
        Column<Falta, SafeHtml> tipoCol = new Column<Falta, SafeHtml>(new SafeHtmlCell()) {
            @Override
            public SafeHtml getValue(Falta object) {
                SafeHtmlBuilder sb = new SafeHtmlBuilder();
                sb.appendHtmlConstant("<div style='outline-style:none; white-space: nowrap;'><strong>" +object.getIdTipo()+ "</strong> " + "<span>"+object.getTipoFalta().getDescripcion()+"</span></div>");
                return sb.toSafeHtml();
            }
        };
        dataGrid.addColumn(tipoCol, "Tipo");
        dataGrid.setColumnWidth(tipoCol, 60, Style.Unit.PCT);

        // Fecha
        Column<Falta, String> fechaCol = new Column<Falta, String>((new NomDateInputCell())) {
            @Override
            public String getValue(Falta object) {
                DateTimeFormat dtf = DateTimeFormat.getFormat("yyy-MM-dd"); // usado por el Input de HTML5
                String result = dtf.format(object.getFechaInicio());
                return result;
            }
        };
        dataGrid.addColumn(fechaCol, "Fecha");
        dataGrid.setColumnWidth(fechaCol, 120, Style.Unit.PX);

        // Dias
        Column<Falta, String> diasCol = new Column<Falta, String>(new NomIntegerInputCell("80")) {
            @Override
            public String getValue(Falta object) {
                Integer result = object == null || object.getDias() == null ? 0 : object.getDias();
                return Integer.toString(result);
            }
        };
        diasCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        dataGrid.addColumn(diasCol, "Días");
        dataGrid.setColumnWidth(diasCol, 68, Style.Unit.PX);

        // Faltas
        Column<Falta, String> faltasCol = new Column<Falta, String>(new TextCell()) {
            @Override
            public String getValue(Falta object) {
                Integer result = object == null || object.getFaltas()== null ? 0 : object.getFaltas();
                return Integer.toString(result);
            }
        };
        faltasCol.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_RIGHT);
        Header<String> forzarFooter = new Header<String>(new TextCell()) {
            @Override
            public String getValue() {
                return "  ";
            }
        };
        //dataGrid.addColumn(faltasCol, "Días");
        dataGrid.addColumn(faltasCol, new SafeHtmlHeader(SafeHtmlUtils.fromString("Faltas")), forzarFooter);
        dataGrid.setColumnWidth(faltasCol, 68, Style.Unit.PX);

        // Folio
        Column<Falta, String> folioCol = new Column<Falta, String>(new NomTextInputCell()) {
            @Override
            public String getValue(Falta object) {
                return object.getFolio();
            }
        };
        dataGrid.addColumn(folioCol, "Folio");
        dataGrid.setColumnWidth(folioCol, 40, Style.Unit.PCT);

    }

    public int setEmpleado(EmpleadoNomina empleado) {
        this.idEmpleado = empleado.getId();
        List<Falta> result = empleado.getFaltaCollection();
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
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            FaltasListener listener = (FaltasListener) it.next();
            listener.onFalta(restEvent);
        }
    }
    // </editor-fold>
    
}
