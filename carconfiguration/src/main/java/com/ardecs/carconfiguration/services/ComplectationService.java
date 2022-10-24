package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Complectation;
import com.ardecs.carconfiguration.repositories.ComplectationRepository;
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
public class ComplectationService {
    private final String message = "Complectation";
    private final ComplectationRepository complectationRepository;

    public ComplectationService(ComplectationRepository complectationRepository) {
        this.complectationRepository = complectationRepository;
    }

    public List<Complectation> readAllComplectations() {
        return complectationRepository.findAll();
    }

    public Complectation readOneComplectation(long id) {
        return complectationRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Complectation complectation) {
        if (complectationRepository.findByName(complectation.getName()).isPresent()){
            throw new DuplicateNameException(message);
        } else complectationRepository.save(complectation);
    }

    @Transactional
    public void update(Complectation complectation, long id) {
        complectationRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        complectation.setId(id);
        complectationRepository.save(complectation);
    }

    @Transactional
    public void delete(long id) {
        complectationRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        complectationRepository.deleteById(id);
    }
}
