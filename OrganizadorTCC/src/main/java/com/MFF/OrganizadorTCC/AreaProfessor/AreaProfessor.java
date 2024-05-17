package com.MFF.OrganizadorTCC.AreaProfessor;

import com.MFF.OrganizadorTCC.Area.Area;
import com.MFF.OrganizadorTCC.Professor.Professor;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "area_professor")
@Table(name ="area_professor")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AreaProfessor {
	@JoinTable(
			name = "area_professor",
			joinColumns=
				@JoinColumn(name = "idProfessor", referencedColumnName = "id"),
			inverseJoinColumns=
				@JoinColumn(name = "idArea", referencedColumnName = "id")
			)
	@Id
	@OneToMany
	private Professor prof;
	
	@Id 
	@OneToMany
	private Area area;
	
	
}
