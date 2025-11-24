package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroPermissaoService {

    private final PermissaoRepository permissaoRepository;

    @Transactional
    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.saveAndFlush(permissao);
    }

    @Transactional
    public void excluir(UUID id) {
        try {
            permissaoRepository.deleteById(id);
            permissaoRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Permissão de código %s não pode ser removida, pois está em uso", id));
        }
    }

}
