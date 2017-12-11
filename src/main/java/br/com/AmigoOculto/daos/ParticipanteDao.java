package br.com.AmigoOculto.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.AmigoOculto.models.Participante;

public interface ParticipanteDao extends JpaRepository<Participante, String>{

	
	
}
