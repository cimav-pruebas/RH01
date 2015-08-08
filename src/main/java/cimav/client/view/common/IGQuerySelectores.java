/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import com.google.gwt.query.client.GQuery;
import com.google.gwt.query.client.Selector;
import com.google.gwt.query.client.Selectors;

/**
 *
 * @author juan.calderon
 */
public interface IGQuerySelectores extends Selectors {
 
   @Selector(".trigger")
    GQuery getFlipCardIcon();
    
}
