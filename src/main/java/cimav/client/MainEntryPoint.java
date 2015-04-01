/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client;

import cimav.client.view.empleados.Customer;
import cimav.client.view.empleados.EmpleadosUI;
import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.UmbrellaException;
import com.google.gwt.user.client.ui.RootLayoutPanel;
import javax.validation.constraints.NotNull;
import org.fusesource.restygwt.client.Defaults;
import org.gwtbootstrap3.client.ui.Label;
import org.gwtbootstrap3.client.ui.TextBox;
import org.jboss.errai.databinding.client.api.DataBinder;

/**
 * Main entry point.
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
    
    private final TextBox nameTextBox = new TextBox();
    private final Label nameLabel = new Label();
    
    /**
     * Creates a new instance of MainEntryPoint
     */
    public MainEntryPoint() {
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
                        Customer customer = null;
                        try {
                            DataBinder<Customer> dataBinder = DataBinder.forType(Customer.class);
                            if (customer == null) 
                            customer = dataBinder
                                .bind(nameTextBox, "name")
                                .bind(nameLabel, "name")
                                .getModel();
                        } catch(Exception e) {
                            System.out.println(">>> " + e.getMessage());
                            GWT.log("<<<<< " + e.getMessage());
                        }
        
                        RootLayoutPanel.get().add(nameTextBox);
                        RootLayoutPanel.get().add(nameLabel);
                        
                        mainUi.setCenterPanel("Departamentos", "Consultas, altas, bajas y cambios", null);
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
