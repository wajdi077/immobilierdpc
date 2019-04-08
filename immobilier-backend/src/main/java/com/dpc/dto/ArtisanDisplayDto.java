package com.dpc.dto;

import lombok.Data;

@Data
public class ArtisanDisplayDto {
	private int id;
	private String nom;
	private String prenom;
	private String email;
	private long telephone;
    private String raisonsocial;
    private CodepostaleDto codepostale;
    private ActivityDto activite;

}
