package com.sindifisco.portal.repository.helper.lancamento;

import java.time.LocalDate;
import java.util.List;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.sindifisco.portal.dto.LancamentoDTO;
import com.sindifisco.portal.model.Lancamento;
import com.sindifisco.portal.model.TipoLancamento;
import com.sindifisco.portal.repository.filter.LancamentoFilter;
import com.sindifisco.portal.repository.paginacao.PaginacaoUtil;

public class LancamentosImpl implements LancamentosQueries{
	
	@PersistenceContext
	private EntityManager manager;
	
	@Autowired
	PaginacaoUtil paginacaoUtil;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly=true)
	public Page<Lancamento> filtrar(LancamentoFilter filtro, Pageable pageable) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Lancamento.class);
		
		paginacaoUtil.preparar(criteria, pageable);
		
		adicionarFiltro(filtro, criteria);
		return new PageImpl<>(criteria.list(), pageable, total(filtro));
	}

	private Long total(LancamentoFilter filtro) {
		Criteria criteria = manager.unwrap(Session.class).createCriteria(Lancamento.class);
		adicionarFiltro(filtro, criteria);
		criteria.setProjection(Projections.rowCount());
		return (Long) criteria.uniqueResult();
	} 

	private void adicionarFiltro(LancamentoFilter filtro, Criteria criteria) {
		if(filtro != null) {
			if(filtro.getCodigo() != null) {
				criteria.add(Restrictions.eq("codigo", filtro.getCodigo()));
			}
			
			if(filtro.getTipoLancamento() != null) {
				criteria.add(Restrictions.eq("tipoLancamento", filtro.getTipoLancamento()));
			}
			if(filtro.getSupCaixa() != null) {
				criteria.add(Restrictions.eq("supCaixa", filtro.getSupCaixa()));
			}
			if(filtro.getDesde() != null) {
				LocalDate desde = LocalDate.from(filtro.getDesde());
				criteria.add(Restrictions.ge("data", desde));
			}
			if(filtro.getAte() != null) {
				LocalDate ate = LocalDate.from(filtro.getAte());
				criteria.add(Restrictions.le("data", ate));
			}
			if(filtro.getValorMinimo() != null) {
				criteria.add(Restrictions.ge("valor", filtro.getValorMinimo()));
			}
			if(filtro.getValorMaximo() != null) {
				criteria.add(Restrictions.le("valor", filtro.getValorMaximo()));
			}
			if(filtro.getPlanoConta() != null) {
				criteria.add(Restrictions.eq("planoConta", filtro.getPlanoConta()));
			}
			if(filtro.getModoPagamento() != null) {
				criteria.add(Restrictions.eq("modoPagamento", filtro.getModoPagamento()));
			}
			if(filtro.getTipoDocumento() != null) {
				criteria.add(Restrictions.eq("tipoDocumento", filtro.getTipoDocumento()));
			}
			if(!StringUtils.isEmpty(filtro.getNumeroDocumento()))  {
				criteria.add(Restrictions.ilike("numeroDocumento", filtro.getNumeroDocumento(), MatchMode.ANYWHERE));
			}
			if(!StringUtils.isEmpty(filtro.getNumeroCheque())) {
				criteria.add(Restrictions.ilike("numeroCheque", filtro.getNumeroCheque(), MatchMode.ANYWHERE));
			}
		}
	}

	@Override
	public List<LancamentoDTO> findByTipoLancamento(TipoLancamento tipoLancamento) {
		String jpql = "SELECT new com.sindifisco.portal.dto.LancamentoDTO(codigo, tipoLancamento, supCaixa, data, valor, planoConta, modoPagamento, tipoDocumento, numeroDocumento, numeroCheque, observacoes, arquivo)"
					+ "FROM Lancamento WHERE lower(tipoLancamento) LIKE lower(:tipoLancamento) ORDER BY data DESC";
		List<LancamentoDTO> lancamentosFiltrados = manager.createQuery(jpql, LancamentoDTO.class)
				.setParameter("findByTipoLancamento", tipoLancamento + "%")
				.getResultList();
				return lancamentosFiltrados;
	}

//	@Override
//	public List<LancamentoReceita> totalPorReceita() {
//		List<LancamentoReceita> lancamentoTipoReceita = manager.createNamedQuery("lancamento.porTipoReceita", LancamentoReceita.class).getResultList();
//		
//		LocalDate now = LocalDate.now();
//		for (int i = 1; i <= 6; i++) {
//			String mesIdeal = String.format("%d/%02d", now.getYear(), now.getMonth().getValue());
//			
//			boolean possuiMes = lancamentoTipoReceita.stream().filter(v -> v.getMes().equals(mesIdeal)).findAny().isPresent();
//			if (!possuiMes) {
//				lancamentoTipoReceita.add(i - 1, new LancamentoReceita(mesIdeal, 0, 0));
//			}
//			
//			now = now.minusMonths(1);
//		}
//		
//		return lancamentoTipo;
//	}

}





















