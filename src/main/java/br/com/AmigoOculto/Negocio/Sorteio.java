package br.com.AmigoOculto.Negocio;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sorteio {
	
	public void sorteia() {
		
		List<String> participantes = Arrays.asList("p1", "p2", "p3", "p4", "p5", "p6");
		List<String> naoSorteados = new ArrayList<String>(participantes);
		Map<String,String> resultado = new HashMap<String,String>();
		
		for (String participante : participantes) {
			
			Integer numero = (int) (Math.random() * naoSorteados.size());
			
			resultado.put(participante, naoSorteados.get(numero));
			
			naoSorteados.remove(naoSorteados.get(numero));
			
		}
		
		for (String participante : resultado.keySet()) {
			
			System.out.println("Participante "+participante+" tirou -------> "+resultado.get(participante));
			
		}
		
	}

}
