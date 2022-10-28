package com.ardecs.carconfiguration.dto;

import lombok.Getter;
import lombok.Setter;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Getter
@Setter
public class AccessoriesAndColorDTO extends AbstractDTO {

    private List<String> accessories;
    @NotNull(message = "Color name should not be empty")
    private String color;
}
