package br.com.amigo.models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sorteio {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	private String nome;
	private Boolean finalizado;
	
	@OneToMany(mappedBy="sorteio")
	private List<Participante> participantes = new ArrayList<>();
	
	public Integer getNumeroParticipantes() {
		return this.participantes.size();
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public List<Participante> getParticipantes() {
		return participantes;
	}
	
	public void setParticipantes(List<Participante> participantes) {
		this.participantes = participantes;
	}

	public Boolean getFinalizado() {
		return finalizado;
	}

	public void setFinalizado(Boolean finalizado) {
		this.finalizado = finalizado;
	}

}
