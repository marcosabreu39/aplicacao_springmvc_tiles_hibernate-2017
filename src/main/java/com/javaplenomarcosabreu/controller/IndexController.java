package com.javaplenomarcosabreu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexController {

	@RequestMapping("/")
	public String index(ModelMap mm) {

		mm.addAttribute("mensagem", "Bem vindo à página inicial.");

		return "index";

	}
}
