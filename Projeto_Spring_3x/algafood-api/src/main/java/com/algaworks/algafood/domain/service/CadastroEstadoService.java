package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.exception.EstadoNotFoundException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;

    @Transactional
    public Estado salvar(Estado estado) {
        return estadoRepository.saveAndFlush(estado);
    }

    @Transactional
    public void excluir(UUID id) {
        try {
            buscarOuFalhar(id);

            estadoRepository.deleteById(id);
            estadoRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(ErrorMessages.VALIDATION_ERROR_ESTADO_EM_USO, id));
        }
    }

    public Estado buscarOuFalhar(UUID id) {
        return estadoRepository.findById(id)
                .orElseThrow(() -> new EstadoNotFoundException(id));
    }

}
