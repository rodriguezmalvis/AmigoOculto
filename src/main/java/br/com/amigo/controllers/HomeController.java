package br.com.amigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import br.com.amigo.daos.SorteioDao;
import br.com.amigo.models.Participante;

@Controller
public class HomeController {
	
	@Value("${welcome.message:test}")
	private String welcome;
	
	@Autowired
	SorteioDao sorteioDao;
	
	@GetMapping("/home")
	public ModelAndView home() {
		
		ModelAndView model = new ModelAndView();
		model.addObject("welcome", welcome);
		model.addObject("participante", new Participante());
		model.addObject("sorteios", sorteioDao.findAll());
		
		return model;
	}

}
