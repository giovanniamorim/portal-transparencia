package com.sindifisco.portal.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sindifisco.portal.model.ModoPagamento;
import com.sindifisco.portal.repository.ModosPagamentos;
import com.sindifisco.portal.service.event.modopagamento.ModoPagamentoSalvarEvent;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class ModoPagamentoService {
	
	@Autowired
	private ModosPagamentos modosPagamentos;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(ModoPagamento modoPagamento) {
		modosPagamentos.save(modoPagamento);
		
		publisher.publishEvent(new ModoPagamentoSalvarEvent(modoPagamento));
	}
	
	
	@Transactional
	public void excluir(ModoPagamento modoPagamento) {
		try {
					modosPagamentos.delete(modoPagamento);
					modosPagamentos.flush();
		}catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel apagar o modo de pagamento selecionado. O mesmo contém relacionamentos");
		}
	}
	

	

}
