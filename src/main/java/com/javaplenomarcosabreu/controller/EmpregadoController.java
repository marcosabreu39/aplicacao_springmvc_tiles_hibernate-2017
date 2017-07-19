package com.javaplenomarcosabreu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaplenomarcosabreu.auxiliar.Auxiliar;
import com.javaplenomarcosabreu.dao.DepartamentoDao;
import com.javaplenomarcosabreu.model.Empregado;

@Controller
public class EmpregadoController {

	@Autowired
	Auxiliar auxiliar;

	DepartamentoDao depDao = new DepartamentoDao();

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro(Empregado empregado) {

		ModelAndView pagina = new ModelAndView("cadastro");

		try {
			pagina.addObject("mensagem", "Página de Cadastro");

			empregado.setDepartamentos(depDao.obterDepartamentos());

			/* pagina.addObject("empregado", empregado); */

			pagina.addObject("pagina", "cadastro");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Empregado empregado, BindingResult result) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("cadastro");

				pagina.addObject("pagina", "cadastro");

				pagina.addObject("mensagem", "Erro ao se tentar cadastrar o empregado");

				empregado.setDepartamentos(depDao.obterDepartamentos());

			} else if (empregado.getCodDepartamento() == null || empregado.getCodDepartamento().equals("")) {

				result.rejectValue("codDepartamento", "error.codDepartamento", "O departamento do empregado deve ser selecionado");
				
				empregado.setDepartamentos(depDao.obterDepartamentos());
				
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
