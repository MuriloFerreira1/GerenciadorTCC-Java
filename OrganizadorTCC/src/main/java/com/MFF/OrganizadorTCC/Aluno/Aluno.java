package com.MFF.OrganizadorTCC.Aluno;

import com.MFF.OrganizadorTCC.Projeto.Projeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "aluno")
@Table(name = "alunos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Aluno {
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "RM")
	private long RM;
	private String nome;
	private String turma;
	private String curso;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	public Aluno(DadosCadastroAluno dados) {
		this.RM = dados.RM();
		this.nome = dados.nome();
		this.turma = dados.turma();
		this.curso = dados.curso();
	}
	
	public void AtualizarAluno(DadosAtualizaAluno dados) {
		this.RM = dados.RM();
		this.nome = dados.nome();
		this.turma = dados.turma();
		this.curso = dados.curso();
	}
}
