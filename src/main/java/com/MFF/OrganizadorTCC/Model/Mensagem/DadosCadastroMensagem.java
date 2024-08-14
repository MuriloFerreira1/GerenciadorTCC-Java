package com.MFF.OrganizadorTCC.Model.Mensagem;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;

public record DadosCadastroMensagem(
	String texto,
	List<MultipartFile> arquivos,
	Aluno aluno,
	Professor professor,
	Projeto projeto
		) {
}
