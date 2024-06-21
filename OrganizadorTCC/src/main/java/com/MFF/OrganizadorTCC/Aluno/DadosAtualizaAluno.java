package com.MFF.OrganizadorTCC.Aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaAluno(
		@NotNull
		Long id,
		Long RM,
		String nome,
		String turma,
		String curso) {

}
