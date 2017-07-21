package com.javaplenomarcosabreu.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.javaplenomarcosabreu.auxiliar.Auxiliar;
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

	DepartamentoDao depDao = new DepartamentoDao();
	
	EmpregadoDao empregadoDao;

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public ModelAndView cadastro(Empregado empregado, ModelMap modelMap) {

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
	public ModelAndView cadastrar(@Valid @ModelAttribute Empregado empregado, @Valid Departamento departamento,			
			BindingResult result, Model model, RedirectAttributes redirectAttributes) {
				
		ModelAndView pagina = null;

		try {

			if (result.hasErrors()) {

				pagina = new ModelAndView("cadastro");

				pagina.addObject("pagina", "cadastro");

				pagina.addObject("mensagem", "Erro ao se tentar cadastrar o empregado");
				 
			} else {				
				
				empregadoDao = new EmpregadoDao();
				
				/*empregadoDao.persist(empregado);*/
				
				redirectAttributes.addFlashAttribute("empregado", empregado); 
				
				redirectAttributes.addFlashAttribute("departamento", departamento);
				
				pagina = new ModelAndView("forward:/cadastro/selecionar-depto");
	
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return pagina;
	}
	
	@RequestMapping(value = "/atualizar-dados", method=RequestMethod.GET)
	public ModelAndView atualizarDados(@ModelAttribute Departamento departamento){
			
		ModelAndView pagina = null;
		
		try {
			
			List<Departamento>departamentos = new ArrayList<>(depDao.obterDepartamentos());
			
			pagina = new ModelAndView("atualizar-dados");
			
			pagina.addObject("departamento", departamentos);
			
			pagina.addObject("mensagem", "Página de atualização de dados cadastrais");
									
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return pagina;
		
	}
	
}
