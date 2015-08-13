/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import com.google.gwt.i18n.client.NumberFormat;
import java.math.BigDecimal;

/**
 *
 * @author juan.calderon
 */
public class Utils {

    /**
     * @param bigDecimal
     * @return $123,451.60
     */
    public static String formatCurrency(BigDecimal bigDecimal) {
        NumberFormat fmt = NumberFormat.getCurrencyFormat();
        String formatted = fmt.format(bigDecimal);    
        return formatted;
    }
    
    /**
     * @param bigDecimal
     * @return 123,452.60
     */
    public static String formatCantidad(BigDecimal bigDecimal) {
        if (bigDecimal == null) {
            bigDecimal = BigDecimal.ZERO;
        }
        NumberFormat fmt = NumberFormat.getFormat("###,###,###.00");
        String formatted = fmt.format(bigDecimal);
        return formatted;
    }
}
