package com.dpc.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.Bundle;

@Repository

public interface BundleRepository extends CrudRepository<Bundle, Long> {

	List<Bundle> findByEnvAndKeyStartingWith(Long env, String key);

	Bundle findByKeyAndEnv(String key, Long env);

	List<Bundle> findByEnv(Long env);
}
