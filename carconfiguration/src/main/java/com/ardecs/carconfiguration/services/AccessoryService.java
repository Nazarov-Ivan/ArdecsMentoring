package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Accessory;
import com.ardecs.carconfiguration.repositories.AccessoryRepository;
import com.ardecs.carconfiguration.exceptions.DuplicateNameException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException.resourceNotFoundIdException;
import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException.resourceNotFoundNameException;

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
        if (accessoryRepository.findByName(accessory.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else accessoryRepository.save(accessory);
    }

    @Transactional
    public void update(Accessory accessory, long id) {
        Accessory oldAccessory = accessoryRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        oldAccessory.setName(accessory.getName());
        accessoryRepository.save(oldAccessory);
    }

    @Transactional
    public void delete(long id) {
        accessoryRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        accessoryRepository.deleteById(id);
    }

    public Accessory findByName(String name) {
        return accessoryRepository.findByName(name).orElseThrow(resourceNotFoundNameException(message));
    }
}
