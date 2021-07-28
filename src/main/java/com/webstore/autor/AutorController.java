package com.webstore.autor;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AutorController {
	
	
	@Autowired
	AutorRepository autorRepository;
	
	@Transactional
	@PostMapping(value = "/autor")
	public ResponseEntity<Object> addAutor(@RequestBody @Valid CadastrarAutorRequest cadastrarAutorRequest) {
		
		boolean existsAutor = autorRepository.existsByEmail(cadastrarAutorRequest.getEmail());
		
		if(existsAutor) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar usuario");
		}
		
		Autor autor = cadastrarAutorRequest.toModel();
	
		autorRepository.save(autor);
		return ResponseEntity.status(HttpStatus.CREATED).body("usuario cadastrado!");
	}
	
}
