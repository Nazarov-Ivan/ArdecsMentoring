package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundModelComplectException;
import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import com.ardecs.carconfiguration.repositories.TransModelComplectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
@RequiredArgsConstructor
public class TransModelComplectService {
    private final String message = "Transmission";
    private final TransModelComplectRepository transModelComplectRepository;
    private final ServicesHelper servicesHelper;

    public TransModelComplect findByModelComplectation(ModelComplectation modelComplectation) {
        return transModelComplectRepository.findByModelComplectationTrans(modelComplectation).
                orElseThrow(() -> new ResourceNotFoundModelComplectException(message));
    }

    @Transactional
    public void create(TransModelComplect transModelComplect, ModelComplectation modelComplectation) {
        transModelComplect.setModelComplectationTrans(modelComplectation);
        transModelComplectRepository.save(transModelComplect);
    }

    @Transactional
    public void update(TransModelComplect transModelComplect, long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        TransModelComplect oldTransModelComplect = transModelComplectRepository.
                findByModelComplectationTrans(modelComplectation)
                .orElseThrow(() -> new ResourceNotFoundModelComplectException(message));
        oldTransModelComplect.setTrans(transModelComplect.getTrans());
        oldTransModelComplect.setPrice(transModelComplect.getPrice());
        transModelComplectRepository.save(oldTransModelComplect);
    }

    @Transactional
    public void updatePrice(long modelId, long compId, int price) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        TransModelComplect transModelComplect = findByModelComplectation(modelComplectation);
        transModelComplect.setPrice(price);
        transModelComplectRepository.save(transModelComplect);
    }
}
