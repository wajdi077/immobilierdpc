package com.dpc.controller;

import java.text.ParseException;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import com.dpc.domain.Activite;
import com.dpc.domain.Agence;
import com.dpc.domain.Artisant;
import com.dpc.domain.Bundle;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Evenement;
import com.dpc.domain.Historique;
import com.dpc.domain.Region;
import com.dpc.domain.Utilisateur;
import com.dpc.domain.Ville;
import com.dpc.dto.ActivityDto;
import com.dpc.dto.AgenceDto;
import com.dpc.dto.ArtisanDisplayDto;
import com.dpc.dto.ArtisanUserDto;
import com.dpc.dto.ArtisantDto;
import com.dpc.dto.BundleDto;
import com.dpc.dto.CategorieDto;
import com.dpc.dto.ChantierDto;
import com.dpc.dto.ChantierIdDto;
import com.dpc.dto.ChantierinstDto;
import com.dpc.dto.ClientArtisanDto;
import com.dpc.dto.ClientChantierDto;
import com.dpc.dto.ClientDto;
import com.dpc.dto.ClientconecteDto;
import com.dpc.dto.CodepostaleDto;
import com.dpc.dto.EvenementDto;
import com.dpc.dto.HistoriqueDto;
import com.dpc.dto.RegionDto;

import com.dpc.dto.UserDto;
import com.dpc.dto.UtilisteurDto;
import com.dpc.dto.VilleDto;

public class MainController {

	static final int ZERO = 0;
	static final int UN = 1;
	static final int DEUX = 2;
	static final int TROIS = 3;
	static final int QUATRE = 4;
	static final int CINQ = 5;
	static final int SIX = 6;
	static final int SEPT = 7;
	static final int HUIT = 8;

	@Autowired
	protected ModelMapper modelMapper;
	
	

	protected BundleDto convertBundleToDto(Bundle bundle) {
		BundleDto bundleDto = modelMapper.map(bundle, BundleDto.class);
		return bundleDto;
	}

	protected Bundle convertBundleToEntity(BundleDto bundleDto) throws ParseException {
		Bundle bundle = modelMapper.map(bundleDto, Bundle.class);
		return bundle;
	}

	
	protected AgenceDto convertAgenceToDto(Agence agence) {
		AgenceDto agencedto = modelMapper.map(agence, AgenceDto.class);
		return agencedto ;
	}
	
	protected Agence convertAgenceDtotoEntiry (AgenceDto agencedto) {
		Agence agence = modelMapper.map(agencedto,Agence.class);
		return agence ;
	}
	protected ClientconecteDto convertUserConecteToDto(Utilisateur user) {
		ClientconecteDto clientconectedto = modelMapper.map(user, ClientconecteDto.class);
		return clientconectedto;
	}
	
	protected Client convertClientToEntity(ClientDto clientdto) {
		Client client = modelMapper.map(clientdto, Client.class);
		return client ;
	}
	
	
	
	
	
	protected ActivityDto convertActivityToDto(Activite activity) {
		ActivityDto activityDto = modelMapper.map(activity, ActivityDto.class);
		return activityDto;
	}

	protected EvenementDto convertEvenementToDto(Evenement evenement) {
		EvenementDto evenementDto = modelMapper.map(evenement, EvenementDto.class);
		return evenementDto;

	}

	protected RegionDto convertRegionToDto(Region region) {
		RegionDto regionDto = modelMapper.map(region, RegionDto.class);
		return regionDto;
	}

	protected CategorieDto convertCategorieToDto(Categorie categorie) {
		CategorieDto categorieDto = modelMapper.map(categorie, CategorieDto.class);
		return categorieDto;
	}

	protected VilleDto convertVilleToDto(Ville ville) {
		VilleDto villeDto = modelMapper.map(ville, VilleDto.class);
		return villeDto;
	}

	protected CodepostaleDto convertCodepostaleToDto(Codepostale codepostale) {
		CodepostaleDto codepostaleDto = modelMapper.map(codepostale, CodepostaleDto.class);
		return codepostaleDto;
	}
	protected ChantierinstDto convertChantierinstToDto(Chantier chantier) {
		ChantierinstDto chantierinstDto = modelMapper.map(chantier, ChantierinstDto.class);
		return chantierinstDto;
	}
	protected ArtisantDto convertArtisantToDto(Artisant artisant) {
		ArtisantDto artisantDto = modelMapper.map(artisant, ArtisantDto.class);
		return artisantDto;
	}

	protected ChantierDto convertChantierToDto(Chantier chantier) {
		ChantierDto chantierDto = modelMapper.map(chantier, ChantierDto.class);
		return chantierDto;
	}
	
