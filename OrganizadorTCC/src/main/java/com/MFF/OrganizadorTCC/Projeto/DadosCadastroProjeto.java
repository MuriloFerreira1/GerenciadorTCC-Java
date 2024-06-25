package com.MFF.OrganizadorTCC.Projeto;

import java.util.List;

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Professor.Professor;

public record DadosCadastroProjeto(
		String nome,
		String descricao,
		List<Aluno> alunos,
		Professor professor,
		Area area) {

}
