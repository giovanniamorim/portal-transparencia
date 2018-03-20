package com.sindifisco.portal.repository.helper.planoconta;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sindifisco.portal.model.PlanoConta;
import com.sindifisco.portal.repository.filter.PlanoContaFilter;

public interface PlanoContasQueries {

	public Page<PlanoConta> filtrar(PlanoContaFilter filtro, Pageable pageable);

}
