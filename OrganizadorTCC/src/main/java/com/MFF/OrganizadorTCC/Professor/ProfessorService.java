package com.MFF.OrganizadorTCC.Professor;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ProfessorService {
	@Autowired
	private ProfessorRepository repository;
	
	public List<Professor> getAll(){
		return repository.findAll(Sort.by("nome").ascending());
	}
	
	public Professor getById(long id) {
		return repository.getReferenceById(id);
	}

}
