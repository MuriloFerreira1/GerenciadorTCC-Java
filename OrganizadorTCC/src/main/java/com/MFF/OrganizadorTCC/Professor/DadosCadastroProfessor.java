package com.MFF.OrganizadorTCC.Professor;

import java.util.List;

import com.MFF.OrganizadorTCC.Area.Area;

public record DadosCadastroProfessor(
		Long RM,
		String nome,
		String curso,
		List<Area> areas) {

}
