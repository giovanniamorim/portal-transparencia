package com.sindifisco.portal.model;

public enum Situacao {
	
	ATIVO("Ativo"),
	INATIVO("Inativo"),
	APOSENTADO("Aposentado"),
	PENSIONISTA("Pensionista");
	
	private String descricao;

	private Situacao(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
}
	

