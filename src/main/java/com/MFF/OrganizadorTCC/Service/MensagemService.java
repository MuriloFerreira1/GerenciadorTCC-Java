package com.MFF.OrganizadorTCC.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MFF.OrganizadorTCC.Model.Mensagem.Mensagem;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Repository.MensagemRepository;

@Service
public class MensagemService{
	
	@Autowired
	private MensagemRepository repository;
	
	public void SalvarMensagem(Mensagem mensagem) {
		repository.save(mensagem);
	}
	
	public List<Mensagem> getMensagens(Projeto projeto){
		return repository.findByProjeto(projeto, Sort.by("horario").descending());
	}
	
}
