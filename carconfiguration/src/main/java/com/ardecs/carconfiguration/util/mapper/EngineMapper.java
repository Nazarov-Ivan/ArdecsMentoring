package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.EngineDTO;
import com.ardecs.carconfiguration.models.entities.Engine;
import org.springframework.stereotype.Component;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class EngineMapper extends AbstractMapper<Engine, EngineDTO> {
    public EngineMapper() {
        super(Engine.class, EngineDTO.class);
    }
}
