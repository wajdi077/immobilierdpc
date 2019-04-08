package com.dpc.service;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Activite;
import com.dpc.domain.Categorie;
import com.dpc.repository.CategorieRepository;

@Service
public class CategorieService {

	 @Autowired
	    private CategorieRepository  categorieRepository;
	
	public List<Categorie>getAllCategories() {
		return (List<Categorie>) categorieRepository.findAll();
	}
	public Categorie GetIdcategorie(Long idcategorie) {
		return categorieRepository.findById(idcategorie);}
	

	public  List< Categorie> GetCategorieByActivity(Activite a )
	{return (List<Categorie>) categorieRepository.findByActivite(a);
	}
	/* public List<String>filenames(String nomimage)
	 {return(List<String>) categorieRepository.findByNomimage(nomimage);}
	 */
	public Categorie GetByNom(String nom) {
		return categorieRepository.findByNom(nom);
	}
	
}
