package com.MFF.OrganizadorTCC.Model.Aluno;

public record DadosListagemAluno(
		Long id,
		Long RM,
		Long CPF,
		String email,
		String nome,
		String turma,
		String curso) {
	public DadosListagemAluno(Aluno aluno) {
		this(aluno.getId(),
			aluno.getRM(),
			aluno.getCPF(),
			aluno.getEmail(),
			aluno.getNome(),
			aluno.getTurma(),
			aluno.getCurso()
		);
	}

}
