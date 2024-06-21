create table if not exists area_professor(
	professor_id bigint not null,
	area_id bigint not null,
	primary key(professor_id,area_id)
);

alter table area_professor
	add constraint FK_Professor 
	foreign key(professor_id) references professores(id);
	
alter table area_professor
	add constraint FK_Area 
	foreign key(area_id) references areas(id);