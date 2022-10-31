package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.ModelDTO;
import com.ardecs.carconfiguration.models.entities.Model;
import com.ardecs.carconfiguration.services.BrandService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.Objects;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class MyModelMapper extends AbstractMapper<Model, ModelDTO> {
    private final ModelMapper mapper;
    private final BrandService brandService;

    public MyModelMapper(org.modelmapper.ModelMapper mapper, BrandService brandService) {
        super(Model.class, ModelDTO.class);
        this.mapper = mapper;
        this.brandService = brandService;
    }

    @PostConstruct
    public void setupMapper() {
        mapper.createTypeMap(Model.class, ModelDTO.class)
                .addMappings(m -> m.skip(ModelDTO::setBrandName)).setPostConverter(toDtoConverter());
        mapper.createTypeMap(ModelDTO.class, Model.class)
                .addMappings(m -> m.skip(Model::setBrand)).setPostConverter(toEntityConverter());
    }

    @Override
    public void mapSpecificFields(Model source, ModelDTO destination) {
        destination.setBrandName(getBrandName(source));
    }

    private String getBrandName(Model source) {
        return Objects.isNull(source) || Objects.isNull(source.getBrand().getName()) ? null : source.getBrand().getName();
    }

    @Override
    @Transactional
    void mapSpecificFields(ModelDTO source, Model destination) {
        destination.setBrand(brandService.findByName(source.getBrandName()));
    }
}
