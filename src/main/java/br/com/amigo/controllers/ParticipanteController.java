package br.com.amigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.amigo.daos.ParticipanteDao;
import br.com.amigo.daos.SorteioDao;
import br.com.amigo.models.Participante;

@Controller
@RequestMapping("participante")
public class ParticipanteController {
	
	@Value("${welcome.message:test}")
	private String welcome;
	
	@Autowired
	ParticipanteDao dao;
	
	@Autowired
	SorteioDao sorteioDao;

	@PostMapping("cadastra")
	public ModelAndView cadastra(@ModelAttribute Participante participante, RedirectAttributes redirectAttributes){
		
		ModelAndView view = new ModelAndView("redirect:/home");
		
		dao.save(participante);
		
		redirectAttributes.addFlashAttribute("mensagem", "participante cadastrado com sucesso");
		
		return view;
		
	}
	
	@PostMapping("remover")
	public ModelAndView removerParticipante(String email, RedirectAttributes redirectAttributes) {
		
		Participante participante = dao.findOne(email);
		
		ModelAndView view = new ModelAndView("redirect:/sorteio/participantes/"+participante.getSorteio().getId());
		
		dao.delete(participante);
		
		redirectAttributes.addFlashAttribute("mensagem", "participante deletado com sucesso");
		
		return view;
		
	}
	
	@PostMapping("editar")
	public ModelAndView editarParticipante(String email) {
		
		ModelAndView model = new ModelAndView("home");
		model.addObject("welcome", welcome);
		model.addObject("participante", dao.findOne(email));
		model.addObject("sorteios", sorteioDao.findAll());
		
		return model;
		
	}
	
}
