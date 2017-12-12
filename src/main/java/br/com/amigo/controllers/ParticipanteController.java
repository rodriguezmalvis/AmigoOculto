package br.com.amigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.amigo.daos.ParticipanteDao;
import br.com.amigo.models.Participante;

@Controller
@RequestMapping("participante")
public class ParticipanteController {
	
	@Autowired
	ParticipanteDao dao;

	@PostMapping("cadastra")
	public ModelAndView cadastra(@ModelAttribute Participante participante, RedirectAttributes redirectAttributes){
		
		ModelAndView view = new ModelAndView("redirect:/home");
		
		dao.save(participante);
		
		redirectAttributes.addFlashAttribute("mensagem", "participante cadastrado com sucesso");
		
		return view;
		
	}
	
}
