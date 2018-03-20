package com.sindifisco.portal.service;

import java.util.Optional;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sindifisco.portal.model.Sindicalizado;
import com.sindifisco.portal.repository.Sindicalizados;
import com.sindifisco.portal.service.exception.CpfSindicalizadoJaCadastradoException;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;

@Service
public class CadastroSindicalizadoService {
	
	@Autowired
	private Sindicalizados sindicalizados;
	
	@Transactional
	public void salvar(Sindicalizado sindicalizado) {
		Optional<Sindicalizado> sindicalizadoExistente = sindicalizados.findByCpf(sindicalizado.getCpfSemFormatacao());
		if(sindicalizadoExistente.isPresent() && sindicalizado.isNovo()){
			throw new CpfSindicalizadoJaCadastradoException("CPF já cadastrado");
		}
		
		sindicalizados.save(sindicalizado);
	}
	

	@Transactional
	public void excluir(Sindicalizado sindicalizado) {
		try {
			sindicalizados.delete(sindicalizado);
			sindicalizados.flush();
		} catch (PersistenceException e) {
			throw new ImpossivelExcluirEntidadeException("ImpossÃ­vel apagar o sindicalizado, o mesmo contÃ©m vendas relacionadas");
		}
	
	}
}