package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.model.dto.input.EstadoInput;
import com.algaworks.algafood.domain.model.dto.output.EstadoModel;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoRepository estadoRepository;
    private final CadastroEstadoService cadastroEstadoService;
    private final EstadoModelAssembler estadoModelAssembler;
    private final EstadoInputDisassembler estadoInputDisassembler;

    @GetMapping
    public ResponseEntity<List<EstadoModel>> listar() {
        List<EstadoModel> estados = estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(estados);
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<EstadoModel> buscar(@PathVariable("estadoId") UUID id) {
        EstadoModel estado = estadoModelAssembler.toModel(cadastroEstadoService.buscarOuFalhar(id));

        return ResponseEntity.status(HttpStatus.OK).body(estado);
    }

    @PostMapping
    public ResponseEntity<EstadoModel> adicionar(@RequestBody @Valid EstadoInput estadoInput) {
        Estado estado = estadoInputDisassembler.toDomainObject(estadoInput);
        EstadoModel estadoNovo = estadoModelAssembler.toModel(cadastroEstadoService.salvar(estado));

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoNovo);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<EstadoModel> atualizar(@PathVariable("estadoId") UUID id, @RequestBody @Valid EstadoInput estadoInput) {
        Estado estadoAtual = cadastroEstadoService.buscarOuFalhar(id);

//        BeanUtils.copyProperties(estado, estadoAtual, "id");
        estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);

        EstadoModel estadoAtualizado = estadoModelAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));

        return ResponseEntity.status(HttpStatus.OK).body(estadoAtualizado);
    }

    @DeleteMapping("/{estadoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("estadoId") UUID id) {
        cadastroEstadoService.excluir(id);
    }

}
