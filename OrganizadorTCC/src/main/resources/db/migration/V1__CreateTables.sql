create table if not exists areas(
	id bigint not null auto_increment,
	nome varchar(127),
	descricao varchar(255),
	primary key(id)
);

create table if not exists professores(
	RM bigint not null,
	nome varchar(40),
	curso varchar(40),
	organizador smallint(1),
	primary key(RM)
);

create table if not exists alunos(
	RM bigint not null,
	projeto_id bigint,
	nome varchar(40),
	curso varchar(40),
	turma varchar(10),
	primary key(RM)
);

create table if not exists area_professor(
	RM_professor bigint not null,
	area_id bigint not null,
	primary key(RM_professor,area_id)
);

create table if not exists projetos(
	id bigint not null auto_increment,
	RM_professor bigint,
	area_id bigint,
	nome varchar(127),
	descricao varchar(255),
	primary key(id)
);

alter table Alunos
	add constraint FK_Aluno_projeto 
	foreign key(projeto_id) references projetos(id);
	
alter table area_professor
	add constraint FK_Professor 
	foreign key(RM_Professor) references professores(RM);
	
alter table area_professor
	add constraint FK_Area 
	foreign key(area_id) references areas(id);

alter table projetos
	add constraint FK_Projeto_Professor 
	foreign key (RM_professor) references professores(RM);
	
alter table projetos 
	add constraint FK_Projeto_Area
	foreign key (area_id) references areas(id);





