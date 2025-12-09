package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.model.dto.output.PermissaoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PermissaoModelAssembler {

    private final ModelMapper modelMapper;

    public PermissaoModel toModel(Permissao permissao) {
        return modelMapper.map(permissao, PermissaoModel.class);
    }

    public List<PermissaoModel> toCollectionModel(List<Permissao> permissoes) {
        return permissoes.stream()
                .map(this::toModel)
                .toList();
    }

}
