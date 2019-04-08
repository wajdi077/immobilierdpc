package com.dpc.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Activite;
import com.dpc.domain.Agence;

@Repository
public interface AgenceRepository extends CrudRepository<Agence, Long>{
	

	public Agence findByNomagence(String nom );
	
	

}
