package com.webstore.livro;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Future;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.webstore.autor.Autor;
import com.webstore.categoria.Categoria;

@Entity
public class Livro {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
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
	@ManyToOne
	private Categoria categoria;
	@NotNull
	@ManyToOne
	private Autor autor;
	
	
	public Livro() {
		super();
	}




	public Livro(@NotBlank String titulo2, @NotBlank @Size(max = 500) String resumo2, @NotBlank String sumario2,
			@NotNull @Min(100) int numeroPags2, @NotNull @Min(20) double preco2, @NotBlank String isbn2,
			@Future LocalDateTime dataPublicacao2, @NotNull Categoria categoria, @NotNull Autor autor) {
		// TODO Auto-generated constructor stub
		this.titulo=titulo2;
		this.resumo=resumo2;
		this.sumario=sumario2;
		this.numeroPags=numeroPags2;
		this.preco=preco2 ;
		this.isbn=isbn2;
		this.dataPublicacao=dataPublicacao2;
		this.categoria=categoria;
		this.autor=autor;
	}




	public long getId() {
		return id;
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


	public Categoria getCategoria() {
		return categoria;
	}


	public Autor getAutor() {
		return autor;
	}


	@Override
	public String toString() {
		return "Livro [id=" + id + ", titulo=" + titulo + ", resumo=" + resumo + ", sumario=" + sumario
				+ ", numeroPags=" + numeroPags + ", preco=" + preco + ", isbn=" + isbn + ", dataPublicacao="
				+ dataPublicacao + ", nomeCategoria=" + this.categoria.getNome() + ", emailAutor=" + this.autor.getEmail() + "]";
	}
	
	
}
