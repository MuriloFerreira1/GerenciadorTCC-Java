package com.MFF.OrganizadorTCC.Model.Aluno;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaAluno(
		@NotNull
		Long id,
		Long RM,
		Long CPF,
		String email,
		String nome,
		String turma,
		String curso) {

}
