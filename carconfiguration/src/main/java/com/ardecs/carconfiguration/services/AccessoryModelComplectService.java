package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.DuplicateModelComplectException;
import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.repositories.AccessoryModelComplectRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException.resourceNotFoundNameException;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
public class AccessoryModelComplectService {
    private final String message = "ModelComplectationAccessory";
    AccessoryModelComplectRepository accessoryModelComplectRepository;
    ServicesHelper servicesHelper;

    public AccessoryModelComplectService(AccessoryModelComplectRepository accessoryModelComplectRepository,
                                         ServicesHelper servicesHelper) {
        this.accessoryModelComplectRepository = accessoryModelComplectRepository;
        this.servicesHelper = servicesHelper;
    }

    public List<AccessoryModelComplect> findByModelComplectation(ModelComplectation modelComplectation) {
        return accessoryModelComplectRepository.findAllByModelComplectationAccessory(modelComplectation)
                .orElse(new ArrayList<>());
    }

    @Transactional
    public void create(Long accessId, Long modelId, Long compId, int price) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        createInModelComplectation(accessId, modelId, compId, price, modelComplectation);
    }

    @Transactional
    public void createInModelComplectation(Long accessId, Long modelId, Long compId, int price,
                                           ModelComplectation modelComplectation) {
        if (accessoryModelComplectRepository.findByAccessoryIdAndModelComplectationAccessory(accessId,
                modelComplectation).isPresent()) {
            throw new DuplicateModelComplectException("Accessory in this Complectation");
        } else accessoryModelComplectRepository.addAccessory(accessId, modelId, compId, price);
    }

    public AccessoryModelComplect findByIdAndModelComplect(Long accessId, ModelComplectation modelComplectation) {
        return accessoryModelComplectRepository.findByAccessoryIdAndModelComplectationAccessory(accessId, modelComplectation)
                .orElseThrow(resourceNotFoundNameException(message));
    }

    @Transactional
    public void deleteAll(ModelComplectation modelComplectation) {
        if (accessoryModelComplectRepository.findAllByModelComplectationAccessory(modelComplectation).isPresent()) {
            accessoryModelComplectRepository.deleteAllByModelComplectationAccessory(modelComplectation);
        }
    }

    @Transactional
    public void delete(AccessoryModelComplect accessoryModelComplect, Long modelId, Long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        findByIdAndModelComplect(accessoryModelComplect.getAccessory().getId(), modelComplectation);
        accessoryModelComplectRepository.delete(accessoryModelComplect.getAccessory().getId(), modelId, compId);
    }

    @Transactional
    public void updatePrice(AccessoryModelComplect accessoryModelComplect, Long modelId, Long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        AccessoryModelComplect oldAccessoryModelComplect = accessoryModelComplectRepository.
                findByAccessoryIdAndModelComplectationAccessory(accessoryModelComplect.getAccessory().getId(),
                modelComplectation).orElseThrow(resourceNotFoundNameException(message));
        oldAccessoryModelComplect.setPrice(accessoryModelComplect.getPrice());
        accessoryModelComplectRepository.save(oldAccessoryModelComplect);
    }
}
