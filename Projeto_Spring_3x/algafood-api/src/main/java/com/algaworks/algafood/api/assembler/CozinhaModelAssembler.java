package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.dto.output.CozinhaModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CozinhaModelAssembler {

    private final ModelMapper modelMapper;

    public CozinhaModel toModel(Cozinha cozinha) {
        return modelMapper.map(cozinha, CozinhaModel.class);
    }

    public List<CozinhaModel> toCollectionModel(List<Cozinha> cozinhas) {
        return cozinhas.stream()
                .map(cozinha -> toModel(cozinha))
                .toList();
    }

}
