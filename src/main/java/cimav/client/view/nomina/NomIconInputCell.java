/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 *
 * @author juan.calderon
 */
public class NomIconInputCell extends TextInputCell {
    private static Template template;

    interface Template extends SafeHtmlTemplates {
        
        String strHtml = "<a onclick='removeSaldo(id)' id='{0}' style='color: #B3B3B3; visibility:hidden;'><i class='fa fa-times fa-lg' style='cursor: pointer; transform: scale(1.2); ' /></a>";
        @SafeHtmlTemplates.Template(strHtml)
        SafeHtml input(String visibility);
    }
    
    public NomIconInputCell() {
        template = GWT.create(Template.class);
    }

    @Override
    public void render(Cell.Context context, String value, SafeHtmlBuilder sb) {
        // Get the view data.
        Object key = context.getKey();
        TextInputCell.ViewData viewData = this.getViewData(key);
        if (viewData != null && viewData.getCurrentValue().equals(value)) {
            clearViewData(key);
            viewData = null;
        }

        // boolean isEditing = this.isEditing(context, null, value);
        String v = (viewData != null) ? viewData.getCurrentValue() : value;
        if (v == null) // || isEditing)
        {
            sb.appendHtmlConstant("<input type='text' tabindex='-1'></input>");
        } else {
            // this is where we set value, size, style
            sb.append(template.input(v));
        }
    }
}
