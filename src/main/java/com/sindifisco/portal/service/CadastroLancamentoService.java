package com.sindifisco.portal.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sindifisco.portal.model.Lancamento;
import com.sindifisco.portal.repository.Lancamentos;
import com.sindifisco.portal.service.event.lancamento.LancamentoSalvarEvent;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;
import com.sindifisco.portal.storage.ArquivoStorage;

@Service
public class CadastroLancamentoService {
	
	@Autowired
	private Lancamentos lancamentos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private ArquivoStorage arquivoStorage;
	
	
	@Transactional
	public void salvar(Lancamento lancamento) {
		lancamentos.save(lancamento);
		
		publisher.publishEvent(new LancamentoSalvarEvent(lancamento));
	}
	
	
	@Transactional
	public void excluir(Lancamento lancamento) {
		try {
			String arquivo = lancamento.getArquivo();
					lancamentos.delete(lancamento);
					lancamentos.flush();
					arquivoStorage.excluir(arquivo);
		}catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar o lancamento selecionado. O mesmo contém relacionamentos");
		}
	}
	

}
