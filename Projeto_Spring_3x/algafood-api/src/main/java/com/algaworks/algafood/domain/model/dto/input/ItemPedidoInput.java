package com.algaworks.algafood.domain.model.dto.input;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class ItemPedidoInput {

    @NotNull
    private UUID produtoId;

    @NotNull
    @PositiveOrZero
    private Integer quantidade;

    private String observacao;

}
