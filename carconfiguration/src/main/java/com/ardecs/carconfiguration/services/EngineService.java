package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Engine;
import com.ardecs.carconfiguration.repositories.EngineRepository;
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
        if (engineRepository.findByName(engine.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else engineRepository.save(engine);
    }

    @Transactional
    public void update(Engine engine, long id) {
        Engine oldEngine = engineRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        oldEngine.setName(engine.getName());
        oldEngine.setDescription(engine.getDescription());
        oldEngine.setPower(engine.getPower());
        engineRepository.save(oldEngine);
    }

    @Transactional
    public void delete(long id) {
        engineRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        engineRepository.deleteById(id);
    }

    public Engine findByName(String name) {
        return engineRepository.findByName(name).orElseThrow(resourceNotFoundNameException(message));
    }
}
