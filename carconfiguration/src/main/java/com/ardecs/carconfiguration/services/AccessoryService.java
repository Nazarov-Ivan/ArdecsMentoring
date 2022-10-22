package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Accessory;
import com.ardecs.carconfiguration.repositories.AccessoryRepository;
import com.ardecs.carconfiguration.util.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;


/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */

@Service
public class AccessoryService {

    private final AccessoryRepository accessoryRepository;

    public AccessoryService(AccessoryRepository accessoryRepository) {
        this.accessoryRepository = accessoryRepository;
    }

    public List<Accessory> readAllAccessories() {
        return accessoryRepository.findAll();
    }

    public Accessory readOneAccessory(long id) {
        return accessoryRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
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
        } else throw new ResourceNotFoundException();
    }

    @Transactional
    public void delete(long id) {
        if (accessoryRepository.existsById(id)) {
            accessoryRepository.deleteById(id);
        } else throw new ResourceNotFoundException();
    }
}
