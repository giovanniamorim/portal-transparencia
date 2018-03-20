package com.sindifisco.portal.repository.helper.sindicalizado;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sindifisco.portal.model.Sindicalizado;
import com.sindifisco.portal.repository.filter.SindicalizadoFilter;

public interface SindicalizadosQueries {
	
	public Page<Sindicalizado> filtrar(SindicalizadoFilter filtro, Pageable pageable);
	public Sindicalizado buscarComCidadeEstado(Long codigo);


}
