package com.MFF.OrganizadorTCC.Professor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaProfessor(
		@NotNull
		Long id,
		Long RM,
		String nome,
		String curso) {

}
