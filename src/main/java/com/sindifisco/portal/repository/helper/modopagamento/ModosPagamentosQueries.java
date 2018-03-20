package com.sindifisco.portal.repository.helper.modopagamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sindifisco.portal.model.ModoPagamento;
import com.sindifisco.portal.repository.filter.ModoPagamentoFilter;

public interface ModosPagamentosQueries {
	
	public Page<ModoPagamento> filtrar(ModoPagamentoFilter filtro, Pageable pageable);
	
}
