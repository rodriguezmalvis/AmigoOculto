package br.com.AmigoOculto.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Participante {

	@Id
	private String email;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
}
