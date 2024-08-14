package com.MFF.OrganizadorTCC.Model.Mensagem;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "menagem")
@Table(name = "mensagens")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Mensagem {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id")
	private long id;
	
	@OneToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	@OneToOne
	@JoinColumn(name = "aluno_id")
	private Aluno aluno;
	
	@OneToOne
	@JoinColumn(name = "professor_id")
	private Professor professor;
	
	private String texto;
	
	private List<MultipartFile> arquivos;
	
	private LocalDateTime horario;
	
	public Mensagem(DadosCadastroMensagem dados) {
		this.texto = dados.texto();
		this.arquivos = dados.arquivos();
		this.horario = LocalDateTime.now();
		this.aluno = dados.aluno();
		this.professor = dados.professor();
		this.projeto = dados.projeto();
	}
}
