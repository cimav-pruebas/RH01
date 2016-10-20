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
public class NomCantidadInputCell extends TextInputCell {

    private static Template template;

    private Boolean asNegative;
    
    interface Template extends SafeHtmlTemplates {
        @SafeHtmlTemplates.Template("<input type='text' min='0.00' max='29999.99'  value='{0}' tabindex='-1' size='12' maxlength='12' style='{1}'></input>")
        SafeHtml input(String value, String style);
    }

    public NomCantidadInputCell() {
        template = GWT.create(Template.class);
        this.asNegative = false;
    }

    public void setAsNegative(boolean asNeg) {
        this.asNegative = asNeg;
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
        String s = (viewData != null) ? viewData.getCurrentValue() : value;
        if (s == null) // || isEditing)
        {
            sb.appendHtmlConstant("<input type=\"text\" tabindex=\"-1\"></input>");
        } else {
            s = s.replace("$", "");
            // this is where we set value, size, style
            boolean isNeg = false; //Double.valueOf(s) < 0.00;
            try {
                String v = s.replace(",", "");
                isNeg = Double.valueOf(v) < 0.00;
            } catch (Exception e) {
                
            }
            if (isNeg || this.asNegative) {
                sb.append(template.input(s, "color:red; width: 100%; text-align: inherit; margin: 0px; height: 22px !important; font-size:11px;"));  
            } else {
                sb.append(template.input(s, "width: 100%; text-align: inherit; margin: 0px; height: 22px !important; font-size:11px;"));  
            }
        }
    }
}
