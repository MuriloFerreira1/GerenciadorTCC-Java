package com.MFF.OrganizadorTCC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.MFF.OrganizadorTCC.Model.Mensagem.DadosCadastroMensagem;
import com.MFF.OrganizadorTCC.Model.Mensagem.Mensagem;
import com.MFF.OrganizadorTCC.Repository.MensagemRepository;

import jakarta.transaction.Transactional;

@Controller
@RequestMapping("/mensagem")
public class MensagemController {
	
	@Autowired
	private MensagemRepository repository;
	
	@PostMapping
	@Transactional
	public String salvarMensagem(DadosCadastroMensagem dados) {
		repository.save(new Mensagem(dados));
		return "redirect:";
	}
}
