package com.MFF.OrganizadorTCC.Security;

import java.util.LinkedList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import com.MFF.OrganizadorTCC.Aluno.Aluno;
import com.MFF.OrganizadorTCC.Aluno.AlunoService;
import com.MFF.OrganizadorTCC.Professor.Professor;
import com.MFF.OrganizadorTCC.Professor.ProfessorService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	 
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private AlunoService alunoService;
	
	@Autowired 
	private ProfessorService professorService;
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
			    auth.jdbcAuthentication()
			      .dataSource(dataSource);
			}
	
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests((authorize) -> 
			authorize.requestMatchers("/login/**","/css/**", "/aluno/arquivo", "/professor/**").permitAll().anyRequest().authenticated()
		);
		http.csrf((csrf) -> 
			csrf.disable()
		);
		return http.build();
	}
	
	@Bean
	public AuthenticationManager authenticationManager(
			UserDetailsService userDetailsService,
			PasswordEncoder passwordEncoder) {
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setUserDetailsService(userDetailsService);
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		
		ProviderManager providerManager = new ProviderManager(authenticationProvider);
		providerManager.setEraseCredentialsAfterAuthentication(false);
		
		return providerManager;
	}
	
	@Bean
	public UserDetailsService userDetailsService() {
		List<UserDetails> usuarios = new LinkedList<UserDetails>();
		List<Aluno> alunos = alunoService.getAll();
		List<Professor> professores = professorService.getAll();
		
		usuarios.addAll(professores);
		usuarios.addAll(alunos);
		
		InMemoryUserDetailsManager memoria = new InMemoryUserDetailsManager(usuarios);
		
		return memoria;
	}
	
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
}
