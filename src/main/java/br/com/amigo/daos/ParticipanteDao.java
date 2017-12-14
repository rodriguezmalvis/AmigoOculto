package br.com.amigo.daos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.amigo.models.Participante;

public interface ParticipanteDao extends JpaRepository<Participante, String>{

	@Query("select p from Participante p where p.token = ?1")
	public Participante findByToken(String token);
	
}
