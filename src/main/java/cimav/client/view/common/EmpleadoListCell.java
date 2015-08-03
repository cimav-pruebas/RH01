/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import cimav.client.data.domain.EmpleadoBase;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.user.client.Window;
import com.google.gwt.view.client.SingleSelectionModel;

/**
 *
 * @author juan.calderon
 */
public class EmpleadoListCell extends AbstractCell<EmpleadoBase> {

    private final SingleSelectionModel<EmpleadoBase> selectionModel;

    public EmpleadoListCell(SingleSelectionModel<EmpleadoBase> selectionModel) {
        this.selectionModel = selectionModel;
    }
        
        @Override
        public void render(Cell.Context context, EmpleadoBase value, SafeHtmlBuilder sb) {
            if (value == null) {
                return;
            }

            boolean isSelected = this.selectionModel != null && this.selectionModel.getSelectedObject() != null
                    && this.selectionModel.getSelectedObject().equals(value);

            // TODO reemplazar código a pie por EmpleadosItem
            String es_null = "---";
            String grupoStr = value.getGrupo() != null ? value.getGrupo().getCode() : es_null;
            String deptoCodeStr = value.getDepartamento() != null ? value.getDepartamento().getCode() : es_null;
            String deptoNameStr = value.getDepartamento() != null ? value.getDepartamento().getName() : es_null;
            String nivelStr = value.getNivel() != null ? value.getNivel().getCode() : es_null;
            String sedeStr = value.getSede() != null ? value.getSede().getAbrev() : es_null;
            //sedeStr = value.isDirty() != null ? value.isDirty().toString() : es_null;

            String html
                    = "<table width='100%' cellspacing='0' cellpadding='0' style='cursor: pointer; text-align: left; vertical-align: middle; border-bottom:1px solid lightgray;'>\n"
                    + "  <tr>\n"
                    + "    <td width='4px' rowspan='6' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    //                    + "    <td colspan='3' style='height:10px;'><span STYLE_INDICADOR_REEMPLAZO /></td>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "    <td width='4px' rowspan='6' style='height:auto; width: 5px; SELECTED_COLOR_REEMPLAZO'></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td width='78px' rowspan='3' style='text-align: center;'><img src='URL_FOTO_REEMPLAZO' style='border:1px solid lightgray; margin-top: 3px; border-radius:50%; padding:2px;'></td>\n"
//                    + "    <td colspan='2' style='vertical-align: bottom;'><h4 style='margin-top: 0px; margin-bottom: 0px; font-size: 17px;'>APELLIDOS_REEMPLAZO,</h4></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='2' style='vertical-align: top;'><h5 style='margin-top: 0px; margin-bottom: 0px;'>NOMBRE_REEMPLAZO</h5></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td  colspan='1'> "
                    //                    + " <code class='label-cyt-grp-niv'><span style='font-size: medium;' >CODE_REEMPLAZO</span></code> "
                    + " <code class='label-cyt-grp-niv'><span >CODE_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >GRUPO_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >NIVEL_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >SEDE_REEMPLAZO</span></code> "
                    + " <code class=\"label-cyt-grp-niv\"><span >DEPTO_CODIGO_REEMPLAZO</span></code> "
                                        + " <code class=\"label-cyt-grp-niv\"><span >ID_REEMPLAZO</span></code> "
                    + "    </td>\n"
                    //                    + "    <td style='text-align: right;'><i class='fa fa-info-circle fa-lg' style='opacity: 0.5; padding-right: 5px;'></i></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td style='text-align:center;' ></td>\n"
                    + "    <td>"
                    + " <code class=\"label-cyt-grp-niv\"><span >DEPTO_NAME_REEMPLAZO</span></code> "
                    + "    </td>\n"
                    + "    <td></td>\n"
                    + "  </tr>\n"
                    + "  <tr>\n"
                    + "    <td colspan='3' style='height:10px;'></td>\n"
                    + "  </tr>\n"
                    + "</table>";

            if (isSelected) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #628cd5;");
//                html = html.replace("STYLE_INDICADOR_REEMPLAZO", 
//                        "style = 'position: relative; float: right; top: 4px; width: 14px; height: 14px; border-top: 3px solid #628cd5;\n" +
//                                                                 "-moz-transform: rotate(45deg); -ms-transform: rotate(45deg); -webkit-transform: rotate(45deg);\n" +
//                                                                 "transform: rotate(45deg); overflow: hidden; right: 8px; border-right: 3px solid #628cd5;' ");
            } else if (value.isDirty() != null && value.isDirty()) {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: lightgray;");
            } else {
                html = html.replace("SELECTED_COLOR_REEMPLAZO", "background-color: #F8F8F8;");
//                html = html.replace("STYLE_INDICADOR_REEMPLAZO", "");
            }
            
            try {
                
                html = html.replace("CODE_REEMPLAZO", chkStrNull(value.getCode()));
                html = html.replace("URL_FOTO_REEMPLAZO", chkStrNull(value.getUrlPhoto()));

    //            html = html.replace("APELLIDOS_REEMPLAZO", ellipse(chkStrNull(value.getApellidoPaterno()), 18) 
    //                    + " " + ellipse(chkStrNull(value.getApellidoMaterno()), 18));
    //            html = html.replace("NOMBRE_REEMPLAZO", chkStrNull(value.getNombre()));
    //            html = html.replaceAll("RFC_REEMPLAZO", chkStrNull(value.getRfc()));
                html = html.replace("NOMBRE_REEMPLAZO", chkStrNull(value.getName()));

                html = html.replace("GRUPO_REEMPLAZO", chkStrNull(grupoStr));
                html = html.replace("NIVEL_REEMPLAZO", chkStrNull(nivelStr));
                html = html.replace("DEPTO_CODIGO_REEMPLAZO", chkStrNull(deptoCodeStr));
                html = html.replace("DEPTO_NAME_REEMPLAZO", ellipse(chkStrNull(deptoNameStr), 32));
                html = html.replace("SEDE_REEMPLAZO", chkStrNull(sedeStr));
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
