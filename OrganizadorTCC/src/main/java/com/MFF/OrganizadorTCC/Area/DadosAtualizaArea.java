package com.MFF.OrganizadorTCC.Area;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaArea(
		@NotNull
		Long id,
		String nome,
		String descricao) {

}
