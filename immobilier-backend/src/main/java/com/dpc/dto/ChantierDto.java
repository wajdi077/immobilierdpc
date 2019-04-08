package com.dpc.dto;



import java.util.Date;

import lombok.Data;

@Data
public class ChantierDto {
	private long id;

	private String discription;

	private String RDV;
private Date publiedon;
	private String etat;
	private String adresse;
	private ClientDto client;
	private CategorieDto categorie;
	private CodepostaleDto codepostale;
	//private String nomclient ;
	// String categorie ;
	
	


}
