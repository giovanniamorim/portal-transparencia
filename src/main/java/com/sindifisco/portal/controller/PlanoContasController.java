package com.sindifisco.portal.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
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
import com.sindifisco.portal.model.PlanoConta;
import com.sindifisco.portal.model.PlanoProfundidade;
import com.sindifisco.portal.model.TipoLancamento;
import com.sindifisco.portal.repository.PlanoContas;
import com.sindifisco.portal.repository.filter.PlanoContaFilter;
import com.sindifisco.portal.service.CadastroPlanoContaService;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping(("/planocontas"))
public class PlanoContasController {
	

	@Autowired
	private CadastroPlanoContaService cadastroPlanoContaService;
	
	@Autowired
	private PlanoContas planoContas;
	
	@RequestMapping("/novo")
	public ModelAndView novo(PlanoConta planoConta) {
		ModelAndView mv = new ModelAndView("planoconta/CadastroPlanoConta");
		mv.addObject("profundidades", PlanoProfundidade.values());
		mv.addObject("tiposlancamentos", TipoLancamento.values());
		
		return mv;
	}
	
	@RequestMapping(value = { "/novo", "\\d+"}, method = RequestMethod.POST)
	public ModelAndView salvar(@Valid PlanoConta planoConta, BindingResult result, Model model, RedirectAttributes redirectAttributes) {
		if(result.hasErrors()) {
			return novo(planoConta);
		}
		
		cadastroPlanoContaService.salvar(planoConta);
		redirectAttributes.addFlashAttribute("mensagem", "Plano de contas salvo com sucesso!");
		return new ModelAndView("redirect:/planocontas/novo");
	}
	
	
	@GetMapping
	public ModelAndView pesquisar(PlanoContaFilter planoContaFilter, BindingResult result, 
			@PageableDefault(size=10)Pageable pageable, HttpServletRequest httpServletRequest ) {
		ModelAndView mv = new ModelAndView("/planoconta/PesquisaPlanoContas");
		mv.addObject("profundidades", PlanoProfundidade.values());
		mv.addObject("tipolancamentos", TipoLancamento.values());
		mv.addObject("planocontas", planoContas.filtrar(planoContaFilter, pageable));
		
		PageWrapper<PlanoConta> paginaWrapper = new PageWrapper<>(planoContas.filtrar(planoContaFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}

	@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public @ResponseBody List<PlanoConta> pesquisar(TipoLancamento tipoLancamento, PlanoProfundidade profundidade){
		return planoContas.findByTipoLancamentoAndProfundidade(tipoLancamento, profundidade);
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") PlanoConta planoConta){
		try {
			cadastroPlanoContaService.excluir(planoConta);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}
	

	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable("codigo") PlanoConta planoConta) {
		ModelAndView mv = novo(planoConta);
		mv.addObject(planoConta);
		return mv;
	}
	
}
