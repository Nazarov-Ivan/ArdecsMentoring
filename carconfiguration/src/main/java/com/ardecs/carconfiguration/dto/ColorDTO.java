package com.ardecs.carconfiguration.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
@Getter
@Setter
public class ColorDTO {

    @Size(min = 2, max = 35, message = "Name should be between "
            + "2 and 35 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;
}
