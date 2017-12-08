package br.com.AmigoOculto.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {
	
	@Value("${welcome.message:test}")
	private String mensagem;
	
	@GetMapping("/home")
	public ModelAndView home() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("mensagem", mensagem);
		
		return model;
	}

}
