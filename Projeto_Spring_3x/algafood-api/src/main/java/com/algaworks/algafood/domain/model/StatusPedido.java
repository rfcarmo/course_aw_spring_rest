package com.algaworks.algafood.domain.model;

import lombok.Getter;

import java.util.List;

public enum StatusPedido {

    CRIADO("Criado"),
    CONFIRMADO("Confirmado", CRIADO),
    ENTREGUE("Entregue", CONFIRMADO),
    CANCELADO("Cancelado", CRIADO, CONFIRMADO);

    @Getter
    private final String descricao;

    private final List<StatusPedido> statusAnteriores;

    StatusPedido(String descricao, StatusPedido... statusAnteriores) {
        this.descricao = descricao;
        this.statusAnteriores = List.of(statusAnteriores);
    }

    public boolean naoPodeMudarPara(StatusPedido novoStatus) {
        StatusPedido statusAtual = this;

        return !novoStatus.statusAnteriores.contains(statusAtual);
    }
}
