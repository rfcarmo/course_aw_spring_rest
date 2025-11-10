package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityInUseException;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/formas-pagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {

    private final FormaPagamentoRepository formaPagamentoRepository;

    private final CadastroFormaPagamentoService cadastroFormaPagamentoService;

    @GetMapping
    public ResponseEntity<List<FormaPagamento>> listar() {
        List<FormaPagamento> formaPagamentos = formaPagamentoRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamento> buscar(@PathVariable("formaPagamentoId")UUID id) {
        Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);

        if (formaPagamento.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamento.get());
    }

    @PostMapping
    public ResponseEntity<FormaPagamento> adicionar(@RequestBody FormaPagamento formaPagamento) {
        FormaPagamento formaPagamentoNova = cadastroFormaPagamentoService.salvar(formaPagamento);
        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoNova);
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<?> atualizar(@PathVariable("formaPagamentoId") UUID id, @RequestBody FormaPagamento formaPagamento) {
        Optional<FormaPagamento> formaPagamentoAtual = formaPagamentoRepository.findById(id);

        if (formaPagamentoAtual.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");

        FormaPagamento formaPagamentoAtualizada = cadastroFormaPagamentoService.salvar(formaPagamentoAtual.get());

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoAtualizada);
    }

    @DeleteMapping("/{formaPagamentoId}")
    public ResponseEntity<String> remover(@PathVariable("formaPagamentoId")UUID id) {
        try {
            Optional<FormaPagamento> formaPagamento = formaPagamentoRepository.findById(id);

            if (formaPagamento.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            cadastroFormaPagamentoService.excluir(id);

            return ResponseEntity.noContent().build();

        } catch (EntityInUseException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }

    }

}
