package com.ardecs.carconfiguration.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.util.Pair;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;


/**
 * @author Nazarov Ivan
 * @date 10/22/2022
 */
@Getter
@Setter
public class ModelComplectationDTO extends AbstractDTO {

    @NotEmpty(message = "Model name should not be empty")
    private String modelName;
    @NotEmpty(message = "Complectation name should not be empty")
    private String compName;
    @NotEmpty(message = "Engine name should not be empty")
    private String engineName;
    @NotNull(message = "Engine price should not be empty")
    private int engineCost;
    @NotEmpty(message = "Transmission name should not be empty")
    private String transName;
    @NotNull(message = "Transmission price should not be empty")
    private int transCost;
    private List<Pair<String, Integer>> accessories;
    @NotNull(message = "At least one color must be specified")
    private List<Pair<String, Integer>> colors;

}
