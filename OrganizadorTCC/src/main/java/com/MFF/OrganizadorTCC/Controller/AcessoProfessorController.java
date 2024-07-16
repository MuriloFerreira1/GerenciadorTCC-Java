package com.MFF.OrganizadorTCC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Model.Professor.Professor;
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
		return "/professor/index";
	}
}
