package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class PermissaoRepositoryImpl implements PermissaoRepository {

    @Override
    public List<Permissao> listar() {
        return List.of();
    }

    @Override
    public Permissao buscar(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public Permissao salvar(Permissao permissao) {
        return null;
    }

    @Override
    @Transactional
    public void remover(Permissao permissao) {

    }
}
