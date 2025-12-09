package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class PermissaoNotFoundException extends EntityNotFoundException{

    public PermissaoNotFoundException(String message) {
        super(message);
    }

    public PermissaoNotFoundException(UUID permissaoId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_PERMISSAO_NAO_ENCONTRADA, permissaoId));
    }
}
