package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.ComplectationDTO;
import com.ardecs.carconfiguration.models.entities.Complectation;
import org.springframework.stereotype.Component;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class ComplectationMapper extends AbstractMapper<Complectation, ComplectationDTO> {
    public ComplectationMapper() {
        super(Complectation.class, ComplectationDTO.class);
    }
}
