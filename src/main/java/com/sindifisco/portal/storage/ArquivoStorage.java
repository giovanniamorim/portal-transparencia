package com.sindifisco.portal.storage;

import org.springframework.web.multipart.MultipartFile;

public interface ArquivoStorage {

	public String salvarTemporariamente(MultipartFile[] files);
	
	public byte[] recuperarArquivoTemporario(String nome);
	
	public void salvar(String arquivo);
	
	public byte[] recuperar(String arquivo);
	
	public byte[] recuperarThumbnail(String arquivoLancamento);
	
	public void excluir(String arquivo);
	
	
}
