package com.javaplenomarcosabreu.auxiliar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import com.javaplenomarcosabreu.model.Departamento;

@Component
public class StringToDepartamentoConverter implements Converter<String, Departamento> {

	@Autowired
	Departamento departamento;

	@Override
	public Departamento convert(String str) {
		try {

			Object conv = str;

			departamento = (Departamento) conv;

		} catch (NumberFormatException e) {
			e.printStackTrace();
		}

		return departamento;
	}

}
