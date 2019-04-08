package com.dpc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Codepostale;
import com.dpc.domain.Ville;

@Repository
public interface CodepostaleRepository extends CrudRepository<Codepostale, Long> {

	List<Codepostale> findByVilleId(Long id);
	
    Codepostale findByCode(Long code);
    
    Codepostale findByNom(String nom);
   
}
