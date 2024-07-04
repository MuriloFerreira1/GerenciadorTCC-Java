package com.MFF.OrganizadorTCC.Controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
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
	
	@GetMapping
	public String carregaPaginaLogon() {
		return "/login/index";
	}
	
	@PostMapping
	public ResponseEntity<Void> login(@RequestBody LoginRequest loginRequest){
		loginRequest.email();
		loginRequest.senha();
		return new ResponseEntity<Void>(null);
	}
	
	public record LoginRequest(String email, String senha) {}
}
