package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroFormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.saveAndFlush(formaPagamento);
    }

    public void excluir(UUID id) {
        try {
            formaPagamentoRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Forma de pagamento de código %s não pode ser removida, pois está em uso", id));
        }
    }

}
