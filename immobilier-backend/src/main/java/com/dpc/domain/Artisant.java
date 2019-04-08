package com.dpc.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Artisant {

	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "artisantId", strategy = "uuid2")
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = false)
	private String nom;

	@Column(unique = false, nullable = false)
	private String prenom;

	@Column(unique = false, nullable = false)
	private String adresse;

	@Column(unique = true, nullable = false)
	private String email;
	@Column(unique = true, nullable = false)
	private long telephone;
	
	@Column(unique = false, nullable = false)
	private String raisonsocial;

	@ManyToOne
	@JoinColumn(name = "codepostaleId")
	@JsonBackReference
	private Codepostale codepostale;

	@JsonIgnore
	@OneToOne(fetch = FetchType.LAZY, mappedBy="artisant")
    @JoinColumn(name = "user_id", nullable = false)
	private Utilisateur user;
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "activiteId")
    private Activite activite;

	

}
