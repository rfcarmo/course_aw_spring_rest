package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CozinhaNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroCozinhaService {

    private final CozinhaRepository cozinhaRepository;

    @Transactional
    public Cozinha salvar(Cozinha cozinha) {
        return cozinhaRepository.saveAndFlush(cozinha);
    }

    @Transactional
    public void excluir(UUID id) {
        try {
            buscarOuFalhar(id);

            cozinhaRepository.deleteById(id);
            cozinhaRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(ErrorMessages.VALIDATION_ERROR_COZINHA_EM_USO, id));
        }
    }

    public Cozinha buscarOuFalhar(UUID id) {
        return cozinhaRepository.findById(id)
                .orElseThrow(() -> new CozinhaNotFoundException(id));
    }

}
