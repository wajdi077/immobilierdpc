package com.dpc.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Activite implements Serializable {

	public Activite() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Activite(Long id, String nom, List<Categorie> listcategorie, List<Artisant> artisans) {
		super();
		this.id = id;
		this.nom = nom;
		this.listcategorie = listcategorie;
		this.artisans = artisans;
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Categorie> getListcategorie() {
		return listcategorie;
	}


	public void setListcategorie(List<Categorie> listcategorie) {
		this.listcategorie = listcategorie;
	}


	public List<Artisant> getArtisans() {
		return artisans;
	}


	public void setArtisans(List<Artisant> artisans) {
		this.artisans = artisans;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = false)
	private String nom;

	@OneToMany(mappedBy = "activite", cascade = CascadeType.ALL)
	public List<Categorie>listcategorie;


	@OneToMany(mappedBy = "activite")
	public List<Artisant>artisans;
}
