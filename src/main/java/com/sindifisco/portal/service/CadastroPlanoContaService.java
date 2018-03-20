package com.sindifisco.portal.service;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sindifisco.portal.model.PlanoConta;
import com.sindifisco.portal.repository.PlanoContas;
import com.sindifisco.portal.service.event.planoconta.PlanoContaSalvaEvent;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroPlanoContaService {

	@Autowired
	private PlanoContas planoContas;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public void salvar(PlanoConta planoConta) {
		planoContas.save(planoConta);
		
		publisher.publishEvent(new PlanoContaSalvaEvent(planoConta));
	}
	
	@Transactional
	public void excluir(PlanoConta planoConta) {
		try {
			planoContas.delete(planoConta);
			planoContas.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("Impossivel excluir este plano. Ele contém relacionamento com registros de lançamentos.");
		}
	}
}
