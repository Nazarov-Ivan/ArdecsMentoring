package com.ardecs.carconfiguration.configurations;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Nazarov Ivan
 * @date 10/28/2022
 */
@Configuration
public class ModelMappingConfiguration {
    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }
}
