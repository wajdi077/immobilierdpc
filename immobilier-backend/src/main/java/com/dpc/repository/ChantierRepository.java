package com.dpc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Activite;
import com.dpc.domain.Artisant;
import com.dpc.domain.Categorie;
import com.dpc.domain.Chantier;
import com.dpc.domain.Client;

@Repository
public interface ChantierRepository extends CrudRepository<Chantier, Long> {

	List<Chantier> findByCodepostaleId(Long id);
	

	List<Chantier> findByEtat(String etat);
	
	List<Chantier>findByCategorie(Categorie categorie);
	
	 Chantier findByAdresse(String adresse);
	 
	 List<Chantier> findByClient(Client client);
	 
	 @Query("SELECT c FROM Chantier c WHERE c.id= :client ")
	 Chantier findByOneClient(@Param("client") Client client);
	
	 
	 Chantier findById(Long idchantier);
	 
		List<Chantier> findByArtisan(Artisant artisan);

	 
	
	
	
	//List<Chantier>findByActivity(Activite activite);
	

}
