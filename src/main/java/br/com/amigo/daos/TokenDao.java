package br.com.amigo.daos;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.amigo.models.TokenEmail;

public interface TokenDao extends JpaRepository<TokenEmail, Integer>{

}
