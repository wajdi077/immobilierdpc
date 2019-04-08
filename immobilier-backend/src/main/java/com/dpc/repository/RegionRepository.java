package com.dpc.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Region;
import com.dpc.domain.Ville;

@Repository

public interface RegionRepository extends CrudRepository<Region, Long> {
	public Region findByNom(String nom);
    public Region findByListville(Ville ville) ;
}
