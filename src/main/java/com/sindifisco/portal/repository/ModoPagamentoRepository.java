package com.sindifisco.portal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.sindifisco.portal.model.ModoPagamento;

@Repository
public interface ModoPagamentoRepository extends JpaRepository<ModoPagamento, Long>{
	
	

}
