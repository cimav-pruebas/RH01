/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.tabulador;

import cimav.client.common.EMethod;
import cimav.client.common.MethodEvent;
import cimav.client.data.domain.Tabulador;
import cimav.client.view.provider.BaseProvider;
import cimav.client.view.provider.NivelesProvider;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.ColumnSortEvent;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.SingleSelectionModel;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class NivelesUi extends Composite {
    
    private static NivelesUiUiBinder uiBinder = GWT.create(NivelesUiUiBinder.class);
    
    interface NivelesUiUiBinder extends UiBinder<Widget, NivelesUi> {
    }
    
    @UiField(provided = true) CellTable<Tabulador> dataGrid;
    
    @UiField(provided = true) SimplePager pager;
    
    public NivelesUi() {
        
        buildGrid();
        
        initWidget(uiBinder.createAndBindUi(this));
        
        NivelesProvider.get().findAll();
        
        NivelesProvider.get().addMethodExecutedListener(new BaseProvider.MethodExecutedListener() {
            @Override
            public void onMethodExecuted(MethodEvent methodEvent) {
                if (EMethod.FIND_ALL.equals(methodEvent.getMethod())
                    || EMethod.FIND_ALL_BASE.equals(methodEvent.getMethod())) {
                    
//                    List<Tabulador> niveles = (List<Tabulador>) methodEvent.getResult();
//                    Window.alert(niveles.size() + " niveles cargados.");
                    
                    // DataGrid es un Widget tipo RequireSize; por lo tanto debe ser hijo de un widget tipo ProvidesResize. 
                    // De lo contrario se "desaparece" al no poder ajustar a los cambios de tamaño.
                    dataGrid.setWidth("100%");
                    dataGrid.setHeight("100%");
                    dataGrid.redraw();

                }
            }
        });
                    
    }
    
    private void buildGrid() {
        // super.onLoad(); //To change body of generated methods, choose Tools | Templates.

        /*
         * Set a key provider that provides a unique key for each contact. If key is
         * used to identify contacts when fields (such as the name and address)
         * change.
         */
        dataGrid = new CellTable<>(NivelesProvider.get().getDataProvider());
        //cellTable.setWidth("100%");
        //cellTable.setHeight("100%");

        /*
         * Do not refresh the headers every time the data is updated. The footer
         * depends on the current data, so we do not disable auto refresh on the
         * footer.
         */
        dataGrid.setAutoHeaderRefreshDisabled(true);

        // Set the message to display when the table is empty.
        dataGrid.setEmptyTableWidget(new Label("No hay Niveles"));

        // Attach a column sort handler to the ListDataProvider to sort the list.
        ColumnSortEvent.ListHandler<Tabulador> sortHandler = new ColumnSortEvent.ListHandler<>(NivelesProvider.get().getDataProvider().getList());
        dataGrid.addColumnSortHandler(sortHandler);

        // Create a Pager to control the table.
        SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);
        pager = new SimplePager(SimplePager.TextLocation.CENTER, pagerResources, false, 0, true);
        pager.setDisplay(dataGrid);

        dataGrid.setPageSize(50);

        // Add a selection model so we can select cells.
        final SelectionModel<Tabulador> selectionModel = new SingleSelectionModel<>(NivelesProvider.get().getDataProvider());
        selectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                //System.out.println("123> " + event.getSource() + " - " + event.getAssociatedType());
                if (event.getSource() instanceof SingleSelectionModel) {

                    SingleSelectionModel selecter = (SingleSelectionModel) event.getSource();
                    Tabulador sel = (Tabulador) selecter.getSelectedObject();

                    GWT.log("Sel>> " + sel);
                }
            }
        });
        dataGrid.setSelectionModel(selectionModel);

        dataGrid.addDomHandler(new DoubleClickHandler() {
            @SuppressWarnings("unchecked")
            @Override
            public void onDoubleClick(DoubleClickEvent event) {
//                DataGrid<Departamento> grid = (DataGrid<Departamento>) event.getSource();
//                int row = grid.getKeyboardSelectedRow();
//                Departamento item = grid.getVisibleItem(row);
            }
        }, DoubleClickEvent.getType());

        initTableColumns(sortHandler);

        // Add the CellList to the adapter in the database.
        NivelesProvider.get().getDataProvider().addDataDisplay(dataGrid);

    }
    
    /**
     * Add the columns to the table.
     */
    private void initTableColumns(ColumnSortEvent.ListHandler<Tabulador> sortHandler) {

        // ID
        Column<Tabulador, String> idCol = new Column<Tabulador, String>(new TextCell()) {
                    @Override
                    public String getValue(Tabulador object) {
                        return object.getId().toString();
                    }
                };
        dataGrid.addColumn(idCol, "ID");
        dataGrid.setColumnWidth(idCol, 40, Style.Unit.PX);

        // Code
        Column<Tabulador, String> codigoCol = new Column<Tabulador, String>((new TextCell())) {
            @Override
            public String getValue(Tabulador object) {
                return object.getCode();
            }
        };
        codigoCol.setSortable(true);
        sortHandler.setComparator(codigoCol, new Comparator<Tabulador>() {
            @Override
            public int compare(Tabulador o1, Tabulador o2) {
                return o1.getCode().compareTo(o2.getCode());
            }
        });
        dataGrid.addColumn(codigoCol, "Código");
        dataGrid.setColumnWidth(codigoCol, 70, Style.Unit.PX);

        // Sueldo
        Column<Tabulador, String> sueldoCol = new Column<Tabulador, String>(new TextCell()) {
                    @Override
                    public String getValue(Tabulador object) {
                        return object.getSueldo().toString();
                    }
                };
        sueldoCol.setSortable(true);
        sortHandler.setComparator(sueldoCol, new Comparator<Tabulador>() {
            @Override
            public int compare(Tabulador o1, Tabulador o2) {
                return o1.getSueldo().compareTo(o2.getSueldo());
            }
        });
        dataGrid.addColumn(sueldoCol, "Sueldo");
        dataGrid.setColumnWidth(sueldoCol, 60, Style.Unit.PX);

        // matDidacticos
        Column<Tabulador, String> matDidacticosCol = new Column<Tabulador, String>(new TextCell()) {
                    @Override
                    public String getValue(Tabulador object) {
                        return object.getMatDidacticos().toString();
                    }
                };
        dataGrid.addColumn(matDidacticosCol, "Mat. Didacticos");
        dataGrid.setColumnWidth(matDidacticosCol, 60, Style.Unit.PX);

        // honorarios
        Column<Tabulador, String> honorariosCol = new Column<Tabulador, String>(new TextCell()) {
                    @Override
                    public String getValue(Tabulador object) {
                        return object.getHonorarios().toString();
                    }
                };
        dataGrid.addColumn(honorariosCol, "Honorarios");
        dataGrid.setColumnWidth(honorariosCol, 60, Style.Unit.PX);

        // compGarantizadaCol
        Column<Tabulador, String> compGarantizadaCol = new Column<Tabulador, String>(new TextCell()) {
                    @Override
                    public String getValue(Tabulador object) {
                        return object.getCompGarantizada().toString();
                    }
                };
        dataGrid.addColumn(compGarantizadaCol, "Comp. Garantizada");
        dataGrid.setColumnWidth(compGarantizadaCol, 60, Style.Unit.PX);

        // cargaAdmin
        Column<Tabulador, String> cargaAdminCol = new Column<Tabulador, String>(new TextCell()) {
                    @Override
                    public String getValue(Tabulador object) {
                        return object.getCargaAdmin().toString();
                    }
                };
        dataGrid.addColumn(cargaAdminCol, "Carga Admin.");
        dataGrid.setColumnWidth(cargaAdminCol, 60, Style.Unit.PX);

    }
    
}
