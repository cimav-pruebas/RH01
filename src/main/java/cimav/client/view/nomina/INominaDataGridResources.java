/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import com.google.gwt.resources.client.CssResource;
import com.google.gwt.user.cellview.client.DataGrid;

/**
 *
 * @author juan.calderon
 */
public interface INominaDataGridResources extends DataGrid.Resources {
 
    @CssResource.NotStrict
    @Source({DataGrid.Style.DEFAULT_CSS, "cimav/client/view/nomina/mominaDataGridStyle.css"})
    @Override
    CustomStyle dataGridStyle();

    
 
    interface CustomStyle extends DataGrid.Style {
     
    }
}
