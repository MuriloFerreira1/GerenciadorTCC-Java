package com.MFF.OrganizadorTCC.Aluno;

public record DadosListagemAluno(
		Long RM,
		String nome,
		String turma,
		String curso) {
	public DadosListagemAluno(Aluno aluno) {
		this(aluno.getRM(),
			aluno.getNome(),
			aluno.getTurma(),
			aluno.getCurso()
		);
	}

}
