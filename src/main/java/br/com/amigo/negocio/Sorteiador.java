package br.com.amigo.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.amigo.models.Participante;

@Component
public class Sorteiador {
	
	@Autowired
	Mailer mailSender;
	
	public Map<Participante,Participante> sorteia( List<Participante> participantes ) {
		
		List<Participante> naoSorteados = new ArrayList<Participante>(participantes);
		Map<Participante,Participante> resultado = new HashMap<Participante,Participante>();
		Integer numSorteado;
		
		for (Participante participante : participantes) {
			
			do {
				
				numSorteado = (int) (Math.random() * naoSorteados.size());
				
			}while(participante.getEmail().equalsIgnoreCase(naoSorteados.get(numSorteado).getEmail()));
			
			resultado.put(participante, naoSorteados.get(numSorteado));
			
			naoSorteados.remove(naoSorteados.get(numSorteado));
			
		}
		
		for (Participante participante : resultado.keySet()) {
			
			//System.out.println("Participante "+participante.getEmail()+" tirou -------> "+resultado.get(participante).getEmail());
			System.out.println("Enviando email do sorteio pra: "+participante.getEmail());
			
			mailSender.sendMailWithJavaMail(
					"meuamigaosecreto@hotmail.com", 
					participante.getEmail(), 
					"Amigo oculto 2017", 
					"Olá "+participante.getNome()+", você tirou "+ resultado.get(participante).getNome() +". Apenas lembrar "
							+ "que o valor do presente é até R$ 50. Feliz Natal!!!");
			
		}
		
		return resultado;
		
	}

	public void validaEmails(List<Participante> participantes) {
		
		for (Participante participante : participantes) {
			
			System.out.println("Enviando email de confirmação pra: "+participante.getEmail());
			
			mailSender.sendMailWithJavaMail(
					"meuamigaosecreto@hotmail.com", 
					participante.getEmail(), 
					"Amigo oculto 2017", 
					"Olá "+participante.getNome()+", esta é uma mensagem automática do sorteio do amigo oculto. Por favor, "
							+ "confirme o recebimento deste email respondendo este email ou no grupo da Family no Zap! Feliz Natal!!!");
		}
		
	}

}
