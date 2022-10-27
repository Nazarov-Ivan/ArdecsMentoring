package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.ColorDTO;
import com.ardecs.carconfiguration.models.entities.Color;
import org.springframework.stereotype.Component;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class ColorMapper extends AbstractMapper<Color, ColorDTO> {
    public ColorMapper() {
        super(Color.class, ColorDTO.class);
    }
}
