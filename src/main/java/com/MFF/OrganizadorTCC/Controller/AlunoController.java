package com.MFF.OrganizadorTCC.Controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

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

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Aluno.DadosAtualizaAluno;
import com.MFF.OrganizadorTCC.Model.Aluno.DadosCadastroAluno;
import com.MFF.OrganizadorTCC.Repository.AlunoRepository;
import com.MFF.OrganizadorTCC.Service.EmailService;
import com.MFF.OrganizadorTCC.Service.EmailService.Opcao;
import com.MFF.OrganizadorTCC.Util.GeradorDeSenha;
import com.MFF.OrganizadorTCC.Util.Cursos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/controleAluno")
public class AlunoController {
	
	@Autowired
	AlunoRepository repository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private GeradorDeSenha geradorDeSenha;
	
	@Autowired
	private EmailService emailService;
	
	@GetMapping
	public String carregaPaginaListagem(Model model){
		model.addAttribute("lista", repository.findAll(Sort.by("nome").ascending()));
		return "/controller/aluno/listagem";
	}
	
	@GetMapping("/formulario")
	public String carregaPaginaFormulario(Long id, Model model) {
		if (id!=null) {
			var Aluno = repository.getReferenceById(id);
			model.addAttribute("aluno", Aluno);
		}
		model.addAttribute("cursos", Cursos.getCursos());
		return "/controller/aluno/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastrar(@Valid DadosCadastroAluno dados) {
		Aluno aluno = new Aluno(dados);
		String senha = geradorDeSenha.gerarSenha();
		emailService.enviarEmail(Opcao.ENCAMINHAR_SENHA, aluno.getEmail(), aluno.getNome(), senha);
		aluno.setSenha(passwordEncoder.encode(senha));
		repository.save(aluno);
		return "redirect:controleAluno";
	}
	
	@PutMapping
	@Transactional
	public String atualizar(@Valid DadosAtualizaAluno dados) {
		var aluno = repository.getReferenceById(dados.id());
		aluno.AtualizarAluno(dados);
		return "redirect:controleAluno";
	}
	
	@DeleteMapping
	@Transactional
	public String deletar(@Valid Long id) {
		repository.deleteById(id);
		return "redirect:controleAluno";
	}
	
	
	@GetMapping("/arquivo")
	public String carregaPaginaCadastroPorAquivo() {
		return "/controller/aluno/arquivo";
	}
	
	@PostMapping("/arquivo")
	@Transactional
	public String cadastroPorAquivo(@RequestParam("arquivo") MultipartFile arquivo) throws Exception{
		BufferedReader reader = new BufferedReader(new InputStreamReader(arquivo.getInputStream()));
		String linha = reader.readLine();
		linha = reader.readLine();
		List<Aluno> alunos = new LinkedList<Aluno>();
		while(linha!=null) {
			String[] dados = linha.split(";");
			Aluno a = new Aluno();
			a.setRM(Integer.parseInt(dados[0]));
			a.setCPF(Long.parseLong(dados[1]));
			a.setEmail(dados[2]);
			a.setNome(dados[3]);
			a.setCurso(dados[4]);
			a.setSenha(passwordEncoder.encode(geradorDeSenha.gerarSenha()));
			alunos.add(a);
			linha = reader.readLine();
		}
		reader.close();
		repository.saveAll(alunos);
		return "redirect:controleAluno";
	}
}
