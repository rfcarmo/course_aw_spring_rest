package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.salvar(cozinha);
    }

    public void excluir(UUID id) {
        try {
            cozinhaRepository.remover(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Cozinha de código %d não pode ser removida, pois está em uso.", id));

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Cozinha de código %d não encontrada.", id));
        }
    }

}
