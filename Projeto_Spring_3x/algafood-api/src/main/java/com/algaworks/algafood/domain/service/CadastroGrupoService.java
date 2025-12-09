package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.exception.GrupoNotFoundException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroGrupoService {

    private final GrupoRepository grupoRepository;
    private final CadastroPermissaoService cadastroPermissaoService;

    @Transactional
    public Grupo salvar(Grupo grupo) {
        return grupoRepository.saveAndFlush(grupo);
    }

    @Transactional
    public void excluir(UUID id) {
        try {
            buscarOuFalhar(id);

            grupoRepository.deleteById(id);
            grupoRepository.flush();

        } catch (DataIntegrityViolationException e) {
            throw new EntityInUseException(String.format(ErrorMessages.VALIDATION_ERROR_GRUPO_EM_USO, id));
        }
    }

    @Transactional
    public void associarPermissao(UUID grupoId, UUID permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);

        grupo.adicionarPermissao(permissao);
    }

    @Transactional
    public void desassociarPermissao(UUID grupoId, UUID permissaoId) {
        Grupo grupo = buscarOuFalhar(grupoId);
        Permissao permissao = cadastroPermissaoService.buscarOuFalhar(permissaoId);

        grupo.removerPermissao(permissao);
    }

    public Grupo buscarOuFalhar(UUID id) {
        return grupoRepository.findById(id)
                .orElseThrow(() -> new GrupoNotFoundException(id));
    }

}
