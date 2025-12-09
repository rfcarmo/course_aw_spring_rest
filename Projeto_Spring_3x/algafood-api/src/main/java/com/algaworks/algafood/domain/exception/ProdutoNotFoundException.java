package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class ProdutoNotFoundException extends EntityNotFoundException {

    public ProdutoNotFoundException(String message) {
        super(message);
    }

    public ProdutoNotFoundException(UUID produtoId, UUID restauranteId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_PRODUTO_NAO_ENCONTRADO, produtoId, restauranteId));
    }
}
