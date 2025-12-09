package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class UsuarioNotFoundException extends EntityNotFoundException {

    public UsuarioNotFoundException(String message) {
        super(message);
    }

    public UsuarioNotFoundException(UUID usuarioId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_USUARIO_NAO_ENCONTRADO, usuarioId));
    }
}
