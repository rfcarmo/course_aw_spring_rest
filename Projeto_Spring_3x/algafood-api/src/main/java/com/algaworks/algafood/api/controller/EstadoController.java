package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/estados")
@RequiredArgsConstructor
public class EstadoController {

    private final EstadoRepository estadoRepository;

    private final CadastroEstadoService cadastroEstadoService;

    @GetMapping
    public ResponseEntity<List<Estado>> listar() {
        List<Estado> estados = estadoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(estados);
    }

    @GetMapping("/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable("estadoId") UUID id) {
        Optional<Estado> estado = estadoRepository.findById(id);

        if (estado.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(estado.get());
    }

    @PostMapping
    public ResponseEntity<Estado> adicionar(@RequestBody Estado estado) {
        Estado estadoNovo = cadastroEstadoService.salvar(estado);

        return ResponseEntity.status(HttpStatus.CREATED).body(estadoNovo);
    }

    @PutMapping("/{estadoId}")
    public ResponseEntity<Estado> atualizar(@PathVariable("estadoId") UUID id, @RequestBody Estado estado) {
        Optional<Estado> estadoAtual = estadoRepository.findById(id);

        if (estadoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(estado, estadoAtual.get(), "id");

        Estado estadoAtualizado = cadastroEstadoService.salvar(estadoAtual.get());

        return ResponseEntity.status(HttpStatus.OK).body(estadoAtualizado);
    }

    @DeleteMapping("/{estadoId}")
    public ResponseEntity<String> remover(@PathVariable("estadoId") UUID id) {
        try {
            Optional<Estado> estadoAtual = estadoRepository.findById(id);

            if (estadoAtual.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            cadastroEstadoService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

}
