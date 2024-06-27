package com.MFF.OrganizadorTCC.Aluno;

public record DadosCadastroAluno(
		Long RM,
		Long CPF,
		String email,
		String nome,
		String turma,
		String curso) {

}
