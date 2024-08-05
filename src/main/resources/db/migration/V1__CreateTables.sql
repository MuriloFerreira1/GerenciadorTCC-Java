create table if not exists areas(
	id bigint not null auto_increment,
	nome varchar(127),
	descricao varchar(255),
	primary key(id)
);

create table if not exists professores(
	id bigint not null auto_increment,
	RM bigint not null,
	CPF bigint not null,
	email varchar(255),
	senha varchar(255),
	nome varchar(40),
	curso varchar(40),
	organizador smallint(1),
	primary key(id)
);

create table if not exists alunos(
	id bigint not null auto_increment,
	RM bigint not null,
	CPF bigint not null,
	email varchar(255),
	senha varchar(255),
	projeto_id bigint,
	nome varchar(40),
	curso varchar(40),
	turma varchar(10),
	primary key(id)
);

create table if not exists projetos(
	id bigint not null auto_increment,
	professor_id bigint,
	area_id bigint,
	nome varchar(127),
	descricao varchar(255),
	primary key(id)
);

alter table Alunos
	add constraint FK_Aluno_projeto 
	foreign key(projeto_id) references projetos(id);

alter table projetos
	add constraint FK_Projeto_Professor 
	foreign key (professor_id) references professores(id);
	
alter table projetos 
	add constraint FK_Projeto_Area
	foreign key (area_id) references areas(id);





