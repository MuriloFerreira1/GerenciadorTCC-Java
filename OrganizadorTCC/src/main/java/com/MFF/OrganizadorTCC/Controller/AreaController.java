package com.MFF.OrganizadorTCC.Controller;

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

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/area")
public class AreaController {
	@Autowired
	private AreaRepository repository;
	
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
		repository.deleteById(id);
		return "redirect:area";
	}
}
