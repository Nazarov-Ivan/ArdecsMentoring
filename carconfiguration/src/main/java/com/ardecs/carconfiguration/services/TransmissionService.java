package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Transmission;
import com.ardecs.carconfiguration.repositories.TransmissionRepository;
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
public class TransmissionService {
    private final String message = "Transmission";
    private final TransmissionRepository transmissionRepository;

    public TransmissionService(TransmissionRepository transmissionRepository) {
        this.transmissionRepository = transmissionRepository;
    }

    public List<Transmission> readAllTransmissions() {
        return transmissionRepository.findAll();
    }

    public Transmission readOneTransmission(long id) {
        return transmissionRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Transmission transmission) {
        if (transmissionRepository.findByName(transmission.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else transmissionRepository.save(transmission);
    }

    @Transactional
    public void update(Transmission accessory, long id) {
        transmissionRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        accessory.setId(id);
        transmissionRepository.save(accessory);
    }

    @Transactional
    public void delete(long id) {
        transmissionRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        transmissionRepository.deleteById(id);
    }
}
