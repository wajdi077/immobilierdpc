package com.dpc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Chantier;
import com.dpc.domain.Client;

@Repository
public interface ClientRepository extends CrudRepository<Client,Long>{
	Client findById(Long id);


	 @Query("SELECT c FROM Chantier c WHERE c.id= :client ")
	 Chantier findByOneClient(@Param("client") Client client);
	 Client findByEmail(String email);

}
