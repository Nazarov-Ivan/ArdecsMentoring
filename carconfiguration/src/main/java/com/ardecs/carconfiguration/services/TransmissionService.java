package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import com.ardecs.carconfiguration.models.entities.Transmission;
import com.ardecs.carconfiguration.repositories.TransmissionRepository;
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
public class TransmissionService {
    private final String message = "Transmission";
    private final TransmissionRepository transmissionRepository;

    public List<Transmission> readAllTransmissions() {
        return transmissionRepository.findAll();
    }

    public Transmission readOneTransmission(long id) {
        return transmissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Transmission transmission) {
        if (transmissionRepository.findByName(transmission.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else transmissionRepository.save(transmission);
    }

    @Transactional
    public void update(Transmission transmission, long id) {
        Transmission oldTransmission = transmissionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundIdException(message));
        oldTransmission.setName(transmission.getName());
        oldTransmission.setDescription(transmission.getDescription());
        transmissionRepository.save(oldTransmission);
    }

    @Transactional
    public void delete(long id) {
        transmissionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        transmissionRepository.deleteById(id);
    }

    public Transmission findByName(String name) {
        return transmissionRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundNameException(message));
    }
}
