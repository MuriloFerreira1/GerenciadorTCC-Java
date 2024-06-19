package com.MFF.OrganizadorTCC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Aluno.AlunoRepository;
import com.MFF.OrganizadorTCC.Aluno.DadosCadastroAluno;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno")//
public class AlunoController {
	
	@Autowired
	AlunoRepository repository;
	
	@GetMapping
	public String carregaPaginaListagem(Model model){
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/aluno/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if (id!=null) {
			var Aluno = repository.getReferenceById(id);
			model.addAttribute("aluno", Aluno);
		}
		return "/aluno/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastrar(@Valid DadosCadastroAluno dados) {
		repository.save(new Aluno(dados));
		return "redirect:aluno";
	}
}
