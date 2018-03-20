package com.sindifisco.portal.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.async.DeferredResult;
import org.springframework.web.multipart.MultipartFile;

import com.sindifisco.portal.dto.ArquivoDTO;
import com.sindifisco.portal.storage.ArquivoStorage;
import com.sindifisco.portal.storage.ArquivoStorageRunnable;

@RestController
@RequestMapping("/arquivos")
public class ArquivoController {
	
	@Autowired
	private ArquivoStorage arquivoStorage;
	
	@PostMapping
	public DeferredResult<ArquivoDTO> upload(@RequestParam("files[]") MultipartFile[] files){
		DeferredResult<ArquivoDTO> resultado = new DeferredResult<>();
		
		Thread thread = new Thread(new ArquivoStorageRunnable(files, resultado, arquivoStorage));
		thread.start();
		
		return resultado;
	}

	@GetMapping("/temp/{nome:.*}")
	public byte[] recuperarArquivoTemporario(@PathVariable String nome){
		return arquivoStorage.recuperarArquivoTemporario(nome);
	}
	
	@GetMapping("/{nome:.*}")
	public byte[] recuperar(@PathVariable String nome) {
		return arquivoStorage.recuperar(nome);
	}
}
