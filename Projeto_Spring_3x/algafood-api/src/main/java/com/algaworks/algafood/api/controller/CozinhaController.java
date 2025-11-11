package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
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
    public ResponseEntity<Cozinha> buscar(@PathVariable("cozinhaId") UUID id) {
        Optional<Cozinha> cozinha = cozinhaRepository.findById(id);

        if (cozinha.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cozinha.get());
    }

    @GetMapping("/por-nome")
    public ResponseEntity<List<Cozinha>> buscarPorNome(@RequestParam String nome) {
        List<Cozinha> cozinhas = cozinhaRepository.findAllByNome(nome);

        if (cozinhas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/por-nome-like")
    public ResponseEntity<List<Cozinha>> buscarPorNomeLike(@RequestParam String nome) {
        List<Cozinha> cozinhas = cozinhaRepository.findAllByNomeContaining(nome);

        if (cozinhas.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

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
        Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

        if (cozinhaAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(cozinha, cozinhaAtual.get(), "id");

        Cozinha cozinhaAtualizada = cadastroCozinhaService.salvar(cozinhaAtual.get());

        return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtualizada);
    }

    @DeleteMapping("/{cozinhaId}")
    public ResponseEntity<String> remover(@PathVariable("cozinhaId") UUID id) {
        try {
            Optional<Cozinha> cozinhaAtual = cozinhaRepository.findById(id);

            if (cozinhaAtual.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            cadastroCozinhaService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }
    }

}
