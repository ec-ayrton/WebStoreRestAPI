package com.webstore.categoria;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	boolean existsByNome(String nome);
	Optional<Categoria> findByNome(String nome);
}
