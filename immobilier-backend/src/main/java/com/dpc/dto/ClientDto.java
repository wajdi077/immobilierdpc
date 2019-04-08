package com.dpc.dto;

import java.util.List;

import com.dpc.domain.Utilisateur;

import lombok.Data;

@Data
public class ClientDto {

	private Long id ;
	private String nom;
	private String prenom;
	private String civilite;
	private String email;
	private long telephone;
	private String password;
	//private Utilisateur user ;
	//private List <Chantier>chantiers ;
    private UserDto userdto;

}
