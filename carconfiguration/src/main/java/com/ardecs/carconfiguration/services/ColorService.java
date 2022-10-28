package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException;
import com.ardecs.carconfiguration.models.entities.Color;
import com.ardecs.carconfiguration.repositories.ColorRepository;
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
public class ColorService {
    private final String message = "Color";

    private final ColorRepository colorRepository;

    public List<Color> readAllColors() {
        return colorRepository.findAll();
    }

    public Color readOneColor(long id) {
        return colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
    }

    @Transactional
    public void create(Color color) {
        if (colorRepository.findByName(color.getName()).isPresent()) {
            throw new DuplicateNameException(message);
        } else colorRepository.save(color);
    }

    @Transactional
    public void update(Color color, long id) {
        Color oldColor = colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        oldColor.setName(color.getName());
        colorRepository.save(oldColor);
    }

    @Transactional
    public void delete(long id) {
        colorRepository.findById(id).orElseThrow(() -> new ResourceNotFoundIdException(message));
        colorRepository.deleteById(id);
    }

    public Color findByName(String name) {
        return colorRepository.findByName(name).orElseThrow(() -> new ResourceNotFoundNameException(message));
    }
}
