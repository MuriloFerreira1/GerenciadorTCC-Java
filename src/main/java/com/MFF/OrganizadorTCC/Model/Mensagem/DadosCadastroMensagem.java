package com.MFF.OrganizadorTCC.Model.Mensagem;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Projeto.Projeto;

public record DadosCadastroMensagem(
	String texto,
	Aluno aluno,
	Professor professor,
	Projeto projeto
		) {
}
