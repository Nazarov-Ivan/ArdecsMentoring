package com.ardecs.carconfiguration.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nazarov Ivan
 * @date 10/23/2022
 */
@Getter
@Setter
public class ComplectationDTO extends AbstractDTO {
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    private String name;
}
