package com.dpc.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Agence {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	Long id;
	
	private String adresse ;
	private String telephone;
	private String nomagence ;
	private String titre ;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getAdresse() {
		return adresse;
	}
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	public String getNomagence() {
		return nomagence;
	}
	public void setNomagence(String nomagence) {
		this.nomagence = nomagence;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public Agence(String adresse, String telephone, String nomagence, String titre) {
		super();
		this.adresse = adresse;
		this.telephone = telephone;
		this.nomagence = nomagence;
		this.titre = titre;
	}
	public Agence() {
		super();
	}
	
	

}
