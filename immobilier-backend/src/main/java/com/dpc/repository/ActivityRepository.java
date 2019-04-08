package com.dpc.repository;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Activite;

@Repository

public interface ActivityRepository extends CrudRepository<Activite, Long> {
	Activite findByNom(String nom);
	Activite findById(Long id);
    
	

}
