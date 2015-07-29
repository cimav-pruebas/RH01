/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

import cimav.client.data.domain.BaseDomain;
import cimav.client.view.common.MethodEvent;
import com.google.gwt.view.client.HasData;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;

/**
 *
 * @author juan.calderon
 */
public class BaseProvider<T> implements IFilter<T>{
 
    //private static BaseProvider instance;

    public final FilteredListDataProvider<T> dataProvider = new FilteredListDataProvider<>(this);    
    private HasData<T> display;

//    public static BaseProvider get() {
//        if (instance == null) {
//            instance = new BaseProvider();
//        }
//        return instance;
//    }

    @Override
    public boolean matchFilter(T value, String filter) {
        return true;
    }
    
    public void order(int orderBy) {
        Collections.sort(dataProvider.getList(), new Comparator<T>() {
            @Override
            public int compare(T o1, T o2) {
                BaseDomain obj1 = (BaseDomain) o1;
                BaseDomain obj2 = (BaseDomain) o2;
                return  obj1.getCode().compareTo(obj2.getCode());
            }
        });
    }
    
    public void addDataDisplay(HasData<T> display) {
        dataProvider.addDataDisplay(display);
        this.display = display;
    }

    public FilteredListDataProvider<T> getDataProvider() {
        return dataProvider;
    }

    public int getRowCount() {
        return this.display.getRowCount();
    }
    public int getTotalRowCount() {
        return dataProvider.getList().size();
    }
    public String getRowCountPropotional() {
        return "" + getRowCount() + "/" + getTotalRowCount();
    }

    /**
     * 
     * @param item
     * @return True if the VisibleItems has the given Item.
     */
    public boolean containsItem(T item) {
        if (this.display == null || item == null) {
            return false;
        }
        boolean result = false;
        for(T t : this.display.getVisibleItems()) {
            if (item.equals(t)) {
                result = true;
                break;
            }
        }
        return result;
    }
    
    // <editor-fold defaultstate="collapsed" desc="interface MethodExecutedListener"> 
    public interface MethodExecutedListener extends java.util.EventListener {
        void onMethodExecuted(MethodEvent methodEvent);
    }
    private final ArrayList listeners = new ArrayList();
    public void addMethodExecutedListener(MethodExecutedListener listener) {
        listeners.add(listener);
    }
    public void removeMethodExecutedListener(MethodExecutedListener listener) {
        listeners.remove(listener);
    }
    public void onMethodExecuted(MethodEvent methodEvent) {
        for(Iterator it = listeners.iterator(); it.hasNext();) {
            MethodExecutedListener listener = (MethodExecutedListener) it.next();
            listener.onMethodExecuted(methodEvent);
        }
    }
    // </editor-fold>
    
}
