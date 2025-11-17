package com.algaworks.algafood.core.validation;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;

@RequiredArgsConstructor
@Getter
public class ValidacaoException extends RuntimeException{

    private final BindingResult bindingResult;

}
