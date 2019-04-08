package com.dpc.domain;

import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Created by LMA
 *
 * 22 mars 2017
 */
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ValueHisto {

	@Id
	//@Type(type = "pg-uuid")
	//@GenericGenerator(name = "valueHistoId", strategy = "uuid2")
	@GeneratedValue
	private Long id;

	@Column(unique = false, nullable = true)
	private String value;

	@Column(unique = false, nullable = true)
	private String comment;

	@ManyToOne
	@JoinColumn(name = "bundleId")
	@JsonBackReference
	private Bundle bundle;

	@Column(unique = false, nullable = false)
	private Long version;

	public ValueHisto(String value, String comment) {
		this.value = value;
		this.comment = comment;
	}

}
