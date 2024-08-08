package com.MFF.OrganizadorTCC.Util;

import java.nio.charset.Charset;
import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GeradorDeSenha {
	public String gerarSenha() {
		byte[] array = new byte[12];
	    Random random = new Random();
	    for(int i = 0;i<12;i++) {
	    	int valor = random.nextInt(48,123);
	    	if((valor>=58 && valor<=64)||(valor>=91 && valor<=96)) {
	    		valor += 7;
	    	}
	    	array[i]=(byte) valor;
	    }
		String senha = new String(array, Charset.forName("UTF-8"));
		return senha;
	}
}
