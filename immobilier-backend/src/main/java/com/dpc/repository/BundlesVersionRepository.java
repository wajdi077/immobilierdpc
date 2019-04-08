package com.dpc.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.BundlesVersion;

@Repository
public interface BundlesVersionRepository extends CrudRepository<BundlesVersion, Long> {
    BundlesVersion findTopByEnvOrderByVersionDesc(Long env);

    List<BundlesVersion> findByEnv(Long env);
}
