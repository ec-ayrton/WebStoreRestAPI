package com.webstore.autor;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

@Entity
public class Autor {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String email;
	private String descricao;
	@PastOrPresent
	private LocalDateTime dataCriacao = LocalDateTime.now();
	
	public Autor() {
		
	}
	
	public Autor(@NotBlank String nome2, @NotBlank @Email String email2, @NotBlank @Size(max = 400) String descricao2) {
		// TODO Auto-generated constructor stub
		this.nome=nome2;
		this.email=email2;
		this.descricao=descricao2;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public String getDescricao() {
		return descricao;
	}
	
	
}
