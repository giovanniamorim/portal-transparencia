package com.sindifisco.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindifisco.portal.model.Lancamento;
import com.sindifisco.portal.repository.helper.lancamento.LancamentosQueries;

@Repository
public interface Lancamentos extends JpaRepository<Lancamento, Long>, LancamentosQueries{
	
	

}
