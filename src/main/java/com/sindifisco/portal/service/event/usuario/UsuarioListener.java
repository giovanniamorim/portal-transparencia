package com.sindifisco.portal.service.event.usuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sindifisco.portal.storage.ArquivoStorage;

@Component
public class UsuarioListener {
	
	@Autowired
	private ArquivoStorage fotoStorage;
	
	@EventListener(condition = "#evento.temFoto()")
	public void usuarioSalva(UsuarioSalvaEvent evento) {
		fotoStorage.salvar(evento.getUsuario().getFoto());
		
	}

}