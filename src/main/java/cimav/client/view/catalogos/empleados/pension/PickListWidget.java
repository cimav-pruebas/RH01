/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados.pension;

import cimav.client.data.domain.Concepto;
import com.google.gwt.dom.client.Document;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.LIElement;
import com.google.gwt.dom.client.Node;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.ui.Widget;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author juan.calderon
 */
public class PickListWidget extends Widget {
    private List<Concepto> capitals;
    private final Element sourceList;
    private final Element targetList;

    public PickListWidget() {
        super();
        Element panel = DOM.createDiv();
        String uniqueId = Document.get().createUniqueId();
        panel.setId(uniqueId);
        setElement(panel);

        sourceList = DOM.createElement("ol");
        sourceList.setClassName("source");
        targetList = DOM.createElement("ol");
        targetList.setClassName("target");

        panel.appendChild(sourceList);
        panel.appendChild(targetList);
    }

    public void initConceptos(List<Concepto> capitals, List<Concepto> selectedConceptos) {
        this.capitals = capitals;
        clearChildren(sourceList);
        clearChildren(targetList);

        Document document = Document.get();
        for (Concepto capital : capitals) {
            LIElement li = document.createLIElement();
            li.setInnerText(capital.getName());
            // "data-key" is used by the jQuery plugin to uniquely identify the list elements
            li.setAttribute("data-key", capital.getName());
            // use JSNI to store the item object in the "data-object" attribute of the list element
            setConcepto(li, capital);
            if (selectedConceptos.contains(capital)) {
                targetList.appendChild(li);
            } else {
                sourceList.appendChild(li);
            }
        }
        // We don't initialize the jQuery plugin until the list elements are in place
        initPlugin();
    }

    public void updateSelectedConceptos(List<Concepto> selectedConceptos) {
        List<LIElement> liElements = new ArrayList<LIElement>();
        // retrieve the list elements from the targetList
        for (int i = 0; i < targetList.getChildCount(); i++ ) {
            Node node = targetList.getChild(i);
            if (node instanceof LIElement) {
                liElements.add((LIElement) node);
            }
        }
        // retrieve the list elements from the sourceList
        for (int i = 0; i < sourceList.getChildCount(); i++ ) {
            Node node = sourceList.getChild(i);
            if (node instanceof LIElement) {
                liElements.add((LIElement) node);
            }
        }
        clearChildren(sourceList);
        clearChildren(targetList);
        // put the selected list elements back in the targetList in the selected order
        for (Concepto capital : selectedConceptos) {
            Iterator<LIElement> iterator = liElements.iterator();
            while (iterator.hasNext()) {
                LIElement li = iterator.next();
                if (capital.equals(getConcepto(li))) {
                    targetList.appendChild(li);
                    iterator.remove();
                    break;
                }
            }
        }
        // put the non-selected list elements back in the sourceList in the original order
        for (Concepto capital : capitals) {
            if (! selectedConceptos.contains(capital)) {
                Iterator<LIElement> iterator = liElements.iterator();
                while (iterator.hasNext()) {
                    LIElement li = iterator.next();
                    if (capital.equals(getConcepto(li))) {
                        sourceList.appendChild(li);
                        iterator.remove();
                        break;
                    }
                }
            }
        }
    }

    public List<Concepto> getSelectedConceptos() {
        List<Concepto> selectedConceptos = new ArrayList<Concepto>();
        for (int i = 0; i < targetList.getChildCount(); i++ ) {
            Node node = targetList.getChild(i);
            if (node instanceof LIElement) {
                LIElement li = (LIElement) node;
                Concepto capital = getConcepto(li);
                if (capital != null) {
                    selectedConceptos.add(capital);
                }
            }
        }
        return selectedConceptos;
    }

    private Element clearChildren(Element element) {
        if (element.hasChildNodes()) {
            while ( element.hasChildNodes()) {
                element.removeChild(element.getLastChild());
            }
        }
        return element;
    }

    private void initPlugin() {
        String id = this.getElement().getId();
        initPickList(id);
    }

    private static native void initPickList(String id) /*-{
        $wnd.jQuery("#" + id).pickList();
    }-*/;

    private native void setConcepto(LIElement li, Concepto capital) /*-{
        $wnd.jQuery(li).data('object', capital);
    }-*/;

    private native Concepto getConcepto(LIElement li) /*-{
        return $wnd.jQuery(li).data('object');
    }-*/;


}