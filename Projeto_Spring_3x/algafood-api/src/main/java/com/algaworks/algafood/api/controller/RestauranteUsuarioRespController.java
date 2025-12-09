package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.dto.output.UsuarioModel;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes/{restauranteId}/responsaveis")
@RequiredArgsConstructor
public class RestauranteUsuarioRespController {

    private final CadastroRestauranteService cadastroRestauranteService;
    private final UsuarioModelAssembler usuarioModelAssembler;

    @GetMapping
    public List<UsuarioModel> listar(@PathVariable UUID restauranteId) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);

        return usuarioModelAssembler.toCollectionModel(restaurante.getResponsaveis());
    }

    @PutMapping("/{usuarioId}")
    public void associar(@PathVariable UUID restauranteId, @PathVariable UUID usuarioId) {
        cadastroRestauranteService.associarUsuarioResp(restauranteId, usuarioId);
    }

    @DeleteMapping("/{usuarioId}")
    public void desassociar(@PathVariable UUID restauranteId, @PathVariable UUID usuarioId) {
        cadastroRestauranteService.desassociarUsuarioResp(restauranteId, usuarioId);
    }

}
