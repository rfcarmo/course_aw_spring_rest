package com.algaworks.algafood.domain.exception;

import java.util.UUID;

public class PedidoNotFoundException extends EntityNotFoundException {
    public PedidoNotFoundException(String message) {
        super(message);
    }

    public PedidoNotFoundException(UUID pedidoId) {
        this(String.format(ErrorMessages.VALIDATION_ERROR_PEDIDO_NAO_ENCONTRADO, pedidoId));
    }
}
