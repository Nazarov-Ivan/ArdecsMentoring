package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.EngineModelComplectDTO;
import com.ardecs.carconfiguration.models.entities.EngineModelComplect;
import com.ardecs.carconfiguration.services.EngineService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Objects;

/**
 * @author Nazarov Ivan
 * @date 10/26/2022
 */
@Component
public class EngineModelComplectMapper extends AbstractMapper<EngineModelComplect, EngineModelComplectDTO> {
    private final ModelMapper mapper;
    private final EngineService engineService;

    @Autowired
    EngineModelComplectMapper(ModelMapper mapper, EngineService engineService) {
        super(EngineModelComplect.class, EngineModelComplectDTO.class);
        this.mapper = mapper;
        this.engineService = engineService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(EngineModelComplect.class, EngineModelComplectDTO.class)
                .addMappings(m -> m.skip(EngineModelComplectDTO::setEngineName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(EngineModelComplectDTO.class, EngineModelComplect.class)
                .addMappings(m -> m.skip(EngineModelComplect::setEngine)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(EngineModelComplect source, EngineModelComplectDTO destination) {
        destination.setEngineName(getEngineName(source));
    }

    private String getEngineName(EngineModelComplect source) {
        return Objects.isNull(source) || Objects.isNull(source.getEngine().getName()) ? null : source.getEngine().getName();
    }

    @Override
    @Transactional
    void mapSpecificFields(EngineModelComplectDTO source, EngineModelComplect destination) {
        destination.setEngine(engineService.findByName(source.getEngineName()));
    }
}
