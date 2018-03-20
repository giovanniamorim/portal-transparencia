package com.sindifisco.portal.repository.filter;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.sindifisco.portal.model.ModoPagamento;
import com.sindifisco.portal.model.PlanoConta;
import com.sindifisco.portal.model.SuprimentoCaixa;
import com.sindifisco.portal.model.TipoDocumento;
import com.sindifisco.portal.model.TipoLancamento;

public class LancamentoFilter {
	
	private Long codigo;
	private TipoLancamento tipoLancamento = TipoLancamento.RECEITA;
	private SuprimentoCaixa supCaixa;
	private LocalDate desde;
	private LocalDate ate;
	private BigDecimal valorMinimo;
	private BigDecimal valorMaximo;
	private PlanoConta planoConta;
	private BigDecimal valorTotalReceita;
	private ModoPagamento modoPagamento;
	private TipoDocumento tipoDocumento;
	private String numeroDocumento;
	private String numeroCheque;
	
	
	
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
	
	public SuprimentoCaixa getSupCaixa() {
		return supCaixa;
	}
	public void setSupCaixa(SuprimentoCaixa supCaixa) {
		this.supCaixa = supCaixa;
	}
	public LocalDate getDesde() {
		return desde;
	}
	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}
	public LocalDate getAte() {
		return ate;
	}
	public void setAte(LocalDate ate) {
		this.ate = ate;
	}
	public BigDecimal getValorMinimo() {
		return valorMinimo;
	}
	public void setValorMinimo(BigDecimal valorMinimo) {
		this.valorMinimo = valorMinimo;
	}
	public BigDecimal getValorMaximo() {
		return valorMaximo;
	}
	public void setValorMaximo(BigDecimal valorMaximo) {
		this.valorMaximo = valorMaximo;
	}
	public PlanoConta getPlanoConta() {
		return planoConta;
	}
	public void setPlanoConta(PlanoConta planoConta) {
		this.planoConta = planoConta;
	}
	public ModoPagamento getModoPagamento() {
		return modoPagamento;
	}
	public void setModoPagamento(ModoPagamento modoPagamento) {
		this.modoPagamento = modoPagamento;
	}
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
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
	public BigDecimal getValorTotalReceita() {
		return valorTotalReceita;
	}
	public void setValorTotalReceita(BigDecimal valorTotalReceita) {
		this.valorTotalReceita = valorTotalReceita;
	}
	
}
