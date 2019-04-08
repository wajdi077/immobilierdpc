package com.dpc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.domain.Authority;
import com.dpc.domain.Utilisateur;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
	@Query("SELECT p FROM Utilisateur p WHERE p.authorities = :authority ")
    public List<Utilisateur> getusersinauthority(@Param("authority") Authority authority);
	

	@Query("SELECT p FROM Utilisateur p WHERE p.profil = :profil ")
    public List<Utilisateur> getusersbyprofil(@Param("profil") String profil);
	
	Utilisateur findByUsername( String username );
	List<Utilisateur> findByProfil(String profil);



}
