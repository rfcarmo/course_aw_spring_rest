package com.algaworks.algafood.api.assembler;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.dto.output.ProdutoModel;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class ProdutoModelAssembler {

    private final ModelMapper modelMapper;

    public ProdutoModel toModel(Produto produto) {
        return modelMapper.map(produto, ProdutoModel.class);
    }

    public List<ProdutoModel> toCollectionModel(List<Produto> produtos) {
        return produtos.stream()
                .map(this::toModel)
                .toList();
    }

}
