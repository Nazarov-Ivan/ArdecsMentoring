package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.exceptions.DuplicateModelComplectException;
import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.repositories.ColorModelComplectRepository;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

import static com.ardecs.carconfiguration.exceptions.ResourceNotFoundNameException.resourceNotFoundNameException;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
public class ColorModelComplectService {
    private final String message = "ModelComplectationColor";
    ColorModelComplectRepository colorModelComplectRepository;
    ServicesHelper servicesHelper;

    public ColorModelComplectService(ColorModelComplectRepository colorModelComplectRepository,  ServicesHelper servicesHelper) {
        this.colorModelComplectRepository = colorModelComplectRepository;
        this.servicesHelper = servicesHelper;
    }

    @Transactional
    public void create(Long colorId, Long modelId, Long compId, int price) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        createInModelComplectation(colorId, modelId, compId, price, modelComplectation);
    }

    @Transactional
    public void createInModelComplectation(Long colorId, Long modelId, Long compId, int price,
                                           ModelComplectation modelComplectation) {
        if (colorModelComplectRepository.findByColorIdAndModelComplectationColor(colorId, modelComplectation).isPresent()) {
            throw new DuplicateModelComplectException("Color in this Complectation");
        } else colorModelComplectRepository.addColor(colorId, modelId, compId, price);
    }

    @Transactional
    public void deleteAll(ModelComplectation modelComplectation) {
        if (colorModelComplectRepository.findAllByModelComplectationColor(modelComplectation).isPresent()) {
            colorModelComplectRepository.deleteAllByModelComplectationColor(modelComplectation);
        }
    }

    @Transactional
    public void delete(ColorModelComplect colorModelComplect, Long modelId, Long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        colorModelComplectRepository.findByColorIdAndModelComplectationColor(colorModelComplect.getColor().getId(),
                modelComplectation).orElseThrow(resourceNotFoundNameException(message));
        colorModelComplectRepository.delete(colorModelComplect.getColor().getId(), modelId, compId);
    }

    @Transactional
    public void updatePrice(ColorModelComplect colorModelComplect, Long modelId, Long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        ColorModelComplect oldColorModelComplect = colorModelComplectRepository.
                findByColorIdAndModelComplectationColor(colorModelComplect.getColor().getId(),
                        modelComplectation).orElseThrow(resourceNotFoundNameException(message));
        oldColorModelComplect.setPrice(colorModelComplect.getPrice());
        colorModelComplectRepository.save(oldColorModelComplect);
    }

    public List<ColorModelComplect> findByModelComplectation(ModelComplectation modelComplectation) {
        return colorModelComplectRepository.findAllByModelComplectationColor(modelComplectation).orElse(new ArrayList<>());
    }

    public ColorModelComplect findByIdAndModelComplect(Long colorId, ModelComplectation modelComplectation) {
        return colorModelComplectRepository.findByColorIdAndModelComplectationColor(colorId, modelComplectation)
                .orElseThrow(resourceNotFoundNameException(message));
    }
}
