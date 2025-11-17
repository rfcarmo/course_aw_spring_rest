package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.math.BigDecimal;

public class MultiploValidator implements ConstraintValidator<Multiplo, Number> {

    private int numero;

    @Override
    public void initialize(Multiplo constraintAnnotation) {
        numero = constraintAnnotation.numero();
    }

    @Override
    public boolean isValid(Number value, ConstraintValidatorContext context) {
        if (value == null) {
            return false;
        }

        BigDecimal valorDecimal = BigDecimal.valueOf(value.doubleValue());
        BigDecimal numeroDecimal = BigDecimal.valueOf(numero);

        return BigDecimal.ZERO.compareTo(valorDecimal.remainder(numeroDecimal)) == 0;
    }
}
