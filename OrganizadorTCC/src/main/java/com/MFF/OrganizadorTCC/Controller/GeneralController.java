package com.MFF.OrganizadorTCC.Controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;

@Controller
public class GeneralController implements ErrorController{
	
	@RequestMapping("/login") 
	public String carregaPaginaLogin() {
		return "/login";
	}
	
	@RequestMapping("/error")
	public String carregePaginaErro(Model model, HttpServletRequest request) {
		Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
		
		if (status!=null) {
			int codigo = Integer.valueOf(status.toString());
			if(codigo == HttpStatus.UNAUTHORIZED.value()) {
				model.addAttribute("codigo",codigo);
				model.addAttribute("texto", "Não Autorizado");
			}else if(codigo == HttpStatus.FORBIDDEN.value()){
				model.addAttribute("codigo",codigo);
				model.addAttribute("texto", "Proibido");
			}else if(codigo == HttpStatus.NOT_FOUND.value()) {
				model.addAttribute("codigo",codigo);
				model.addAttribute("texto", "Não encontrado");
			}else if(codigo == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
				model.addAttribute("codigo",codigo);
				model.addAttribute("texto", "Erro interno de Servidor");
			}
		}
		return "error";
	}
	
}
