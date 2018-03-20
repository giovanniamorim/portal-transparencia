package com.sindifisco.portal.storage;

import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.sindifisco.portal.dto.ArquivoDTO;

public class FotoStorageRunnable implements Runnable {
	
	private MultipartFile[] files;
	private DeferredResult<ArquivoDTO> resultado;
	private FotoStorage fotoStorage;
	
	public FotoStorageRunnable(MultipartFile[] files, DeferredResult<ArquivoDTO> resultado, FotoStorage fotoStorage) {
		this.files = files;
		this.resultado = resultado;
		this.fotoStorage = fotoStorage;
	}

	@Override
	public void run() {
		String nomeFoto = this.fotoStorage.salvarTemporariamente(files);
		String contentType = files[0].getContentType();
		resultado.setResult(new ArquivoDTO(nomeFoto, contentType));
	}

}
