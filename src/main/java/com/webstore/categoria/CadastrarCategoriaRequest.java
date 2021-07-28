package com.webstore.categoria;

import javax.validation.constraints.NotBlank;

public class CadastrarCategoriaRequest {

	@NotBlank
	private String nome;
	
	
	
	public CadastrarCategoriaRequest() {
		super();
	}



	public CadastrarCategoriaRequest(@NotBlank String nome) {
		super();
		this.nome = nome;
	}



	public Categoria toModel() {
		 return new Categoria(this.nome);
	}



	public String getNome() {
		return nome;
	}
	
	
}
