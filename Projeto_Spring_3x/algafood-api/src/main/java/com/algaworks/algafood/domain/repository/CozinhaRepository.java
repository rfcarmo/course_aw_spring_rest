package com.algaworks.algafood.domain.repository;

import com.algaworks.algafood.domain.model.Cozinha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, UUID> {

    Optional<List<Cozinha>> findAllByNome(String name);

    Optional<List<Cozinha>> findAllByNomeContaining(String name);

    boolean existsByNome(String nome);

}
