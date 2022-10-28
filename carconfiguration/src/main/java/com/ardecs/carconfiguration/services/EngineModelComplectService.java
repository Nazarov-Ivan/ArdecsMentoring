package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundModelComplectException;
import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.repositories.EngineModelComplectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
@RequiredArgsConstructor
public class EngineModelComplectService {
    private final String message = "EngineModelComplect";
    private final EngineModelComplectRepository engineModelComplectRepository;
    private final ServicesHelper servicesHelper;

    public EngineModelComplect findByModelComplectation(ModelComplectation modelComplectation) {
        return engineModelComplectRepository.findByModelComplectationEngine(modelComplectation).
                orElseThrow(() -> new ResourceNotFoundModelComplectException("Engine"));
    }

    @Transactional
    public void create(EngineModelComplect engineModelComplect, ModelComplectation modelComplectation) {
        engineModelComplect.setModelComplectationEngine(modelComplectation);
        engineModelComplectRepository.save(engineModelComplect);
    }

    @Transactional
    public void update(EngineModelComplect engineModelComplect, long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        EngineModelComplect oldEngineModelComplect = engineModelComplectRepository.
                findByModelComplectationEngine(modelComplectation).orElseThrow(() -> new ResourceNotFoundModelComplectException("Engine"));
        oldEngineModelComplect.setEngine(engineModelComplect.getEngine());
        oldEngineModelComplect.setPrice(engineModelComplect.getPrice());
        engineModelComplectRepository.save(oldEngineModelComplect);
    }

    @Transactional
    public void updatePrice(long modelId, long compId, int price) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        EngineModelComplect engineModelComplect = findByModelComplectation(modelComplectation);
        engineModelComplect.setPrice(price);
        engineModelComplectRepository.save(engineModelComplect);
    }
}
