package com.javaplenomarcosabreu.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

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
	public ModelAndView criarDepartamento(Departamento departamento, HttpSession session) {

		ModelAndView pagina = null;
		
		try {
			/*departamento.setDepartamentos(depDao.obterDepartamentos());*/
			
			departamentos = depDao.obterDepartamentos();
			
			List<Long> values = depDao.obterDeptosVazios();
			
			Map<String, Long> results = new HashMap<>();
			
			results.put("Total de departamentos vazios:", values.get(0));
			
			results.put("Total de empregados cadastrados:", empregadoDao.contarTodosOsEmpregados());
			
			results.put("Total de departamentos cadastrados:", depDao.contarDepartamentos());
			
			/*session.setAttribute("results", results);*/
			
			pagina = new ModelAndView("criar-departamento");		

			pagina.addObject("mensagem", "Página de controle de Departamentos");

			pagina.addObject("pagina", "departamento");
			
			pagina.addObject("results", results);
			
			pagina.addObject("departamentos", departamentos);

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
				
				departamentos = depDao.obterDepartamentos();
				
				List<Long> values = depDao.obterDeptosVazios();
				
				Map<String, Long> results = new TreeMap<>();
				
				results.put("Total de departamentos vazios:", values.get(0));
				
				results.put("Total de empregados cadastrados:", empregadoDao.contarTodosOsEmpregados());
				
				results.put("Total de departamentos cadastrados:", depDao.contarDepartamentos());
				
				pagina.addObject("results", results);
				
				pagina.addObject("departamentos", departamentos);

			} else if (depDao.checarDepartamento(departamento.getNomeDepartamento())) {

				result.rejectValue("nomeDepartamento", "error.departamento", "Esse Departamento já existe");

				mensagem = "Ocorreu um erro na criação do Departamento";

				pagina.addObject("mensagem", mensagem);
				
				departamentos = depDao.obterDepartamentos();

				/*departamento.setDepartamentos(depDao.obterDepartamentos());*/

				pagina.addObject("departamento", departamento);
				
				List<Long> values = depDao.obterDeptosVazios();
				
				Map<String, Long> results = new TreeMap<>();
				
				results.put("Total de departamentos vazios:", values.get(0));
				
				results.put("Total de empregados cadastrados:", empregadoDao.contarTodosOsEmpregados());
				
				results.put("Total de departamentos cadastrados:", depDao.contarDepartamentos());
				
				pagina.addObject("results", results);
				
				pagina.addObject("departamentos", departamentos);

			} else {

				departamentoDao.persist(departamento);

				mensagem = "O Departamento " + departamento.getNomeDepartamento() + " foi criado com sucesso";

				departamento = new Departamento();

				departamento.setDepartamentos(depDao.obterDepartamentos());

				pagina.addObject("departamento", departamento);

				pagina.addObject("mensagem", mensagem);
				
				List<Long> values = depDao.obterDeptosVazios();
				
				Map<String, Long> results = new TreeMap<>();
				
				results.put("Total de departamentos vazios:", values.get(0));
				
				results.put("Total de empregados cadastrados:", empregadoDao.contarTodosOsEmpregados());
				
				results.put("Total de departamentos cadastrados:", depDao.contarDepartamentos());
				
				pagina.addObject("results", results);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return pagina;
	}

	@RequestMapping(value = "/cadastro/selecionar-depto", method = {RequestMethod.GET, RequestMethod.POST})
	public ModelAndView escolherDepto(@ModelAttribute Departamento departamento, HttpSession session, Empregado empregado) {

		ModelAndView pagina = null;

		try {	

				pagina = new ModelAndView("selecionar-depto");

				pagina.addObject("departamento", departamento);

				pagina.addObject("pagina", "cadastro");

				pagina.addObject("mensagem", "Selecione o departamento do novo empregado");

				departamentos = depDao.obterDepartamentos();
				
				session.setAttribute("empregado", empregado);
				
				session.setAttribute("departamentos", departamentos);
				
		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;

	}
	
	@SuppressWarnings("null")
	@RequestMapping(value = "/cadastro/selecionar-depto/concluir", method=RequestMethod.POST)
	public ModelAndView concluirCadastro(@Valid Departamento departamento, BindingResult result, 
			Empregado empregado, HttpSession session){
		
		ModelAndView pagina = null;
		
		try {
			
			if(result.hasErrors()){
				
				pagina = new ModelAndView("selecionar-depto");
								
				Object empregadoObj = session.getAttribute("empregado");
				
				empregado = (Empregado)empregadoObj;
				
				pagina.addObject("empregado", empregado);				
												
				pagina.addObject("mensagem", "Erro na finalização do cadastro");
				
				pagina.addObject("pagina", "cadastro");				
				
			} else if(departamento.getId() == null || departamento.getId().equals("")) {
				
				pagina = new ModelAndView("selecionar-depto");
				
				result.rejectValue("id", "error.departamento", "O departamento não foi encontrado");
				
				Object empregadoObj = session.getAttribute("empregado");
				
				empregado = (Empregado)empregadoObj;
				
				pagina.addObject("empregado", empregado);				
				
				pagina.addObject("mensagem", "Erro na finalização do cadastro");
				
				pagina.addObject("pagina", "cadastro");	
			} else {
			
			pagina = new ModelAndView("sucesso");	
				
			Object empregadoObj = session.getAttribute("empregado");	
			
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
	
	@RequestMapping(value="/atualizar-dados/listar-empregados/listar-selecionado/atualizar-depto", method=RequestMethod.POST)
	public ModelAndView atualizarDepto(@Valid Empregado empregado, BindingResult result, 
			@ModelAttribute Departamento departamento, HttpSession session){
		
		ModelAndView pagina = null;
		
		try {
			
			if(result.hasErrors()){
			
				result.rejectValue("cpf", "error.empregado", "CPF do empregado não encontrado");
				
				pagina = new ModelAndView("exibir-empregado");
				
				pagina.addObject("mensagem", "Erro para atualizar o departamento do empregado");
				
				pagina.addObject("pagina", "dados");				
			
			} else {
				
				pagina = new ModelAndView("atualizar-depto");
				
				pagina.addObject("mensagem", "Página de atualização do departamento do empregado");
				
				pagina.addObject("pagina", "dados");
				
				departamentos = depDao.obterDepartamentos();
				
				empregado = empregadoDao.buscarEmpregadoViaCpf(empregado.getCpf());
				
				Integer id = empregadoDao.obterCodDepartamento(empregado.getCpf()); 
								
				for (int i = 0; i < departamentos.size(); i++) {
					
					if(departamentos.get(i).getId() == id){
						
						departamentos.remove(i);
						
					}
					
				}
				
				pagina.addObject("empregado", empregado);
				
				session.setAttribute("departamentos", departamentos);
				
				session.setAttribute("empregado", empregado);
				
			}
			
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagina;
	}
	
	@RequestMapping(value="/atualizar-dados/listar-empregados/listar-selecionado/atualizar-depto/concluir", method=RequestMethod.POST)
	public ModelAndView concluirAtualizaçãoDoDepto(@Valid Departamento departamento, BindingResult result,
			HttpSession session){
		
		ModelAndView pagina = null;
		
		try {
			
			if(result.hasErrors()){
				
				pagina = new ModelAndView("atualizar-depto");
					
				pagina.addObject("mensagem", "Erro ao tentar atualizar o departamento");
				
				pagina.addObject("pagina", "dados");
									
			} else if(departamento.getId() == null || departamento.getId().equals("")) {
				
				result.rejectValue("id", "error.departamento", "O departamento não foi encontrado");
				
				pagina = new ModelAndView("atualizar-depto");
				
				pagina.addObject("mensagem", "Erro ao tentar atualizar o departamento");
				
				pagina.addObject("pagina", "dados");
				
			} else {
				
				pagina = new ModelAndView("sucesso");
				
				Object empregadoObj = session.getAttribute("empregado");
				
				Empregado empregado = (Empregado)empregadoObj;
				
				empregado.setCodDepartamento(departamento);
				
				empregadoDao.merge(empregado);
				
				pagina.addObject("empregado", empregado);
				
				departamento = depDao.buscarDepartamento(departamento.getId());
				
				pagina.addObject("departamento", departamento);
				
				pagina.addObject("mensagem", "Departamento do empregado atualizado com sucesso");
				
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagina;
	}

}
