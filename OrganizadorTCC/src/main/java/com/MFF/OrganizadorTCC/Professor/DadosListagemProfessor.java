package com.MFF.OrganizadorTCC.Professor;

public record DadosListagemProfessor(
		Long id,
		String nome,
		String curso) {
	public DadosListagemProfessor(Professor professor) {
		this(professor.getRM(), 
				professor.getNome(), 
				professor.getCurso());
	}
}
