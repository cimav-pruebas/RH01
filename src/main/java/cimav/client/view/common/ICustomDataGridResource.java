/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.user.cellview.client.DataGrid;

/**
 *
 * @author juan.calderon
 */
public interface ICustomDataGridResource extends DataGrid.Resources {

    @ClientBundle.Source({DataGrid.Style.DEFAULT_CSS, "cimav/client/view/nomina/mominaDataGridStyle.css"})
    @Override
    MyCustomStyle dataGridStyle();

    public interface MyCustomStyle extends DataGrid.Style {}

}
