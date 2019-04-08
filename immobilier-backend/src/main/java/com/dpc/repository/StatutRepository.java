package com.dpc.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dpc.domain.Statut;

public interface StatutRepository extends JpaRepository<Statut, Long> {
	public Statut findByNom(String nom);

}
