package com.MFF.OrganizadorTCC.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Area.Area;import com.MFF.OrganizadorTCC.Area.AreaService;
import com.MFF.OrganizadorTCC.Professor.DadosAtualizaProfessor;
import com.MFF.OrganizadorTCC.Professor.DadosCadastroProfessor;
import com.MFF.OrganizadorTCC.Professor.Professor;
import com.MFF.OrganizadorTCC.Professor.ProfessorRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository repository;
	
	@Autowired
	AreaService areaServ = new AreaService();
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/professor/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaListagem(Long id, Model model) {
		if(id!=null) {
			var professor = repository.getReferenceById(id);
			model.addAttribute(professor);
		}
		model.addAttribute("areas", areaServ.getAll());
		return "/professor/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastra(DadosCadastroProfessor dados) {
		for(Area area: dados.areas()) {
			System.out.println(area.getId()+"|"+area.getNome());
		}
		repository.save(new Professor(dados));
		return "redirect:professor";
	}
	
	@PutMapping
	@Transactional
	public String atualiza(DadosAtualizaProfessor dados) {
		for(Area area: dados.areas()) {
			System.out.println(area.getId()+"|"+area.getNome());
		}
		var professor = repository.getReferenceById(dados.id());
		professor.atualizaProfessor(dados);
		return "redirect:professor";
	}
	
	@DeleteMapping
	@Transactional
	public String deleta(Long id) {
		repository.deleteById(id);
		return "redirect:professor";
	}
}
