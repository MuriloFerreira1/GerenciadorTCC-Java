package com.MFF.OrganizadorTCC.Model.Professor;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.MFF.OrganizadorTCC.Model.Area.Area;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;
import com.MFF.OrganizadorTCC.Model.User.User;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
public class Professor extends User{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private long id;
	private long RM;
	private long CPF;
	private String nome;
	private String curso;	
	private boolean organizador;
	
	@OneToMany(mappedBy = "professor")
	private List<Projeto> projetos;
	
	@ManyToMany
	@JoinTable(
			name = "area_professor",
			joinColumns= 
				@JoinColumn(name = "Professor_id", referencedColumnName = "id"),
			inverseJoinColumns= 
				@JoinColumn(name = "Area_id", referencedColumnName = "id")
			)
	private List<Area> areas;
	
	public Professor(DadosCadastroProfessor dados) {
		this.RM = dados.RM();
		this.CPF = dados.CPF();
		setEmail(dados.email());
		this.nome = dados.nome();
		this.curso = dados.curso();
	}
	
	public void atualizaProfessor(DadosAtualizaProfessor dados) {
		this.id = dados.id();
		this.nome = dados.nome();
		this.CPF = dados.CPF();
		setEmail(dados.email());
		this.curso = dados.curso();
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority("ROLE_PROFESSOR"));
		if (organizador) {
			authorities.add(new SimpleGrantedAuthority("ROLE_ADMINISTRADOR"));
		}
		return authorities;
	}
	
}
