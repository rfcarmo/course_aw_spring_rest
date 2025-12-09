package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.ProdutoNotFoundException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroProdutoService {

    private final ProdutoRepository produtoRepository;

    public Produto buscarOuFalhar(UUID restauanteId, UUID produtoId) {
        return produtoRepository.findById(restauanteId, produtoId).orElseThrow(() ->
            new ProdutoNotFoundException(produtoId, restauanteId));
    }

    @Transactional
    public Produto salvar(Produto produto) {
        return produtoRepository.saveAndFlush(produto);
    }

}
