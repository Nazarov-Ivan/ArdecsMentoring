package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Engine;
import com.ardecs.carconfiguration.repositories.EngineRepository;
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
public class EngineService {
    private final String message = "Engine";
    private final EngineRepository engineRepository;

    public EngineService(EngineRepository accessoryRepository) {
        this.engineRepository = accessoryRepository;
    }

    public List<Engine> readAllEngines() {
        return engineRepository.findAll();
    }

    public Engine readOneEngine(long id) {
        return engineRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Engine engine) {
        engineRepository.save(engine);
    }

    @Transactional
    public void update(Engine engine, long id) {
        if (engineRepository.existsById(id)) {
            engine.setId(id);
            engineRepository.save(engine);
        } else throw new ResourceNotFoundIdException(message);
    }

    @Transactional
    public void delete(long id) {
        if (engineRepository.existsById(id)) {
            engineRepository.deleteById(id);
        } else throw new ResourceNotFoundIdException(message);
    }
}
