package com.MFF.OrganizadorTCC.Model.Area;

import jakarta.validation.constraints.NotNull;

public record DadosAtualizaArea(
		@NotNull
		Long id,
		String nome,
		String descricao) {

}
