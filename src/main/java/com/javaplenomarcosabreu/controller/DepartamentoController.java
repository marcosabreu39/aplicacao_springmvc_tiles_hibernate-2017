package com.javaplenomarcosabreu.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.javaplenomarcosabreu.dao.Dao;
import com.javaplenomarcosabreu.dao.DepartamentoDao;
import com.javaplenomarcosabreu.model.Departamento;

@Controller
public class DepartamentoController {

	@Autowired
	Dao<Departamento> departamentoDao;

	@Autowired
	DepartamentoDao depDao;

	@RequestMapping(value = "/criar-departamento", method = RequestMethod.GET)
	public ModelAndView criarDepartamento(Departamento departamento) {

		try {
			departamento.setDepartamentos(depDao.obterDepartamentos());

		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		ModelAndView pagina = new ModelAndView("criar-departamento");

		try {

			pagina.addObject("mensagem", "Página de controle de Departamentos");

			pagina.addObject("pagina", "departamento");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;
	}

	@RequestMapping(value = "/criar-departamento", method = RequestMethod.POST)
	public ModelAndView departamentoCriado(@Valid Departamento departamento, BindingResult result) {

		ModelAndView pagina = new ModelAndView("criar-departamento");

		String mensagem = "";

		try {
			if (result.hasErrors()) {

				mensagem = "Ocorreu um erro ao se tentar inserir o Departamento";

				pagina.addObject("mensagem", mensagem);

				pagina.addObject("pagina", "departamento");

			} else if (depDao.checarDepartamento(departamento.getNomeDepartamento())) {

				result.rejectValue("nomeDepartamento", "error.departamento", "Esse Departamento já existe");

				mensagem = "Ocorreu um erro na criação do Departamento";

				pagina.addObject("mensagem", mensagem);
								
				departamento.setDepartamentos(depDao.obterDepartamentos());

				pagina.addObject("departamento", departamento);

			} else {

				departamentoDao.persist(departamento);

				mensagem = "O Departamento " + departamento.getNomeDepartamento() + " foi criado com sucesso";

				departamento = new Departamento();

				departamento.setDepartamentos(depDao.obterDepartamentos());

				pagina.addObject("departamento", departamento);

				pagina.addObject("mensagem", mensagem);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagina;
	}
}
