package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class EstadoRepositoryImpl implements EstadoRepository {

    @Override
    public List<Estado> listar() {
        return List.of();
    }

    @Override
    public Estado buscar(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Estado salvar(Estado estado) {
        return null;
    }

    @Override
    @Transactional
    public void remover(UUID id) {
        Estado estado = buscar(id);

        if (estado == null) {
            throw new EmptyResultDataAccessException(1);
        }


    }
}
