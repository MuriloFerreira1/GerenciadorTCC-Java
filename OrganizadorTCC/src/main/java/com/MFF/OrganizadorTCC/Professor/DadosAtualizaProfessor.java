package com.MFF.OrganizadorTCC.Professor;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaProfessor(
		@NotNull
		Long id,
		String nome,
		String curso) {

}
