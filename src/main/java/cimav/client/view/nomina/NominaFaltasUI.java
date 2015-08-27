/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import cimav.client.data.domain.ETipoFalta;
import cimav.client.data.domain.Falta;
import cimav.client.data.rest.FaltaREST;
import cimav.client.view.common.Utils;
import com.google.gwt.cell.client.EditTextCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.dom.client.TableRowElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.gwtbootstrap3.client.ui.Anchor;

/**
 *
 * @author juan.calderon
 */
public class NominaFaltasUI extends Composite {
    
    private static NominaFaltasUIUiBinder uiBinder = GWT.create(NominaFaltasUIUiBinder.class);
    
    interface NominaFaltasUIUiBinder extends UiBinder<Widget, NominaFaltasUI> {
    }
    
    @UiField
    private HTMLPanel htmlPanel;
    
    @UiField(provided = true)
    private DataGrid<Falta> dataGrid;
    
    @UiField
    private Anchor anchorPlus;
    
    private int empleadoId;
    private ListDataProvider<Falta> provider;
    private FaltaREST faltaREST;
    
    private final ValueListBox<ETipoFalta> faltaChosen;
    
    public NominaFaltasUI() {
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
        
    }
    private void buildGrid() {

        List<Falta> list = new ArrayList<>();
        provider = new ListDataProvider<>(list);
        dataGrid = new DataGrid<>(provider.getKeyProvider());

        dataGrid.setAutoHeaderRefreshDisabled(true);

        dataGrid.setEmptyTableWidget(new Label("Sin movimientos"));

        dataGrid.setPageSize(20);

        initTableColumns();

//        // Add the CellList to the adapter in the database.
        provider.addDataDisplay(dataGrid);

        
    }
    private int c = 0;

    private class ClickPlus implements ClickHandler {

        @Override
        public void onClick(ClickEvent event) {
        }
    }
    
    private void initTableColumns() {


    }
    
    private FaltaREST getREST() {
        if (faltaREST == null) {
            faltaREST = new FaltaREST();
        }
        return faltaREST;
    }
}
