package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class CidadeNotFoundException extends EntityNotFoundException {
    public CidadeNotFoundException(String message) {
        super(message);
    }

    public CidadeNotFoundException(UUID cidadeId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_CIDADE_NAO_ENCONTRADA, cidadeId));
    }
}
