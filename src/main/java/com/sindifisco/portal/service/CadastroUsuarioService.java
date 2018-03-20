package com.sindifisco.portal.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sindifisco.portal.model.Usuario;
import com.sindifisco.portal.repository.Usuarios;
import com.sindifisco.portal.service.exception.EmailJaCadastradoException;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;
import com.sindifisco.portal.service.exception.SenhaObrigatoriaUsuarioException;


@Service
public class CadastroUsuarioService {
	
	@Autowired
	private Usuarios usuarios;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void salvar(Usuario usuario) {
		Optional<Usuario> usuarioExistente = usuarios.findByEmail(usuario.getEmail());
		if(usuarioExistente.isPresent() && !usuarioExistente.get().equals(usuario)) {
			throw new EmailJaCadastradoException("Já existe um usuário cadastrado com este email.");
		}
		
		if(usuario.isNovo() && StringUtils.isEmpty(usuario.getSenha())) {
			throw new SenhaObrigatoriaUsuarioException("A senha é obrigatória para novos usuários.");
		}
		
		if(usuario.isNovo() || !StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		} else if (StringUtils.isEmpty(usuario.getSenha())) {
			usuario.setSenha(usuarioExistente.get().getSenha());
		}
		usuario.setConfirmacaoSenha(usuario.getSenha());
		
		if(!usuario.isNovo() && usuario.getAtivo() == null) {
			usuario.setAtivo(usuarioExistente.get().getAtivo());
		}
		
		usuarios.save(usuario);
	}

	@Transactional
	public void alterarStatus(Long[] codigos, StatusUsuario statusUsuario) {
		statusUsuario.executar(codigos, usuarios);
	}

	@Transactional
	public void excluir(Usuario usuario) {
		try {
			usuarios.delete(usuario);
			usuarios.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel excluir este usuário. O mesmo está relacionado a outras áreas do sistema.");
		}
	}
	
}
