package com.MFF.OrganizadorTCC.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MFF.OrganizadorTCC.Model.Professor.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long>{
	
	Optional<Professor> findByEmail(String email); 
}
