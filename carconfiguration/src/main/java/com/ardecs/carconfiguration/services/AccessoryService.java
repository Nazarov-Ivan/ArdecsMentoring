package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Accessory;
import com.ardecs.carconfiguration.repositories.AccessoryRepository;
import com.ardecs.carconfiguration.util.ResourceNotFoundIdException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;
import static com.ardecs.carconfiguration.util.ResourceNotFoundIdException.resourceNotFoundIdException;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */

@Service
public class AccessoryService {
    private final String message = "Accessory";
    private final AccessoryRepository accessoryRepository;

    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public List<Accessory> readAllAccessories() {
        return accessoryRepository.findAll();
    }

    public Accessory readOneAccessory(long id) {
        return accessoryRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Accessory accessory) {
        accessoryRepository.save(accessory);
    }

    @Transactional
    public void update(Accessory accessory, long id) {
        if (accessoryRepository.existsById(id)) {
            accessory.setId(id);
            accessoryRepository.save(accessory);
        } else throw new ResourceNotFoundIdException(message);
    }

    @Transactional
    public void delete(long id) {
        if (accessoryRepository.existsById(id)) {
            accessoryRepository.deleteById(id);
        } else throw new ResourceNotFoundIdException(message);
    }
}
