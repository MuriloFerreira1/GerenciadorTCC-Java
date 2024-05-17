package com.MFF.OrganizadorTCC.Aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

public class AlunoService {
	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> getAll(){
		return repository.findAll(Sort.by("nome").ascending());
	}
	
	public Aluno getById(long RM) {
		return repository.getReferenceById(RM);
	}
}
