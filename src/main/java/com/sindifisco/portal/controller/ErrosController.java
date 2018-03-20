package com.sindifisco.portal.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ErrosController {
	
	@GetMapping("/400")
	public String erro400() {
		return "400";
	}
	
	@GetMapping("/404")
	public String paginaNaoEncontrada() {
		return "404";
	}
	
	
	@GetMapping("/405")
	public String erro405() {
		return "500";
	}
	
	@PostMapping("/500")
	public String erro500() {
		return("500");
	}

}
