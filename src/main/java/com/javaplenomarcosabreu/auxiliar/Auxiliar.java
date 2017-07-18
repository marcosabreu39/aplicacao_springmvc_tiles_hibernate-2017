package com.javaplenomarcosabreu.auxiliar;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.stereotype.Component;

@Component
public class Auxiliar {
	
	public Date gerarDataEhoraAtual() throws Exception {
		Date data = new Date(System.currentTimeMillis());
		Locale local = new Locale("pt", "BR");
		SimpleDateFormat fmt = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss", local);
		String dataEhoraCadastroSTR = fmt.format(data);
		Date dataEhoraAtual = null;
		try {
			dataEhoraAtual = (Date) fmt.parse(dataEhoraCadastroSTR);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return dataEhoraAtual;
	}
	
	
	/*public String checarAtributo(String nomeAtributo, String atributo) {
		String msgAtributo = null;
		
		try {
			msgAtributo = usuarioDao.searchByAttribute(nomeAtributo, atributo)
					? "Esse " + nomeAtributo + " já está cadastrado." : "";
		} catch (Exception e) {
			// TODO: handle exception
		}

		return msgAtributo;

	}*/

}
