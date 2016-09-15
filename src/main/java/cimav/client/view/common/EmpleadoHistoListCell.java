/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import cimav.client.data.domain.EGrupo;
import cimav.client.data.domain.EmpleadoHisto;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.query.client.GQuery;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 *
 * @author calderon
 */
public class EmpleadoHistoListCell  extends AbstractCell<EmpleadoHisto> {

    private final SingleSelectionModel<EmpleadoHisto> selectionModel;

    private String colorSelected;
    
    public EmpleadoHistoListCell(SingleSelectionModel<EmpleadoHisto> selectionModel) {
        this.selectionModel = selectionModel;
        this.colorSelected = "#628cd5";
    }
        
    public void setSelectable(boolean selectable, int empIdSel) {
        if (selectable) {
            colorSelected = "#628cd5";
        } else {
            colorSelected = "lightgray"; // "#f0ad4e";
        }
        
        GQuery.$("#td_selec_" + empIdSel + "_left").css("background-color", colorSelected);
        GQuery.$("#td_selec_" + empIdSel + "_right").css("background-color", colorSelected);
        
    }
        @Override
        public void render(Cell.Context context, EmpleadoHisto value, SafeHtmlBuilder sb) {
            if (value == null) {
                return;
            }

            boolean isSelected = this.selectionModel != null && this.selectionModel.getSelectedObject() != null
                    && this.selectionModel.getSelectedObject().equals(value);

            String es_null = "---";
            String grupoStr = value.getGrupo() != null ? value.getGrupo().getCode() : es_null;
            boolean tieneEstimulos = EGrupo.CYT.equals(value.getGrupo());
            if (tieneEstimulos) {
                String estimulos = value.getEstimulosProductividad() != null ? "" + value.getEstimulosProductividad() : es_null;
                grupoStr = grupoStr + "(" + estimulos + ")";
            }
            String estimulosCyt = value.getEstimulosProductividad() != null ? "" + value.getEstimulosProductividad() : es_null;
//            String deptoCodeStr = value.getDepartamento() != null ? value.getDepartamento().getCode() : es_null;
//            String deptoNameStr = value.getDepartamento() != null ? value.getDepartamento().getName() : es_null;
//            String nivelStr = value.getNivel() != null ? value.getNivel().getCode() : es_null;
//            String nivelNombreStr = value.getNivel() != null ? value.getNivel().getName(): es_null;
//            String sedeStr = value.getSede() != null ? value.getSede().getAbrev() : es_null;
//            DateTimeFormat dtf = DateTimeFormat.getFormat("dd/MMM/yyyy");
//            String fechaAntStr = dtf.format(value.getFechaAntiguedad());
//            String diasMesesAniosStr = "Nulo";
//            if (value.getPantYears() != null) {
//                diasMesesAniosStr = value.getPantYears() + " años(s), "
//                        + value.getPantMonths() + " mese(s), "
//                        + value.getPantDayOdd() + " días(s)";
//            }
            
            String td_selec_id = "td_selec_" + value.getId();
            
            String html
                    = "<table width='100%' cellspacing='0' cellpadding='0' style='cursor: pointer; text-align: left; vertical-align: middle; border-bottom:1px solid lightgray;'>\n"
                    + " <tbody> \n"
                    + "  <tr >\n"
                    + "    <td rowspan='6' id='" + td_selec_id + "_left' class'td_selection' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "    <td rowspan='6' id='" + td_selec_id + "_right' class'td_selection' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td width='78px' rowspan='3' style='text-align: center;'>"
                    + "         <img data-toggle='tooltip' data-placement='left' title='TOOL_TIP_ID_REEMPLAZO' src='URL_FOTO_REEMPLAZO' style='border:1px solid lightgray; margin-top: 3px; border-radius:50%; padding:2px;'/>"
                    + "    </td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='2' style='vertical-align: top;'><h5 style='margin-top: 0px; margin-bottom: 0px;'>NOMBRE_REEMPLAZO</h5></td>\n"
                    + "  </tr>\n"
                    + "  <tr >\n"
                    + "    <td  colspan='1' style='line-height: 1.8;'> "
                    + "         <code class='label-cyt-grp-niv'><span >CODE_REEMPLAZO</span></code> "
                    + "         <code class='label-cyt-grp-niv'><span >GRUPO_REEMPLAZO</span></code> "
                    + "         <code class='label-cyt-grp-niv' data-toggle='tooltip' data-placement='left' title='TOOL_TIP_NIVEL_REEMPLAZO'><span >NIVEL_REEMPLAZO</span></code> "
                    + "         <code class='label-cyt-grp-niv'><span >SEDE_REEMPLAZO</span></code> "
                    + "         <code class='label-cyt-grp-niv' data-toggle='tooltip' data-placement='left' title='DATO_ANTIGUEDAD_REEMPLAZO'><span >FECHA_ANTIGUEDAD_REEMPLAZO</span></code> "
                    + "    </td>\n"
                    + "  </tr>\n"
                    + "  <tr style='border-bottom:1px solid lightgray;'>\n"
                    + "    <td style='text-align:center;' ></td>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "  </tr>\n"
                    + " </tbody> "
                    + "</table>";

            html
                    = "<table width='100%' cellspacing='0' cellpadding='0' style='cursor: pointer; text-align: left; vertical-align: middle; border-bottom:1px solid lightgray;'>\n"
                    + " <tbody> \n"
                    + "  <tr >\n"
                    + "    <td colspan='2' style='height:10px; font-size:x-small;'>1:1,1:2</td>\n"
                    + "  </tr>\n"
                    + "  <tr >\n"
                    + "    <td style='width:80px; text-align: center; vertical-align: top; padding-top:2px;'><code style='background-color:blue; color:white; font-size:larger;'>2016|01</code></td>\n"
                    + "    <td style=''>"
                        + " <table style'margin-left: 20px;'><tbody>"
                        + "     <tr>"
                        + "         <td style='text-align: right; vertical-align: bottom; padding-bottom: 2px;'>fecha ingreso</td><td style='padding-left: 4px; font-size: large;'>10/01/2010</td><td>1:3</td><td>1:4</td><td>1:5</td><td>1:6</td><td>1:7</td>"
                        + "     </tr>"
                        + "     <tr>"
                        + "         <td style='text-align: right; vertical-align: bottom;padding-bottom: 2px;'>fecha antigüedad</td><td style='padding-left: 4px; font-size: large;'>10/Ene/2010</td><td>2:3</td><td>2:4</td><td>2:5</td><td>2:6</td><td>2:7</td>"
                        + "     </tr>"
                        + "     <tr>"
                        + "         <td style='text-align: right; vertical-align: bottom; padding-bottom: 2px;'>fecha baja</td><td style='padding-left: 4px; font-size: large;'>---</td><td>3:3</td><td>3:4</td><td>3:5</td><td>3:6</td><td>3:7</td>"
                        + "     </tr>"
                        + "     <tr>"
                        + "         <td style='text-align: right; vertical-align: bottom; padding-bottom: 2px;'>...</td><td style='padding-left: 4px; font-size: large;'>---</td><td>4:3</td><td>4:4</td><td>4:5</td><td>4:6</td><td>4:7</td>"
                        + "     </tr>"
                        + " </tbody></table>"
                    + "    </td>\n"
                    + "  </tr>\n"
                    + "  <tr >\n"
                    + "    <td colspan='2' style='height:10px; font-size:x-small;'>3:1,3:2</td>\n"
                    + "  </tr>\n"
                    + " </tbody> "
                    + "</table>";
            
            if (isSelected) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: " + colorSelected + ";"); // 628cd5;");
            } else if (value.isDirty() != null && value.isDirty()) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: lightgray;");
            } else {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #F8F8F8;");
            }
            
            try {
                
                String idReem = "---";
                if (value.getId() != null) {
                    idReem = value.getId().toString();
                }
                html = html.replace("CODE_REEMPLAZO", chkStrNull(value.getCode()));
//                html = html.replace("URL_FOTO_REEMPLAZO", chkStrNull(value.getUrlPhoto()));
                html = html.replace("NOMBRE_REEMPLAZO", chkStrNull(value.getName()));
                html = html.replace("GRUPO_REEMPLAZO", chkStrNull(grupoStr));
//                html = html.replace("TOOL_TIP_NIVEL_REEMPLAZO", chkStrNull(nivelNombreStr));
//                html = html.replace("NIVEL_REEMPLAZO", chkStrNull(nivelStr));
//                html = html.replace("DEPTO_CODIGO_REEMPLAZO", chkStrNull(deptoCodeStr));
//                html = html.replace("TOOL_TIP_DEPTO_REEMPLAZO", chkStrNull(deptoNameStr));
//                html = html.replace("SEDE_REEMPLAZO", chkStrNull(sedeStr));
//                html = html.replace("FECHA_ANTIGUEDAD_REEMPLAZO", chkStrNull(fechaAntStr));
//                html = html.replace("DATO_ANTIGUEDAD_REEMPLAZO", chkStrNull(diasMesesAniosStr));
                if (value.getId() != null) {
                    html = html.replace("ID_REEMPLAZO", value.getId().toString());
                } else {
                    html = html.replace("ID_REEMPLAZO", "---");
                }

                sb.appendHtmlConstant(html);
            } catch (Exception e) {
                Window.alert("Catch it! " + html);
            }
        }
        
        private String chkStrNull(String str) {
            String r = str != null ? str.trim() : "---";
            return r;
        }

//    public static native String camelize(String str)/*-{
//            return (str.match(/\-/gi) ? str.toLowerCase().replace(/\-(\w)/gi, function(a, c){return c.toUpperCase();}) : str);
//    }-*/;    
//    
//    public static String capitalize(String value) {
//    return value == null ? value : value.substring(0, 1).toUpperCase() + value.substring(1).toLowerCase();
//  }
//
        /**
         * Truncate a string and add an ellipsis ('...') to the end if it exceeds
         * the specified length.
         *
         * @param value the string to truncate
         * @param len the maximum length to allow before truncating
         * @return the converted text
         */
        public String ellipse(String value, int len) {
            if (value != null && value.length() > len) {
                return value.substring(0, len - 3) + "...";
            }
            return value;
        }


}
