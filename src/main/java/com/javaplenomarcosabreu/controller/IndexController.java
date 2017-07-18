package com.javaplenomarcosabreu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class IndexController {

	@RequestMapping("/")
	public ModelAndView index(ModelAndView mv, ModelMap modelMap) {

		ModelAndView pagina = new ModelAndView("index");
		
		pagina.addObject("mensagem", "Bem vindo à página inicial");
		
		pagina.addObject("pagina", "index");
				
		return pagina;

	}
}
