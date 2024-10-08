package com.MFF.OrganizadorTCC.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Projeto.DadosAtualizaProjeto;
import com.MFF.OrganizadorTCC.Model.Projeto.DadosCadastroProjeto;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Repository.ProjetoRepository;
import com.MFF.OrganizadorTCC.Service.AlunoService;
import com.MFF.OrganizadorTCC.Service.AreaService;
import com.MFF.OrganizadorTCC.Service.ProfessorService;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/controleProjeto")
public class ProjetoController {
	@Autowired
	private ProjetoRepository repository;
	
	@Autowired
	private ProfessorService profRepo;
	
	@Autowired
	private AlunoService alunoRepo;
	
	@Autowired
	private AreaService areaRepo;
	
	@GetMapping
	public String carregaPaginaListagem(Model model) {
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/controller/projeto/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if(id!=null) {
			model.addAttribute(repository.getReferenceById(id));
		}
		model.addAttribute("areas", areaRepo.getAll());
		model.addAttribute("professores", profRepo.getAll());
		model.addAttribute("alunos", alunoRepo.getAll());
		
		return "/controller/projeto/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastra(DadosCadastroProjeto dados) {
		repository.save(relacionaProjeto((long)0,dados.nome(),dados.descricao(),dados.alunos(),dados.professor(),dados.area()));
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"))){
			return "redirect:controleProjeto";
		}
		return "redirect:";
	}
	
	@PutMapping
	@Transactional
	public String atualiza(DadosAtualizaProjeto dados) {
		Projeto p = relacionaProjeto(dados.id(),dados.nome(),dados.descricao(),dados.alunos(),dados.professor(),dados.area());
		p.setComentario(dados.comentario());
		p.setAceito(dados.aceito());
		repository.save(p);
		if(SecurityContextHolder.getContext().getAuthentication().getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"))){
			return "redirect:controleProjeto";
		}
		return "redirect:";
	}
	
	@DeleteMapping
	@Transactional
	public String deleta(Long id) {
		repository.deleteById(id);
		return "redirect:controleProjeto";
	}
	
	private Projeto relacionaProjeto(Long id, String nome, String descricao, List<Aluno> alunos, Professor professor, Area area) {
		Projeto projeto = new Projeto();
		if(id!=0) {
			projeto = repository.getReferenceById(id);
		}else {
			projeto.setId(id);
			projeto.setNome(nome);
			projeto.setDescricao(descricao);
		}
		if(professor!=null) {
			professor = profRepo.getById(professor.getId());
			projeto.setProfessor(professor);
		}
		if(alunos!=null) {
			List<Long> ids = new ArrayList<Long>();
			for(Aluno aluno: alunos) {
				ids.add(aluno.getId());
			}
			alunos = alunoRepo.getAllById(ids);
			projeto.setAlunos(alunos);
		}
		
		if(area!=null) {
			area = areaRepo.getById(area.getId());
			projeto.setArea(area);
		}
		return projeto;
	}
	
}
