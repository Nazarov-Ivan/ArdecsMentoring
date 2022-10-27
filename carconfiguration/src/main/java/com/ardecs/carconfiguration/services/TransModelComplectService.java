package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import com.ardecs.carconfiguration.repositories.TransModelComplectRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundModelComplectException.resourceNotFoundModelComplectException;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
public class TransModelComplectService {
    private final String message = "Transmission";
    private final TransModelComplectRepository transModelComplectRepository;
    ServicesHelper servicesHelper;

    public TransModelComplectService(TransModelComplectRepository transModelComplectRepository, ServicesHelper servicesHelper) {
        this.transModelComplectRepository = transModelComplectRepository;
        this.servicesHelper = servicesHelper;
    }

    public TransModelComplect findByModelComplectation(ModelComplectation modelComplectation) {
        return transModelComplectRepository.findByModelComplectationTrans(modelComplectation).
                orElseThrow(resourceNotFoundModelComplectException(message));
    }

    @Transactional
    public void create(Long transId, Long modelId, Long compId, int price) {
        transModelComplectRepository.addTransmission(transId, modelId, compId, price);
    }

    @Transactional
    public void delete(ModelComplectation modelComplectation) {
        transModelComplectRepository.findByModelComplectationTrans(modelComplectation)
                .orElseThrow(resourceNotFoundModelComplectException(message));
        transModelComplectRepository.deleteByModelComplectationTrans(modelComplectation);
    }

    @Transactional
    public void update(TransModelComplect transModelComplect, long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        TransModelComplect oldTransModelComplect = transModelComplectRepository.
                findByModelComplectationTrans(modelComplectation).orElseThrow(resourceNotFoundModelComplectException(message));
        oldTransModelComplect.setTrans(transModelComplect.getTrans());
        oldTransModelComplect.setPrice(transModelComplect.getPrice());
        transModelComplectRepository.save(oldTransModelComplect);
    }
}
