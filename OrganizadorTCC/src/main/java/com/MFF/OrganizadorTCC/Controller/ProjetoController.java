package com.MFF.OrganizadorTCC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Projeto.ProjetoRepository;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	@Autowired
	ProjetoRepository repository;
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/projeto/listagem";
	}
	
	@GetMapping("/teste")
	public String teste() {
		return "teste";
	}
}
