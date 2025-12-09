package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class FormaPagamentoNotFoundException extends EntityNotFoundException{

    public FormaPagamentoNotFoundException(String message) {
        super(message);
    }

    public FormaPagamentoNotFoundException(UUID formaPagamentoId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_FORMA_PAGAMENTO_NAO_ENCONTRADA, formaPagamentoId));
    }
}
