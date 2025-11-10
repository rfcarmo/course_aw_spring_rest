package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cidade;

import java.util.List;
import java.util.UUID;

public interface CidadeRepository {

    List<Cidade> listar();
    Cidade buscar(UUID id);
    Cidade salvar(Cidade cidade);
    void remover(UUID id);

}
