CREATE TABLE mensagens (
	id BigInt NOT NULL auto_increment,
	texto VarChar(255) NOT NULL,
	horario DateTime NOT NULL,
	aluno_id BigInt NULL,
	professor_id BigInt NULL,
	projeto_id BigInt NOT NULL,
	PRIMARY KEY(id)
);

CREATE TABLE mensagens_arquivos(
	mensagem_id BigInt NOT NULL,
	id BigInt NOT NULL auto_increment,
	arquivo BLOB NOT NULL,
	PRIMARY KEY(id,mensagem_id)
);

ALTER TABLE mensagens_arquivos
	add constraint FK_Mensagem_arquivo
	FOREIGN KEY(mensagem_id) REFERENCES Mensagens(id);

ALTER TABLE mensagens
	add constraint FK_Mensagem_professor
	FOREIGN KEY(professor_id) REFERENCES professores(id);

ALTER TABLE mensagens
	add constraint FK_Mensagem_aluno
	FOREIGN KEY(aluno_id) REFERENCES alunos(id);

ALTER TABLE mensagens
	add constraint FK_Mensagem_projeto
	FOREIGN KEY(projeto_id) REFERENCES projetos(id);