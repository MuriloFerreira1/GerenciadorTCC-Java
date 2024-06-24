package com.MFF.OrganizadorTCC.Professor;

import java.util.List;

import com.MFF.OrganizadorTCC.Area.Area;

public record DadosListagemProfessor(
		Long id,
		Long RM,
		String nome,
		String curso,
		List<Area> areas) {
	public DadosListagemProfessor(Professor professor) {
		this(professor.getId(),
				professor.getRM(), 
				professor.getNome(), 
				professor.getCurso(),
				professor.getAreas());
	}
}
