package com.MFF.OrganizadorTCC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Aluno.AlunoRepository;
import com.MFF.OrganizadorTCC.Area.AreaRepository;
import com.MFF.OrganizadorTCC.Professor.ProfessorRepository;
import com.MFF.OrganizadorTCC.Projeto.ProjetoRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/projeto")
public class ProjetoController {
	@Autowired
	private ProjetoRepository repository;
	
	@Autowired
	private ProfessorRepository profRepo;
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	@Autowired
	private AreaRepository areaRepo;
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/projeto/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if(id!=null) {
			model.addAttribute(repository.getReferenceById(id));
		}
		model.addAttribute("areas", areaRepo.findAll(Sort.by("nome").ascending()));
		model.addAttribute("professores", profRepo.findAll(Sort.by("nome").ascending()));
		model.addAttribute("alunos", alunoRepo.findAll(Sort.by("nome").ascending()));
		return "/projeto/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastra() {
		return "redirect:projeto";
	}
}
