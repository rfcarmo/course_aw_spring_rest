package com.algaworks.algafood.api.controller;

import com.algaworks.algafood.core.validation.ValidacaoException;
import com.algaworks.algafood.domain.exception.BusinessException;
import com.algaworks.algafood.domain.exception.CozinhaNotFoundException;
import com.algaworks.algafood.domain.exception.RestauranteNotFoundException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComFreteGratisSpecification;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteComNomeSemelhanteSpecification;
import com.algaworks.algafood.infrastructure.repository.spec.RestauranteSpecs;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.ReflectionUtils;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.SmartValidator;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/restaurantes")
@RequiredArgsConstructor
public class RestauranteController {

    private final RestauranteRepository restauranteRepository;
    private final CadastroRestauranteService cadastroRestauranteService;
    private final SmartValidator validator;

    @GetMapping
    public ResponseEntity<List<Restaurante>> listar() {
        List<Restaurante> restaurantes = restauranteRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/{restauranteId}")
    public ResponseEntity<Restaurante> buscar(@PathVariable("restauranteId") UUID id) {
        Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(id);
        return ResponseEntity.status(HttpStatus.OK).body(restaurante);
    }

    @GetMapping("/por-taxa-frete")
    public ResponseEntity<List<Restaurante>> buscarPorTaxaFrete(@RequestParam BigDecimal taxaInicial, @RequestParam BigDecimal taxaFinal) {
        List<Restaurante> restaurantes = restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com a taxa de frete informada."));

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/por-nome-e-frete")
    public ResponseEntity<List<Restaurante>> buscarPorTaxaFrete(@RequestParam(required = false) String nome,
                                                                @RequestParam(required = false) BigDecimal taxaFreteInicial,
                                                                @RequestParam(required = false) BigDecimal taxaFreteFinal) {
        List<Restaurante> restaurantes = restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        List<Restaurante> restaurantes2 = restauranteRepository.find2(nome, taxaFreteInicial, taxaFreteFinal)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes2);
    }

    @GetMapping("/por-nome-cozinhaId")
    public ResponseEntity<List<Restaurante>> buscarPorNomeCozinhaId(@RequestParam String nome, @RequestParam("cozinhaId") UUID id) {
        List<Restaurante> restaurantes = restauranteRepository.findByNomeContainingAndCozinhaId(nome, id)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @GetMapping("/por-nome-e-cozinha")
    public ResponseEntity<List<Restaurante>> buscarPorNomeECozinha(@RequestParam String nome, @RequestParam("cozinhaId") UUID id) {
        List<Restaurante> restaurantes = restauranteRepository.consultarPorNome(nome, id)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        List<Restaurante> restaurantes2 = restauranteRepository.consultarPorNome2(nome, id)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes2);
    }

    @GetMapping("/primeiro-por-nome")
    public ResponseEntity<Restaurante> buscarPrimeiroPorNome(String nome) {
        Restaurante restaurante = restauranteRepository.findFirstByNomeContaining(nome)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        return ResponseEntity.status(HttpStatus.OK).body(restaurante);
    }

    @GetMapping("/top2-por-nome")
    public ResponseEntity<List<Restaurante>> BuscarDoisPrimeirosPorNome(String nome) {
        List<Restaurante> restaurantes = restauranteRepository.findTop2ByNomeContaining(nome)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

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
        List<Restaurante> restaurantes = restauranteRepository.findComFreteGratis(nome)
                .orElseThrow(() -> new RestauranteNotFoundException("Nenhum restaurante encontrado com os filtros informados."));

        return ResponseEntity.status(HttpStatus.OK).body(restaurantes);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
        try {
            return cadastroRestauranteService.salvar(restaurante);
        } catch (CozinhaNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PutMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante atualizar(@PathVariable("restauranteId") UUID id, @RequestBody @Valid Restaurante restaurante) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(id);

        BeanUtils.copyProperties(restaurante, restauranteAtual, "id", "formasPagamento", "endereco", "dataCadastro", "produtos");

        try {
            return cadastroRestauranteService.salvar(restauranteAtual);
        } catch (CozinhaNotFoundException e) {
            throw new BusinessException(e.getMessage(), e);
        }
    }

    @PatchMapping("/{restauranteId}")
    @ResponseStatus(HttpStatus.OK)
    public Restaurante atualizarParcial(@PathVariable("restauranteId") UUID id, @RequestBody Map<String, Object> restaurante, HttpServletRequest request) {
        Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(id);

        merge(restaurante, restauranteAtual, request);
        validate(restauranteAtual, "restaurante");

        return atualizar(id, restauranteAtual);
    }

    private void validate(Restaurante restaurante, String objectName) {
        BeanPropertyBindingResult bindingResult = new BeanPropertyBindingResult(restaurante, objectName);

        validator.validate(restaurante, bindingResult);

        if (bindingResult.hasErrors()) {
            throw new ValidacaoException(bindingResult);
        }
    }

    private void merge(Map<String, Object> dadosOrigem, Restaurante restauranteDestino, HttpServletRequest request) {
        ServletServerHttpRequest servletServerHttpRequest = new ServletServerHttpRequest(request);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES, true);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);

            Restaurante restauranteOrigem = objectMapper.convertValue(dadosOrigem, Restaurante.class);

            dadosOrigem.forEach((nomePropriedade, valorPropriedade) -> {
                Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade);
                field.setAccessible(true);

                Object novoValor = ReflectionUtils.getField(field, restauranteOrigem);

                ReflectionUtils.setField(field, restauranteDestino, novoValor);
            });
        } catch (IllegalArgumentException e) {
            Throwable rootCause = ExceptionUtils.getRootCause(e);
            throw new HttpMessageNotReadableException(e.getMessage(), rootCause, servletServerHttpRequest);
        }
    }

}
