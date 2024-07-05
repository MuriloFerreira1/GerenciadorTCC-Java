package com.MFF.OrganizadorTCC.Controller;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class LoginController {
	
	private final AuthenticationManager authenticationManager;
	
	public LoginController(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}
	
	@PostMapping
	public Authentication login(@RequestBody LoginRequest loginRequest) throws Exception{
		Authentication authenticationRequest = 
				UsernamePasswordAuthenticationToken.unauthenticated(loginRequest.email, loginRequest.senha);
		Authentication authenticationResponse = 
				this.authenticationManager.authenticate(authenticationRequest);
		SecurityContext security = SecurityContextHolder.getContext();
		security.setAuthentication(authenticationResponse);
		
		return security.getAuthentication();
	}
	
	public record LoginRequest(String email, String senha) {}
}
