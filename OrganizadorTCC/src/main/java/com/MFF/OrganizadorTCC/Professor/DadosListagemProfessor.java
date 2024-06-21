package com.MFF.OrganizadorTCC.Professor;

public record DadosListagemProfessor(
		Long id,
		Long RM,
		String nome,
		String curso) {
	public DadosListagemProfessor(Professor professor) {
		this(professor.getId(),
				professor.getRM(), 
				professor.getNome(), 
				professor.getCurso());
	}
}
