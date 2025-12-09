package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.exception.FormaPagamentoNotFoundException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroFormaPagamentoService {

    private final FormaPagamentoRepository formaPagamentoRepository;

    @Transactional
    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.saveAndFlush(formaPagamento);
    }

    @Transactional
    public void excluir(UUID id) {
        try {
            buscarOuFalhar(id);

            formaPagamentoRepository.deleteById(id);
            formaPagamentoRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(ErrorMessages.VALIDATION_ERROR_FORMA_PAGAMENTO_EM_USO, id));
        }
    }

    public FormaPagamento buscarOuFalhar(UUID id) {
        return formaPagamentoRepository.findById(id)
                .orElseThrow(() -> new FormaPagamentoNotFoundException(id));
    }

}
