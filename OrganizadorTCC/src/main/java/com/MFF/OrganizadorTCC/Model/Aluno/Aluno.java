package com.MFF.OrganizadorTCC.Model.Aluno;

import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Model.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
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
public class Aluno extends User{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private long RM;
	private long CPF;
	private String nome;
	private String turma;
	private String curso;
	
	@OneToOne
	private Projeto projeto;
	
	public Aluno(DadosCadastroAluno dados) {
		this.RM = dados.RM();
		this.CPF = dados.CPF();
		setEmail(dados.email());
		this.nome = dados.nome();
		this.turma = dados.turma();
		this.curso = dados.curso();
	}
	
	public void AtualizarAluno(DadosAtualizaAluno dados) {
		this.id = dados.id();
		this.RM = dados.RM();
		this.CPF = dados.CPF();
		setEmail(dados.email());
		this.nome = dados.nome();
		this.turma = dados.turma();
		this.curso = dados.curso();
	}

}
