package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import com.ardecs.carconfiguration.models.entities.Complectation;
import com.ardecs.carconfiguration.repositories.ComplectationRepository;
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
public class ComplectationService {
    private final String message = "Complectation";
    private final ComplectationRepository complectationRepository;

    public List<Complectation> readAllComplectations() {
        return complectationRepository.findAll();
    }

    public Complectation readOneComplectation(long id) {
        return complectationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Complectation complectation) {
        if (complectationRepository.findByName(complectation.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else complectationRepository.save(complectation);
    }

    @Transactional
    public void update(Complectation complectation, long id) {
        Complectation oldComplectation = complectationRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundIdException(message));
        oldComplectation.setName(complectation.getName());
        complectationRepository.save(oldComplectation);
    }

    @Transactional
    public void delete(long id) {
        complectationRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        complectationRepository.deleteById(id);
    }

    public List<Complectation> getCompByModelId(long modelId) {
        List<Complectation> complectationList = complectationRepository.getCompByIdOfModel(modelId);
        if (complectationList.isEmpty()) {
            throw new ResourceNotFoundIdException("Complectations in model");
        } else return complectationList;
    }

    public Complectation findByName(String name) {
        return complectationRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundNameException(message));
    }
}
