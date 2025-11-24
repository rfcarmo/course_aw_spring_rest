package com.algaworks.algafood.domain.model.dto.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EstadoInput {

    @NotBlank
    private String nome;

}
