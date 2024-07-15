package com.MFF.OrganizadorTCC.Model.Professor;

import java.util.List;

import com.MFF.OrganizadorTCC.Model.Area.Area;

public record DadosCadastroProfessor(
		Long RM,
		Long CPF,
		String email,
		String nome,
		String curso,
		List<Area> areas) {

}
