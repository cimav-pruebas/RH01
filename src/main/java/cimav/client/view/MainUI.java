/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view;

import cimav.client.GWTService;
import cimav.client.GWTServiceAsync;
import cimav.client.data.domain.Quincena;
import cimav.client.data.rest.BaseREST;
import cimav.client.data.rest.CalculoREST;
import cimav.client.view.cerrar.BtnCerrarUi;
import cimav.client.view.common.Ajax;
import cimav.client.view.common.EMethod;
import cimav.client.view.common.ETypeResult;
import cimav.client.view.common.MethodEvent;
import cimav.shared.Usuario;
import com.github.gwtbootstrap.client.ui.Icon;
import com.github.gwtbootstrap.client.ui.NavLink;
import com.github.gwtbootstrap.client.ui.NavList;
import com.github.gwtbootstrap.client.ui.SplitDropdownButton;
import com.github.gwtbootstrap.client.ui.base.IconAnchor;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.api.gwt.oauth2.client.Auth;
import com.google.api.gwt.oauth2.client.AuthRequest;
import com.google.gwt.core.client.Callback;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NodeList;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.StackLayoutPanel;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.gwtbootstrap3.client.ui.Button;
import org.gwtbootstrap3.client.ui.constants.IconFlip;
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

    // constants for OAuth2 (don't forget to update GOOGLE_CLIENT_ID  -  http://code.google.com/apis/console)
    private static final Auth AUTH = Auth.get();
    private static final String GOOGLE_AUTH_URL = "https://accounts.google.com/o/oauth2/auth";
    private static final String GOOGLE_CLIENT_ID = "137884691024-m5vuc37r008v173kkfkaectus2eh3bhe.apps.googleusercontent.com"; //411158167495.apps.googleusercontent.com";
    // scret 8XDaLF40sc0-XGtxtLThmNLN
    // The auth scope being requested. This scope will allow the application to identify who the authenticated user is.
    private static final String USER_INFO_PROFILE_SCOPE = "https://www.googleapis.com/auth/userinfo.profile https://www.googleapis.com/auth/userinfo.email";

    private final GWTServiceAsync gwtServiceAsync;
    private Usuario usuario;
    
    public static final String OPT_NONE = "optionNone";
    public static final String OPT_PERSONAL = "optionPersonal";
    public static final String OPT_DEPARTAMENTOS = "optionDepartamentos";
    public static final String OPT_TABULADOR = "optionTabulador";
    public static final String OPT_NOMINA = "optionNomina";
    public static final String OPT_NOMINA_HISTO = "optionNominaHisto";

    @UiField StackLayoutPanel westPanel;
    @UiField FlowPanel workPanel;
    @UiField SplitDropdownButton dropDownMenuLoggin;
    //@UiField HorizontalPanel centerPanelHeaderId;
    @UiField Label lTitulo;
    @UiField Label lSubTitulo;
    @UiField Label lNumQuincena;
    @UiField Label lStatus;
    @UiField Label lFechasQuincena;

    @UiField DockLayoutPanel dockPanelLogged;
    @UiField NavLink lnkLoger;
    @UiField NavLink lnkCerrar;
    
    @UiField Button bars;
    @UiField DockLayoutPanel dockLayoutCenterPanel;
    
    Widget currentWorkWidget;

    //private static Quincena quincena;

    public MainUI() {
        
        this.gwtServiceAsync = GWT.create(GWTService.class);
        
        // crea al Usuario (vacío y no logeado)
        this.usuario = new Usuario();
        
        initWidget(uiBinder.createAndBindUi(this));

        westPanel.getElement().setAttribute("id", "west-panel");      
        
        lnkLoger.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                if (usuario.isLogged()) {
                    loginOut();
                } else {
                    loginIn();
                }
            }
        });
        
        BtnCerrarUi btnCerrarUi = new BtnCerrarUi();
        lnkCerrar.add(btnCerrarUi);
        btnCerrarUi.getBtnCerrar().addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                CalculoREST calculoREST = new CalculoREST();
                calculoREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                    @Override
                    public void onRESTExecuted(MethodEvent restEvent) {
                        if (EMethod.CERRAR_QUINCENA.equals(restEvent.getMethod())) {
                            if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                                Quincena.set((Quincena) restEvent.getResult());

                                String quin = Quincena.get().getQuincena() < 10 ? "0" + Quincena.get().getQuincena() : "" + Quincena.get().getQuincena();
                                lNumQuincena.setText(quin);
                                DateTimeFormat fmt = DateTimeFormat.getFormat("EEEE, MMM dd");
                                lFechasQuincena.setText("" + fmt.format(Quincena.get().getFechaInicio()) + " - " + fmt.format(Quincena.get().getFechaFinCalendario()));

                                lStatus.setText(Quincena.get().getStatusStr());

                            } else {
                                GrowlOptions go = new GrowlOptions();
                                go.setType(GrowlType.DANGER);
                                go.setDelay(0);
                                Growl.growl("Falló al cerrar la Quincena. " + restEvent.getReason(), go);
                            }
                        }
                    }
                });
                calculoREST.cerrarQuincena(true);
            }
        });
        
        // lo 1ewro es salirse
        loginOut();
        
        dockLayoutCenterPanel.getElement().getParentElement().setAttribute("id", "contenedor-west-id");
        bars.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                Element ele = Document.get().getElementById("contenedor-west-id");
                String position = ele.getStyle().getPosition();
                if (position.contains("absolute")) {
                    ele.getStyle().setPosition(Style.Position.STATIC);
                    westPanel.setVisible(false);
                    bars.setIconFlip(IconFlip.VERTICAL);
                } else {
                    ele.getStyle().setPosition(Style.Position.ABSOLUTE);
                    westPanel.setVisible(true);
                    bars.setIconFlip(IconFlip.HORIZONTAL);
                }
            }
        });
        
    }
    
    public void setTit(String t) {
        lSubTitulo.setText(t);
    }
    
    private void loginIn() {
        
        final AuthRequest req = new AuthRequest(GOOGLE_AUTH_URL, GOOGLE_CLIENT_ID).withScopes(USER_INFO_PROFILE_SCOPE);
        
        // Calling login() will display a popup to the user the first time it is
        // called. Once the user has granted access to the application,
        // subsequent calls to login() will not display the popup, and will
        // immediately result in the callback being given the token to use.
        
        /* Esta es la llamada al Login */

        // http://www.sw-engineering-candies.com/blog-1/howtogetuserinformationwithoauth2inagwtandgoogleappenginejavaapplication
        
        //https://www.googleapis.com/oauth2/v1/userinfo?alt=json&access_token=ya29.CjBxA9sjWcFMGa1gZyNEBsVyw7IBfh8ISz8kI27D3qW3_aQmgoYOtMDfJC7uCfoamwA
        
        
        AUTH.login(req, new Callback<String, Throwable>() {
            @Override
            public void onSuccess(final String tokenAutorizacion) {
                // Si se logea, regresa un Token Autorizado
                if (!tokenAutorizacion.trim().isEmpty()) { //TODO simpre considera que no tiene token
                    // Si no tiene AUN el token autorizado, busca la autorización
                    gwtServiceAsync.loginProfile(tokenAutorizacion, Ajax.call(new AsyncCallback<Usuario>() {
                        @Override
                        public void onFailure(Throwable caught) {
                            // Falló la autorización
                            GWT.log("login.autorizacion -> onFailure");
                            
                            // se Deslogea
                            loginOut();
                        }
                        @Override
                        public void onSuccess(Usuario usuarioSrv) {
                            // Paso la autorización
                            
                            // asigna el usuario logeado del lado del servidor al usuario del lado del cliente
                            usuario = usuarioSrv;

                            boolean tieneNom = usuario.getNombre() != null && !usuario.getNombre().trim().isEmpty();
                            boolean esDelCimav = usuario.getEmail() != null && usuario.getEmail().endsWith("@cimav.edu.mx"); 

                            // buscar en DB
                            List<String> permitidos =  new ArrayList<>();
                            permitidos.add("juan.calderon");
                            permitidos.add("maria.rangel");
                            permitidos.add("griselda.tamez");
                            permitidos.add("mariana.lopez");
                            permitidos.add("blanca.lopez");

                            String cuenta = usuario.getCuenta();
                            boolean esRegistrado = permitidos.contains(cuenta);

                            if (!tieneNom || !esDelCimav || !esRegistrado) {
                                loginOut();
                            } else {
                            
                            usuario.setLogged(true);
                            
                            // Actulizar componentes de Login
//                            loginImage.setVisible(true);
//                            loginImage.setUrl(usuario.getPictureUrl());
//                            loginLabel.setText(usuario.getNombre());
//                            loginSignImage.setTitle("Salir");
//                            loginSignImage.setUrl(Constantes.ICON_SIGN_OUT);                            
                                dropDownMenuLoggin.setText(usuario.getNombre());
                                lnkLoger.setText("Salir");
                                lnkLoger.setIcon(IconType.SIGNOUT);
                                lnkCerrar.setVisible(true);

                                // Muestra toda el Area Central
                                dockPanelLogged.setVisible(true);

                                BaseREST.initHeader(usuario.getCuenta());
                            }
                        }
                    }));
                    
                }
                
            }
            @Override
            public void onFailure(Throwable caught) {
                // No se logeo
                GWT.log("login -> onFailure");
                
                // se Deslogea
                loginOut();
            }
        });
        
    }
    
    private void loginOut() {
        // Borra todas las cuentas logeadas de esta clase
        Auth.get().clearAllTokens(); 
        
        // Descarga al usuario. Lo vacia y lo pone en No-logeado.
        usuario = new Usuario();

        BaseREST.initHeader(usuario.getCuenta());
        
        dropDownMenuLoggin.setText("Favor de firmarse");
        lnkLoger.setText("Entrar");
        lnkLoger.setIcon(IconType.SIGNIN);
        lnkCerrar.setVisible(false); 
        
        // Esconde toda el area central 
        dockPanelLogged.setVisible(false);
    }
    
    @UiHandler({"optionUno", "optionDos", "optionTres", OPT_PERSONAL, OPT_DEPARTAMENTOS, OPT_TABULADOR, OPT_NOMINA, OPT_NOMINA_HISTO})
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

    
    
    @Override
    protected void onLoad() {
        super.onLoad(); 

        BaseREST.initHeader("no.logged");
        
        if (Quincena.get() == null) {
            CalculoREST calculoREST = new CalculoREST();
            calculoREST.addRESTExecutedListener(new BaseREST.RESTExecutedListener() {
                @Override
                public void onRESTExecuted(MethodEvent restEvent) {
                    if (EMethod.FIND_QUINCENA.equals(restEvent.getMethod())) {
                        if (ETypeResult.SUCCESS.equals(restEvent.getTypeResult())) {
                            Quincena.set((Quincena) restEvent.getResult());
                            
                            String quin = Quincena.get().getQuincena() < 10 ? "0" + Quincena.get().getQuincena() : "" + Quincena.get().getQuincena();
                            lNumQuincena.setText(quin);
                            DateTimeFormat fmt = DateTimeFormat.getFormat("EEEE, MMM dd");
                            lFechasQuincena.setText("" + fmt.format(Quincena.get().getFechaInicio()) + " - " + fmt.format(Quincena.get().getFechaFinCalendario()));
                            
                            lStatus.setText(Quincena.get().getStatusStr());
                            
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
        
        NodeList<Element> lst = dropDownMenuLoggin.getParent().getParent().getElement().getElementsByTagName("div");
        lst.getItem(1).getStyle().setOverflow(Style.Overflow.VISIBLE);
        GQuery.$(".divider").css("height", "2px");
        
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
