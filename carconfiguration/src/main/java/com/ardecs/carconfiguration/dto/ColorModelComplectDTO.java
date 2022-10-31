package com.ardecs.carconfiguration.dto;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Getter
@Setter
public class ColorModelComplectDTO extends AbstractDTO {
    @NotNull(message = "Color name should not be empty")
    @Size(min = 2, max = 35, message = "Name should be between "
            + "2 and 35 characters")
    private String colorName;

    @NotNull(message = "Price should not be empty")
    private int price;

    @Null
    private ModelComplectation modelComplectation;
}
