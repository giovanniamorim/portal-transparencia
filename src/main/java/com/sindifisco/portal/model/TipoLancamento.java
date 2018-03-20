package com.sindifisco.portal.model;

public enum TipoLancamento {
	
	RECEITA("Receita"),
	DESPESA("Depesa");
	
	private String descricao;

	private TipoLancamento(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
