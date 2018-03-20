package com.sindifisco.portal.repository.helper.lancamento;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.sindifisco.portal.dto.LancamentoDTO;
import com.sindifisco.portal.model.Lancamento;
import com.sindifisco.portal.model.TipoLancamento;
import com.sindifisco.portal.repository.filter.LancamentoFilter;

public interface LancamentosQueries {
	
	
	public Page<Lancamento> filtrar(LancamentoFilter filtro, Pageable pageable );

	public List<LancamentoDTO> findByTipoLancamento(TipoLancamento tipoLancamento);
	


	

}
