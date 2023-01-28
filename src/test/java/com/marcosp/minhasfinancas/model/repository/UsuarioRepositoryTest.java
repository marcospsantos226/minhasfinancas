package com.marcosp.minhasfinancas.model.repository;


import org.assertj.core.api.Assertions;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.marcosp.minhasfinancas.model.entity.Usuario;

import java.util.Optional;


@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class UsuarioRepositoryTest {
	
	@Autowired
	UsuarioRepository repository;

	@Autowired
	TestEntityManager entityManager;

	@Test
	public void deveVerificarAExistenciaDeUmEmail() {
		//cenário
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		
//		ação / execução
		boolean result = repository.existsByEmail("usuario@email.com");
		
		
		//verificacao
		Assertions.assertThat(result).isTrue();
	}
	
	public void deveRetornarFalsoQuandoNaoHouverUsuarioCadastradoComOEmail() {
		//cenário

		
	   //ação / execucao
		boolean result = repository.existsByEmail("usuario@email.com");
		
		
		//verificacao
		Assertions.assertThat(result).isFalse();
	}

	public void devePersistirUmUsuarioNaBaseDeDados(){
		//cenario
		Usuario usuario = criarUsuario();

		//acao
		Usuario usuarioSalvo = repository.save(usuario);

		//verificacao
		Assertions.assertThat(usuarioSalvo.getId()).isNotNull();
	}

	public void deveBuscarUmUsuarioPorEmail(){
		Usuario usuario = criarUsuario();
		entityManager.persist(usuario);

		//verificacao
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");

		Assertions.assertThat(result.isPresent()).isTrue();

	}

	public void deveRetornarVazioAoBuscarUsuarioPorEmailQuandoNaoExistirNaBase(){


		//verificacao
		Optional<Usuario> result = repository.findByEmail("usuario@email.com");

		Assertions.assertThat(result.isPresent()).isFalse();

	}

	public static Usuario criarUsuario(){
		return Usuario.builder().nome("usuario").email("usuario@email.com").senha("senha").build();
	}
}
