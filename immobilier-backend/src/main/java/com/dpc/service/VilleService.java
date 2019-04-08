package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Ville;
import com.dpc.repository.VilleRepository;


@Service
public class VilleService {
	@Autowired 
	private VilleRepository villeRepository;
	public List<Ville>getAllVille(){
		return (List<Ville>)villeRepository.findAll();
	}

}






