package com.algaworks.algafood.domain.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class PedidoModel {

    private UUID id;
    private BigDecimal subtotal;
    private BigDecimal taxaFrete;
    private BigDecimal valorTotal;
    private String status;
    private RestauranteResumoModel restaurante;
    private FormaPagamentoModel formaPagamento;
    private UsuarioModel cliente;
    private EnderecoModel enderecoEntrega;
    private OffsetDateTime dataCriacao;
    private OffsetDateTime dataConfirmacao;
    private OffsetDateTime dataCancelamento;
    private OffsetDateTime dataEntrega;
    private List<ItemPedidoModel> itens;

}
