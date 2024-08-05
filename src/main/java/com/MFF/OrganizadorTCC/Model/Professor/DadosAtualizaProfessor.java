package com.MFF.OrganizadorTCC.Model.Professor;

import java.util.List;

import com.MFF.OrganizadorTCC.Model.Area.Area;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaProfessor(
		@NotNull
		Long id,
		Long RM,
		Long CPF,
		String email,
		String nome,
		String curso,
		List<Area> areas) {

}
