package com.MFF.OrganizadorTCC.Model.Projeto;

import jakarta.validation.constraints.NotNull;
import java.util.List;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;

public record DadosAtualizaProjeto(
		@NotNull
		Long id,
		String nome,
		String descricao,
		List<Aluno> alunos,
		Professor professor,
		Area area,
		String comentario,
		Boolean aceito) {

}
