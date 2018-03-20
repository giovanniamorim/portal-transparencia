package com.sindifisco.portal.repository.helper.planoconta;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sindifisco.portal.model.PlanoConta;
import com.sindifisco.portal.repository.filter.PlanoContaFilter;
import com.sindifisco.portal.repository.paginacao.PaginacaoUtil;

@Service
public class PlanoContasImpl implements PlanoContasQueries{


	@PersistenceContext
	private EntityManager manager;

	@Autowired
	private PaginacaoUtil paginacaoUtil;
	

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public Page<PlanoConta> filtrar(PlanoContaFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(PlanoConta.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		adicionarFiltro(filtro, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(PlanoContaFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(PlanoConta.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	}
	
	
	private void adicionarFiltro(PlanoContaFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(filtro.getCodigoPai() != null) {
				criteria.add(Restrictions.eq("codigoPai", filtro.getCodigoPai()));
			}
			if(!StringUtils.isEmpty(filtro.getDescricao())) {
				criteria.add(Restrictions.ilike("descricao", filtro.getDescricao(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(filtro.getCodigoFilho())) {
				criteria.add(Restrictions.ilike("codigoFilho", filtro.getCodigoFilho()));
			
			
			}
			if(filtro.getTipoLancamento() != null) {
				criteria.add(Restrictions.eq("tipoLancamento", filtro.getTipoLancamento()));
			}
			if(filtro.getProfundidade() != null) {
				criteria.add(Restrictions.eqOrIsNull("profundidade", filtro.getProfundidade()));
			}
		}
	}

	
//	@Override
//	public List<PlanoContaDTO> findByTipoLancamento(TipoLancamento tipoLancamento) {
//			String jpql = "SELECT new com.sindifisco.portal.dto.PlanoContaDTO (codigo, anoExercicio, codigoFilho, codigoPai, descricao, profundidade, tipoLancamento) "
//					+  " FROM PlanoConta where tipoLancamento = :tipoLancamento";
//			List<PlanoContaDTO> planoContasFiltradas = manager.createQuery(jpql, PlanoContaDTO.class)
//					.setParameter("tipoLancamento", tipoLancamento + "%")
//					.getResultList();
//		return planoContasFiltradas;
//	}
//	
//	

	

}
