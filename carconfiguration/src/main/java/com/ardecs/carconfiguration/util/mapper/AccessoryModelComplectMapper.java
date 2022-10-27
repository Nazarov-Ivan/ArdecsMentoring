package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.AccessoryModelComplectDTO;
import com.ardecs.carconfiguration.models.entities.AccessoryModelComplect;
import com.ardecs.carconfiguration.services.AccessoryService;
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
public class AccessoryModelComplectMapper extends AbstractMapper<AccessoryModelComplect, AccessoryModelComplectDTO> {
    private final ModelMapper mapper;
    private final AccessoryService accessoryService;

    @Autowired
    AccessoryModelComplectMapper(ModelMapper mapper, AccessoryService accessoryService) {
        super(AccessoryModelComplect.class, AccessoryModelComplectDTO.class);
        this.mapper = mapper;
        this.accessoryService = accessoryService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(AccessoryModelComplect.class, AccessoryModelComplectDTO.class)
                .addMappings(m -> m.skip(AccessoryModelComplectDTO::setAccessoryName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(AccessoryModelComplectDTO.class, AccessoryModelComplect.class)
                .addMappings(m -> m.skip(AccessoryModelComplect::setAccessory)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(AccessoryModelComplect source, AccessoryModelComplectDTO destination) {
        destination.setAccessoryName(getAccessoryName(source));
    }

    private String getAccessoryName(AccessoryModelComplect source) {
        return Objects.isNull(source) || Objects.isNull(source.getAccessory().getName()) ? null : source.getAccessory().getName();
    }

    @Override
    @Transactional
    void mapSpecificFields(AccessoryModelComplectDTO source, AccessoryModelComplect destination) {
        destination.setAccessory(accessoryService.findByName(source.getAccessoryName()));
    }
}
