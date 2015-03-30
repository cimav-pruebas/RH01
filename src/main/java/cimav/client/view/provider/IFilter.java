/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.provider;

/**
 *
 * @author juan.calderon
 * @param <T>
 */
public interface IFilter<T> {

    boolean matchFilter(T value, String filter);
}
