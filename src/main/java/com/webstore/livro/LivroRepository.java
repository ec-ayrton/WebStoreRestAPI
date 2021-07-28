package com.webstore.livro;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LivroRepository extends JpaRepository<Livro, Long>{

	boolean existsByTitulo(String titulo);
	boolean existsByIsbn(String isbn);

}
