package com.javaplenomarcosabreu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaplenomarcosabreu.auxiliar.Auxiliar;
import com.javaplenomarcosabreu.dao.Dao;
import com.javaplenomarcosabreu.dao.DepartamentoDao;
import com.javaplenomarcosabreu.dao.EmpregadoDao;
import com.javaplenomarcosabreu.model.Departamento;
import com.javaplenomarcosabreu.model.Empregado;

@Controller
public class EmpregadoController {

	@Autowired
	Auxiliar auxiliar;

	@Autowired
	Departamento departamento;

	@Autowired
	DepartamentoDao depDao;

	@Autowired
	Dao<Empregado> empregadoGenericDao;

	@Autowired
	EmpregadoDao empregadoDao;

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro(@ModelAttribute Empregado empregado) {

		ModelAndView pagina = new ModelAndView("cadastro");

		try {

			pagina.addObject("mensagem", "Página de Cadastro");

			pagina.addObject("pagina", "cadastro");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.POST)
	public ModelAndView cadastrar(@Valid Empregado empregado, BindingResult result,
			RedirectAttributes redirectAttributes) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("cadastro");

				pagina.addObject("pagina", "cadastro");

				pagina.addObject("mensagem", "Erro ao se tentar cadastrar o empregado");

			} else if(empregadoDao.empregadoJaCadastrado(empregado.getCpf())) {
				
				result.rejectValue("cpf", "error.empregado", "Empregado já cadastrado");
				
				pagina = new ModelAndView("cadastro");
				
				pagina.addObject("mensagem", "Erro erro ao se tentar cadastrar o empregado");
				
				pagina.addObject("pagina", "cadastro");
				
			} else {			
				
				redirectAttributes.addFlashAttribute("empregado", empregado);

				redirectAttributes.addFlashAttribute("departamento", departamento);

				pagina = new ModelAndView("forward:/cadastro/selecionar-depto");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;
	}

	@RequestMapping(value = "/atualizar-dados", method = RequestMethod.GET)
	public ModelAndView atualizarDados(@ModelAttribute Departamento departamento, HttpSession session) {

		ModelAndView pagina = null;

		try {

			List<Departamento> departamentos = new ArrayList<>(depDao.buscarDepartamentosOcupados());

			session.setAttribute("departamentos", departamentos);
			
			pagina = new ModelAndView("atualizar-dados");

			pagina.addObject("pagina", "dados");
			
			pagina.addObject("mensagem", "Página de atualização de dados cadastrais");

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}
	
	@RequestMapping(value = "/atualizar-dados/listar-empregados", method = RequestMethod.POST)
	public ModelAndView listarEmpregadosDepto(@Valid Departamento departamento, BindingResult result,
			@ModelAttribute Empregado empregado, HttpSession session) {

		ModelAndView pagina = null;

		try {
			
			if (result.hasErrors() || departamento.getId() == null || departamento.getId().equals("")) {

				result.rejectValue("id", "error.departamento", "Nenhum departamento foi selecionado");
				
				pagina = new ModelAndView("atualizar-dados");
				
				pagina.addObject("mensagem", "Ocorreu um erro na busca dos empregados");
				
				pagina.addObject("pagina", "dados");

			} else {

				List<Empregado>empregados = empregadoDao.buscarEmpregados(departamento);

				pagina = new ModelAndView("listar-empregados");

				session.setAttribute("empregados", empregados);
				
				pagina.addObject("pagina", "dados");
				
				pagina.addObject("mensagem", "Página de busca de empregados");

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/atualizar-dados/listar-empregados/listar-selecionado", method = RequestMethod.POST)
	public ModelAndView listarDadosEmpregado(@Valid Empregado empregado, BindingResult result, HttpSession session) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors() || empregado.getCpf() == null || empregado.getCpf().equals("")) {

				result.rejectValue("cpf", "error.empregado", "Nenhum empregado foi selecionado");
				
				pagina = new ModelAndView("listar-empregados");
				
				pagina.addObject("mensagem", "Erro ao tentar exibir os dados do empregado");
				
				pagina.addObject("pagina", "dados");				

			} else {
				
				pagina = new ModelAndView("exibir-empregado");

				empregado = empregadoDao.buscarEmpregadoViaCpf(empregado.getCpf());

				pagina.addObject("empregado", empregado);

				Departamento departamento = depDao.buscarDepartamento(empregado.getCodDepartamento().getId());

				pagina.addObject("departamento", departamento);

				pagina.addObject("mensagem", "Página com os dados do empregado selecionado");	
				
				pagina.addObject("pagina", "dados");
				
			}			

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/atualizar-dados/listar-empregados/listar-selecionado/remover-empregado", method = RequestMethod.POST)
	public ModelAndView removerEmpregado(@Valid Empregado empregado, BindingResult result) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("exibir-empregado");

				pagina.addObject("mensagem", "ocorreu um erro para remover os dados do empregado");
				
				pagina.addObject("pagina", "dados");

			} else {

				empregado = empregadoDao.buscarEmpregadoViaCpf(empregado.getCpf());

				Departamento departamento = depDao.buscarDepartamento(empregado.getId());

				pagina = new ModelAndView("sucesso");

				pagina.addObject("departamento", departamento);

				pagina.addObject("empregado", empregado);

				pagina.addObject("mensagem", "Os dados cadastrais do empregado foram removidos com sucesso");

				empregadoGenericDao.delete(empregado);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@RequestMapping(value = "/atualizar-dados/listar-empregados/listar-selecionado/atualizar-empregado", method = RequestMethod.POST)
	public ModelAndView atualizarEmpregado(@Valid @ModelAttribute Empregado empregado, BindingResult result, HttpSession session) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("exibir-empregado");

				pagina.addObject("mensagem", "Ocorreu um erro no momento de obter os dados para atualizar");
				
				pagina.addObject("pagina", "dados");

			} else {

				pagina = new ModelAndView("atualizar-empregado");

				pagina.addObject("mensagem", "Página de atualização de dados cadastrais");

				empregado = empregadoDao.buscarEmpregadoViaCpf(empregado.getCpf());
				
				session.setAttribute("empregadoBD", empregado);
				
				pagina.addObject("empregado", empregado);
				
				pagina.addObject("pagina", "dados");
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

	@SuppressWarnings("unused")
	@RequestMapping(value = "/atualizar-dados/listar-empregados/listar-selecionado/atualizar-empregado/concluir", method = RequestMethod.POST)
	public ModelAndView atualizarDadosEmpregado(@Valid Empregado empregado, BindingResult result, HttpSession session) {

		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("atualizar-empregado");

				pagina.addObject("mensagem", "Ocorreu um erro para atualizar os dados do empregado");

			} else {
								
				Object empregadoObj = session.getAttribute("empregadoBD");
				
				Empregado empregadoBefore = (Empregado)empregadoObj;
				
				empregado.setId(empregadoBefore.getId());
				
				empregado.setDataCadastro(empregadoBefore.getDataCadastro());
				
				empregado.setCodDepartamento(empregadoBefore.getCodDepartamento());
				
				empregadoDao.merge(empregado); 
				
				pagina = new ModelAndView("sucesso");

				pagina.addObject("mensagem", "Atualização cadastral realizada com sucesso");

				pagina.addObject("empregado", empregado);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}

}
