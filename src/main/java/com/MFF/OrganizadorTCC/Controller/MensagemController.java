package com.MFF.OrganizadorTCC.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MFF.OrganizadorTCC.Model.Mensagem.DadosCadastroMensagem;
import com.MFF.OrganizadorTCC.Model.Mensagem.Mensagem;
import com.MFF.OrganizadorTCC.Repository.MensagemRepository;

@RestController
@RequestMapping("/mesnagem")
public class MensagemController {
	
	@Autowired
	private MensagemRepository repository;
	
	@PostMapping
	public void salvarMensagem(DadosCadastroMensagem dados) {
		repository.save(new Mensagem(dados));
	}
}
