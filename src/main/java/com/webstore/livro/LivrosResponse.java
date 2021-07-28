package com.webstore.livro;

public class LivrosResponse {
	long id;
	String nome;
	public LivrosResponse(long id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}
	
	public long getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	
}
