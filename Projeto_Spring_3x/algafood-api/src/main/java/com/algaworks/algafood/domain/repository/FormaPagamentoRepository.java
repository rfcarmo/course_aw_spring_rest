package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.FormaPagamento;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, UUID> {
}
