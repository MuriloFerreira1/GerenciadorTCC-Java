package com.MFF.OrganizadorTCC.Area;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "area")
@Table(name = "areas")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Area {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String nome;
	private String descricao;
	/*
	@ManyToMany
	@JoinColumns(foreignKey = )
	private List<AreaProfessor> professores;
	*/
	
	public Area (DadosCadastroArea dados) {
		this.nome = dados.nome();
		this.descricao = dados.descricao();
	}
	
	public void atualizaArea(DadosAtualizaArea dados) {
		this.id = dados.id();
		this.nome = dados.nome();
		this.descricao = dados.descricao();
	}
}
