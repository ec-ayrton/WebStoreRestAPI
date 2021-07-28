package com.webstore.livro;

public class ListaDetalhesLivro {

	private String titulo;
	private int numeroPags;
	private double preco;
	
	
	
	public ListaDetalhesLivro() {
		super();
	}

	public ListaDetalhesLivro(String titulo, int numeroPags, double preco) {
		super();
		this.titulo = titulo;
		this.numeroPags = numeroPags;
		this.preco = preco;
	}

	public String getTitulo() {
		return titulo;
	}

	public int getNumeroPags() {
		return numeroPags;
	}

	public double getPreco() {
		return preco;
	}
	
}
