package com.algaworks.algafood.infrastructure.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Repository
public class FormaPagamentoRepositoryImpl implements FormaPagamentoRepository {

    @Override
    public List<FormaPagamento> listar() {
        return List.of();
    }

    @Override
    public FormaPagamento buscar(UUID id) {
        return null;
    }

    @Override
    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return null;
    }

    @Override
    @Transactional
    public void remover(FormaPagamento formaPagamento) {

    }
}
