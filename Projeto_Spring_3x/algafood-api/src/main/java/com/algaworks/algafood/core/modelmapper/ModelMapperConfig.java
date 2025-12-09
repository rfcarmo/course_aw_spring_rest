package com.algaworks.algafood.core.modelmapper;

import com.algaworks.algafood.domain.model.Endereco;
import com.algaworks.algafood.domain.model.ItemPedido;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.dto.input.ItemPedidoInput;
import com.algaworks.algafood.domain.model.dto.input.RestauranteInput;
import com.algaworks.algafood.domain.model.dto.output.EnderecoModel;
import com.algaworks.algafood.domain.model.dto.output.ItemPedidoModel;
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

        modelMapper.createTypeMap(Endereco.class, EnderecoModel.class)
                .addMapping(src -> src.getCidade().getEstado().getNome(),
                        (dest, v) -> dest.getCidade().setEstado((String) v));

        modelMapper.createTypeMap(ItemPedidoInput.class, ItemPedido.class)
                .addMappings(mapper -> mapper.skip(ItemPedido::setId));

        return modelMapper;
    }

}
