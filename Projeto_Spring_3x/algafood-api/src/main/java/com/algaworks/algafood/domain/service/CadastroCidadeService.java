package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;

    private final EstadoRepository estadoRepository;

    public Cidade salvar(Cidade cidade) {
        UUID estadoId = cidade.getEstado().getId();

        Estado estado = estadoRepository.buscar(estadoId);

        if (estado == null) {
            throw new EntityNotFoundException(String.format("Estado de id %s não encontrado", estadoId));
        }

        cidade.setEstado(estado);

        return cidadeRepository.salvar(cidade);
    }

    public void excluir(UUID id) {
        try {
            cidadeRepository.remover(id);
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(String.format("Cidade de id %s não encontrada", id));

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Cidade de id %s não pode ser removida, pois está em uso", id));
        }
    }

}
