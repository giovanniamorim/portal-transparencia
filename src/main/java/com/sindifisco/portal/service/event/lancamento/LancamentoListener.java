package com.sindifisco.portal.service.event.lancamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import com.sindifisco.portal.storage.ArquivoStorage;

@Component
public class LancamentoListener {
	
	@Autowired
	private ArquivoStorage arquivoStorage;

	@EventListener(condition = "#evento.temArquivo() and #evento.novoArquivo")
	public void lancamentoSalva(LancamentoSalvarEvent evento) {
		arquivoStorage.salvar(evento.getLancamento().getArquivo());
	}
}
