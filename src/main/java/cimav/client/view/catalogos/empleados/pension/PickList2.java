/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.catalogos.empleados.pension;

import cimav.client.data.domain.Concepto;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.CellList;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;
import com.google.gwt.view.client.SelectionChangeEvent;
import static java.time.zone.ZoneRulesProvider.refresh;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import org.gwtbootstrap3.client.ui.Button;

/**
 *
 * @author juan.calderon
 */
public class PickList2 extends Composite {

    private static final Binder binder = GWT.create(Binder.class);
    @SuppressWarnings("rawtypes")
    interface Binder extends UiBinder<Widget, PickList2> {
    }

    @UiField Label                 sourceLabel;
    @UiField Label                 destLabel;
    
    @UiField(provided=true) CellList<Concepto> sourceList;
    @UiField(provided=true) CellList<Concepto> destList;

    @UiField Button                     addButton;
    @UiField Button                     addAllButton;
    @UiField Button                     removeButton;
    @UiField Button                     removeAllButton;

    private final SortedListModel<Concepto>    sourceListModel;
    private final MultiSelectionModel<Concepto> sourceSelModel;
    private final SortedListModel<Concepto>    destListModel;
    private final MultiSelectionModel<Concepto> destSelModel;

    private boolean                     addEnabled      = true;
    private boolean                     removeEnabled   = true;
    
    private Command                     changeCommand;
    
    
    public PickList2(Cell<Concepto> cellRenderer) {
        this(cellRenderer, null, null);
    }

    public PickList2(Cell<Concepto> cellRenderer, List<Concepto> srcList) {
        this(cellRenderer, srcList, null);
    }

