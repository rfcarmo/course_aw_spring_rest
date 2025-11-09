package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CozinhaRepositoryImpl implements CozinhaRepository {

    @Override
    public List<Cozinha> listar() {
        return List.of();
    }

    @Override
    public Cozinha buscar(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return null;
    }

    @Override
    @Transactional
    public void remover(Cozinha cozinha) {

    }
}
