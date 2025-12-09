package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.dto.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.dto.output.FormaPagamentoModel;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/formas-pagamento")
@RequiredArgsConstructor
public class FormaPagamentoController {

    private final FormaPagamentoRepository formaPagamentoRepository;
    private final CadastroFormaPagamentoService cadastroFormaPagamentoService;
    private final FormaPagamentoModelAssembler formaPagamentoModelAssembler;
    private final FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;

    @GetMapping
    public ResponseEntity<List<FormaPagamentoModel>> listar() {
        List<FormaPagamentoModel> formaPagamentos = formaPagamentoModelAssembler.toCollectionModel(formaPagamentoRepository.findAll());
        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentos);
    }

    @GetMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> buscar(@PathVariable("formaPagamentoId") UUID id) {
        FormaPagamentoModel formaPagamento = formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.buscarOuFalhar(id));

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamento);
    }

    @PostMapping
    public ResponseEntity<FormaPagamentoModel> adicionar(@RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamento = formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput);
        FormaPagamentoModel formaPagamentoNova = formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamento));
        return ResponseEntity.status(HttpStatus.CREATED).body(formaPagamentoNova);
    }

    @PutMapping("/{formaPagamentoId}")
    public ResponseEntity<FormaPagamentoModel> atualizar(@PathVariable("formaPagamentoId") UUID id, @RequestBody FormaPagamentoInput formaPagamentoInput) {
        FormaPagamento formaPagamentoAtual = cadastroFormaPagamentoService.buscarOuFalhar(id);

//        BeanUtils.copyProperties(formaPagamento, formaPagamentoAtual.get(), "id");
        formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);

        FormaPagamentoModel formaPagamentoAtualizada = formaPagamentoModelAssembler.toModel(cadastroFormaPagamentoService.salvar(formaPagamentoAtual));

        return ResponseEntity.status(HttpStatus.OK).body(formaPagamentoAtualizada);
    }

    @DeleteMapping("/{formaPagamentoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable("formaPagamentoId") UUID id) {
        cadastroFormaPagamentoService.excluir(id);

    }

}
