package com.MFF.OrganizadorTCC.Model.Projeto;

import java.util.List;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;

public record DadosCadastroProjeto(
		String nome,
		String descricao,
		List<Aluno> alunos,
		Professor professor,
		Area area) {

}
