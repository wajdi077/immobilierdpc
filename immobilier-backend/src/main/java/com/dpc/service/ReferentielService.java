package com.dpc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Activite;
import com.dpc.domain.Categorie;
import com.dpc.domain.Codepostale;
import com.dpc.domain.Region;
import com.dpc.domain.Ville;
import com.dpc.repository.ActivityRepository;
import com.dpc.repository.CategorieRepository;
import com.dpc.repository.CodepostaleRepository;
import com.dpc.repository.RegionRepository;
import com.dpc.repository.VilleRepository;

@Service
public class ReferentielService {
	@Autowired
	private RegionRepository regionRepository;
	@Autowired
	private VilleRepository villeRepository;
	@Autowired
	private CodepostaleRepository codepostaleRepository;
	@Autowired
	private ActivityRepository activiteRepository;
	@Autowired
	private CategorieRepository categorieRepository;

	public Region createRegion(String nameRegion) {
		Region region = new Region(nameRegion);

		return this.regionRepository.save(region);
	}

	public Ville createVille(String regionName, String villeName) {
		Region region = regionRepository.findByNom(regionName);
		Ville ville = new Ville(villeName);
		ville.setRegion(region);
		return this.villeRepository.save(ville);

	}

	public Codepostale createCodePostale(String codePostaleName, Double codePostaleCode, String villeName) {
		long code = (new Double(codePostaleCode)).longValue();
		Codepostale cpostale = new Codepostale();
		cpostale.setCode(code);
		cpostale.setNom(codePostaleName);
		cpostale.setVille(villeRepository.findByNom(villeName));
		return this.codepostaleRepository.save(cpostale);

	}

	public Activite createActivite(String activiteName) {
		Activite activite = new Activite();
		activite.setNom(activiteName);
		return this.activiteRepository.save(activite);

	}

	public Categorie createCategorie(String categorieName, String activiteName,String photoName) {
		Categorie categorie = new Categorie();
		categorie.setNom(categorieName);
		categorie.setNomimage(photoName);
		categorie.setActivite(activiteRepository.findByNom(activiteName));
		return this.categorieRepository.save(categorie);
	}
}
