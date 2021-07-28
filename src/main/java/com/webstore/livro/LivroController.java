package com.webstore.livro;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.webstore.autor.Autor;
import com.webstore.autor.AutorRepository;
import com.webstore.categoria.Categoria;
import com.webstore.categoria.CategoriaRepository;

@RestController
public class LivroController {
	@Autowired
	LivroRepository livroRepository;
	
	@Autowired
	CategoriaRepository categoriaRepository;
	
	@Autowired
	AutorRepository autorRepository;
	
	
	@Transactional
	@PostMapping(value = "/livro")
	public ResponseEntity<Object> addLivro(@RequestBody @Valid CadastrarLivroRequest cadastrarLivroRequest) {
		//
		boolean existsLivro = 	livroRepository.existsByTitulo(cadastrarLivroRequest.getTitulo()) || 
				livroRepository.existsByIsbn(cadastrarLivroRequest.getIsbn());
		if(existsLivro) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar Livro");
		}
		//
		Optional<Categoria> cat = categoriaRepository.findByNome(cadastrarLivroRequest.getNomeCategoria());
		if(cat.isEmpty()) {
			return ResponseEntity.badRequest().body("Categoria invalida");
		}
		//
		Optional<Autor> autor = autorRepository.findByEmail(cadastrarLivroRequest.getEmailAutor());
		if(autor.isEmpty()) {
			return ResponseEntity.badRequest().body("Autor invalido");
		}
		//
		Livro livro = cadastrarLivroRequest.toModel(cat.get(),autor.get());
	
		System.out.println(livro.toString());
		livroRepository.save(livro);
		return ResponseEntity.status(HttpStatus.CREATED).body("Livro cadastrado!");		
	}
	@GetMapping(value = "/livros")
	public List<LivrosResponse> ListarLivros() {
		List<LivrosResponse> livrosResponse = new ArrayList<>();
		List<Livro> livrosFromDb = livroRepository.findAll();
		
		if(livrosFromDb.isEmpty()) {
			return null;
		}
		for(Livro livro:livrosFromDb) {
			livrosResponse.add(new LivrosResponse(livro.getId(), livro.getTitulo()));
		}
		return livrosResponse;
	}
	@GetMapping(value = "/livro/{idLivro}")
	public ResponseEntity<Object> ListarDetalhesLivro( @NotNull @Valid @PathVariable long idLivro) {
	
		 Optional<Livro> livroOpt = livroRepository.findById(idLivro);
		
		if(livroOpt.isEmpty()) {
			return ResponseEntity.notFound().build();
		}
		Livro livroFromDb = livroOpt.get();
		ListaDetalhesLivro listaDetalhesLivro =  new ListaDetalhesLivro(livroFromDb.getTitulo(),livroFromDb.getNumeroPags(),livroFromDb.getPreco());
		
		return ResponseEntity.ok(listaDetalhesLivro);
	}
}
