package com.sindifisco.portal.dto;

import java.time.LocalDate;

public class PlanoContaDTO {

	private Long codigo;
	private LocalDate anoExercicio;
	private String codigoFilho;
	private String codigoPai;
	private String descricao;
	private String profundidade;
	private String tipoLancamento;
	
	
	public PlanoContaDTO(Long codigo, LocalDate anoExercicio, String codigoFilho, String codigoPai, String descricao,
			String profundidade, String tipoLancamento) {
		this.codigo = codigo;
		this.anoExercicio = anoExercicio;
		this.codigoFilho = codigoFilho;
		this.codigoPai = codigoPai;
		this.descricao = descricao;
		this.profundidade = profundidade;
		this.tipoLancamento = tipoLancamento;
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


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


	public String getProfundidade() {
		return profundidade;
	}


	public void setProfundidade(String profundidade) {
		this.profundidade = profundidade;
	}


	public String getTipoLancamento() {
		return tipoLancamento;
	}


	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

	
}
