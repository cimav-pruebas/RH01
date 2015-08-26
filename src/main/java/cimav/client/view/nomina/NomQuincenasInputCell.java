package cimav.client.view.nomina;


import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author juan.calderon
 */
public class NomQuincenasInputCell extends TextInputCell {

    private static Template template;

    interface Template extends SafeHtmlTemplates {
        @Template("<input type='number' min='1' max='24' step='1' value='{0}' tabindex='-1' size='3' maxlength='2' style='{1}'></input>")
        SafeHtml input(String value, String style);
    }

    public NomQuincenasInputCell() {
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
        String s = (viewData != null) ? viewData.getCurrentValue() : value;
        if (s == null) // || isEditing)
        {
            sb.appendHtmlConstant("<input type=\"text\" tabindex=\"-1\"></input>");
        } else {
            // this is where we set value, size, style
            sb.append(template.input(s, "width: 100%; text-align: inherit; margin: 0px; height: 24px !important; padding: 2px 6px;"));
        }
    }
}
