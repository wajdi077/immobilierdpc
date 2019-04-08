package com.dpc.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.dpc.domain.Chantier;
import com.dpc.domain.Historique;

public interface HistoriqueRepository  extends CrudRepository<Historique, Long>{
	 List<Historique> findByChantier(Chantier chantier);
	 

}
