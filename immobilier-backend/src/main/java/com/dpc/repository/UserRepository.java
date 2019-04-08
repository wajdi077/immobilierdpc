package com.dpc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.dpc.domain.Artisant;
import com.dpc.domain.Utilisateur;


public interface UserRepository extends CrudRepository<Utilisateur, Long> {
	// User findByLOGIN(String login);
	// @Query("SELECT u FROM User u WHERE u.id= :")
	// @Query("SELECT u FROM User u JOIN u.role r WHERE r.role=:rolename")
	// @Query( "SELECT u FROM User u role r  WHERE u.role.nom=:nom")
	// public List<User> findByArtisant(@Param("nom")String nom);
	// @Query("SELECT u FROM User u WHERE u.id= :client ")
	// public List<User>findByClient();
	// @Query( "select u from User u inner join u.role_id r where r.role_id in :nom=role_artisan")
	// List<User> findByRole(@Param("role") Role role);
	
	
	 public Utilisateur findByUsername(String login);
 
	
	 

}
