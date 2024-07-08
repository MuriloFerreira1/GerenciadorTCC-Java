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

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Aluno.AlunoService;
import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Area.AreaService;
import com.MFF.OrganizadorTCC.Professor.Professor;
import com.MFF.OrganizadorTCC.Professor.ProfessorService;
import com.MFF.OrganizadorTCC.Projeto.DadosAtualizaProjeto;
import com.MFF.OrganizadorTCC.Projeto.DadosCadastroProjeto;
import com.MFF.OrganizadorTCC.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Projeto.ProjetoRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/projeto")
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
		return "/projeto/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if(id!=null) {
			model.addAttribute(repository.getReferenceById(id));
		}
		model.addAttribute("areas", areaRepo.getAll());
		model.addAttribute("professores", profRepo.getAll());
		model.addAttribute("alunos", alunoRepo.getAll());
		
		return "/projeto/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastra(DadosCadastroProjeto dados) {
		repository.save(relacionaProjeto((long)0,dados.nome(),dados.descricao(),dados.alunos(),dados.professor(),dados.area()));
		return "redirect:projeto";
	}
	
	@PutMapping
	@Transactional
	public String atualiza(DadosAtualizaProjeto dados) {
		repository.save(relacionaProjeto(dados.id(),dados.nome(),dados.descricao(),dados.alunos(),dados.professor(),dados.area()));
		return "redirect:projeto";
	}
	
	@DeleteMapping
	@Transactional
	public String deleta(Long id) {
		repository.deleteById(id);
		return "redirect:projeto";
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
		professor = profRepo.getById(professor.getId());
		List<Long> ids = new ArrayList<Long>();
		for(Aluno aluno: alunos) {
			ids.add(aluno.getId());
		}
		alunos = alunoRepo.getAllById(ids);
		area = areaRepo.getById(area.getId());
		projeto.setProfessor(professor);
		projeto.setAlunos(alunos);
		projeto.setArea(area);
		return projeto;
	}
	
}
