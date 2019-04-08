package com.dpc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dpc.domain.Region;
import com.dpc.repository.RegionRepository;

@Service
public class RegionService {
	@Autowired
	private RegionRepository regionRepository;

	public List<Region> getAllregion() {

		return (List<Region>) regionRepository.findAll();

	}

}
