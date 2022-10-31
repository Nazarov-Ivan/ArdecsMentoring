package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.TransModelComplectDTO;
import com.ardecs.carconfiguration.models.entities.TransModelComplect;
import com.ardecs.carconfiguration.services.TransmissionService;
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
public class TransModelComplectMapper extends AbstractMapper<TransModelComplect, TransModelComplectDTO> {
    private final ModelMapper mapper;
    private final TransmissionService transmissionService;

    @Autowired
    TransModelComplectMapper(ModelMapper mapper, TransmissionService transmissionService) {
        super(TransModelComplect.class, TransModelComplectDTO.class);
        this.mapper = mapper;
        this.transmissionService = transmissionService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(TransModelComplect.class, TransModelComplectDTO.class)
                .addMappings(m -> m.skip(TransModelComplectDTO::setTransName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(TransModelComplectDTO.class, TransModelComplect.class)
                .addMappings(m -> m.skip(TransModelComplect::setTrans)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(TransModelComplect source, TransModelComplectDTO destination) {
        destination.setTransName(getTransName(source));
    }

    private String getTransName(TransModelComplect source) {
        return Objects.isNull(source) || Objects.isNull(source.getTrans().getName()) ? null : source.getTrans().getName();
    }

    @Override
    @Transactional
    void mapSpecificFields(TransModelComplectDTO source, TransModelComplect destination) {
        destination.setTrans(transmissionService.findByName(source.getTransName()));
    }
}
