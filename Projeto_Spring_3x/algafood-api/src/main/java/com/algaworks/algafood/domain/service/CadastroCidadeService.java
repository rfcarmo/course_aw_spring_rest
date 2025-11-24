package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.CidadeNotFoundException;
import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroCidadeService {

    private final CidadeRepository cidadeRepository;
    private final CadastroEstadoService cadastroEstadoService;

    @Transactional
    public Cidade salvar(Cidade cidade) {
        UUID estadoId = cidade.getEstado().getId();

        Estado estado = cadastroEstadoService.buscarOuFalhar(estadoId);

        cidade.setEstado(estado);

        return cidadeRepository.saveAndFlush(cidade);
    }

    @Transactional
    public void excluir(UUID id) {
        try {
            buscarOuFalhar(id);

            cidadeRepository.deleteById(id);
            cidadeRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(ErrorMessages.VALIDATION_ERROR_CIDADE_EM_USO, id));
        }
    }

    public Cidade buscarOuFalhar(UUID id) {
        return cidadeRepository.findById(id)
                .orElseThrow(() -> new CidadeNotFoundException(id));
    }

}
