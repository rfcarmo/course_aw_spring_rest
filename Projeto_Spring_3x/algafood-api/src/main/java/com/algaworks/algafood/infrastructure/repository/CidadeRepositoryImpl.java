package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class CidadeRepositoryImpl implements CidadeRepository {

    @Override
    public List<Cidade> listar() {
        return List.of();
    }

    @Override
    public Cidade buscar(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Cidade salvar(Cidade cidade) {
        return null;
    }

    @Override
    @Transactional
    public void remover(Cidade cidade) {

    }
}
