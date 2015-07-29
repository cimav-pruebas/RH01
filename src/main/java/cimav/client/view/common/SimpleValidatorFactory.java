/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cimav.client.view.common;

import cimav.client.data.domain.Departamento;
import cimav.client.data.domain.Empleado;
import com.google.gwt.core.client.GWT;
import com.google.gwt.validation.client.AbstractGwtValidatorFactory;
import com.google.gwt.validation.client.GwtValidation;
import com.google.gwt.validation.client.impl.AbstractGwtValidator;
import javax.validation.Validator;

/**
 *
 * @author juan.calderon
 */
public final class SimpleValidatorFactory extends AbstractGwtValidatorFactory {
    
    @GwtValidation({Empleado.class, Departamento.class})
    public interface GwtValidator extends Validator {
    }

    @Override
    public AbstractGwtValidator createValidator() {
        return GWT.create(GwtValidator.class);
    }
    
}
