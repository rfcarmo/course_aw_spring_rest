package com.algaworks.algafood;

import com.algaworks.algafood.domain.exception.CozinhaNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.ConstraintViolationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

@SpringBootTest
class CadastroCozinhaIT {

    private CadastroCozinhaService cadastroCozinhaService;

    @Autowired
    public CadastroCozinhaIT(CadastroCozinhaService cadastroCozinhaService) {
        this.cadastroCozinhaService = cadastroCozinhaService;
    }

    @Test
    public void testarCadastroCozinhaComSucesso() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome("Chinesa");

        novaCozinha = cadastroCozinhaService.salvar(novaCozinha);

        Assertions.assertThat(novaCozinha).isNotNull();
        Assertions.assertThat(novaCozinha.getId()).isNotNull();
        Assertions.assertThat(novaCozinha.getNome()).isEqualTo(novaCozinha.getNome());
    }

    @Test
    public void deveFalhar_QuandoCadastrarCozinhaSemNome() {
        Cozinha novaCozinha = new Cozinha();
        novaCozinha.setNome(null);

        Assertions.assertThatExceptionOfType(ConstraintViolationException.class)
                        .isThrownBy(() -> cadastroCozinhaService.salvar(novaCozinha));
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaEmUso() {
        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(UUID.fromString("5776008f-ad3f-409b-8ce9-7b1c3e16defb"));

        Assertions.assertThatExceptionOfType(EntityInUseException.class)
                .isThrownBy(() -> cadastroCozinhaService.excluir(cozinha.getId()));
    }

    @Test
    public void deveFalhar_QuandoExcluirCozinhaInexistente() {
        Assertions.assertThatExceptionOfType(CozinhaNotFoundException.class)
                .isThrownBy(() -> cadastroCozinhaService.excluir(UUID.fromString("00000000-0000-0000-0000-000000000000")));
    }

}
