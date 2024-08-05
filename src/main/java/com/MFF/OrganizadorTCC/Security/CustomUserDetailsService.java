package com.MFF.OrganizadorTCC.Security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.MFF.OrganizadorTCC.Model.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Model.Aluno.AlunoRepository;
import com.MFF.OrganizadorTCC.Model.Professor.Professor;
import com.MFF.OrganizadorTCC.Model.Professor.ProfessorRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService{
	
	@Autowired
	private AlunoRepository alunoRepo;
	
	@Autowired
	private ProfessorRepository professorRepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Aluno> aluno = alunoRepo.findByEmail(username);
		if (aluno.isPresent()) {
			return new User(aluno.get().getUsername(), aluno.get().getPassword(), aluno.get().getAuthorities());
		}
		Optional<Professor> professor = professorRepo.findByEmail(username);
		if (professor.isPresent()) {
			return new User(professor.get().getUsername(),professor.get().getPassword(), professor.get().getAuthorities());
		}
		
		throw new UsernameNotFoundException(username);
	}


}
