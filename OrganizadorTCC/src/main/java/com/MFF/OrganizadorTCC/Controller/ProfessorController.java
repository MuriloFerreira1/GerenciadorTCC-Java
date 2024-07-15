package com.MFF.OrganizadorTCC.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Professor.DadosAtualizaProfessor;
import com.MFF.OrganizadorTCC.Model.Professor.DadosCadastroProfessor;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Professor.ProfessorRepository;
import com.MFF.OrganizadorTCC.Service.AreaService;
import com.MFF.OrganizadorTCC.Util.Util;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/controleProfessor")
public class ProfessorController {
	
	@Autowired
	private ProfessorRepository repository;
	
	@Autowired
	private AreaService areaServ;
	
	private final PasswordEncoder passwordEncoder;
	
	public ProfessorController(PasswordEncoder passwordEncoder) {
		this.passwordEncoder = passwordEncoder;
	}
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/controller/professor/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaListagem(Long id, Model model) {
		if(id!=null) {
			var professor = repository.getReferenceById(id);
			model.addAttribute(professor);
		}
		model.addAttribute("cursos", Util.getCursos());
		model.addAttribute("areas", areaServ.getAll());
		return "/controller/professor/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastra(DadosCadastroProfessor dados) {
		List<Long> id= new ArrayList<Long>();
		for(Area area: dados.areas()) { id.add(area.getId()); }
		List<Area> areas = id.stream()
                .map(areaServ::getById)
                .collect(Collectors.toList());
		
		Professor professor = new Professor(dados);
		professor.setAreas(areas);
		repository.save(professor);
		
		return "redirect:controleProfessor";
	}
	
	@PutMapping
	@Transactional
	public String atualiza(DadosAtualizaProfessor dados) {
		List<Long> id= new ArrayList<Long>();
		for(Area area: dados.areas()) { id.add(area.getId()); }
		List<Area> areas = id.stream()
                .map(areaServ::getById)
                .collect(Collectors.toList());
		var professor = repository.getReferenceById(dados.id());
		professor.setAreas(areas);
		professor.atualizaProfessor(dados);
		return "redirect:controleProfessor";
	}
	
	@DeleteMapping
	@Transactional
	public String deleta(Long id) {
		repository.deleteById(id);
		return "redirect:controleProfessor";
	}
	
	@GetMapping("/arquivo")
	public String carregaPaginaCadastroPorAquivo() {
		return "/aluno/arquivo";
	}
	
	@PostMapping("/arquivo")
	@Transactional
	public String cadastroPorAquivo(@RequestParam("arquivo") MultipartFile arquivo) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream(), Charset.defaultCharset()));
		String linha = reader.readLine();
		linha = reader.readLine();
		List<Professor> professores = new LinkedList<Professor>();
		while(linha!=null) {
			String[] dados = linha.split(";");
			Professor p = new Professor();
			p.setRM(Integer.parseInt(dados[0]));
			p.setCPF(Long.parseLong(dados[1]));
			p.setEmail(dados[2]);
			p.setNome(dados[3]);
			p.setCurso(dados[4]);
			p.setSenha(passwordEncoder.encode(Util.senhaAleatoria()));
			professores.add(p);
			linha = reader.readLine();
		}
		reader.close();
		repository.saveAll(professores);
		return "redirect:controleProfessor";
	}
	
	@GetMapping("/especial")
	@Transactional
	public String custom() {
		Professor p = repository.getReferenceById((long) 1);
		p.setSenha(passwordEncoder.encode(p.getSenha()));
		repository.save(p);
		return "redirect:controleProfessor";
	}
}
