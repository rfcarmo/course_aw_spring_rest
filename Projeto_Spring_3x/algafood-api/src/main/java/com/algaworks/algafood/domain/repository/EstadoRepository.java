package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Estado;

import java.util.List;
import java.util.UUID;

public interface EstadoRepository {

    List<Estado> listar();
    Estado buscar(UUID id);
    Estado salvar(Estado estado);
    void remover(Estado estado);

}
