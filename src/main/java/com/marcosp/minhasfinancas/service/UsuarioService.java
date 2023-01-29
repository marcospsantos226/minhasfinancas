package com.marcosp.minhasfinancas.service;

import com.marcosp.minhasfinancas.model.entity.Usuario;

import java.util.Optional;

public interface UsuarioService {

	Usuario autenticar(String email, String senha);
	
	Usuario salvarUsuario(Usuario usuario);
	
	void validarEmail(String email);

	Optional<Usuario> ObterPorId(Long id);

}
