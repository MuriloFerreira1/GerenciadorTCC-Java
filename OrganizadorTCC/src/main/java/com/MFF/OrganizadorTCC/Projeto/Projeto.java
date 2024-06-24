package com.MFF.OrganizadorTCC.Projeto;

import java.util.List;

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Professor.Professor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name="projeto")
@Table(name="projetos")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Projeto {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	
	private String nome;
	private String descricao;
	
	@OneToMany
	private List<Aluno> alunos;
	//
	@OneToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	@OneToOne
	@JoinColumn(name = "area_id")
	private Area area;
	
	public Projeto(DadosCadastroProjeto proj) {
		this.nome = proj.nome();
		this.descricao = proj.descricao();
	}
	
	public void AtualizaProjeto(DadosAtualizaProjeto proj) {
		this.nome = proj.nome();
		this.descricao = proj.descricao();
	}
}
