package br.com.AmigoOculto.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.AmigoOculto.models.Participante;

@Controller
public class HomeController {
	
	@Value("${welcome.message:test}")
	private String welcome;
	
	@GetMapping("/home")
	public ModelAndView home() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("welcome", welcome);
		model.addObject("participante", new Participante());
		
		return model;
	}

}
