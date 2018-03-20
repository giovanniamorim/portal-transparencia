package com.sindifisco.portal.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;

import org.springframework.util.StringUtils;


@Entity
@Table(name = "lancamentos")
public class Lancamento implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Enumerated(EnumType.STRING)
	@NotNull(message = "O tipo de lancamento é obrigatório")
	@Column(name = "tipo_lancamento")
	private TipoLancamento tipoLancamento;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "sup_caixa")
	@NotNull(message = "Indique se este lançamento é um suprimento de caixa")
	private SuprimentoCaixa supCaixa;
	
	@NotNull(message = "A data é obrigatória")
	private LocalDate data;
	
	@NotNull(message = "O valor é obrigatório")
	@DecimalMin(value="0.01", message="O valor mínima para o lançamento é de 0,01 centavo")
	private BigDecimal valor;
	
	@NotNull(message = "O plano de contas é obrigatório")
	@OneToOne
	@JoinColumn(name = "codigo_plano_conta")
	private PlanoConta planoConta;
	
	@OneToOne
	@NotNull(message = "O modo de pagamento é obrigatório")
	@JoinColumn(name = "codigo_modo_pagamento")
	private ModoPagamento modoPagamento;
	
	@NotNull(message = "O tipo de documento é obrigatório")
	@OneToOne
	@JoinColumn(name = "codigo_tipo_documento")
	private TipoDocumento tipoDocumento;
	
	@Column(name = "numero_documento")
	private String numeroDocumento;
	
	@Column(name = "numero_cheque")
	private String numeroCheque;
	
	private String observacoes;
	
	private String arquivo;
	
	@Column(name = "content_type")
	private String ContentType;
	
	@Transient
	private boolean novoArquivo;
	
	
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

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
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
	

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public String getArquivo() {
		return arquivo;
	}

	public void setArquivo(String arquivo) {
		this.arquivo = arquivo;
	}

	public String getContentType() {
		return ContentType;
	}

	public void setContentType(String contentType) {
		ContentType = contentType;
	}

	
	public String getArquivoOuMock() {
		return !StringUtils.isEmpty(arquivo) ? arquivo : "arquivo-mock.jpg";
	}
	
	public boolean temArquivo() {
		return !StringUtils.isEmpty(this.arquivo);
	}

	public boolean isNovoArquivo() {
		return novoArquivo;
	}
	
	public void setNovoArquivo(boolean novoArquivo) {
		this.novoArquivo = novoArquivo;
	}
	
	public boolean isNovo() {
		return codigo==null;
	}
	
	
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Lancamento other = (Lancamento) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	
	

}
