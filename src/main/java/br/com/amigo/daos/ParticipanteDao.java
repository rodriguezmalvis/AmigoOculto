package br.com.amigo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amigo.models.Participante;

public interface ParticipanteDao extends JpaRepository<Participante, String>{

	
	
}
