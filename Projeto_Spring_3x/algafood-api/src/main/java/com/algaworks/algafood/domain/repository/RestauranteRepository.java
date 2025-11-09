package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;

import java.util.List;
import java.util.UUID;

public interface RestauranteRepository {

    List<Restaurante> listar();
    Restaurante buscar(UUID id);
    Restaurante salvar(Restaurante restaurante);
    void remover(Restaurante restaurante);

}
