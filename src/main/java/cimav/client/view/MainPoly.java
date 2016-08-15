/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 *
 * @author calderon
 */
public class MainPoly extends Composite {

    interface MainUiBinder extends UiBinder<HTMLPanel, MainPoly> {
    }

    private static MainUiBinder ourUiBinder = GWT.create(MainUiBinder.class);

    public MainPoly() {
        initWidget(ourUiBinder.createAndBindUi(this));
    }
}
