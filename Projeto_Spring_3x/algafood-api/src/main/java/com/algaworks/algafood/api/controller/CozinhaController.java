package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.CozinhaNotFoundException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/cozinhas")
@RequiredArgsConstructor
public class CozinhaController {

    private final CozinhaRepository cozinhaRepository;

    private final CadastroCozinhaService cadastroCozinhaService;

    @GetMapping
    public ResponseEntity<List<Cozinha>> listar() {
        List<Cozinha> cozinhas = cozinhaRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/{cozinhaId}")
    public Cozinha buscar(@PathVariable("cozinhaId") UUID id) {
        return cadastroCozinhaService.buscarOuFalhar(id);
    }

    @GetMapping("/por-nome")
    public ResponseEntity<List<Cozinha>> buscarPorNome(@RequestParam String nome) {
        List<Cozinha> cozinhas = cozinhaRepository.findAllByNome(nome)
                .orElseThrow(() -> new CozinhaNotFoundException(ErrorMessages.VALIDATION_ERROR_COZINHA_NAO_ENCONTRADA_POR_NOME));

        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/por-nome-like")
    public ResponseEntity<List<Cozinha>> buscarPorNomeLike(@RequestParam String nome) {
        List<Cozinha> cozinhas = cozinhaRepository.findAllByNomeContaining(nome)
                .orElseThrow(() -> new CozinhaNotFoundException(ErrorMessages.VALIDATION_ERROR_COZINHA_NAO_ENCONTRADA_POR_NOME));

        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/exists-nome")
    public ResponseEntity<?> buscarSeExistePorNome(@RequestParam String nome) {
        boolean existe = cozinhaRepository.existsByNome(nome);

        return ResponseEntity.status(HttpStatus.OK).body(existe);
    }

    @PostMapping
    public ResponseEntity<Cozinha> adicionar(@RequestBody Cozinha cozinha) {
        Cozinha cozinhaNova = cadastroCozinhaService.salvar(cozinha);

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaNova);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<Cozinha> atualizar(@PathVariable("cozinhaId") UUID id, @RequestBody Cozinha cozinha) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);

        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");

        Cozinha cozinhaAtualizada = cadastroCozinhaService.salvar(cozinhaAtual);

        return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtualizada);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("cozinhaId") UUID id) {
        cadastroCozinhaService.excluir(id);
    }

}
