package com.MFF.OrganizadorTCC.Area;

import java.util.List;

import com.MFF.OrganizadorTCC.Professor.Professor;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
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
@Embeddable
public class Area {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private String nome;
	private String descricao;
	
	@ManyToMany
	@JoinTable(
			name = "area_professor",
			joinColumns= {
				@JoinColumn(name = "Area_id")},
			inverseJoinColumns= {
				@JoinColumn(name = "Professor_id")}
			)
	private List<Professor> professores;
	
	
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
