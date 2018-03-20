package com.sindifisco.portal.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "sindicalizado")
public class Sindicalizado implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long codigo;
	
	private String matricula;
	
	private String nome;
	
	@Enumerated(EnumType.STRING)
	@Column(name = "tipo_pessoa")
	private TipoPessoa tipoPessoa;
	
	@Column(name = "cpf")
	@NotBlank(message="O campo CPF é obrigatório")
	private String cpf;
	
	private String rg;
	
	private String telefone;
	
	private String celular;
	
	@Email(message="O email da SEFA é inválido")
	@Column(name = "email_sefa")
	private String emailSefa;
	
	@Column(name = "tipo_sindicalizado")
	@Enumerated(EnumType.STRING)
	private TipoSindicalizado tipoSindicalizado;
	
	@JsonIgnore // na pesquisa rápida o endereço deve ser ignorado caso contrário dá erro. Mas se quiser tem que inicializar no ClientesController
	@Embedded
	private Endereco endereco;

	public Long getCodigo() {
		return codigo;
	}

	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	
	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public TipoPessoa getTipoPessoa() {
		return tipoPessoa;
	}

	public void setTipoPessoa(TipoPessoa tipoPessoa) {
		this.tipoPessoa = tipoPessoa;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getEmailSefa() {
		return emailSefa;
	}

	public void setEmailSefa(String emailSefa) {
		this.emailSefa = emailSefa;
	}

	public TipoSindicalizado getTipoSindicalizado() {
		return tipoSindicalizado;
	}

	public void setTipoSindicalizado(TipoSindicalizado tipoSindicalizado) {
		this.tipoSindicalizado = tipoSindicalizado;
	}

	public String getCpfSemFormatacao() {
		return TipoPessoa.removerFormatacao(this.cpf);
	}
	
	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public boolean isNovo() {
		return codigo == null;
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
		Sindicalizado other = (Sindicalizado) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}
	

	
}
