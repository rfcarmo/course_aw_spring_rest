package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroEstadoService {

    private final EstadoRepository estadoRepository;

    public Estado salvar(Estado estado) {
        return estadoRepository.salvar(estado);
    }

    public void excluir(UUID id) {
        try {
            estadoRepository.remover(id);

        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Estado de id %s não encontrado", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Estado de id %s não pode ser removido, pois está em uso", id));
        }
    }

}
