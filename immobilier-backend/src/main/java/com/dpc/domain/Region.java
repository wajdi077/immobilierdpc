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
import javax.persistence.OneToMany;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Region {

	public Region(String nom) {

		this.nom = nom;
	}

	@Id
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = false)
	private String nom;
@JsonIgnore
	@OneToMany(mappedBy = "region", cascade = CascadeType.ALL, fetch=FetchType.LAZY)
	private List<Ville>listville;


}
