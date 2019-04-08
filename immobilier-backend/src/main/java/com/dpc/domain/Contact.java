package com.dpc.domain;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data

public class Contact {
	@Id
	@GeneratedValue
	private Long id;
	@Column( nullable = false)
	private String email;
	@Column( nullable = false)
	private String nom ;
	@Column( nullable = false)
	private String prenom;
	@Column( nullable = false)
	private String message;
	@Column( nullable = false)
	private Long telephone;
	
	private String datenvoie;
	

}
