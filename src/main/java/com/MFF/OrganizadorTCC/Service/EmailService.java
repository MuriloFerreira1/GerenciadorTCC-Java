package com.MFF.OrganizadorTCC.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService{
	
	public enum Opcao{
		ENCAMINHAR_SENHA(0);
		
		private int valor=0;
		
		private Opcao(int valor) {
			this.valor=valor;
		}
	}
	
	@Autowired
	private JavaMailSender sender;
	
	public void enviarEmail(Opcao opcao, String email, String nome, String senha) {
		MimeMessage mensagem = null;
		
		switch(opcao.valor) {
			case 0:
				mensagem = encaminharSenha(email, nome, senha);
				break;
			default:
				System.err.println("Opção de e-mail não suportada");
		}
		
		sender.send(mensagem);
	}

	private MimeMessage encaminharSenha(String email, String nome, String senha) {
		MimeMessage mensagem = sender.createMimeMessage();
		String corpo = String.format(
				"""
				<h1>Olá %s!</h1>
				<p>sua senha é: %s</p>
				""", nome, senha);
		try {
			mensagem.setRecipients(MimeMessage.RecipientType.TO,email);
			mensagem.setFrom("<teste@localhost.com>'Gerenciador TCC'");
			mensagem.setSubject("Senha do Sistema de gerenciamento de TCC");
			mensagem.setContent(corpo, "text/html; charset=utf-8");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return mensagem;
	}
	
}
