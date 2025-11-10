package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepository {

    @Override
    public List<Restaurante> listar() {
        return List.of();
    }

    @Override
    public Restaurante buscar(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        return null;
    }

    @Override
    @Transactional
    public void remover(UUID id) {

        Restaurante restaurante = buscar(id);

        if (restaurante == null) {
            throw new EmptyResultDataAccessException(1);
        }

    }
}
