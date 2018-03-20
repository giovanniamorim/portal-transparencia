package com.sindifisco.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sindifisco.portal.controller.page.PageWrapper;
import com.sindifisco.portal.dto.LancamentoDTO;
import com.sindifisco.portal.model.Lancamento;
import com.sindifisco.portal.model.PlanoProfundidade;
import com.sindifisco.portal.model.SuprimentoCaixa;
import com.sindifisco.portal.model.TipoLancamento;
import com.sindifisco.portal.repository.Lancamentos;
import com.sindifisco.portal.repository.ModosPagamentos;
import com.sindifisco.portal.repository.PlanoContas;
import com.sindifisco.portal.repository.TiposDocumentos;
import com.sindifisco.portal.repository.filter.LancamentoFilter;
import com.sindifisco.portal.service.CadastroLancamentoService;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;


@Controller
@RequestMapping("/receitas")
public class ReceitasController {

	
	
	@Autowired
	private PlanoContas planoContas;
	
	@Autowired
	private ModosPagamentos modosPagamentos;
	
	@Autowired
	private TiposDocumentos tiposDocumentos;
	
	@Autowired
	private CadastroLancamentoService cadastroLancamentoService;
	
	@Autowired
	private Lancamentos lancamentos;
	
	@GetMapping("/nova")
	public ModelAndView nova(Lancamento lancamento) {
		ModelAndView mv = new ModelAndView("lancamento/receita/CadastroReceita");
		mv.addObject("tiposlancamentos", TipoLancamento.RECEITA);
		mv.addObject("planocontas", planoContas.findByTipoLancamento(TipoLancamento.RECEITA));
		mv.addObject("modospagamentos", modosPagamentos.findAll());
		mv.addObject("tiposdocumentos", tiposDocumentos.findAll());
		mv.addObject("supcaixas", SuprimentoCaixa.values());
		
		return mv;
	}
	
	@RequestMapping(value = { "nova", "{\\d+}" }, method = RequestMethod.POST)
	public ModelAndView salvarReceita(@Valid Lancamento lancamento, BindingResult result, Model model, RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return nova(lancamento);
		}
		
		cadastroLancamentoService.salvar(lancamento);
		attributes.addFlashAttribute("mensagem", "Receita salva com sucesso!");
		return new ModelAndView("redirect:/receitas/nova");
	}
	
	
	@GetMapping
	public ModelAndView pesquisa(LancamentoFilter lancamentoFilter, BindingResult result,
			@PageableDefault(size=10, direction = Sort.Direction.DESC, sort = "data") Pageable pageable, HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("lancamento/receita/PesquisaReceitas");
		mv.addObject("tiposlancamentos", TipoLancamento.RECEITA);
		mv.addObject("planocontas", planoContas.findByTipoLancamentoAndProfundidade(TipoLancamento.RECEITA, PlanoProfundidade.ANALÍTICA ));
		mv.addObject("modospagamentos", modosPagamentos.findAll());
		mv.addObject("tiposdocumentos", tiposDocumentos.findAll());
		mv.addObject("supcaixas", SuprimentoCaixa.values());
		
		PageWrapper<Lancamento> paginaWrapper = new PageWrapper<>(lancamentos.filtrar(lancamentoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
		
		
	}
	
	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<LancamentoDTO> pesquisarReceita(TipoLancamento tipoLancamento){
		return lancamentos.findByTipoLancamento(tipoLancamento);
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?>  excluir(@PathVariable("codigo") Lancamento lancamento){
		try {
			cadastroLancamentoService.excluir(lancamento);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") Lancamento lancamento) {
		ModelAndView mv = nova(lancamento);
		mv.addObject(lancamento);
		return mv;
	}
	
}
