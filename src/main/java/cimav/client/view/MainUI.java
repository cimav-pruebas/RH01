/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view;

import cimav.client.data.domain.Quincena;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.CalculoREST;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavList;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Iterator;
import org.gwtbootstrap3.extras.growl.client.ui.Growl;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlOptions;
import org.gwtbootstrap3.extras.growl.client.ui.GrowlType;

/**
 *
 * @author juan.calderon
 */
public class MainUI extends Composite {

    //static EventBus eventBus = GWT.create(SimpleEventBus.class); 
    private static final MainUiBinder uiBinder = GWT.create(MainUiBinder.class);

    interface MainUiBinder extends UiBinder<Widget, MainUI> {
    }

    public static final String OPT_NONE = "optionNone";
    public static final String OPT_PERSONAL = "optionPersonal";
    public static final String OPT_DEPARTAMENTOS = "optionDepartamentos";
    public static final String OPT_TABULADOR = "optionTabulador";
    public static final String OPT_NOMINA = "optionNomina";

    @UiField StackLayoutPanel westPanel;
    @UiField FlowPanel workPanel;
    //@UiField HorizontalPanel centerPanelHeaderId;
    @UiField Label lTitulo;
    @UiField Label lSubTitulo;
    @UiField Label lNumQuincena;

    Widget currentWorkWidget;

    private static Quincena quincena;
    
    public void setTit(String t) {
        lSubTitulo.setText(t);
    }

    @UiHandler({"optionUno", "optionDos", "optionTres", OPT_PERSONAL, OPT_DEPARTAMENTOS, OPT_TABULADOR, OPT_NOMINA})
    protected void onClick(ClickEvent e) {

        // Pone todos los sub-menus en DesActivados
        Iterator<Widget> arrayOfWidgets = westPanel.iterator();
        while (arrayOfWidgets.hasNext()) {
            Widget widgetNavList = arrayOfWidgets.next();
            if (widgetNavList instanceof NavList) {
                NavList navList = (NavList) widgetNavList;
                for (int i = 0; i < navList.getWidgetCount(); i++) {
                    Widget widgetNavLink = navList.getWidget(i);
                    if (widgetNavLink instanceof NavLink) {
                        NavLink navLink = (NavLink) widgetNavLink;
                        navLink.setActive(false);
                    }
                }
            }
        }
        // Activa el clickeado
        IconAnchor src = (IconAnchor) e.getSource();
        NavLink navLink = (NavLink) src.getParent();
        navLink.setActive(true);

        // just to know...
        onOptionMenuChange(navLink.getName());
        if (OPT_PERSONAL.equals(navLink.getName())) {
        } else {
        }
    }

    public MainUI() {
        
        initWidget(uiBinder.createAndBindUi(this));

        westPanel.getElement().setAttribute("id", "west-panel");        
    }

    @Override
    protected void onLoad() {
        super.onLoad(); 
        
        if (quincena == null) {
            CalculoREST calculoREST = new CalculoREST();
            calculoREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.FIND_QUINCENA.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            quincena = (Quincena) restEvent.getResult();
                            
                            lNumQuincena.setText("" + quincena.getQuincena());
                            
                        } else {
                            GrowlOptions go = new GrowlOptions();
                            go.setType(GrowlType.DANGER);
                            go.setDelay(0);
                            Growl.growl("Falló al cargar la Quincena. " + restEvent.getReason(), go);
                        }
                    }
                }
            });
            calculoREST.findQuincena();
        }
        
        //centerPanelHeaderId.getElement().setId("centerPanelHeaderId");
//        //GQuery.$("#centerPanelHeaderId").css("border","solid 5px red");
//        List<Widget> tds = GQuery.$("#centerPanelHeaderId").$("table > tr > td").widgets();
//        if (tds != null && tds.size() >= 2) {
//            tds.get(0).getElement().getStyle().setWidth(300, Style.Unit.PX);
//            tds.get(1).getElement().getStyle().setWidth(100, Style.Unit.PCT);
//        }
//        centerPanelHeaderId.setCellWidth(centerPanelHeaderId.getWidget(0), "600px");
//        centerPanelHeaderId.setCellWidth(centerPanelHeaderId.getWidget(1), "50%");
//        centerPanelHeaderId.setCellHorizontalAlignment(centerPanelHeaderId.getWidget(1), HasHorizontalAlignment.HorizontalAlignmentConstant.endOf(HasDirection.Direction.RTL));

    }
    
    

    public void setCenterPanel(String titulo, String subTitulo, Widget widget) {

        // remover el widget anterior (si habia)
        if (currentWorkWidget != null && workPanel.getWidgetIndex(currentWorkWidget) >= 0) {
            workPanel.remove(currentWorkWidget);
        }
        //workPanel.clear(); // remover lo anterior

        // keep el widget nuevo
        currentWorkWidget = widget;

        lTitulo.setText(titulo);
        lSubTitulo.setText(subTitulo);

        if (currentWorkWidget != null) {
            workPanel.add(currentWorkWidget); // agregar el enviado
        }
    }

    public static Quincena getQuincena() {
        return quincena;
    }
    
    // <editor-fold defaultstate="collapsed" desc="interface OptionMenuChangeListener para cuando selecciona una Option"> 
    public interface OptionMenuChangeListener extends java.util.EventListener {

        void onOptionMenuChange(String option);
    }
    private final ArrayList listeners = new ArrayList();

    public void addOptionMenuChangeListener(OptionMenuChangeListener listener) {
        listeners.add(listener);
    }

    public void removeOptionMenuChangeListener(OptionMenuChangeListener listener) {
        listeners.remove(listener);
    }

    public void onOptionMenuChange(String option) {
        for (Iterator it = listeners.iterator(); it.hasNext();) {
            OptionMenuChangeListener listener = (OptionMenuChangeListener) it.next();
            listener.onOptionMenuChange(option);
        }
    }
    // </editor-fold>

}
