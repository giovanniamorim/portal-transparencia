package com.sindifisco.portal.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.util.StringUtils;

public class LancamentoDTO {
	
	private Long codigo;
	private String tipoLancamento;
	private String supCaixa;
	private LocalDate data;
	private BigDecimal valor;
	private String planoConta;
	private String modoPagamento;
	private String tipoDocumento;
	private String numeroDocumento;
	private String numeroCheque;
	private String observacoes;
	private String arquivo;
	
	
	public LancamentoDTO(Long codigo, String tipoLancamento, String supCaixa, LocalDate data, BigDecimal valor,
			String planoConta, String modoPagamento, String tipoDocumento, String numeroDocumento, String numeroCheque,
			String observacoes, String arquivo) {
		this.codigo = codigo;
		this.tipoLancamento = tipoLancamento;
		this.supCaixa = supCaixa;
		this.data = data;
		this.valor = valor;
		this.planoConta = planoConta;
		this.modoPagamento = modoPagamento;
		this.tipoDocumento = tipoDocumento;
		this.numeroDocumento = numeroDocumento;
		this.numeroCheque = numeroCheque;
		this.observacoes = observacoes;
		this.arquivo = StringUtils.isEmpty(arquivo) ? "arquivo-mock.jpg" : arquivo;
	}


	public Long getCodigo() {
		return codigo;
	}


	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}


	public String getTipoLancamento() {
		return tipoLancamento;
	}


	public void setTipoLancamento(String tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}


	public String getSupCaixa() {
		return supCaixa;
	}


	public void setSupCaixa(String supCaixa) {
		this.supCaixa = supCaixa;
	}


	public LocalDate getData() {
		return data;
	}


	public void setData(LocalDate data) {
		this.data = data;
	}


	public BigDecimal getValor() {
		return valor;
	}


	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}


	public String getPlanoConta() {
		return planoConta;
	}


	public void setPlanoConta(String planoConta) {
		this.planoConta = planoConta;
	}


	public String getModoPagamento() {
		return modoPagamento;
	}


	public void setModoPagamento(String modoPagamento) {
		this.modoPagamento = modoPagamento;
	}


	public String getTipoDocumento() {
		return tipoDocumento;
	}


	public void setTipoDocumento(String tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}


	public String getNumeroDocumento() {
		return numeroDocumento;
	}


	public void setNumeroDocumento(String numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}


	public String getNumeroCheque() {
		return numeroCheque;
	}


	public void setNumeroCheque(String numeroCheque) {
		this.numeroCheque = numeroCheque;
	}


	public String getObservacoes() {
		return observacoes;
	}


	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}


	public String getArquivo() {
		return arquivo;
	}


	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}
	

}
