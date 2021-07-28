package com.webstore.autor;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AutorRepository extends JpaRepository<Autor, Long>{

	public Optional<Autor> findByEmail(String email);
	boolean existsByEmail(String email);
	
}
