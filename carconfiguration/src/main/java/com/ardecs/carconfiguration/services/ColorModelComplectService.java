package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.DuplicateModelComplectException;
import com.ardecs.carconfiguration.exceptions.ResourceNotFoundIdException;
import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.repositories.ColorModelComplectRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;


/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
@RequiredArgsConstructor
public class ColorModelComplectService {
    private final String message = "ModelComplectationColor";
    private final ColorModelComplectRepository colorModelComplectRepository;
    private final ServicesHelper servicesHelper;

    @Transactional
    public void create(ColorModelComplect colorModelComplect, Long modelId, Long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        createInModelComplectation(colorModelComplect, modelComplectation);
    }

    @Transactional
    public void createInModelComplectation(ColorModelComplect colorModelComplect, ModelComplectation modelComplectation) {
        if (colorModelComplectRepository.findByColorIdAndModelComplectationColor(colorModelComplect.getColor().getId(),
                modelComplectation).isPresent()) {
            throw new DuplicateModelComplectException("Color in this Complectation");
        } else {
            colorModelComplect.setModelComplectationColor(modelComplectation);
            colorModelComplectRepository.save(colorModelComplect);
        }
    }

    @Transactional
    public void delete(long colorId, long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        if (colorModelComplectRepository.findAllByModelComplectationColor(modelComplectation)
                .orElseThrow(() -> new ResourceNotFoundIdException("ModelComplectation")).size() > 1) {
            colorModelComplectRepository.delete(colorModelComplectRepository
                    .findByColorIdAndModelComplectationColor(colorId, modelComplectation)
                    .orElseThrow(() -> new ResourceNotFoundIdException("Color")));
        } else throw new RuntimeException("The complectation must have at least one color");
    }

    @Transactional
    public void updatePrice(long colorId, long modelId, long compId, int price) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        ColorModelComplect colorModelComplect = findByColorIdAndModelComplect(colorId, modelComplectation);
        colorModelComplect.setPrice(price);
        colorModelComplectRepository.save(colorModelComplect);
    }

    public List<ColorModelComplect> findByModelComplectation(ModelComplectation modelComplectation) {
        return colorModelComplectRepository.findAllByModelComplectationColor(modelComplectation).orElseGet(ArrayList::new);
    }

    public ColorModelComplect findByColorIdAndModelComplect(Long colorId, ModelComplectation modelComplectation) {
        return colorModelComplectRepository.findByColorIdAndModelComplectationColor(colorId, modelComplectation)
                .orElseThrow(() -> new ResourceNotFoundIdException(message));
    }
}
