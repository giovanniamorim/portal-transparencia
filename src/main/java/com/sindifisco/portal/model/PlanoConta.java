package com.sindifisco.portal.model;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "plano_contas")
public class PlanoConta implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	@Column(name = "ano_exercicio")
	private LocalDate anoExercicio;
	
	@Column(name = "codigo_pai")
	private String codigoPai;
	
	
	@Column(name = "codigo_filho")
	private String codigoFilho;
	
	@Column(name = "descricao")
	private String descricao;
	
	@Column(name = "profundidade")
	@Enumerated(EnumType.STRING)
	private PlanoProfundidade profundidade;
	
	@Column(name = "tipo_lancamento")
	@Enumerated(EnumType.STRING)
	private TipoLancamento tipoLancamento;
	
	
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
	public String getCodigoPai() {
		return codigoPai;
	}
	public void setCodigoPai(String codigoPai) {
		this.codigoPai = codigoPai;
	}
	public String getCodigoFilho() {
		return codigoFilho;
	}
	public void setCodigoFilho(String codigoFilho) {
		this.codigoFilho = codigoFilho;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public PlanoProfundidade getProfundidade() {
		return profundidade;
	}
	public void setProfundidade(PlanoProfundidade profundidade) {
		this.profundidade = profundidade;
	}
	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
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
		PlanoConta other = (PlanoConta) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	

}
