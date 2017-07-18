package com.javaplenomarcosabreu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaplenomarcosabreu.auxiliar.Auxiliar;
import com.javaplenomarcosabreu.model.Departamento;
import com.javaplenomarcosabreu.model.Empregado;

@Controller
public class EmpregadoController {

	@Autowired
	Auxiliar auxiliar;

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro(Empregado empregado, Departamento departamento, ModelMap modelMap) {

		ModelAndView pagina = new ModelAndView("cadastro");

		try {
			pagina.addObject("mensagem", "Página de Cadastro");

			pagina.addObject("empregado", empregado);

			pagina.addObject("departamento", departamento);
			
			pagina.addObject("pagina", "cadastro");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Empregado empregado, @Valid Departamento departamento, BindingResult result) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("cadastro");

				pagina.addObject("pagina", "cadastro");

				pagina.addObject("mensagem", "Erro ao se tentar cadastrar o empregado");

			} else {

				empregado.setDataCadastro(auxiliar.gerarDataEhoraAtual());
				
				
				
				pagina = new ModelAndView("sucesso");

				pagina.addObject("empregado", empregado);

				pagina.addObject("mensagem", "Cadastro realizado com sucesso");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;
	}
}
