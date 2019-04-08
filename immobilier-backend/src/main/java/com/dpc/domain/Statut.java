package com.dpc.domain;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Data
public class Statut {
	@Id@GeneratedValue(strategy=GenerationType.IDENTITY)
    @Generated(GenerationTime.INSERT)
private Long id;
	@Column(unique = false, nullable = false)
private String nom;
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany(mappedBy="statut")
	private List<Chantier>chanties ;
	
}