package com.dpc.repository;

import java.util.UUID;



import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dpc.domain.ValueHisto;

@Repository
public interface ValueHistoRepository extends CrudRepository<ValueHisto, Long> {

    ValueHisto findTopByBundleIdOrderByVersionDesc(Long bundle);

    ValueHisto findByBundleIdAndVersion(Long bundle, Long version);
}
