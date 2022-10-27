package com.ardecs.carconfiguration.dto;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;
import javax.validation.constraints.Size;

/**
 * @author Nazarov Ivan
 * @date 10/25/2022
 */
@Getter
@Setter
public class EngineModelComplectDTO extends AbstractDTO {
    @NotEmpty(message = "Engine name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between "
            + "2 and 50 characters")
    private String engineName;

    @NotNull(message = "Price should not be empty")
    private int price;

    @Null
    private ModelComplectation modelComplectation;
}
