package com.dpc.domain;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity

public class Ville {
	public Ville() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Ville(Long id, String nom, Region region, List<Codepostale> listcodepostale) {
		super();
		this.id = id;
		this.nom = nom;
		this.region = region;
		this.listcodepostale = listcodepostale;
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
	public Region getRegion() {
		return region;
	}
	public void setRegion(Region region) {
		this.region = region;
	}
	public List<Codepostale> getListcodepostale() {
		return listcodepostale;
	}
	public void setListcodepostale(List<Codepostale> listcodepostale) {
		this.listcodepostale = listcodepostale;
	}
	public Ville(String nom) {
		this.nom = nom;
	}

	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "villeId", strategy = "uuid2")
	@GeneratedValue
	private Long  id;

	@Column(unique = false, nullable = false)
	private String nom;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "regionId")
	
	private Region region;
@JsonIgnore
	@OneToMany(mappedBy = "ville", cascade = CascadeType.ALL,fetch=FetchType.LAZY)
	
	private List<Codepostale>listcodepostale;


}
