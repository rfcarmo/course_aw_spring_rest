package com.algaworks.algafood.core.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.ValidationException;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

public class ValorZeroIncluiDescValidator implements ConstraintValidator<ValorZeroIncluiDesc, Object> {

    private String valorField;
    private String descricaoField;
    private String descricaoObrigatoria;

    @Override
    public void initialize(ValorZeroIncluiDesc constraint) {
        this.valorField = constraint.valorField();
        this.descricaoField = constraint.descricaoField();
        this.descricaoObrigatoria = constraint.descricaoObrigatoria();
    }

    @Override
    public boolean isValid(Object objetoValidado, ConstraintValidatorContext context) {
        try {
            BigDecimal valor = (BigDecimal) BeanUtils.getPropertyDescriptor(objetoValidado.getClass(), valorField).getReadMethod().invoke(objetoValidado);
            String descricao = (String) BeanUtils.getPropertyDescriptor(objetoValidado.getClass(), descricaoField).getReadMethod().invoke(objetoValidado);

            if ((valor != null) && (BigDecimal.ZERO.compareTo(valor) == 0)) {
                if ((descricao != null) && descricao.toLowerCase().contains(descricaoObrigatoria.toLowerCase())) {
                    return true;
                }
            } else {
                return true;
            }
        } catch (Exception e) {
            throw new ValidationException(e);
        }

        return false;
    }
}
