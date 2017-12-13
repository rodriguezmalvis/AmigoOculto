package br.com.amigo.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import br.com.amigo.daos.ParticipanteDao;
import br.com.amigo.daos.SorteioDao;
import br.com.amigo.models.Participante;
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
	Sorteiador sorteiador;
	
	@GetMapping("form")
	public ModelAndView formulario() {
		
		ModelAndView view = new ModelAndView("sorteioForm");
		
		view.addObject("sorteio", new Sorteio());
		
		view.addObject("welcome", welcome);
		
		return view;
		
	}
	
	@PostMapping("cadastra")
	public ModelAndView cadastra(Sorteio sorteio, RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:form");
		
		sorteio.setFinalizado(false);
		
		sorteioDao.save(sorteio);
		
		redirectAttributes.addFlashAttribute("mensagem","Sorteio cadastrado com sucesso");
		
		return view;
		
	}
	
	@PostMapping("valida")
	public ModelAndView validaEmails(Integer idSorteio,RedirectAttributes redirectAttributes) throws Exception {
		
		ModelAndView view = new ModelAndView("redirect:participantes/"+idSorteio);
		
		sorteiador.validaEmails(participanteDao.findAll());
		
		redirectAttributes.addFlashAttribute("mensagem","Emails enviados para verificação dos participantes");
		
		return view;
		
	}
	
	@PostMapping("sorteia")
	public ModelAndView sorteia(Integer idSorteio, RedirectAttributes redirectAttributes) {
		
		ModelAndView view = new ModelAndView("redirect:participantes/"+idSorteio);
		
		Sorteio sorteio = sorteioDao.getOne(idSorteio);
		
		Integer confirmados = (int) sorteio.getParticipantes().stream().filter(Participante::isEmailConfirmado).count();
		
		if(confirmados == sorteio.getNumeroParticipantes()) {
			if(sorteio.getNumeroParticipantes() <= 3 ) {
				redirectAttributes.addFlashAttribute("alerta","Numero minimo de participantes não atingido (Min 3)");
			}else {
				sorteiador.sorteia(sorteio.getParticipantes());
				redirectAttributes.addFlashAttribute("mensagem","Sorteio realizado com sucesso");
			}
		}else {
			redirectAttributes.addFlashAttribute("alerta","Existem participantes que não confirmaram o email");
		}
		
		return view;
		
	}
	
	@GetMapping("sorteios")
	public ModelAndView sorteios() {
		
		ModelAndView view = new ModelAndView("listaSorteios");
		view.addObject("sorteios", sorteioDao.findAll());
		view.addObject("welcome", welcome);
		
		return view;
		
	}
	
	@GetMapping("participantes/{idSorteio}")
	public ModelAndView lista(@PathVariable Integer idSorteio) {
		
		ModelAndView view = new ModelAndView("listaParticipantes");
		
		Sorteio sorteio = sorteioDao.findOne(idSorteio);
		
		view.addObject("sorteio", sorteio);
		view.addObject("welcome", welcome);
		
		return view;
		
	}

}
