package com.MFF.OrganizadorTCC.Projeto;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

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
