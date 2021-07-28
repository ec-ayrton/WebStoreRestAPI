package com.webstore.livro;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.webstore.autor.Autor;
import com.webstore.categoria.Categoria;

public class CadastrarLivroRequest {

	@NotBlank
	@Column(unique = true)
	private String titulo;
	@NotBlank
	@Size(max = 500)
	private String resumo;
	@NotBlank
	private String sumario;
	@NotNull
	@Min(value = 100)
	private int numeroPags;
	@NotNull
	@Min(value = 20)
	private double preco;
	@NotBlank
	@Column(unique = true)
	private String isbn;
	@Future
	private LocalDateTime dataPublicacao;
	@NotNull
	private String nomeCategoria;
	@NotNull
	private String emailAutor;
	
	
	
	public CadastrarLivroRequest(@NotBlank String titulo, @NotBlank @Size(max = 500) String resumo,
			@NotBlank String sumario, @NotBlank @Min(100) int numeroPags, @NotNull @Min(20) double preco,
			@NotBlank String isbn, @Future LocalDateTime dataPublicacao, @NotNull String nomeCategoria,
			@NotNull String emailAutor) {
		super();
		this.titulo = titulo;
		this.resumo = resumo;
		this.sumario = sumario;
		this.numeroPags = numeroPags;
		this.preco = preco;
		this.isbn = isbn;
		this.dataPublicacao = dataPublicacao;
		this.nomeCategoria = nomeCategoria;
		this.emailAutor = emailAutor;
	}



	public CadastrarLivroRequest() {
		super();
	}

	

	



	public String getTitulo() {
		return titulo;
	}



	public String getResumo() {
		return resumo;
	}



	public String getSumario() {
		return sumario;
	}



	public int getNumeroPags() {
		return numeroPags;
	}



	public double getPreco() {
		return preco;
	}



	public String getIsbn() {
		return isbn;
	}



	public LocalDateTime getDataPublicacao() {
		return dataPublicacao;
	}



	public String getNomeCategoria() {
		return nomeCategoria;
	}



	public String getEmailAutor() {
		return emailAutor;
	}



	@Override
	public String toString() {
		return "CadastrarLivroRequest [titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario
				+ ", numeroPags=" + numeroPags + ", preco=" + preco + ", isbn=" + isbn + ", dataPublicacao="
				+ dataPublicacao + ", nomeCategoria=" + nomeCategoria + ", emailAutor=" + emailAutor + "]";
	}



	public Livro toModel(Categoria categoria, Autor autor) {
		// TOD
		return new Livro(this.titulo,this.resumo,this.sumario,this.numeroPags,this.preco,this.isbn,this.dataPublicacao,categoria,autor);
	}
	
}
