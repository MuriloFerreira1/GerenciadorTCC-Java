package com.MFF.OrganizadorTCC.Area;

public record DadosListagemArea(
		Long id,
		String nome,
		String descricao) {
	public DadosListagemArea(Area area) {
		this(area.getId(), 
			area.getNome(), 
			area.getDescricao());
	}
}
