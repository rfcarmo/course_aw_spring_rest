package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.domain.exception.CozinhaNotFoundException;
import com.algaworks.algafood.domain.exception.ErrorMessages;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.dto.input.CozinhaInput;
import com.algaworks.algafood.domain.model.dto.output.CozinhaModel;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;
import jakarta.validation.Valid;
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
    private final CozinhaModelAssembler cozinhaModelAssembler;
    private final CozinhaInputDisassembler cozinhaInputDisassembler;

    @GetMapping
    public ResponseEntity<List<CozinhaModel>> listar() {
        List<CozinhaModel> cozinhas = cozinhaModelAssembler.toCollectionModel(cozinhaRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(cozinhas);
    }

    @GetMapping("/{cozinhaId}")
    public CozinhaModel buscar(@PathVariable("cozinhaId") UUID id) {
        return cozinhaModelAssembler.toModel(cadastroCozinhaService.buscarOuFalhar(id));
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
    public ResponseEntity<CozinhaModel> adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinha = cozinhaInputDisassembler.toDomainObject(cozinhaInput);
        CozinhaModel cozinhaNova = cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinha));

        return ResponseEntity.status(HttpStatus.CREATED).body(cozinhaNova);
    }

    @PutMapping("/{cozinhaId}")
    public ResponseEntity<CozinhaModel> atualizar(@PathVariable("cozinhaId") UUID id, @RequestBody @Valid CozinhaInput cozinhaInput) {
        Cozinha cozinhaAtual = cadastroCozinhaService.buscarOuFalhar(id);

//        BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
        cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);

        CozinhaModel cozinhaAtualizada = cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinhaAtual));

        return ResponseEntity.status(HttpStatus.OK).body(cozinhaAtualizada);
    }

    @DeleteMapping("/{cozinhaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("cozinhaId") UUID id) {
        cadastroCozinhaService.excluir(id);
    }

}
