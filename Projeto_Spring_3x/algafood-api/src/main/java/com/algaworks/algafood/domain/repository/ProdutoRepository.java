package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, UUID> {

    @Query("FROM Produto p WHERE p.restaurante.id = :restauranteId AND p.id = :produtoId")
    Optional<Produto> findById(@Param("restauranteId") UUID restauranteId, @Param("produtoId") UUID produtoId);

    List<Produto> findByRestaurante(Restaurante restaurante);

}
