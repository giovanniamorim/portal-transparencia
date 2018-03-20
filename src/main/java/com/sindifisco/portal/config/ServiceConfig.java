package com.sindifisco.portal.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import com.sindifisco.portal.service.CadastroUsuarioService;
import com.sindifisco.portal.storage.ArquivoStorage;
import com.sindifisco.portal.storage.local.ArquivoStorageLocal;

@Configuration
@ComponentScan(basePackageClasses = CadastroUsuarioService.class)
public class ServiceConfig {
	
	@Bean
	public ArquivoStorage arquivoStorage() {
		return new ArquivoStorageLocal(); 
	}
	

}



