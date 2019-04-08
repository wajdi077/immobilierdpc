package com.dpc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Artisant;


@Repository
	public interface ArtisantRepository extends CrudRepository<Artisant, Long> { 
	List<Artisant> findById(Long id);
	public Artisant findByEmail(String email);
	public Artisant findByTelephone(Long telephone);
	
	

	

}
