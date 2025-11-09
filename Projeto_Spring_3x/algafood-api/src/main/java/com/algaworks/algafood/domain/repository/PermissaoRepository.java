package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Permissao;

import java.util.List;
import java.util.UUID;

public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar(UUID id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);

}
