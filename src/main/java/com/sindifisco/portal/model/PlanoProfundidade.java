package com.sindifisco.portal.model;

public enum PlanoProfundidade {
	
	ANALÍTICA("Analítica"),
	SINTÉTICA("Sintética");
	
	private String descricao;

	private PlanoProfundidade(String descricao) {
		this.descricao = descricao;
	}

	public String getDescricao() {
		return descricao;
	}
	
}
