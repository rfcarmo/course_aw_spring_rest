package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroPermissaoService {

    private final PermissaoRepository permissaoRepository;

    public Permissao salvar(Permissao permissao) {
        return permissaoRepository.saveAndFlush(permissao);
    }

    public void excluir(UUID id) {
        try {
            permissaoRepository.deleteById(id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format("Permissão de código %s não pode ser removida, pois está em uso", id));
        }
    }

}
