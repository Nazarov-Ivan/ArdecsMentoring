package com.ardecs.carconfiguration.util.mapper;

import com.ardecs.carconfiguration.dto.TransmissionDTO;
import com.ardecs.carconfiguration.models.entities.Transmission;
import org.springframework.stereotype.Component;

/**
 * @author Nazarov Ivan
 * @date 10/27/2022
 */
@Component
public class TransmissionMapper extends AbstractMapper<Transmission, TransmissionDTO> {
    public TransmissionMapper() {
        super(Transmission.class, TransmissionDTO.class);
    }
}