    public PickList2(Cell<Concepto> cellRenderer, List<Concepto> srcList, List<Concepto> dstList) {
        if (cellRenderer == null) {
            throw new NullPointerException("cellRenderer");
        }
        
        sourceSelModel = new MultiSelectionModel<Concepto>();
        sourceSelModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                onSrcListSelectionChanged();
            }
        });
        sourceList = new CellList<Concepto>(cellRenderer);
        sourceList.setSelectionModel(sourceSelModel);
        sourceListModel = new SortedListModel<Concepto>();
        sourceListModel.addDataDisplay(sourceList);

        destSelModel = new MultiSelectionModel<Concepto>();
        destSelModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
            @Override
            public void onSelectionChange(SelectionChangeEvent event) {
                onDstListSelectionChanged();
            }
        });
        destList = new CellList<Concepto>(cellRenderer);
        destList.setSelectionModel(destSelModel);
        destListModel = new SortedListModel<Concepto>();
        destListModel.addDataDisplay(destList);
        
        initWidget(binder.createAndBindUi(this));

        if (dstList != null) {
            setDestinationList(dstList);
        }
        if (srcList != null) {
            setSourceList(srcList);
        }
    }

    @UiHandler("addButton")
    void onAddButtonClick(ClickEvent event) {
        fireDataChanged(sourceSelModel.getSelectedSet(), 
                sourceListModel, destListModel, true);
    }
    
    @UiHandler("addAllButton")
    void onAddAllButtonClick(ClickEvent event) {
        fireDataChanged(sourceListModel.getList(), 
                sourceListModel, destListModel, true);
    }
    
    @UiHandler("removeButton")
    void onRemoveButtonClick(ClickEvent event) {
        fireDataChanged(getDstListValues(false), 
                destListModel, sourceListModel, false);
    }
    
    @UiHandler("removeAllButton")
    void onRemoveAllButtonClick(ClickEvent event) {
        fireDataChanged(getDstListValues(true), 
                destListModel, sourceListModel, false);
    }

    public String getSourceTitle() {
        return sourceLabel.getText();
    }

    public void setSourceTitle(String newValue) {
        sourceLabel.setText(newValue);
    }

    public String getDestinationTitle() {
        return destLabel.getText();
    }

    public void setDestinationTitle(String newValue) {
        destLabel.setText(newValue);
    }

    public List<Concepto> getSourceList() {
        return new ArrayList<Concepto>(sourceListModel.getList());
    }

    public void setSourceList(List<Concepto> srcList) {
        sourceListModel.clear();
        sourceSelModel.clear();
        if (srcList != null) {
            sourceListModel.addAll(srcList);
        }
        
        mergeSrcList();
        onSourceListChanged();
    }

    public List<Concepto> getDestinationList() {
        return new ArrayList<Concepto>(destListModel.getList());
    }
    
    public void setDestinationList(List<Concepto> dstList) {
        destListModel.clear();
        destSelModel.clear();
        if (dstList != null) {
            destListModel.addAll(dstList);
        }
        
        if (mergeSrcList()) {
            sourceSelModel.clear();
            onSourceListChanged();
        }
        
        onDestinationListChanged();
    }

    public void setLists(List<Concepto> srcList, List<Concepto> dstList) {
        setDestinationList(dstList);
        setSourceList(srcList);
    }
    
    public void setChangeCommand(Command cmd) {
        changeCommand = cmd;
    }

    public void setAddEnabled(boolean enabled) {
        addEnabled = enabled;
        
        if (enabled) {
            onSourceListChanged();
            onSrcListSelectionChanged();
        } else {
            addButton.setEnabled(false);
            addAllButton.setEnabled(false);
        }
    }

    public boolean isAddEnabled() {
        return addEnabled;
    }
    
    public void setRemoveEnabled(boolean enabled) {
        removeEnabled = enabled;
        
        if (enabled) {
            onDestinationListChanged();
            onDstListSelectionChanged();
        } else {
            removeButton.setEnabled(false);
            removeAllButton.setEnabled(false);
        }
    }

    public boolean isRemoveEnabled() {
        return removeEnabled;
    }

    private boolean mergeSrcList() {
        return sourceListModel.removeAll(destListModel.getList());
    }
    
    private void onSrcListSelectionChanged() {
        if (!addEnabled) {
            return;
        }
        
        if (sourceSelModel.getSelectedSet().isEmpty()) {
            addButton.setEnabled(false);
        } else {
            addButton.setEnabled(true);
        }
    }

    private void onDstListSelectionChanged() {
        if (!removeEnabled) {
            return;
        }

        List<Concepto> data = getDstListValues(false);
        if (data.isEmpty()) {
            removeButton.setEnabled(false);
        } else {
            removeButton.setEnabled(true);
        }
    }

    protected void onChangeValues(Collection<Concepto> items, List<Concepto> srcList, 
            List<Concepto> dstList, boolean isAddOperation) {
        
        setLists(srcList, dstList);
    }

    private void notifyListeners() {
        if (changeCommand != null) {
            changeCommand.execute();
        }
    }
    
    private void onSourceListChanged() {
        if (!addEnabled) {
            return;
        }

        if (sourceListModel.getList().size() > 0) {
            addAllButton.setEnabled(true);
        } else {
            addButton.setEnabled(false);
            addAllButton.setEnabled(false);
        }
    }

    private void onDestinationListChanged() {
        if (!removeEnabled) {
            return;
        }

        List<Concepto> data = getDstListValues(true);
        if (!data.isEmpty()) {
            removeAllButton.setEnabled(true);
        } else {
            removeButton.setEnabled(false);
            removeAllButton.setEnabled(false);
        }
    }

    private List<Concepto> getDstListValues(boolean all) {
        List<Concepto> data = new ArrayList<Concepto>();
        if (all) {
            data.addAll(destListModel.getList());
        } else {
            data.addAll(destSelModel.getSelectedSet());
        }
        
        Iterator<Concepto> iter = data.iterator();
        while (iter.hasNext()) {
            Concepto o = iter.next();
            if (o instanceof Item) {
                if (!((Item)o).isMovable()) {
                    iter.remove();
                }
            }
        }
        
        return data;
    }
    
    private void fireDataChanged(Collection<Concepto> objects, 
            SortedListModel<Concepto> srcListModel, SortedListModel<Concepto> dstListModel, 
            boolean isAddOperation) {
        
        if (objects == null) {
            return;
        }
        
        ArrayList<Concepto> dstList = new ArrayList<Concepto>(objects);
        dstList.addAll(dstListModel.getList());
        
        ArrayList<Concepto> list = new ArrayList<Concepto>(srcListModel.getList());
        list.removeAll(objects);

        if (isAddOperation) {
            onChangeValues(objects, list, dstList, true);
        } else {
            onChangeValues(objects, dstList, list, false);
        }
        
        notifyListeners();
    }

    
    private static class SortedListModel<Concepto> extends ListDataProvider<Concepto> {

        public void addAll(List<Concepto> list) {
            if (list == null) {
                return;
            }
            
            List<Concepto> data = getList();
            data.addAll(list);
            
            Collections.sort(data, new Comparator<Concepto>() {
                @Override
                public int compare(Concepto o1, Concepto o2) {
                    if (o1 instanceof Item) {
                        Item i1 = (Item)o1;
                        Item i2 = (Item)o2;
                        if (i1.isMovable() != i2.isMovable()) {
                            return (i1.isMovable() ? 1 : -1);
                        }
                    }
                    
                    return o1.toString().compareTo(o2.toString());
                }
            });
            
            refresh();
        }

        public void clear() {
            setList(new ArrayList<Concepto>());
        }

        public boolean removeAll(List<Concepto> list) {
            List<Concepto> model = new ArrayList<Concepto>(getList());
            boolean res = model.removeAll(list);
            setList(model);
            return res;
        }
    }
    
    
    public static interface Item {
        
        public boolean isMovable();
    
    }
    
}