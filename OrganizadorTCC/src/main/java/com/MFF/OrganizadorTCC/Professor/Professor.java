package com.MFF.OrganizadorTCC.Professor;

import java.util.List;

import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Projeto.Projeto;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "professor")
@Table(name = "professores")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class Professor {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private long RM;
	private String nome;
	private String curso;
	
	@OneToMany
	private List<Projeto> projetos;
	
	public Professor(DadosCadastroProfessor dados) {
		this.nome = dados.nome();
		this.curso = dados.curso();
	}
	
	public void atualizaProfessor(DadosAtualizaProfessor dados) {
		
	}
	
}
