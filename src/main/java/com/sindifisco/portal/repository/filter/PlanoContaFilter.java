package com.sindifisco.portal.repository.filter;

import java.time.LocalDate;

import com.sindifisco.portal.model.PlanoProfundidade;
import com.sindifisco.portal.model.TipoLancamento;

public class PlanoContaFilter {
	
	private LocalDate anoExercicio;
	private String codigoFilho;
	private String codigoPai;
	private String descricao;
	private TipoLancamento tipoLancamento;
	private PlanoProfundidade profundidade;
	
	public LocalDate getAnoExercicio() {
		return anoExercicio;
	}
	public void setAnoExercicio(LocalDate anoExercicio) {
		this.anoExercicio = anoExercicio;
	}
	
	public String getCodigoFilho() {
		return codigoFilho;
	}
	public void setCodigoFilho(String codigoFilho) {
		this.codigoFilho = codigoFilho;
	}
	public String getCodigoPai() {
		return codigoPai;
	}
	public void setCodigoPai(String codigoPai) {
		this.codigoPai = codigoPai;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	public PlanoProfundidade getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(PlanoProfundidade profundidade) {
		this.profundidade = profundidade;
	}
	
	

}
