package com.MFF.OrganizadorTCC.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class AcessoProfessorController {
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping
	public String carregaPaginaInicioProfessor(Model model) {
		Professor professor = professorService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("professor",professor);
		List<Projeto> projetos = professor.getProjetos();
		projetos.removeIf((projeto) -> projeto.isAceito()==false);
		model.addAttribute("aceitos", projetos);
		return "/professor/index";
	}
	
	@GetMapping("/abertos")
	public String carregaPaginaProjetosPendentes(Model model) {
		Professor professor = professorService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("professor",professor);
		List<Projeto> projetos = professor.getProjetos();
		projetos.removeIf((projeto) -> projeto.isAceito()==true);
		model.addAttribute("pendentes", projetos);
		return "/professor/projetos";
	}
}
