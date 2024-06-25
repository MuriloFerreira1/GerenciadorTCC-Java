package com.MFF.OrganizadorTCC.Projeto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import com.MFF.OrganizadorTCC.Professor.Professor;
import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Area.Area;

public record DadosAtualizaProjeto(
		@NotNull
		Long id,
		String nome,
		String descricao,
		List<Aluno> alunos,
		Professor professor,
		Area area) {

}
