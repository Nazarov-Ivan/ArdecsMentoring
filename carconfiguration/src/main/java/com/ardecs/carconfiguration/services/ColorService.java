package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Color;
import com.ardecs.carconfiguration.repositories.ColorRepository;
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
public class ColorService {
    private final String message = "Color";

    private final ColorRepository colorRepository;

    public ColorService(ColorRepository colorRepositoryRepository) {
        this.colorRepository = colorRepositoryRepository;
    }

    public List<Color> readAllColors() {
        return colorRepository.findAll();
    }

    public Color readOneColor(long id) {
        return colorRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Color accessory) {
        colorRepository.save(accessory);
    }

    @Transactional
    public void update(Color color, long id) {
        if (colorRepository.existsById(id)) {
            color.setId(id);
            colorRepository.save(color);
        } else throw new ResourceNotFoundIdException(message);
    }

    @Transactional
    public void delete(long id) {
        if (colorRepository.existsById(id)) {
            colorRepository.deleteById(id);
        } else throw new ResourceNotFoundIdException(message);
    }
}
