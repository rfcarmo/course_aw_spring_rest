package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.PermissaoRepository;
import com.algaworks.algafood.domain.service.CadastroPermissaoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/permissoes")
@RequiredArgsConstructor
public class PermissaoController {

    private final PermissaoRepository permissaoRepository;

    private final CadastroPermissaoService cadastroPermissaoService;

    @GetMapping
    public ResponseEntity<List<Permissao>> listar() {
        List<Permissao> permissoes = permissaoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(permissoes);
    }

    @GetMapping("/{permissaoId}")
    public ResponseEntity<Permissao> buscar(@PathVariable("permissaoId") UUID id) {
        Optional<Permissao> permissao = permissaoRepository.findById(id);

        if (permissao.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(permissao.get());
    }

    @PostMapping
    public ResponseEntity<Permissao> adicionar(@RequestBody Permissao permissao) {
        Permissao permissaoNova = cadastroPermissaoService.salvar(permissao);
        return ResponseEntity.status(HttpStatus.CREATED).body(permissaoNova);
    }

    @PutMapping("/{permissaoId}")
    public ResponseEntity<?> atualizar(@PathVariable("permissaoId") UUID id, @RequestBody Permissao permissao) {
        Optional<Permissao> permissaoAtual = permissaoRepository.findById(id);

        if (permissaoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(permissao, permissaoAtual.get(), "id");

        Permissao permissaoAtualizada = cadastroPermissaoService.salvar(permissaoAtual.get());

        return ResponseEntity.status(HttpStatus.OK).body(permissaoAtualizada);
    }

    @DeleteMapping("/{permissaoId}")
    public ResponseEntity<String> excluir(@PathVariable("permissaoId") UUID id) {
        try {
            Optional<Permissao> permissao = permissaoRepository.findById(id);

            if (permissao.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            cadastroPermissaoService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
