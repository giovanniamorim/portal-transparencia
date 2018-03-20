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
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.sindifisco.portal.controller.page.PageWrapper;
import com.sindifisco.portal.model.Sindicalizado;
import com.sindifisco.portal.model.TipoPessoa;
import com.sindifisco.portal.repository.Estados;
import com.sindifisco.portal.repository.Sindicalizados;
import com.sindifisco.portal.repository.filter.SindicalizadoFilter;
import com.sindifisco.portal.service.CadastroSindicalizadoService;
import com.sindifisco.portal.service.exception.CpfSindicalizadoJaCadastradoException;
import com.sindifisco.portal.service.exception.ImpossivelExcluirEntidadeException;

@Controller
@RequestMapping("/sindicalizados")
public class SindicalizadosController {
	
	@Autowired
	private CadastroSindicalizadoService cadastroSindicalizadoService;
	
	@Autowired
	private Sindicalizados sindicalizados;
	
	@Autowired
	private Estados estados;
	
	@GetMapping("/novo")
	public ModelAndView novo(Sindicalizado sindicalizado) {
		ModelAndView mv = new ModelAndView("sindicalizado/CadastroSindicalizado");
		mv.addObject("estados", estados.findAll());
		mv.addObject("tiposPessoa", TipoPessoa.values());
		return mv;
	}
	
	@PostMapping("/novo")
	public ModelAndView salvar(@Valid Sindicalizado sindicalizado, BindingResult result,
			RedirectAttributes attributes) {
		if(result.hasErrors()) {
			return novo(sindicalizado);
		}
		
		try {
			cadastroSindicalizadoService.salvar(sindicalizado);
		}catch (CpfSindicalizadoJaCadastradoException e) {
			result.rejectValue("cpfOuCnpj", e.getMessage(), e.getMessage());
			return novo(sindicalizado);
		}

		attributes.addFlashAttribute("mensagem", "Sindicalizado salvo com sucesso!");
		return new ModelAndView("redirect:/sindicalizados/novo");
	}
	
	@GetMapping
	public ModelAndView pesquisar(SindicalizadoFilter sindicalizadoFilter, BindingResult result, @PageableDefault(size=2) Pageable pageable,
			HttpServletRequest httpServletRequest) {
		ModelAndView mv = new ModelAndView("/sindicalizado/PesquisaSindicalizados");
		mv.addObject("tiposPessoa", TipoPessoa.values());
		
		PageWrapper<Sindicalizado> paginaWrapper = new PageWrapper<>(sindicalizados.filtrar(sindicalizadoFilter, pageable), httpServletRequest);
		mv.addObject("pagina", paginaWrapper);
		return mv;
	}
	
	@DeleteMapping("/{codigo}")
	public @ResponseBody ResponseEntity<?> excluir(@PathVariable("codigo") Sindicalizado sindicalizado) {
		try {
			cadastroSindicalizadoService.excluir(sindicalizado);
		} catch (ImpossivelExcluirEntidadeException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		return ResponseEntity.ok().build();
	}

//	@GetMapping("/{codigo}")
//	public ModelAndView editar(@PathVariable("codigo") Sindicalizado sindicalizado) {
//		Long codigoCidade = sindicalizado.getEndereco().getCidade().getCodigo();
//		Cidade buscarComCidadeEstado = sindicalizados.buscarComCidadeEstado(codigo);
//		sindicalizado.getEndereco().setEstado(buscarComCidadeEstado.getEstado());
//		ModelAndView mv = novo(sindicalizado);
//		mv.addObject("estados", this.estados.findAll());
//		mv.addObject("tiposPessoa", TipoPessoa.values());
//		mv.addObject(sindicalizado);
//		return mv;
//	}
	
	
	@GetMapping("/{codigo}")
	public ModelAndView editar(@PathVariable Long codigo) {
		Sindicalizado sindicalizado = sindicalizados.buscarComCidadeEstado(codigo);
		ModelAndView mv = novo(sindicalizado);
		mv.addObject(sindicalizado);
		return mv;
		
	}
	
	
	@RequestMapping(consumes = {MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody List<Sindicalizado> pesquisar(String nome){ //o @ResponseBody transforma todo o mÃ©todo em Json
		validarTamanhoNome(nome);
		return sindicalizados.findByNomeStartingWithIgnoreCase(nome);
	}

	private void validarTamanhoNome(String nome) {
		if(StringUtils.isEmpty(nome) || nome.length() < 3 ) {
			throw new IllegalArgumentException();
		}
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<Void> tratarIllegalArgumentException(IllegalArgumentException e){
		return ResponseEntity.badRequest().build();
		
	}

}










