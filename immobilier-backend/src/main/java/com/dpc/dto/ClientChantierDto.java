package com.dpc.dto;



import lombok.Data;
@Data
public class ClientChantierDto {
	private String discription;
	private String date ;
	private String typebatiment ;

	private ClientDto client ;
	private String  nomcategorie;
	
	private String codepostale ;
	private String adresse;

}
