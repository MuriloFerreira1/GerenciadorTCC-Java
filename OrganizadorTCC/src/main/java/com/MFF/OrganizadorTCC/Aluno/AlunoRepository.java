package com.MFF.OrganizadorTCC.Aluno;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Optional<Aluno> findByEmail(String email);
}
