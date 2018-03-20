package com.sindifisco.portal.model;

public enum SuprimentoCaixa {
	S("Sim"),
	N("Não");
	
	private String descricao;
	
	SuprimentoCaixa(String descricao){
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
