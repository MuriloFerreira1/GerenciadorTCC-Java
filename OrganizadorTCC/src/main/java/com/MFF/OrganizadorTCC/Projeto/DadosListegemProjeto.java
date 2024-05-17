package com.MFF.OrganizadorTCC.Projeto;

public record DadosListegemProjeto(
		Long id,
		String nome,
		String descricao) {
	public DadosListegemProjeto(Projeto proj) {
		this(proj.getId(),
		proj.getNome(),
		proj.getDescricao());
	}

}
