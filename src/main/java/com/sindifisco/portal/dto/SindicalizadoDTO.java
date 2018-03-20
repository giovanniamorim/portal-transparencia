package com.sindifisco.portal.dto;

public class SindicalizadoDTO {
	
	private Long codigo;
	private String matricula;
	private String nome;
	private String cpf;
	private String tipoSindicalizado;
	
	
	public SindicalizadoDTO(Long codigo, String matricula, String nome, String cpf, String tipoSindicalizado) {
		this.codigo = codigo;
		this.matricula = matricula;
		this.nome = nome;
		this.cpf = cpf;
		this.tipoSindicalizado = tipoSindicalizado;
	}


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


	public String getCpf() {
		return cpf;
	}


	public void setCpf(String cpf) {
		this.cpf = cpf;
	}


	public String getTipoSindicalizado() {
		return tipoSindicalizado;
	}


	public void setTipoSindicalizado(String tipoSindicalizado) {
		this.tipoSindicalizado = tipoSindicalizado;
	}


	
	

}
