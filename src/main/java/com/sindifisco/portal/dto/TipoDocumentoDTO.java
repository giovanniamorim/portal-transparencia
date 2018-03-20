package com.sindifisco.portal.dto;

public class TipoDocumentoDTO {
	
	private Long codigo;
	private Long nome;
	
	public TipoDocumentoDTO(Long codigo, Long nome) {
		super();
		this.codigo = codigo;
		this.nome = nome;
	}
	public Long getCodigo() {
		return codigo;
	}
	public void setCodigo(Long codigo) {
		this.codigo = codigo;
	}
	public Long getNome() {
		return nome;
	}
	public void setNome(Long nome) {
		this.nome = nome;
	}
	
	

}
