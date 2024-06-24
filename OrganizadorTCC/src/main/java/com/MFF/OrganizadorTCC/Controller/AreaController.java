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
import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Area.AreaRepository;
import com.MFF.OrganizadorTCC.Area.DadosAtualizaArea;
import com.MFF.OrganizadorTCC.Area.DadosCadastroArea;
import com.MFF.OrganizadorTCC.Professor.Professor;
import com.MFF.OrganizadorTCC.Professor.ProfessorRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaRepository repository;
	
	@Autowired
	private ProfessorRepository profRepo;
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/area/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if(id!=null) {
			var area = repository.getReferenceById(id);
			model.addAttribute(area);
		}
		return "/area/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastrar(DadosCadastroArea dados) {
		repository.save(new Area(dados));
		return "redirect:area";
	}
	
	@PutMapping
	@Transactional
	public String atualizar(DadosAtualizaArea dados) {
		var area = repository.getReferenceById(dados.id());
		area.atualizaArea(dados);
		return "redirect:area";
	}
	
	@DeleteMapping
	@Transactional
	public String deletar(Long id) {
		List<Professor> professores = profRepo.findAll();
		for(Professor prof: professores) {
			List<Area> areas = new ArrayList<Area>();
			for(Area area : prof.getAreas()) {
				if(id!=area.getId()) {
					areas.add(area);
				}
			}
			prof.setAreas(areas);
		}
		repository.deleteById(id);
		return "redirect:area";
	}
}
