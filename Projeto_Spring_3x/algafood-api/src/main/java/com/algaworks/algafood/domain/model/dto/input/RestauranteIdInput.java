package com.algaworks.algafood.domain.model.dto.input;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class RestauranteIdInput {

    @NotNull
    private UUID id;

}
