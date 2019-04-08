package com.dpc.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;

import javax.persistence.ManyToOne;


import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Historique {

	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "historiqueId", strategy = "uuid2")
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = false)
	private String discriptif;

	@Column(unique = false, nullable = false)
	private String  date;
	@Column(unique = false, nullable = false)
	private String action;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonBackReference
	private Utilisateur user;

	@ManyToOne
	@JoinColumn(name = "chantierId")
	@JsonBackReference
	private Chantier chantier;

	/*@ManyToOne
	@JoinColumn(name = "artisantId")
	@JsonBackReference
	private Artisant artisant;

	@ManyToOne
	@JoinColumn(name = "evenementId")
	@JsonBackReference
	private Evenement evenement;

	/*@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "historique_user", joinColumns = @JoinColumn(name = "historique_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"))
	private List<User> users;

	@ManyToMany
	@LazyCollection(LazyCollectionOption.FALSE)
	@JoinTable(name = "historique_artisant", joinColumns = @JoinColumn(name = "historique_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "artisant_id", referencedColumnName = "id"))
	private List<Artisant> artisants;*/
}
