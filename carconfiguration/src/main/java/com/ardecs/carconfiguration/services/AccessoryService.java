package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Accessory;
import com.ardecs.carconfiguration.repositories.AccessoryRepository;
import com.ardecs.carconfiguration.util.DuplicateNameException;
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
        if (accessoryRepository.findByName(accessory.getName()).isPresent()){
            throw new DuplicateNameException(message);
        } else accessoryRepository.save(accessory);
    }

    @Transactional
    public void update(Accessory accessory, long id) {
        accessoryRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        accessory.setId(id);
        accessoryRepository.save(accessory);
    }

    @Transactional
    public void delete(long id) {
        accessoryRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        accessoryRepository.deleteById(id);
    }
}
