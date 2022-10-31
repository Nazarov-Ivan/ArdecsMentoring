package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.AccessoryModelComplectDTO;
import com.ardecs.carconfiguration.dto.ColorModelComplectDTO;
import com.ardecs.carconfiguration.dto.EngineModelComplectDTO;
import com.ardecs.carconfiguration.dto.ModelComplectationDTO;
import com.ardecs.carconfiguration.dto.TransModelComplectDTO;
import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.models.entities.ModelComplectation;
import com.ardecs.carconfiguration.models.entities.ModelComplectationId;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import com.ardecs.carconfiguration.services.ComplectationService;
import com.ardecs.carconfiguration.services.ModelService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author Nazarov Ivan
 * @date 10/26/2022
 */
@Component
public class ModelComplectationMapper extends AbstractMapper<ModelComplectation, ModelComplectationDTO> {
    private final ModelMapper mapper;
    private final ModelService modelService;
    private final ComplectationService complectationService;
    private final EngineModelComplectMapper engineModelComplectMapper;
    private final TransModelComplectMapper transModelComplectMapper;
    private final AccessoryModelComplectMapper accessoryModelComplectMapper;
    private final ColorModelComplectMapper colorModelComplectMapper;

    @Autowired
    public ModelComplectationMapper(ModelMapper mapper, ModelService modelService, ComplectationService complectationService,
                                    EngineModelComplectMapper engineModelComplectMapper,
                                    TransModelComplectMapper transModelComplectMapper,
                                    AccessoryModelComplectMapper accessoryModelComplectMapper,
                                    ColorModelComplectMapper colorModelComplectMapper) {
        super(ModelComplectation.class, ModelComplectationDTO.class);
        this.mapper = mapper;
        this.modelService = modelService;
        this.complectationService = complectationService;
        this.engineModelComplectMapper = engineModelComplectMapper;
        this.transModelComplectMapper = transModelComplectMapper;
        this.accessoryModelComplectMapper = accessoryModelComplectMapper;
        this.colorModelComplectMapper = colorModelComplectMapper;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ModelComplectation.class, ModelComplectationDTO.class)
                .addMappings(m -> m.skip(ModelComplectationDTO::setModelName)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setCompName)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setEngineName)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setEngineCost)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setTransName)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setTransCost)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setAccessories)).setPostConverter(toDtoConverter())
                .addMappings(m -> m.skip(ModelComplectationDTO::setColors)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ModelComplectationDTO.class, ModelComplectation.class)
                .addMappings(m -> m.skip(ModelComplectation::setModel)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ModelComplectation::setComp)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ModelComplectation::setId)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ModelComplectation::setEngineModelComplect)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ModelComplectation::setTransModelComplect)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ModelComplectation::setAccessoryModelComplects)).setPostConverter(toEntityConverter())
                .addMappings(m -> m.skip(ModelComplectation::setColorModelComplects)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ModelComplectation source, ModelComplectationDTO destination) {
        destination.setModelName(getModelName(source));
        destination.setCompName(getCompName(source));
        destination.setEngineCost(Objects.requireNonNull(getEngineModelComplect(source)).getPrice());
        destination.setEngineName(Objects.requireNonNull(getEngineModelComplect(source)).getEngine().getName());
        destination.setTransCost(Objects.requireNonNull(getTransModelComplect(source)).getPrice());
        destination.setTransName(Objects.requireNonNull(getTransModelComplect(source)).getTrans().getName());
        destination.setAccessories(getAccessoriesNameAndPrice(getAccessoryModelComplects(source)));
        destination.setColors(getColorsNameAndPrice(getColorModelComplects(source)));
    }

    private String getModelName(ModelComplectation source) {
        return Objects.isNull(source) || Objects.isNull(source.getModel().getName()) ? null : source.getModel().getName();
    }

    private String getCompName(ModelComplectation source) {
        return Objects.isNull(source) || Objects.isNull(source.getComp().getName()) ? null : source.getComp().getName();
    }

    private EngineModelComplect getEngineModelComplect(ModelComplectation source) {
        return Objects.isNull(source) || Objects.isNull(source.getEngineModelComplect()) ? null : source.getEngineModelComplect();
    }

    private TransModelComplect getTransModelComplect(ModelComplectation source) {
        return Objects.isNull(source) || Objects.isNull(source.getTransModelComplect()) ? null : source.getTransModelComplect();
    }

    private List<AccessoryModelComplect> getAccessoryModelComplects(ModelComplectation source) {
        return Objects.isNull(source) || Objects.isNull(source.getAccessoryModelComplects())
                ? null : source.getAccessoryModelComplects();
    }

    private List<Pair<String, Integer>> getAccessoriesNameAndPrice(List<AccessoryModelComplect> accessoryModelComplectList) {
        List<Pair<String, Integer>> accessories = new ArrayList<>();
        if (!Objects.requireNonNull(accessoryModelComplectList).isEmpty()) {
            for (AccessoryModelComplect accessoryModelComplect : accessoryModelComplectList) {
                Pair<String, Integer> accessoryPair = Pair.of(accessoryModelComplect.getAccessory().getName(),
                        accessoryModelComplect.getPrice());
                accessories.add(accessoryPair);
            }
        }
        return accessories;
    }

    private List<ColorModelComplect> getColorModelComplects(ModelComplectation source) {
        return Objects.isNull(source) || Objects.isNull(source.getColorModelComplects()) ? null : source.getColorModelComplects();
    }

    private List<Pair<String, Integer>> getColorsNameAndPrice(List<ColorModelComplect> colorModelComplects) {
        List<Pair<String, Integer>> colors = new ArrayList<>();
        if (!Objects.requireNonNull(colorModelComplects).isEmpty()) {
            for (ColorModelComplect colorModelComplect : colorModelComplects) {
                Pair<String, Integer> colorPair = Pair.of(colorModelComplect.getColor().getName(),
                        colorModelComplect.getPrice());
                colors.add(colorPair);
            }
        }
        return colors;
    }

    @Override
    @Transactional
    void mapSpecificFields(ModelComplectationDTO source, ModelComplectation destination) {
        destination.setModel(modelService.findByName(source.getModelName()));
        destination.setComp(complectationService.findByName(source.getCompName()));
        ModelComplectationId modelComplectationId = new ModelComplectationId();
        modelComplectationId.setModelId(destination.getModel().getId());
        modelComplectationId.setCompId(destination.getComp().getId());
        destination.setId(modelComplectationId);
        EngineModelComplectDTO engineModelComplectDTO = new EngineModelComplectDTO();
        engineModelComplectDTO.setModelComplectation(destination);
        engineModelComplectDTO.setEngineName(source.getEngineName());
        engineModelComplectDTO.setPrice(source.getEngineCost());
        destination.setEngineModelComplect(engineModelComplectMapper.toEntity(engineModelComplectDTO));
        TransModelComplectDTO transModelComplectDTO = new TransModelComplectDTO();
        transModelComplectDTO.setModelComplectation(destination);
        transModelComplectDTO.setTransName(source.getTransName());
        transModelComplectDTO.setPrice(source.getTransCost());
        destination.setTransModelComplect(transModelComplectMapper.toEntity(transModelComplectDTO));
        List<AccessoryModelComplect> accessoryModelComplectList = new ArrayList<>();
        if (source.getAccessories() != null) {
            for (int i = 0; i < source.getAccessories().size(); i++) {
                AccessoryModelComplectDTO accessoryModelComplectDTO = new AccessoryModelComplectDTO();
                accessoryModelComplectDTO.setModelComplectation(destination);
                accessoryModelComplectDTO.setAccessoryName(source.getAccessories().get(i).getFirst());
                accessoryModelComplectDTO.setPrice(source.getAccessories().get(i).getSecond());
                accessoryModelComplectList.add(accessoryModelComplectMapper.toEntity(accessoryModelComplectDTO));
            }
        }
        destination.setAccessoryModelComplects(accessoryModelComplectList);
        List<ColorModelComplect> colorModelComplectsList = new ArrayList<>();
        if (source.getColors() != null) {
            for (int i = 0; i < source.getColors().size(); i++) {
                ColorModelComplectDTO modelComplectDTO = new ColorModelComplectDTO();
                modelComplectDTO.setModelComplectation(destination);
                modelComplectDTO.setColorName(source.getColors().get(i).getFirst());
                modelComplectDTO.setPrice(source.getColors().get(i).getSecond());
                colorModelComplectsList.add(colorModelComplectMapper.toEntity(modelComplectDTO));
            }
        }
        destination.setColorModelComplects(colorModelComplectsList);
    }
}
