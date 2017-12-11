package br.com.AmigoOculto.negocio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.com.AmigoOculto.models.Participante;

public class Sorteio {
	
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
			
			System.out.println("Participante "+participante.getEmail()+" tirou -------> "+resultado.get(participante).getEmail());
			
		}
		
		return resultado;
		
	}

}
