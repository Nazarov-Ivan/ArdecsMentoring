package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.AccessoryDTO;
import com.ardecs.carconfiguration.models.entities.Accessory;
import org.springframework.stereotype.Component;

/**
 * @author Nazarov Ivan
 * @date 10/26/2022
 */
@Component
public class AccessoryMapper extends AbstractMapper<Accessory, AccessoryDTO> {
    public AccessoryMapper() {
        super(Accessory.class, AccessoryDTO.class);
    }
}
