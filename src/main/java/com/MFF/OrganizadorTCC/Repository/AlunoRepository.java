package com.MFF.OrganizadorTCC.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long>{
	
	Optional<Aluno> findByEmail(String email);
}
