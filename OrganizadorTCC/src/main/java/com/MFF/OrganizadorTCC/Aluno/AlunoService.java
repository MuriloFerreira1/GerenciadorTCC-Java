package com.MFF.OrganizadorTCC.Aluno;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AlunoService {
	@Autowired
	private AlunoRepository repository;
	
	public List<Aluno> getAll(){
		return repository.findAll(Sort.by("nome").ascending());
	}
	
	public List<Aluno> getAllById(List<Long> ids){
		return repository.findAllById(ids);
	}
	
	public Aluno getById(long RM) {
		return repository.getReferenceById(RM);
	}
}
