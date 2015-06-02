/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client;

import cimav.client.view.departamentos.DeptosUi;
import cimav.client.view.empleados.EmpleadosUI;
import cimav.client.view.tabulador.TabuladoresUi;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import javax.validation.constraints.NotNull;
import org.fusesource.restygwt.client.Defaults;
import org.jboss.errai.databinding.client.BindableProxyLoader;

/**
 * Main entry point.̛
 *
 * @author juan.calderon
 */
public class MainEntryPoint implements EntryPoint {

    static {

        /* Default initialize for RestyGWT */
        Defaults.setServiceRoot(GWT.getHostPageBaseURL());
        Defaults.ignoreJsonNulls();
        String DATEFORMAT = "yyyy-MM-dd'T'HH:mm:ssZ";
        Defaults.setDateFormat(DATEFORMAT); //2015-01-14T00:00:00-0700
    }
    
    // Vistas UI
    private final MainUI mainUi;
    private EmpleadosUI empleadosUI;
    private DeptosUi deptosUI;
    private TabuladoresUi tabuladoresUi;
    
//    public static Modal myModal;
    
    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
        
        // Errai Binding
        BindableProxyLoader proxyLoader = GWT.create(BindableProxyLoader.class);
        proxyLoader.loadBindableProxies();        
        
        mainUi = new MainUI();
    }
    
    @Override
    public void onModuleLoad() {
        
        GWT.setUncaughtExceptionHandler(new GWT.UncaughtExceptionHandler() {
            @Override
            public void onUncaughtException(@NotNull Throwable e) {
                ensureNotUmbrellaError(e);
            }
        });
        
//        myModal = new Modal();
//        myModal.setTitle("The Title...");
//        myModal.ensureDebugId("myModal");
//        myModal.setBackdrop(BackdropType.STATIC);
//        myModal.setCloseVisible(true);
//        myModal.setDynamicSafe(true);
//        myModal.hide();
////        Style sty = new Style() {
////            @Override
////            public String get() {
////                return "top: 10%; left:50%; width:560px; height:400px;   background-color: red;";
////            }
////        };
////        myModal.setStyle(sty);
//        
//        RootLayoutPanel.get().add(myModal);
        RootLayoutPanel.get().add(mainUi);
        
        mainUi.addOptionMenuChangeListener(new MainUI.OptionMenuChangeListener() {

            @Override
            public void onOptionMenuChange(String option) {
                switch (option) {
                    case MainUI.OPT_PERSONAL:
                        if (empleadosUI == null) {
                            // actua como Singleton en el ciclo de vida
                            empleadosUI = new EmpleadosUI();
                        }
                        mainUi.setCenterPanel("Personal", "Consultas, altas, bajas y cambios", empleadosUI);
                        break;
                    case MainUI.OPT_DEPARTAMENTOS:
                        if (deptosUI == null) {
                            deptosUI = new DeptosUi();
                        }
                        mainUi.setCenterPanel("Departamentos", "Consultas, altas, bajas y cambios", deptosUI);
                        break;
                    case MainUI.OPT_TABULADOR:
                        if (tabuladoresUi == null) {
                            tabuladoresUi = new TabuladoresUi();
                        }
                        mainUi.setCenterPanel("Tabuladores", "Consultas, altas y bajas", tabuladoresUi);
                        break;
                    default:
                        mainUi.setCenterPanel(option, "Not Yet Implemented...", null);
                        break;
                }

            }
        });
        
    }
    
    private static void ensureNotUmbrellaError(@NotNull Throwable e) {
        for (Throwable th : ((UmbrellaException) e).getCauses()) {
            if (th instanceof UmbrellaException) {
                ensureNotUmbrellaError(th);
            } else {
                System.err.println(th);
            }
        }
    }
    
}
