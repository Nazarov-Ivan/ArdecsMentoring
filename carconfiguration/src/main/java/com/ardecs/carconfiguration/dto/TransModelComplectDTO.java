package com.ardecs.carconfiguration.dto;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @author Nazarov Ivan
 * @date 10/26/2022
 */
@Getter
@Setter
public class TransModelComplectDTO extends AbstractDTO {
    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    @NotNull(message = "Transmission name should not be empty")
    private String transName;

    @NotNull(message = "Price should not be empty")
    private Integer price;

    @Null
    private ModelComplectation modelComplectation;
}
