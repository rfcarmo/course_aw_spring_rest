package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class CozinhaNotFoundException extends EntityNotFoundException {
    public CozinhaNotFoundException(String message) {
        super(message);
    }

    public CozinhaNotFoundException(UUID cozinhaId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_COZINHA_NAO_ENCONTRADA, cozinhaId));
    }
}
