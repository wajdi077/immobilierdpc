package com.dpc.dto;

import lombok.Data;

@Data
public class ArtisanUserDto {
	private String nom;
    private String adresse;
	private String prenom;
	private long telephone;
    private String raisonsocial;
    private ActivityDto activite;
 

}
