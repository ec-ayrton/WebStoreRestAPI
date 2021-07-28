package com.webstore.autor;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Optional;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureDataJpa
@Transactional
public class AutorControllerTest {
	
	private final String urlHost = "http://localhost:8080";
	
	@Autowired
	MockMvc mockMvc;
	
	@Autowired
	AutorRepository  autorRepository;
	
	@Autowired
	ObjectMapper objectMapper;
	
	@BeforeEach
	public void limpaBaseDeDados() {
		autorRepository.deleteAll();
	}
	
	
	@Test
	@DisplayName("adicionar um novo autor")
	void adicionaAutorTest() throws JsonProcessingException, Exception {
	
		CadastrarAutorRequest cadastrarAutorRequest= new CadastrarAutorRequest("Ayrton sousa","ec.ayrton@gmail.com","aluno ufc");
		mockMvc.perform(MockMvcRequestBuilders.post(urlHost+"/autor").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cadastrarAutorRequest))).andExpect(MockMvcResultMatchers.status().isCreated());                                                                                                          
		
		Optional<Autor> autor = autorRepository.findByEmail(cadastrarAutorRequest.getEmail());
		
		assertTrue(autor.isPresent());
		assertEquals(cadastrarAutorRequest.getEmail(), autor.get().getEmail());
	}
	@Test
	@DisplayName("adicionar um novo autor com nome invalido")
	void adicionaAutorComNomeInvalidoTest() throws JsonProcessingException, Exception {
	
		CadastrarAutorRequest cadastrarAutorRequest= new CadastrarAutorRequest("","ec.ayrton@gmail.com","aluno ufc");
		mockMvc.perform(MockMvcRequestBuilders.post(urlHost+"/autor").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cadastrarAutorRequest))).andExpect(MockMvcResultMatchers.status().is4xxClientError());                                                                                                          
		
		Optional<Autor> autor = autorRepository.findByEmail(cadastrarAutorRequest.getEmail());
		
		assertTrue(autor.isEmpty());
	}
	@Test
	@DisplayName("adicionar um novo autor que ja existe no banco")
	void adicionaAutorJaExistenteTest() throws JsonProcessingException, Exception {
		
		CadastrarAutorRequest cadastrarAutorRequest= new CadastrarAutorRequest("Ayrton sousa","ec.ayrton@gmail.com","aluno ufc");
		
		Autor autor = new Autor(cadastrarAutorRequest.getNome(),cadastrarAutorRequest.getEmail(),cadastrarAutorRequest.getDescricao());
		autorRepository.save(autor);
		mockMvc.perform(MockMvcRequestBuilders.post(urlHost+"/autor").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(cadastrarAutorRequest))).andExpect(MockMvcResultMatchers.status().isConflict());                                                                                                          
		
	}
}
