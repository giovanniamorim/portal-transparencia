package com.sindifisco.portal.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sindifisco.portal.controller.page.PageWrapper;
import com.sindifisco.portal.model.ModoPagamento;
import com.sindifisco.portal.repository.ModosPagamentos;

@Controller
@RequestMapping("/modospagamentos")
public class ModoPagamentoController{
	
	
	@Autowired
	private ModosPagamentos modosPagamentos;
	
	@GetMapping
	public ModelAndView listar(@PageableDefault(size=5) Pageable pageable, HttpServletRequest httpServletRequest){
		ModelAndView mv = new ModelAndView("modopagamento/ListaModoPagamento");
		mv.addObject("modospagamentos", modosPagamentos.findAll());
		
		PageWrapper<ModoPagamento> paginaWrapper = new PageWrapper<>(modosPagamentos.findAll(pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		
		return mv;
	}
	

}