	protected ChantierIdDto convertChantierIdToDto(Chantier chantier) {
		ChantierIdDto chantieridDto = modelMapper.map(chantier, ChantierIdDto.class);
		return chantieridDto;
	}
	
	protected Chantier convertdtochantiertochantier(ChantierIdDto c )
	{
		Chantier chantier = modelMapper.map(c, Chantier.class);
		return chantier;

		
	}

	protected HistoriqueDto convertHistoricToDto(Historique historic) {
		HistoriqueDto historicDto = modelMapper.map(historic, HistoriqueDto.class);
		return historicDto;
	}
	protected Historique dtoToHistoric(HistoriqueDto historicDto) {
		Historique historic = modelMapper.map(historicDto, Historique.class);
		return historic;
	}
	protected ClientDto convertClientToDto(Client client) {
		ClientDto clientDto = modelMapper.map(client, ClientDto.class);
		return clientDto;
	}

	protected Client dtoToClient(ClientDto clientDto) {
		Client client = modelMapper.map(clientDto, Client.class);
		return client;
	}
	
	



	protected Artisant dtoToArtisant(ArtisantDto artisantDto) {
		Artisant artisant = modelMapper.map(artisantDto, Artisant.class);
		return artisant;
	}
	protected ArtisanUserDto convertArtisanUserToDto(Artisant artisant) {
		ArtisanUserDto artisanUserDto = modelMapper.map(artisant, ArtisanUserDto.class);
		return artisanUserDto;
	}
	protected Artisant dtoToArtisanUser(ArtisanUserDto artisanUserDto) {
		Artisant artisant = modelMapper.map(artisanUserDto, Artisant.class);
		return artisant;
	}
	protected Artisant dtoToArtisantDisplay(ArtisanDisplayDto artisanDisplaytDto) {
		Artisant artisant = modelMapper.map(artisanDisplaytDto, Artisant.class);
		return artisant;
	}
	

	protected ArtisanDisplayDto convertArtisantdisplayToDto(Artisant artisant) {
		ArtisanDisplayDto artisanDisplaytDto = modelMapper.map(artisant, ArtisanDisplayDto.class);
		return artisanDisplaytDto;
	}
	
	protected Chantier dtoToChantierinst(ChantierinstDto chantierDto) {
		Chantier chantier = modelMapper.map(chantierDto, Chantier.class);
		return chantier;

	}
	

	protected Chantier dtoToChantier(ChantierDto chantierDto) {
		Chantier chantier = modelMapper.map(chantierDto, Chantier.class);
		return chantier;

	}
	protected Utilisateur dtoToUser(UserDto userDto) {
		Utilisateur user = modelMapper.map(userDto, Utilisateur.class);
		return user;

	}
	protected UserDto convertUserToDto(Utilisateur user) {
		UserDto userDto = modelMapper.map(user, UserDto.class);
		return userDto;
	}

	
	protected Utilisateur dtoToUtilisateur(UtilisteurDto utilisateurDto) {
		Utilisateur user = modelMapper.map(utilisateurDto, Utilisateur.class);
		return user;

	}
	protected UtilisteurDto convertUtilisateurToDto(Utilisateur user) {
		UtilisteurDto utilisateurDto = modelMapper.map(user, UtilisteurDto.class);
		return utilisateurDto;
	}
	
	
	
	protected Chantier dtoToClientChantier(ClientChantierDto clienchantierDto) {
		Chantier chantier = modelMapper.map(clienchantierDto, Chantier.class);
		return chantier;

	}
	
	protected ClientChantierDto convertClientChantierToDto(Chantier chantier) {
		ClientChantierDto clientchantierDto = modelMapper.map(chantier, ClientChantierDto.class);
		return clientchantierDto;
	}
	
	
	// client artisant convert dto 
	
	
	protected ClientArtisanDto convertClientsToDto(Client entity) {
		ClientArtisanDto clientartisanDto = modelMapper.map(entity, ClientArtisanDto.class);
		return clientartisanDto;
	}
	protected ClientArtisanDto convertArtisantsToDto(Artisant entity) {
		ClientArtisanDto clientartisanDto = modelMapper.map(entity, ClientArtisanDto.class);
		return clientartisanDto;
	}

	protected Artisant dtoToArtisans(ClientArtisanDto clientartisanDto) {
		Artisant user = modelMapper.map(clientartisanDto, Artisant.class);
		return user;
	}	
	protected Client dtoToClients(ClientArtisanDto clientartisanDto) {
		Client user = modelMapper.map(clientartisanDto, Client.class);
		return user;
	}	
}