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
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.util.List;

/**
 * @author Nazarov Ivan
 * @date 10/14/2022
 */
@Service
public class ModelComplectationService {
    ModelComplectationRepository modelComplectationRepository;
    ComplectationService complectationService;
    AccessoryModelComplectService accessoryModelComplectService;
    EngineModelComplectService engineModelComplectService;
    TransModelComplectService transModelComplectService;
    ColorModelComplectService colorModelComplectService;
    ServicesHelper servicesHelper;
    AccessoryService accessoryService;
    ColorService colorService;

    public ModelComplectationService(ModelComplectationRepository modelComplectationRepository,
                                     ComplectationService complectationService,
                                     AccessoryModelComplectService accessoryModelComplectService,
                                     EngineModelComplectService engineModelComplectService,
                                     TransModelComplectService transModelComplectService,
                                     ColorModelComplectService colorModelComplectService,
                                     AccessoryService accessoryService, ColorService colorService,
                                     ServicesHelper servicesHelper) {
        this.modelComplectationRepository = modelComplectationRepository;
        this.complectationService =  complectationService;
        this.accessoryModelComplectService = accessoryModelComplectService;
        this.engineModelComplectService = engineModelComplectService;
        this.transModelComplectService = transModelComplectService;
        this.colorModelComplectService = colorModelComplectService;
        this.accessoryService = accessoryService;
        this.colorService = colorService;
        this.servicesHelper = servicesHelper;
    }

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
                    findByIdAndModelComplect(accessoryService.findByName(s).getId(), modelComplectation).getPrice();
        }
        ColorModelComplect colorModelComplect =  colorModelComplectService
                .findByIdAndModelComplect(colorService.findByName(accessoriesAndColorDTO.getColor()).getId(), modelComplectation);
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
            long modelId = modelComplectation.getModel().getId();
            long compId = modelComplectation.getComp().getId();
            engineModelComplectService.create(engineModelComplect.getEngine().getId(),
                    modelId, compId,
                    engineModelComplect.getPrice());
            transModelComplectService.create(transModelComplect.getTrans().getId(),
                    modelId, compId,
                    transModelComplect.getPrice());
            for (AccessoryModelComplect modelComplect : accessoryModelComplect) {
                accessoryModelComplectService.createInModelComplectation(modelComplect.getAccessory().getId(),
                        modelId, compId, modelComplect.getPrice(), modelComplectation);
            }
            for (ColorModelComplect modelComplect : colorModelComplect) {
                colorModelComplectService.createInModelComplectation(modelComplect.getColor().getId(),
                        modelId, compId, modelComplect.getPrice(), modelComplectation);
            }
        }
    }

    @Transactional
    public void delete(long modelId, long compId) {
        ModelComplectation modelComplectation = readOneModelComplectation(modelId, compId);
        engineModelComplectService.delete(modelComplectation);
        transModelComplectService.delete(modelComplectation);
        accessoryModelComplectService.deleteAll(modelComplectation);
        colorModelComplectService.deleteAll(modelComplectation);
        modelComplectationRepository.delete(modelComplectation);
    }
}
