package com.dpc.domain;

import java.io.Serializable;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;



@Entity

public class Categorie implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;
	//test git

	@Column(unique = false, nullable = false)
	private String nom;
	@Column(unique = false, nullable = true)
	private String prestation;
	private String nomimage;

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


	public String getPrestation() {
		return prestation;
	}


	public void setPrestation(String prestation) {
		this.prestation = prestation;
	}


	public String getNomimage() {
		return nomimage;
	}


	public void setNomimage(String nomimage) {
		this.nomimage = nomimage;
	}


	public Activite getActivite() {
		return activite;
	}


	public void setActivite(Activite activite) {
		this.activite = activite;
	}


	public List<Chantier> getChanties() {
		return chanties;
	}


	public void setChanties(List<Chantier> chanties) {
		this.chanties = chanties;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}


	@Override
	public String toString() {
		return "Categorie [id=" + id + ", nom=" + nom + ", prestation=" + prestation + ", nomimage=" + nomimage
				+ ", activite=" + activite + ", chanties=" + chanties + "]";
	}


	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "activiteId")
	private Activite activite;

	
	@LazyCollection(LazyCollectionOption.TRUE)
	@OneToMany(mappedBy="categorie")
	private List<Chantier>chanties ;

}