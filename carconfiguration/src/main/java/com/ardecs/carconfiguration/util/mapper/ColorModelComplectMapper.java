package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.ColorModelComplectDTO;
import com.ardecs.carconfiguration.models.entities.ColorModelComplect;
import com.ardecs.carconfiguration.services.ColorService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Objects;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class ColorModelComplectMapper extends AbstractMapper<ColorModelComplect, ColorModelComplectDTO> {
    private final ModelMapper mapper;
    private final ColorService colorService;

    @Autowired
    ColorModelComplectMapper(ModelMapper mapper, ColorService colorService) {
        super(ColorModelComplect.class, ColorModelComplectDTO.class);
        this.mapper = mapper;
        this.colorService = colorService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(ColorModelComplect.class, ColorModelComplectDTO.class)
                .addMappings(m -> m.skip(ColorModelComplectDTO::setColorName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ColorModelComplectDTO.class, ColorModelComplect.class)
                .addMappings(m -> m.skip(ColorModelComplect::setColor)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(ColorModelComplect source, ColorModelComplectDTO destination) {
        destination.setColorName(getColorName(source));
    }

    private String getColorName(ColorModelComplect source) {
        return Objects.isNull(source) || Objects.isNull(source.getColor().getName()) ? null : source.getColor().getName();
    }

    @Override
    @Transactional
    void mapSpecificFields(ColorModelComplectDTO source, ColorModelComplect destination) {
        destination.setColor(colorService.findByName(source.getColorName()));
    }
}
