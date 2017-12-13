package br.com.amigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.amigo.daos.ParticipanteDao;
import br.com.amigo.daos.SorteioDao;
import br.com.amigo.models.Sorteio;
import br.com.amigo.negocio.Sorteiador;

@Controller
@RequestMapping("sorteio")
public class SorteioController {
	
	@Value("${welcome.message:test}")
	private String welcome;
	
	@Autowired
	ParticipanteDao participanteDao;
	
	@Autowired
	SorteioDao sorteioDao;
	
	@Autowired
	Sorteiador sorteio;
	
	@GetMapping("form")
	public ModelAndView formulario() {
		
		ModelAndView view = new ModelAndView("sorteioForm");
		
		view.addObject("sorteio", new Sorteio());
		
		return view;
		
	}
	
	@PostMapping("cadastra")
	public ModelAndView cadastra(Sorteio sorteio, RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:form");
		
		sorteio.setFinalizado(false);
		
		sorteioDao.save(sorteio);
		
		view.addObject("welcome", welcome);
		
		redirectAttributes.addFlashAttribute("mensagem","Sorteio cadastrado com sucesso");
		
		return view;
		
	}
	
	@PostMapping("valida")
	public ModelAndView validaEmails(RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:lista");
		
		sorteio.validaEmails(participanteDao.findAll());
		
		redirectAttributes.addFlashAttribute("mensagem","Emails enviados para verificação dos participantes");
		
		return view;
		
	}
	
	@PostMapping("sorteia")
	public ModelAndView sorteia(RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:lista");
		
		sorteio.sorteia(participanteDao.findAll());
		
		redirectAttributes.addFlashAttribute("mensagem","Sorteio realizado com sucesso");
		
		return view;
		
	}
	
	@GetMapping("sorteios")
	public ModelAndView sorteios() {
		
		ModelAndView view = new ModelAndView("listaSorteios");
		view.addObject("participantes", participanteDao.findAll());
		view.addObject("welcome", welcome);
		
		return view;
		
	}
	
	@GetMapping("lista")
	public ModelAndView lista() {
		
		ModelAndView view = new ModelAndView("lista");
		view.addObject("participantes", participanteDao.findAll());
		view.addObject("welcome", welcome);
		
		return view;
		
	}

}
