package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class EstadoNotFoundException extends EntityNotFoundException {
    public EstadoNotFoundException(String message) {
        super(message);
    }

    public EstadoNotFoundException(UUID estadoId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_ESTADO_NAO_ENCONTRADO, estadoId));
    }
}
