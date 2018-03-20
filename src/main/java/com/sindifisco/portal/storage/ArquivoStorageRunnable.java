package com.sindifisco.portal.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.sindifisco.portal.dto.ArquivoDTO;

public class ArquivoStorageRunnable implements Runnable {
	

	private MultipartFile[] files;
	private DeferredResult<ArquivoDTO> resultado;
	private ArquivoStorage arquivoStorage;
	
	public ArquivoStorageRunnable(MultipartFile[] files, DeferredResult<ArquivoDTO> resultado,
			ArquivoStorage arquivoStorage) {
		this.files = files;
		this.resultado = resultado;
		this.arquivoStorage = arquivoStorage;
	}


	@Override
	public void run() {
		String nomeArquivo = this.arquivoStorage.salvarTemporariamente(files);
		String contentType = files[0].getContentType();
		resultado.setResult(new ArquivoDTO(nomeArquivo, contentType));

	}

}
