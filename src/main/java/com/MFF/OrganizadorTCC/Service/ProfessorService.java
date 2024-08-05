package com.MFF.OrganizadorTCC.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Repository.ProfessorRepository;

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
	
	public Professor getByEmail(String email) {
		Optional<Professor> professor = repository.findByEmail(email);
		if(professor.isPresent()) {
			return professor.get();
		}
		return new Professor();
	}

}
