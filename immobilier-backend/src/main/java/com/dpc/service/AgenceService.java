package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Activite;
import com.dpc.domain.Agence;
import com.dpc.repository.AgenceRepository;

@Service
public class AgenceService {
	
	@Autowired
	private AgenceRepository agencerepos ;
	
	public List<Agence> getAgence() {

		return (List<Agence>) agencerepos.findAll();

	}
	
	public Agence UpdateAgence (Agence agence) {
		
		
		return this.agencerepos.save(agence);
		
	}
	
	public Agence findagence(Long id ) {
		return this.agencerepos.findOne(id);
	}
	
	
	
	
	
	

}
