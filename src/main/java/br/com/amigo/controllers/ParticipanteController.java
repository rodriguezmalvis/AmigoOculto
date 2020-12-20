package br.com.amigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	ParticipanteDao participanteDao;
	
	@Autowired
	SorteioDao sorteioDao;

	@PostMapping("cadastra")
	public ModelAndView cadastra(@ModelAttribute Participante participante, RedirectAttributes redirectAttributes){
		
		ModelAndView view = new ModelAndView("redirect:/home");
		
		participanteDao.save(participante);
		
		redirectAttributes.addFlashAttribute("mensagem", "participante cadastrado com sucesso");
		
		return view;
		
	}
	
	@PostMapping("remover")
	public ModelAndView removerParticipante(String email, RedirectAttributes redirectAttributes) {
		
		Participante participante = participanteDao.findOne(email);
		
		ModelAndView view = new ModelAndView("redirect:/sorteio/participantes/"+participante.getSorteio().getId());
		
		participanteDao.delete(participante);
		
		redirectAttributes.addFlashAttribute("mensagem", "participante deletado com sucesso");
		
		return view;
		
	}
	
	@PostMapping("editar")
	public ModelAndView editarParticipante(String email) {
		
		ModelAndView model = new ModelAndView("home");
		model.addObject("welcome", welcome);
		model.addObject("participante", participanteDao.findOne(email));
		model.addObject("sorteios", sorteioDao.findAll());
		
		return model;
		
	}
	
	@GetMapping("validaEmail/{token}")
	public ModelAndView validaEmailParticipante( @PathVariable String token) {
		
		ModelAndView model = new ModelAndView("emailValidado");
		
		String[] arrtoken = token.split("O_o");
		arrtoken = arrtoken[1].split("o_O");
		token = arrtoken[0];
		
		Participante participante = participanteDao.findByToken(token);
		
		if(participante != null) {

			participante.setEmailConfirmado(true);
			participanteDao.save(participante);
			model.addObject("welcome", welcome);
			model.addObject("mensagem", "Email validado com sucesso");
			
		}else {
			
			
			
		}
		
		return model;
		
	}
	
}
