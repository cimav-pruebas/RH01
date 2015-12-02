/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.nomina;

import org.gwtbootstrap3.client.ui.Badge;
import org.gwtbootstrap3.client.ui.TabListItem;

/**
 *
 * @author juan.calderon
 */
public class TabBadgeListItem extends TabListItem {

    private Badge badge;
    
    // <a href="javascript:;" data-toggle="tab" data-target="#tabPercepPorSaldoId">Saldo</a>
    
    public TabBadgeListItem() {
        this("");
    }

    public TabBadgeListItem(String text) {
        super(text);
        badge = new Badge("0");
        badge.getElement().setAttribute("style", "margin-left: 5px; font-size: 10px; padding: 1px 6px; vertical-align: middle; background-color: burlywood;");
        
        add(badge, (com.google.gwt.dom.client.Element)anchor.getElement());
    }

    public void setCount(String count) {
        this.badge.setText(count);
    }
    
    
}
