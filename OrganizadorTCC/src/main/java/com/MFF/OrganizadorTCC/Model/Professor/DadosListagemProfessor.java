package com.MFF.OrganizadorTCC.Model.Professor;

import java.util.List;

import com.MFF.OrganizadorTCC.Model.Area.Area;

public record DadosListagemProfessor(
		Long id,
		Long RM,
		Long CPF,
		String email,
		String nome,
		String curso,
		List<Area> areas) {
	public DadosListagemProfessor(Professor professor) {
		this(professor.getId(),
				professor.getRM(),
				professor.getCPF(),
				professor.getEmail(),
				professor.getNome(), 
				professor.getCurso(),
				professor.getAreas());
	}
}
