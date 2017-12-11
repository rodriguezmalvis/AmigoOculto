package br.com.AmigoOculto.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.AmigoOculto.daos.ParticipanteDao;
import br.com.AmigoOculto.negocio.Sorteio;

@Controller
@RequestMapping("sorteio")
public class SorteioController {
	
	@Value("${welcome.message:test}")
	private String welcome;
	
	@Autowired
	ParticipanteDao dao;
	
	@PostMapping("sorteia")
	public ModelAndView sorteia(RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:lista");
		
		new Sorteio().sorteia(dao.findAll());
		
		redirectAttributes.addFlashAttribute("mensagem","Sorteio realizado com sucesso");
		
		return view;
		
	}
	
	@GetMapping("lista")
	public ModelAndView lista() {
		
		ModelAndView view = new ModelAndView("lista");
		view.addObject("participantes", dao.findAll());
		view.addObject("welcome", welcome);
		
		return view;
		
	}

}
