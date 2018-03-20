package com.sindifisco.portal.service.event.lancamento;

import org.springframework.util.StringUtils;

import com.sindifisco.portal.model.Lancamento;

public class LancamentoSalvarEvent{

	
	private Lancamento lancamento;

	public LancamentoSalvarEvent(Lancamento lancamento) {
		this.lancamento = lancamento;
	}

	public Lancamento getLancamento() {
		return lancamento;
	}

	public void setLancamento(Lancamento lancamento) {
		this.lancamento = lancamento;
	}
	
	public boolean temArquivo() {
		return !StringUtils.isEmpty(lancamento.getArquivo());
	}
	
	public boolean isNovoArquivo() {
		return lancamento.isNovoArquivo();
	}

}
