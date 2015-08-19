/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import com.google.gwt.cell.client.TextInputCell;
import com.google.gwt.cell.client.TextInputCell.ViewData;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.client.SafeHtmlTemplates;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

class NomInputCell extends TextInputCell {

    private static Template template;

    interface Template extends SafeHtmlTemplates {

        // {0}, {1}, {2} relate to value, size, style

        @Template("<input type=\"text\" value=\"{0}\" tabindex=\"-1\" size=\"{1}\" maxlength=\"{1}\" style=\"{2}\"></input>")
        SafeHtml input(String value, String size, String style);
    }

    public NomInputCell() {
        template = GWT.create(Template.class);
    }

    @Override
    public void render(Context context, String value, SafeHtmlBuilder sb) {
        // Get the view data.
        Object key = context.getKey();
        ViewData viewData = this.getViewData(key);
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
            sb.append(template.input(s, "3", "width: 100%; text-align: inherit; margin: 0px; padding: 2px; height: 24px !important;"));
        }
    }
}
