package br.com.amigo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.amigo.models.Sorteio;

public interface SorteioDao extends JpaRepository<Sorteio, Integer> {
	
}
