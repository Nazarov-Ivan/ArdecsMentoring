package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.DuplicateModelComplectException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.repositories.AccessoryModelComplectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
@RequiredArgsConstructor
public class AccessoryModelComplectService {
    private final String message = "ModelComplectationAccessory";
    private final AccessoryModelComplectRepository accessoryModelComplectRepository;
    private final ServicesHelper servicesHelper;

    public List<AccessoryModelComplect> findByModelComplectation(ModelComplectation modelComplectation) {
        return accessoryModelComplectRepository.findAllByModelComplectationAccessory(modelComplectation)
                .orElseGet(ArrayList::new);
    }

    @Transactional
    public void create(AccessoryModelComplect accessoryModelComplect, Long modelId, Long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        createInModelComplectation(accessoryModelComplect, modelComplectation);
    }

    @Transactional
    public void createInModelComplectation(AccessoryModelComplect accessoryModelComplect,
                                           ModelComplectation modelComplectation) {
        if (accessoryModelComplectRepository.findByAccessoryIdAndModelComplectationAccessory(accessoryModelComplect.
                getAccessory().getId(), modelComplectation).isPresent()) {
            throw new DuplicateModelComplectException("Accessory in this Complectation");
        } else {
            accessoryModelComplect.setModelComplectationAccessory(modelComplectation);
            accessoryModelComplectRepository.save(accessoryModelComplect);
        }

    }

    public AccessoryModelComplect findByAccessIdAndModelComplect(Long accessId, ModelComplectation modelComplectation) {
        return accessoryModelComplectRepository.findByAccessoryIdAndModelComplectationAccessory(accessId, modelComplectation)
                .orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void delete(long accessoryId, long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        accessoryModelComplectRepository.delete(accessoryModelComplectRepository
                .findByAccessoryIdAndModelComplectationAccessory(accessoryId, modelComplectation)
                .orElseThrow(() -> new ResourceNotFoundIdException("Accessory")));
    }

    @Transactional
    public void updatePrice(long accessoryId, long modelId, long compId, int price) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        AccessoryModelComplect accessoryModelComplect = findByAccessIdAndModelComplect(accessoryId, modelComplectation);
        accessoryModelComplect.setPrice(price);
        accessoryModelComplectRepository.save(accessoryModelComplect);
    }
}
