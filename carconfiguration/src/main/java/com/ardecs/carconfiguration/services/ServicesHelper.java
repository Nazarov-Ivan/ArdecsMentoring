package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.ModelComplectationId;
import com.ardecs.carconfiguration.repositories.ModelComplectationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException.resourceNotFoundIdException;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
@RequiredArgsConstructor
public class ServicesHelper {
    private final ModelComplectationRepository modelComplectationRepository;

    public ModelComplectation findModelComplectById(long modelId, long compId) {
        ModelComplectationId modelComplectationId = new ModelComplectationId();
        modelComplectationId.setModelId(modelId);
        modelComplectationId.setCompId(compId);
        return modelComplectationRepository.findById(modelComplectationId)
                .orElseThrow(resourceNotFoundIdException("For this model complectation"));
    }
}
