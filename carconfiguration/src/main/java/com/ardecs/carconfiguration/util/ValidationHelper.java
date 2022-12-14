package com.ardecs.carconfiguration.util;

import com.ardecs.carconfiguration.exceptions.ResourceNotCreatedException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import java.util.List;

/**
 * @author Nazarov Ivan
 * @date 10/24/2022
 */
public class ValidationHelper {
    public static void checkingErrorsMethod(BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            StringBuilder errorMsg = new StringBuilder();
            List<FieldError> errors = bindingResult.getFieldErrors();
            for (FieldError error : errors) {
                errorMsg.append(error.getField())
                        .append(" - ").append(error.getDefaultMessage())
                        .append(";");
            }
            throw new ResourceNotCreatedException(errorMsg.toString());
        }
    }
}
