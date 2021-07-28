package com.webstore.categoria;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoriaController {
	
	
	@Autowired
	CategoriaRepository CategoriaRepository;
	
	@Transactional
	@PostMapping(value = "/categoria")
	public ResponseEntity<Object> addCategoria(@RequestBody @Valid CadastrarCategoriaRequest cadastrarCategoriaRequest) {
		
		boolean existsNome = CategoriaRepository.existsByNome(cadastrarCategoriaRequest.getNome());
		
		if(existsNome) {
			return ResponseEntity.status(HttpStatus.CONFLICT).body("Erro ao cadastrar Categoria.");
		}
		
		Categoria categoria = cadastrarCategoriaRequest.toModel();
	
		CategoriaRepository.save(categoria);
		return ResponseEntity.ok("categoria cadastrada cadastrada!");
	}
	
}

