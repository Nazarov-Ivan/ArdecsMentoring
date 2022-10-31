package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.BrandDTO;
import com.ardecs.carconfiguration.models.entities.Brand;
import org.springframework.stereotype.Component;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class BrandMapper extends AbstractMapper<Brand, BrandDTO> {
    public BrandMapper() {
        super(Brand.class, BrandDTO.class);
    }
}
