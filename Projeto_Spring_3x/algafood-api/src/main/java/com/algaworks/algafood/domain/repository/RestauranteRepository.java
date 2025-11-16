package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface RestauranteRepository extends JpaRepository<Restaurante, UUID>, RestauranteRepositoryQueries,
        JpaSpecificationExecutor<Restaurante> {

    @Query("from Restaurante r join fetch r.cozinha left join fetch r.formasPagamento")
    List<Restaurante> findAll();

    Optional<List<Restaurante>> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);

    Optional<List<Restaurante>> findByNomeContainingAndCozinhaId(String nome, UUID cozinhaId);

    @Query("from Restaurante r where r.nome like %:nome% and r.cozinha.id = :id")
    Optional<List<Restaurante>> consultarPorNome(String nome, @Param("id") UUID cozinhaId);

    Optional<List<Restaurante>> consultarPorNome2(String nome, @Param("id") UUID cozinhaId);

    Optional<Restaurante> findFirstByNomeContaining(String name);

    Optional<List<Restaurante>> findTop2ByNomeContaining(String name);

    int countByCozinhaId(UUID cozinhaId);

}
