package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.RestauranteNotFoundException;
import com.algaworks.algafood.domain.model.*;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CadastroRestauranteService {

    private final RestauranteRepository restauranteRepository;
    private final CadastroCozinhaService cadastroCozinhaService;
    private final CadastroCidadeService cadastroCidadeService;
    private final CadastroFormaPagamentoService cadastroFormaPagamentoService;
    private final CadastroUsuarioService cadastroUsuarioService;

    @Transactional
    public Restaurante salvar(Restaurante restaurante) {
        UUID cozinhaId = restaurante.getCozinha().getId();
        UUID cidadeId = restaurante.getEndereco().getCidade().getId();

        Cozinha cozinha = cadastroCozinhaService.buscarOuFalhar(cozinhaId);
        Cidade cidade = cadastroCidadeService.buscarOuFalhar(cidadeId);

        restaurante.setCozinha(cozinha);
        restaurante.getEndereco().setCidade(cidade);

        return restauranteRepository.saveAndFlush(restaurante);
    }

    @Transactional
    public void ativar(UUID id) {
        Restaurante restaurante = buscarOuFalhar(id);
        restaurante.ativar();
    }

    @Transactional
    public void ativar(List<UUID> ids) {
        ids.forEach(this::ativar);
    }

    @Transactional
    public void inativar(UUID id) {
        Restaurante restaurante = buscarOuFalhar(id);
        restaurante.inativar();
    }

    @Transactional
    public void inativar(List<UUID> ids) {
        ids.forEach(this::inativar);
    }

    @Transactional
    public void abrir(UUID id) {
        Restaurante restaurante = buscarOuFalhar(id);
        restaurante.abrir();
    }

    @Transactional
    public void fechar(UUID id) {
        Restaurante restaurante = buscarOuFalhar(id);
        restaurante.fechar();
    }

    @Transactional
    public void associarFormaPagamento(UUID restauranteId, UUID formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.adicionarFormaPagamento(formaPagamento);
    }

    @Transactional
    public void desassociarFormaPagamento(UUID restauranteId, UUID formaPagamentoId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        FormaPagamento formaPagamento = cadastroFormaPagamentoService.buscarOuFalhar(formaPagamentoId);

        restaurante.removerFormaPagamento(formaPagamento);
    }

    @Transactional
    public void associarUsuarioResp(UUID restauranteId, UUID usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        restaurante.adicionarResponsavel(usuario);
    }

    @Transactional
    public void desassociarUsuarioResp(UUID restauranteId, UUID usuarioId) {
        Restaurante restaurante = buscarOuFalhar(restauranteId);
        Usuario usuario = cadastroUsuarioService.buscarOuFalhar(usuarioId);

        restaurante.removerResponsavel(usuario);
    }

    public Restaurante buscarOuFalhar(UUID id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RestauranteNotFoundException(id));
    }

}
