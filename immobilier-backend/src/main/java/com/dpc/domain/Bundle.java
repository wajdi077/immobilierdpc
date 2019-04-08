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

/**
 * Created by LMA
 *
 * 22 mars 2017
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Bundle {

	
	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "bundleId", strategy = "uuid2")
	@GeneratedValue
	private Long id;

	
	@Column(unique = false, nullable = false)
	private Long env;

	
	@Column(unique = false, nullable = false)
	private String key;

	
	@Column(unique = false, nullable = true)
	private String value;

	
	@Column(unique = false, nullable = true)
	private String comment;

	
	@OneToMany(mappedBy = "bundle", cascade = CascadeType.ALL)
	private Set<ValueHisto> listValue = new HashSet<>();

}
