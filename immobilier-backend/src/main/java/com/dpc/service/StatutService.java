package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import com.dpc.domain.Statut;
import com.dpc.repository.StatutRepository;

public class StatutService {
	@Autowired
	private StatutRepository statutrepository ;
	public List<Statut> getAllstatuts() {

		return (List<Statut>) statutrepository.findAll();

	}

}
