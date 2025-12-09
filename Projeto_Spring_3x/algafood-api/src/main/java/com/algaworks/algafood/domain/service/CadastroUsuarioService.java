package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.exception.UsuarioNotFoundException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroUsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final CadastroGrupoService cadastroGrupo;

    @Transactional
    public Usuario salvar(Usuario usuario) {
        boolean emailEmUso = usuarioRepository.existsByEmailAndIdNot(usuario.getEmail(), usuario.getId());

        if (emailEmUso) {
            throw new BusinessException(String.format(ErrorMessages.MSG_ERROR_EMAIL_JA_CADASTRADO, usuario.getEmail()));
        }

        return usuarioRepository.saveAndFlush(usuario);
    }

    public void alterarSenha(UUID usuarioId, String senhaAtual, String novaSenha) {
        Usuario usuario = buscarOuFalhar(usuarioId);

        if (!usuario.senhaCoincideCom(senhaAtual)) {
            throw new BusinessException(ErrorMessages.MSG_ERROR_SENHA_NAO_COINCIDE);
        }

        usuario.setSenha(novaSenha);
        usuarioRepository.saveAndFlush(usuario);
    }

    @Transactional
    public void associarGrupo(UUID usuarioId, UUID grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        usuario.adicionarGrupo(grupo);
    }

    @Transactional
    public void desassociarGrupo(UUID usuarioId, UUID grupoId) {
        Usuario usuario = buscarOuFalhar(usuarioId);
        Grupo grupo = cadastroGrupo.buscarOuFalhar(grupoId);

        usuario.removerGrupo(grupo);
    }

    public Usuario buscarOuFalhar(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new UsuarioNotFoundException(String.format(ErrorMessages.VALIDATION_ERROR_USUARIO_NAO_ENCONTRADO, id)));
    }

}
