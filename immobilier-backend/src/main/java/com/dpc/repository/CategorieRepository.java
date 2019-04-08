package com.dpc.repository;

import java.util.List;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Activite;

import com.dpc.domain.Categorie;

@Repository
public interface CategorieRepository extends CrudRepository<Categorie, Long> {
	 Categorie findById(Long id);
	 
	 Categorie findByNom(String nom );
	 
	 public List< Categorie> findByActivite(Activite a);
	 
	 @Query("SELECT c FROM Categorie c WHERE c.id= :activite ")
	 public Categorie getcatbyactivite (@Param("activite") Activite activite);
	
	 @Query("SELECT nomimage FROM Categorie  c where c.activite.nom=:nomactivite ")
	public  List<String> filenames(@Param(value = "nomactivite") String nomactivite );
	
	 
}