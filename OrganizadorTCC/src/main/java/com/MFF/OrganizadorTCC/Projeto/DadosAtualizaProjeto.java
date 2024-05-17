package com.MFF.OrganizadorTCC.Projeto;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaProjeto(
		@NotNull
		Long id,
		String nome,
		String descricao) {

}
