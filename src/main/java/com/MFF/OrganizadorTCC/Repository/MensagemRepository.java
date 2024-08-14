package com.MFF.OrganizadorTCC.Repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.MFF.OrganizadorTCC.Model.Mensagem.Mensagem;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Long>{
	
	public List<Mensagem> findByProjeto(Projeto projeto, Sort sort);
}
