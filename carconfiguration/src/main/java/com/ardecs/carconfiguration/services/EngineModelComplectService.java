package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.repositories.EngineModelComplectRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundModelComplectException.resourceNotFoundModelComplectException;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
public class EngineModelComplectService {
    private final String message = "EngineModelComplect";
    private final EngineModelComplectRepository engineModelComplectRepository;
    ServicesHelper servicesHelper;

    public EngineModelComplectService(EngineModelComplectRepository engineModelComplectRepository,
                                      ServicesHelper servicesHelper) {
        this.engineModelComplectRepository = engineModelComplectRepository;
        this.servicesHelper = servicesHelper;
    }

    public EngineModelComplect findByModelComplectation(ModelComplectation modelComplectation) {
        return engineModelComplectRepository.findByModelComplectationEngine(modelComplectation).
                orElseThrow(resourceNotFoundModelComplectException("Engine"));
    }

    @Transactional
    public void create(Long engineId, Long modelId, Long compId, int price) {
        engineModelComplectRepository.addEngine(engineId, modelId, compId, price);
    }

    @Transactional
    public void update(EngineModelComplect engineModelComplect, long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        EngineModelComplect oldEngineModelComplect = engineModelComplectRepository.
                findByModelComplectationEngine(modelComplectation).orElseThrow(resourceNotFoundModelComplectException("Engine"));
        oldEngineModelComplect.setEngine(engineModelComplect.getEngine());
        oldEngineModelComplect.setPrice(engineModelComplect.getPrice());
        engineModelComplectRepository.save(oldEngineModelComplect);
    }

    @Transactional
    public void delete(ModelComplectation modelComplectation) {
        engineModelComplectRepository.findByModelComplectationEngine(modelComplectation).
                orElseThrow(resourceNotFoundModelComplectException(message));
        engineModelComplectRepository.deleteByModelComplectationEngine(modelComplectation);
    }
}
