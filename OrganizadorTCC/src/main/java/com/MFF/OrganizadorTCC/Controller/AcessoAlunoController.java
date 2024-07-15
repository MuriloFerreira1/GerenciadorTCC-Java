package com.MFF.OrganizadorTCC.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/aluno")
public class AcessoAlunoController {
	
	@GetMapping
	public String carregaPaginaIndexDeAluno(Model model) {
		return "/aluno/index";//
	}
}
