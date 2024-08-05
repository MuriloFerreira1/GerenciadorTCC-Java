package com.MFF.OrganizadorTCC.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;

@Repository
public interface ProjetoRepository extends JpaRepository<Projeto, Long>{
	
}
