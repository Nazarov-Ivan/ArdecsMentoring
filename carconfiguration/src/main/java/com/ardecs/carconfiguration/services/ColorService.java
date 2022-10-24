package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.models.entities.Color;
import com.ardecs.carconfiguration.repositories.ColorRepository;
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
    public void create(Color color) {
        if (colorRepository.findByName(color.getName()).isPresent()){
            throw new DuplicateNameException(message);
        } else colorRepository.save(color);
    }

    @Transactional
    public void update(Color color, long id) {
        colorRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        color.setId(id);
        colorRepository.save(color);
    }

    @Transactional
    public void delete(long id) {
        colorRepository.findById(id).orElseThrow(resourceNotFoundIdException(message));
        colorRepository.deleteById(id);
    }
}
