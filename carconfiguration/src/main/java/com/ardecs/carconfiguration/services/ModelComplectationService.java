package com.ardecs.carconfiguration.services;

import com.ardecs.carconfiguration.dto.AccessoriesAndColorDTO;
import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.models.entities.Complectation;
import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import com.ardecs.carconfiguration.repositories.ModelComplectationRepository;
import com.ardecs.carconfiguration.exceptions.DuplicateModelComplectException;
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
public class ModelComplectationService {

    private final ModelComplectationRepository modelComplectationRepository;
    private final ComplectationService complectationService;
    private final AccessoryModelComplectService accessoryModelComplectService;
    private final EngineModelComplectService engineModelComplectService;
    private final TransModelComplectService transModelComplectService;
    private final ColorModelComplectService colorModelComplectService;
    private final ServicesHelper servicesHelper;
    private final AccessoryService accessoryService;
    private final ColorService colorService;

    public List<Complectation> readAllModelComplectations(long modelId) {
        return  complectationService.getCompByModelId(modelId);
    }

    public ModelComplectation readOneModelComplectation(long modelId, long compId) {
        ModelComplectation modelComplectation = servicesHelper.findModelComplectById(modelId, compId);
        modelComplectation.setEngineModelComplect(engineModelComplectService.findByModelComplectation(modelComplectation));
        modelComplectation.setTransModelComplect(transModelComplectService.findByModelComplectation(modelComplectation));
        modelComplectation.setAccessoryModelComplects(accessoryModelComplectService.findByModelComplectation(modelComplectation));
        modelComplectation.setColorModelComplects(colorModelComplectService.findByModelComplectation(modelComplectation));
        return modelComplectation;
    }

    public int getPrice(long modelId, long compId, AccessoriesAndColorDTO accessoriesAndColorDTO) {
        ModelComplectation modelComplectation = readOneModelComplectation(modelId, compId);
        int priceAccessories = 0;
        for (String s : accessoriesAndColorDTO.getAccessories()) {
            priceAccessories += accessoryModelComplectService.
                    findByAccessIdAndModelComplect(accessoryService.findByName(s).getId(), modelComplectation).getPrice();
        }
        ColorModelComplect colorModelComplect =  colorModelComplectService
                .findByColorIdAndModelComplect(colorService
                        .findByName(accessoriesAndColorDTO.getColor()).getId(), modelComplectation);
        return modelComplectation.getModel().getPrice() + modelComplectation.getEngineModelComplect().getPrice()
                + modelComplectation.getTransModelComplect().getPrice() + priceAccessories + colorModelComplect.getPrice();
    }

    @Transactional
    public void create(ModelComplectation modelComplectation, EngineModelComplect engineModelComplect,
                       TransModelComplect transModelComplect, List<AccessoryModelComplect> accessoryModelComplect,
                       List<ColorModelComplect> colorModelComplect) {
        if (modelComplectationRepository.findById(modelComplectation.getId()).isPresent()) {
            throw new DuplicateModelComplectException("Complectation");
        } else {
            modelComplectationRepository.save(modelComplectation);
            engineModelComplectService.create(engineModelComplect, modelComplectation);
            transModelComplectService.create(transModelComplect, modelComplectation);
            for (AccessoryModelComplect modelComplect : accessoryModelComplect) {
                accessoryModelComplectService.createInModelComplectation(modelComplect, modelComplectation);
            }
            for (ColorModelComplect modelComplect : colorModelComplect) {
                colorModelComplectService.createInModelComplectation(modelComplect, modelComplectation);
            }
        }
    }

    @Transactional
    public void delete(long modelId, long compId) {
        ModelComplectation modelComplectation = readOneModelComplectation(modelId, compId);
        modelComplectationRepository.delete(modelComplectation);
    }
}
