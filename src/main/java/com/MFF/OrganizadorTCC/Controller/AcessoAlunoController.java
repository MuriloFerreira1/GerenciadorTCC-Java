package com.MFF.OrganizadorTCC.Controller;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Mensagem.Mensagem;
import com.MFF.OrganizadorTCC.Service.AlunoService;

@Controller
@RequestMapping("/aluno")
public class AcessoAlunoController {
	
	@Autowired
	private AlunoService alunoService;
	
	@GetMapping
	public String carregaPaginaInicioAluno(Model model) {
		Aluno aluno = alunoService.getByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Mensagem> mensagens = aluno.getProjeto().getMensagens();
		Collections.reverse(mensagens);
		aluno.getProjeto().setMensagens(mensagens);
		model.addAttribute("aluno",aluno);
		return "/aluno/index";
	}
}
