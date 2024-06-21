package com.MFF.OrganizadorTCC.Aluno;

public record DadosListagemAluno(
		Long id,
		Long RM,
		String nome,
		String turma,
		String curso) {
	public DadosListagemAluno(Aluno aluno) {
		this(aluno.getId(),
			aluno.getRM(),
			aluno.getNome(),
			aluno.getTurma(),
			aluno.getCurso()
		);
	}

}
