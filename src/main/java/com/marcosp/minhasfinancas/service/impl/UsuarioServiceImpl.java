package com.marcosp.minhasfinancas.service.impl;

import com.marcosp.minhasfinancas.exception.erroAutenticacao;
import org.springframework.stereotype.Service;

import com.marcosp.minhasfinancas.exception.RegraNegocioException;
import com.marcosp.minhasfinancas.model.entity.Usuario;
import com.marcosp.minhasfinancas.model.repository.UsuarioRepository;
import com.marcosp.minhasfinancas.service.UsuarioService;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private UsuarioRepository repository;
	
	
	
	public UsuarioServiceImpl(UsuarioRepository repository) {
		super();
		this.repository = repository;
	}

	@Override
	public Usuario autenticar(String email, String senha) {
		Optional<Usuario> usuario = repository.findByEmail(email);

		if (!usuario.isPresent()) {
			throw new erroAutenticacao("Usuario não encontrado para o e-mail informado");
			
		}
		if(!usuario.get().getSenha().equals(senha)){
			throw new erroAutenticacao("Senha invalida");
		}

		return usuario.get();
	}

	@Override
	@Transactional
	public Usuario salvarUsuario(Usuario usuario) {
		validarEmail(usuario.getEmail());
		return repository.save(usuario);
	}

	@Override
	public void validarEmail(String email) {
		boolean existe = repository.existsByEmail(email);
		if(existe) {
			throw new RegraNegocioException("Já existe um usuário cadastrado com este email");
		}
		
	}

	@Override
	public Optional<Usuario> obterPorId(Long id) {
		return repository.findById(id);
	}

}
