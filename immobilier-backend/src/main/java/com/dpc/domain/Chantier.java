package com.dpc.domain;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Chantier {

	@Id
	@GeneratedValue
	private Long  id;

	@Column(unique = false, nullable = false)
	private String discription;

	@Column(unique = false, nullable = true)
	private String date ;
	
	@Column(unique = false, nullable = true)
	private String typebatiment ;
	
	@Column(unique = false, nullable = false)
	private String RDV;

	@Column(unique = false, nullable = false)
	private String etat;

	@Column(unique = false, nullable = true)
	private String adresse;
	

	

	@Column(name="created_on")
	private Date createdon;
	@Column(name="publied_on")
	private Date publiedon;
	@Column(name="annuled_on")
	private Date annuledon;
	@ManyToOne
	@JoinColumn(name = "codepostaleId")
	private Codepostale codepostale;

	@ManyToOne
	
	@JoinColumn(name = "clientId")

	private Client client;

	@ManyToMany
	@JoinTable(name = "chantier_historique", joinColumns = @JoinColumn(name = "chantier_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "historique_id", referencedColumnName = "id"))
	private List<Historique> historiques;

	/*@ManyToMany
	@JoinTable(name = "chantier_artisant", joinColumns = @JoinColumn(name = "chantier_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "artisant_id", referencedColumnName = "id"))
	private List<Artisant> artisants;*/
	
	@ManyToOne
	@JoinColumn(name= "artisanId" )
	private Artisant artisan;


	@ManyToOne
	@JoinColumn(name= "statuId" )
	private Statut statut;
	
	@ManyToOne
	@JoinColumn(name= "categorieId" )
	private Categorie categorie;
	

}
