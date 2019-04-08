package com.dpc.domain;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Evenement {

	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "evenementId", strategy = "uuid2")
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = false)
	private String nom;
/*
	@OneToMany(mappedBy = "evenement", cascade = CascadeType.ALL)
	private Set<Historique> listhistorique = new HashSet<>();
*/
}
