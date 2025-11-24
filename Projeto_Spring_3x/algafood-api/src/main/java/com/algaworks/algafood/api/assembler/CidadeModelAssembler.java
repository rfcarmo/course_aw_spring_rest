package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.dto.output.CidadeModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class CidadeModelAssembler {

    private final ModelMapper modelMapper;

    public CidadeModel toModel(Cidade cidade) {
        return modelMapper.map(cidade, CidadeModel.class);
    }

    public List<CidadeModel> toCollectionModel(List<Cidade> cidades) {
        return cidades.stream()
                .map(cidade -> toModel(cidade))
                .toList();
    }

}
