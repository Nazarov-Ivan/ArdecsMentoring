package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import com.ardecs.carconfiguration.models.entities.Accessory;
import com.ardecs.carconfiguration.repositories.AccessoryRepository;
import com.ardecs.carconfiguration.exceptions.DuplicateNameException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */

@Service
@RequiredArgsConstructor
public class AccessoryService {
    private final String message = "Accessory";
    private final AccessoryRepository accessoryRepository;

    public List<Accessory> readAllAccessories() {
        return accessoryRepository.findAll();
    }

    public Accessory readOneAccessory(long id) {
        return accessoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Accessory accessory) {
        if (accessoryRepository.findByName(accessory.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else accessoryRepository.save(accessory);
    }

    @Transactional
    public void update(Accessory accessory, long id) {
        Accessory oldAccessory = accessoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        oldAccessory.setName(accessory.getName());
        accessoryRepository.save(oldAccessory);
    }

    @Transactional
    public void delete(long id) {
        accessoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        accessoryRepository.deleteById(id);
    }

    public Accessory findByName(String name) {
        return accessoryRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundNameException(message));
    }
}
