package com.MFF.OrganizadorTCC.Projeto;

import java.util.List;

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Professor.Professor;

public record DadosListegemProjeto(
		Long id,
		String nome,
		String descricao,
		List<Aluno> alunos,
		Professor professor,
		Area area) {
	public DadosListegemProjeto(Projeto proj) {
		this(proj.getId(),
		proj.getNome(),
		proj.getDescricao(),
		proj.getAlunos(),
		proj.getProfessor(),
		proj.getArea());
	}

}
