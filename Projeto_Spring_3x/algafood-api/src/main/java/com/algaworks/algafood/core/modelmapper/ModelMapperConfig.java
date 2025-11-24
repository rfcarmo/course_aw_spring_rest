package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.dto.input.RestauranteInput;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        /*
        modelMapper.createTypeMap(Restaurante.class, RestauranteInput.class)
                .addMapping(Restaurante::getTaxaFrete, RestauranteInput::setPrecoFrete);
        */

        return modelMapper;
    }

}
