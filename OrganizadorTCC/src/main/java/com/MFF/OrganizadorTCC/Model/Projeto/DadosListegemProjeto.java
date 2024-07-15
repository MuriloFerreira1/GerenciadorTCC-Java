package com.MFF.OrganizadorTCC.Model.Projeto;

import java.util.List;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;

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
