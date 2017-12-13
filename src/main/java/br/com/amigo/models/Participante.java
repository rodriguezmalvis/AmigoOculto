package br.com.amigo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Participante {

	@Id
	private String email;
	
	private String nome;
	
	private String token;

	@Temporal(TemporalType.DATE)
	private Date dataToken;
	
	private boolean emailConfirmado;
	
	@ManyToOne
	@JoinColumn(name="id_sorteio",nullable=false)
	private Sorteio sorteio;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Date getDataToken() {
		return dataToken;
	}

	public void setDataToken(Date dataToken) {
		this.dataToken = dataToken;
	}
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public boolean isEmailConfirmado() {
		return emailConfirmado;
	}
	
	public String isEmailConfirmadoSimNao() {
		return emailConfirmado ? "Sim" : "Nao" ;
	}

	public void setEmailConfirmado(boolean emailConfirmado) {
		this.emailConfirmado = emailConfirmado;
	}

	public Sorteio getSorteio() {
		return sorteio;
	}

	public void setSorteio(Sorteio sorteio) {
		this.sorteio = sorteio;
	}
	
	
}
