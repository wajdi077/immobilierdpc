package com.dpc.domain;

import java.util.List;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JacksonInject;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Client {

	@Id
	
	@GeneratedValue
	
	private Long id;
	@Column(unique = false, nullable = false)
	private String nom;

	
	@Column(unique = false, nullable = false)
	private String prenom;

	
	@Column(unique = false, nullable = false)
	private long telephone;

	
	@Column(unique = false, nullable = false)
	private String email;

	
	@Column(unique = false, nullable = false)
	private String civilite;
	
	@JsonIgnore
	 @JoinColumn(name = "user_id", nullable = false)
	 @OneToOne(fetch = FetchType.LAZY ,mappedBy="client")
	
	 private Utilisateur user;
	
	@JsonIgnore
	@OneToMany(mappedBy="client")
	 @LazyCollection(LazyCollectionOption.TRUE)
	private List<Chantier>chanties ;

}
