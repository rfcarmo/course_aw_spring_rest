package com.algaworks.algafood.domain.model.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class FormaPagamentoModel {

    private UUID id;
    private String descricao;

}
