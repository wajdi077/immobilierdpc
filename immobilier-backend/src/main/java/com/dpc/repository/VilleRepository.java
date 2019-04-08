package com.dpc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Codepostale;
import com.dpc.domain.Region;
import com.dpc.domain.Ville;

@Repository

public interface VilleRepository extends CrudRepository<Ville, Long> {

	List<Ville> findByRegionId(Long id);

	Ville findByNom(String nom);
   public Region findByRegion(String nomville);
	
	
	public List<Ville> findByRegion(Region nom);
	public Ville findByListcodepostale(Codepostale p);



}
