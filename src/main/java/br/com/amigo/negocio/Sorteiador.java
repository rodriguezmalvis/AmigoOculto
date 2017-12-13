package br.com.amigo.negocio;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.amigo.daos.ParticipanteDao;
import br.com.amigo.models.Participante;

@Component
public class Sorteiador {
	
	@Autowired
	Mailer mailSender;
	
	@Autowired
	ParticipanteDao dao;
	
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

	public void validaEmails(List<Participante> participantes) throws Exception {
		
		for (Participante participante : participantes) {
			
			String MD5 = geraMD5(participante);
			
			String token = Calendar.getInstance().getTimeInMillis()+"O_o"+MD5+"o_O"+Calendar.getInstance().getTimeInMillis();
			
			participante.setToken(MD5);
			participante.setDataToken(new Date());
			
			dao.save(participante);
			
			System.out.println("Enviando email de confirmação pra: "+participante.getEmail());
			
			mailSender.sendMailWithJavaMail(
					"meuamigaosecreto@hotmail.com", 
					participante.getEmail(), 
					participante.getSorteio().getNome(), 
					"Olá "+participante.getNome()+", esta é uma mensagem automática do sorteio "+ participante.getSorteio().getNome() +". Por favor, "
							+ "clique neste link pra validar o seu email: http://localhost:8080/AmigoOculto/participante/validaEmail/"+ token +" Boa Sorte!!!");
		}
		
	}

	private String geraMD5(Participante participante) throws NoSuchAlgorithmException {
		MessageDigest m = MessageDigest.getInstance("MD5");
		m.update(participante.getEmail().getBytes(),0,participante.getEmail().length());
		String hash = new BigInteger(1,m.digest()).toString(16);
		return hash;
	}

}
