package com.sindifisco.portal.storage.local;

import static java.nio.file.FileSystems.getDefault;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import com.sindifisco.portal.storage.ArquivoStorage;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

public class ArquivoStorageLocal implements ArquivoStorage {

	private static final Logger LOGGER = LoggerFactory.getLogger(ArquivoStorageLocal.class);
	private static final String THUMBNAIL_PREFIX = "thumbnail.";
	
	private Path local;
	private Path localTemporario;
	
	public ArquivoStorageLocal() {
		this(getDefault().getPath(System.getProperty("user.home"), ".portalarquivos"));
	}
	
	public ArquivoStorageLocal(Path path) {
		this.local = path;
		criarPastas();
	}
	

	@Override
	public void salvar(String arquivo) {
		try {
			Files.move(this.localTemporario.resolve(arquivo), this.local.resolve(arquivo));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao mover a arquivo para o destino final", e);
		}
		
		try {
			Thumbnails.of(this.local.resolve(arquivo).toString()).size(40, 68).toFiles(Rename.PREFIX_DOT_THUMBNAIL);
		} catch (IOException e) {
			throw new RuntimeException("Erro ao gerar o thumbnail da imagem", e);
		}
		
	}
	
	private void criarPastas() {
		try {
			Files.createDirectories(this.local);
			this.localTemporario = getDefault().getPath(this.local.toString(), "temp");
			Files.createDirectories(this.localTemporario);
			
			if(LOGGER.isDebugEnabled()) {
				LOGGER.debug("Pasta para salvar arquivos criada com sucesso!.");
				LOGGER.debug("Pasta Default: " + this.local.toAbsolutePath());
				LOGGER.debug("Pasta temporária: " + this.localTemporario.toAbsolutePath());
			}
			
		} catch (IOException e) {
			throw new RuntimeException("Erro ao criar diretório para arquivos.", e);
		}
		
	}

	@Override
	public String salvarTemporariamente(MultipartFile[] files) {
		String novoNome = null;
		if(files != null & files.length > 0) {
			MultipartFile arquivo = files[0];
			novoNome = renomearArquivo(arquivo.getOriginalFilename());
			try {
				arquivo.transferTo(new File(this.localTemporario.toAbsolutePath().toString() + getDefault().getSeparator() + novoNome));
			} catch (IOException e) {
				throw new RuntimeException("Erro ao salvar a arquivo na pasta temporária.", e);
			}
			
		}
		return novoNome;
	}
	
	
	@Override
	public byte[] recuperarArquivoTemporario(String nome) {
		try {
			return Files.readAllBytes(this.localTemporario.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler a arquivo temporária.", e);
		}
	}
	
	@Override
	public byte[] recuperar(String nome) {
		try {
			return Files.readAllBytes(this.local.resolve(nome));
		} catch (IOException e) {
			throw new RuntimeException("Erro ao ler a arquivo.", e);
		}
	}
	
	@Override
	public byte[] recuperarThumbnail(String arquivoLancamento) {
		return recuperar(THUMBNAIL_PREFIX + arquivoLancamento);
	}
	

	@Override
	public void excluir(String arquivo) {
		try {
			Files.deleteIfExists(this.local.resolve(arquivo));
			Files.deleteIfExists(this.local.resolve(THUMBNAIL_PREFIX + arquivo));
		} catch (IOException e) {
			LOGGER.warn(String.format("Erro ao apagar arquivo '%s'. Mensagem: %s", arquivo, e.getMessage()));
		}
		
	}
	
	private String renomearArquivo(String nomeOriginal) {
		String novoNome = UUID.randomUUID().toString() + "_" + nomeOriginal;
		
		if(LOGGER.isDebugEnabled()) {
			LOGGER.debug(String.format("Nome Original: %s, Novo nome: %s", nomeOriginal, novoNome));
		}
		return novoNome;
		
	}
}
