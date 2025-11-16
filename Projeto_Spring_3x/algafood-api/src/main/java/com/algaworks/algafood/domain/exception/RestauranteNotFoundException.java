package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class RestauranteNotFoundException extends EntityNotFoundException {
    public RestauranteNotFoundException(String message) {
        super(message);
    }

    public RestauranteNotFoundException(UUID restauranteId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_RESTAURANTE_NAO_ENCONTRADO, restauranteId));
    }
}
