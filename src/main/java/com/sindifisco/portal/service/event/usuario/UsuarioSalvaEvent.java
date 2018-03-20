package com.sindifisco.portal.service.event.usuario;

import org.springframework.util.StringUtils;

import com.sindifisco.portal.model.Usuario;

public class UsuarioSalvaEvent {
	
	private Usuario usuario;
	
	public UsuarioSalvaEvent(Usuario usuario) {
		this.usuario = usuario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public boolean temFoto() {
		return !StringUtils.isEmpty(usuario.getFoto());
	}
	
}
