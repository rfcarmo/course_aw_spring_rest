package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.domain.exception.EntityNotFoundException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpecification;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpecification;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;

    private final CadastroRestauranteService cadastroRestauranteService;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable("restauranteId") UUID id) {
        Optional<Restaurante> restaurante = restauranteRepository.findById(id);

        if (restaurante.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurante.get());
    }

    @GetMapping("/por-taxa-frete")
    public ResponseEntity<List<Restaurante>> buscarPorTaxaFrete(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
        List<Restaurante> restaurantes = restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/por-nome-e-frete")
    public ResponseEntity<List<Restaurante>> buscarPorTaxaFrete(@RequestParam(required = false) String nome,
                                                                @RequestParam(required = false) BigDecimal taxaFreteInicial,
                                                                @RequestParam(required = false) BigDecimal taxaFreteFinal) {
        List<Restaurante> restaurantes = restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
        List<Restaurante> restaurantes2 = restauranteRepository.find2(nome, taxaFreteInicial, taxaFreteFinal);

        if (restaurantes2.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes2);
    }

    @GetMapping("/por-nome-cozinhaId")
    public ResponseEntity<List<Restaurante>> buscarPorNomeCozinhaId(@RequestParam String nome, @RequestParam("cozinhaId") UUID id) {
        List<Restaurante> restaurantes = restauranteRepository.findByNomeContainingAndCozinhaId(nome, id);

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/por-nome-e-cozinha")
    public ResponseEntity<List<Restaurante>> buscarPorNomeECozinha(@RequestParam String nome, @RequestParam("cozinhaId") UUID id) {
        List<Restaurante> restaurantes = restauranteRepository.consultarPorNome(nome, id);
        List<Restaurante> restaurantes2 = restauranteRepository.consultarPorNome2(nome, id);

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes2);
    }

    @GetMapping("/primeiro-por-nome")
    public ResponseEntity<Restaurante> buscarPrimeiroPorNome(String nome) {
        Optional<Restaurante> restaurante = restauranteRepository.findFirstByNomeContaining(nome);

        if (restaurante.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurante.get());
    }

    @GetMapping("/top2-por-nome")
    public ResponseEntity<List<Restaurante>> BuscarDoisPrimeirosPorNome(String nome) {
        List<Restaurante> restaurantes = restauranteRepository.findTop2ByNomeContaining(nome);

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/count-por-id")
    public ResponseEntity<?> BuscarQuantidadePorId(@RequestParam("cozinhaId") UUID id) {
        int quantidade = restauranteRepository.countByCozinhaId(id);

        return ResponseEntity.status(HttpStatus.OK).body(quantidade);
    }

    @GetMapping("/frete-gratis")
    public ResponseEntity<List<Restaurante>> BuscarComFreteGratis(String nome) {
        var comFreteGratis = new RestauranteComFreteGratisSpecification();
        var comNomeSemelhante = new RestauranteComNomeSemelhanteSpecification(nome);

        List<Restaurante> restaurantes = restauranteRepository.findAll(comFreteGratis.and(comNomeSemelhante));

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/frete-gratis-spec")
    public ResponseEntity<List<Restaurante>> BuscarComFreteGratisSpec(String nome) {
        List<Restaurante> restaurantes = restauranteRepository
                .findAll(RestauranteSpecs.comFreteGratis().and(RestauranteSpecs.comNomeSemelhante(nome)));

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/frete-gratis-opt")
    public ResponseEntity<List<Restaurante>> BuscarComFreteGratisOpt(String nome) {
        List<Restaurante> restaurantes = restauranteRepository.findComFreteGratis(nome);

        if (restaurantes.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @PostMapping
    public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante) {
        try {
            cadastroRestauranteService.salvar(restaurante);

            return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{restauranteId}")
    public ResponseEntity<?> atualizar(@PathVariable("restauranteId") UUID id, @RequestBody Restaurante restaurante) {
        try {
            Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

            if (restauranteAtual.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");

            Restaurante restauranteAtualizado = cadastroRestauranteService.salvar(restauranteAtual.get());

            return ResponseEntity.status(HttpStatus.OK).body(restauranteAtualizado);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PatchMapping("/{restauranteId}")
    public ResponseEntity<?> atualizarParcial(@PathVariable("restauranteId") UUID id, @RequestBody Map<String, Object> restaurante) {
        Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

        if (restauranteAtual.isEmpty()) {
            ResponseEntity.notFound().build();
        }

        merge(restaurante, restauranteAtual.get());

        return atualizar(id, restauranteAtual.get());
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino) {
        ObjectMapper objectMapper = new ObjectMapper();
        Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

        dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
            Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
            field.setAccessible(true);

            Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

            ReflectionUtils.setField(field, restauranteDestino, novoValor);
        });
    }

}
