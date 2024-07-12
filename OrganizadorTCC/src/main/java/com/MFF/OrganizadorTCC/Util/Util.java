package com.MFF.OrganizadorTCC.Util;

import java.nio.charset.Charset;
import java.util.Random;

public class Util {
	
	private static String[] cursos = {"Análise e Desenvolvimento de Sistemas", "Comércio Exterior", "Desenvolvimento de produtos plásticos",
			"Gestão Empresarial", "Gestão de Recursos Humanos", "Logística", "Polímeros"};
	
	public static String[] getCursos() {
		return cursos;
	}
	
	public static String senhaAleatoria() {
		byte[] array = new byte[12];
	    Random random = new Random();
	    for(int i = 0;i<12;i++) {
	    	array[i]=(byte) random.nextInt(48,127);
	    }
		String senha = new String(array, Charset.forName("UTF-8"));
		return senha;
	}
}
