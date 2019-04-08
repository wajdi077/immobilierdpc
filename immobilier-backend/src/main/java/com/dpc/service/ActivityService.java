package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Activite;
import com.dpc.repository.ActivityRepository;

@Service
public class ActivityService {

	@Autowired
	private ActivityRepository activityRespository;

	public List<Activite> getAllactivities() {

		return (List<Activite>) activityRespository.findAll();

	}
	public Activite getById(long id)
	{return activityRespository.findById(id);}
	
	public Activite getByNom(String nom) {
		return activityRespository.findByNom(nom);
	}
}