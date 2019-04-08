package com.dpc.dto;


import lombok.Data;

@Data
public class ArtisantDto {

	private String nom;

	private String prenom;
	private String email;
	private long telephone;
    private String raisonsocial;
    private long codePostale;
    private String activite;

}
