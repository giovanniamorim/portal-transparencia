package com.sindifisco.portal.service.event.modopagamento;

import com.sindifisco.portal.model.ModoPagamento;

public class ModoPagamentoSalvarEvent  {

	private ModoPagamento modoPagamento;

	public ModoPagamentoSalvarEvent(ModoPagamento modoPagamento) {
		this.modoPagamento = modoPagamento;
	}

	public ModoPagamento getModoPagamento() {
		return modoPagamento;
	}

	public void setModoPagamento(ModoPagamento modoPagamento) {
		this.modoPagamento = modoPagamento;
	}
	
	

}
