package com.sindifisco.portal.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindifisco.portal.model.PlanoConta;
import com.sindifisco.portal.model.PlanoProfundidade;
import com.sindifisco.portal.model.TipoLancamento;
import com.sindifisco.portal.repository.helper.planoconta.PlanoContasQueries;

@Repository
public interface PlanoContas extends JpaRepository<PlanoConta, Long>, PlanoContasQueries{


	Page<PlanoConta> findAll(Pageable pageable);
	
	public List<PlanoConta> findByTipoLancamentoAndProfundidade(TipoLancamento receita, PlanoProfundidade analitica);
	
	public List<PlanoConta> findByTipoLancamento(TipoLancamento tipoLancamento);


}
