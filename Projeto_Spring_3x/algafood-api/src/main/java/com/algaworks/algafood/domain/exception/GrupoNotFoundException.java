package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class GrupoNotFoundException extends EntityNotFoundException {

    public GrupoNotFoundException(String message) {
        super(message);
    }

    public GrupoNotFoundException(UUID id) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_GRUPO_NAO_ENCONTRADO, id));
    }
}
