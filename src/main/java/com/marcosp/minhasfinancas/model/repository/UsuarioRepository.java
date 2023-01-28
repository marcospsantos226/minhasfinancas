package com.marcosp.minhasfinancas.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.marcosp.minhasfinancas.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
	boolean existsByEmail(String email);


	Optional<Usuario> findByEmail(String email);
}
