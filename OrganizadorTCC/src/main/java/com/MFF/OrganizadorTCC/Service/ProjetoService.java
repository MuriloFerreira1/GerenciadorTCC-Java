package com.MFF.OrganizadorTCC.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Model.Projeto.ProjetoRepository;

public class ProjetoService {
	
	@Autowired
	private ProjetoRepository repository;
	
	public List<Projeto> getAll(){
		return repository.findAll(Sort.by("nome").ascending());
	}
	
	public Projeto getById(long id) {
		return repository.getReferenceById(null);
	}

}
