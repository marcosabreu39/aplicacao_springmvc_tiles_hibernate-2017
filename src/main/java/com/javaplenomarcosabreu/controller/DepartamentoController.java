package com.javaplenomarcosabreu.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.javaplenomarcosabreu.auxiliar.Auxiliar;
import com.javaplenomarcosabreu.dao.Dao;
import com.javaplenomarcosabreu.dao.DepartamentoDao;
import com.javaplenomarcosabreu.dao.EmpregadoDao;
import com.javaplenomarcosabreu.model.Departamento;
import com.javaplenomarcosabreu.model.Empregado;

@Controller
public class DepartamentoController {

	@Autowired
	Dao<Departamento> departamentoDao;

	@Autowired
	DepartamentoDao depDao;

	@Autowired
	Auxiliar auxiliar;
	
	@Autowired
	EmpregadoDao empregadoDao;

	List<Departamento> departamentos;

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

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

	@RequestMapping(value = "/cadastro/selecionar-depto", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView escolherDepto(@ModelAttribute Departamento departamento, 
			@ModelAttribute Empregado empregado, HttpSession session) {

		ModelAndView pagina = null;

		try {
						
				session.setAttribute("empregadoSess", empregado);
			
				departamento.getEmpregados().add(empregado);

				pagina = new ModelAndView("selecionar-depto");

				pagina.addObject("empregado", empregado); 

				pagina.addObject("departamento", departamento);

				pagina.addObject("pagina", "cadastro");

				pagina.addObject("mensagem", "Selecione o departamento do novo empregado");

				departamentos = depDao.obterDepartamentos();

				pagina.addObject("departamentos", departamentos);			
			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/cadastro/selecionar-depto/concluir", method=RequestMethod.POST)
	public ModelAndView concluirCadastro(@Valid @ModelAttribute Departamento departamento, BindingResult result, 
			@ModelAttribute Empregado empregado, HttpSession session){
		
		ModelAndView pagina = null;
		
		try {
			
			if(result.hasErrors()){
				
				pagina = new ModelAndView("selecionar-dept");
				
				pagina.addObject("mensagem", "erro na Finalização do cadastro");
				
				pagina.addObject("pagina", "cadastro");				
				
			}  else {
			
			pagina = new ModelAndView("sucesso");	
				
			Object empregadoObj = session.getAttribute("empregadoSess");	
			
			empregado = (Empregado) empregadoObj;
			
			empregado.setCodDepartamento(departamento);
			
			empregado.setDataCadastro(auxiliar.gerarDataEhoraAtual());
			
			departamento = depDao.buscarDepartamento(departamento.getId());
			
			empregadoDao.persist(empregado);
			
			pagina.addObject("departamento", departamento);
			
			pagina.addObject("mensagem", "Cadastro concluído com sucesso");
			
			pagina.addObject("departamento", departamento);
			
			pagina.addObject("empregado", empregado);			
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagina;
		
	}

}
