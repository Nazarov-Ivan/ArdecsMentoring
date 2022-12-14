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
public class TransmissionDTO extends AbstractDTO {
    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    @NotEmpty(message = "Name should not be empty")
    private String name;

    private String description;
}
