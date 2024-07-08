package com.MFF.OrganizadorTCC.Controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Aluno.AlunoRepository;
import com.MFF.OrganizadorTCC.Aluno.DadosAtualizaAluno;
import com.MFF.OrganizadorTCC.Aluno.DadosCadastroAluno;
import com.MFF.OrganizadorTCC.Util.Cursos;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@Controller
@RequestMapping("/aluno")
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
		model.addAttribute("cursos", Cursos.getCursos());
		return "/aluno/formulario";
	}
	
	@PostMapping
	@Transactional
	public String cadastrar(@Valid DadosCadastroAluno dados) {
		repository.save(new Aluno(dados));
		return "redirect:aluno";
	}
	
	@PutMapping
	@Transactional
	public String atualizar(@Valid DadosAtualizaAluno dados) {
		var aluno = repository.getReferenceById(dados.id());
		aluno.AtualizarAluno(dados);
		return "redirect:aluno";
	}
	
	@DeleteMapping
	@Transactional
	public String deletar(@Valid Long id) {
		repository.deleteById(id);
		return "redirect:aluno";
	}
	
	
	@GetMapping("/arquivo")
	public String carregaPaginaCadastroPorAquivo() {
		return "/aluno/arquivo";
	}
	
	@PostMapping("/arquivo")
	@Transactional
	public String CadastroPorAquivo(@RequestParam("arquivo") MultipartFile arquivo) throws Exception{
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
			a.setSenha(Cursos.senhaAleatoria());
			alunos.add(a);
			linha = reader.readLine();
		}
		reader.close();
		repository.saveAll(alunos);
		return "redirect:aluno";
	}
}
