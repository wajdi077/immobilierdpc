package com.dpc.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.dpc.domain.AccessUrl;
import com.dpc.domain.Authority;



public interface IAccessUrl extends JpaRepository<AccessUrl, Long> {

	@Query("SELECT p FROM AccessUrl p WHERE p.authority = :authority ")
    public List<AccessUrl> findbyauthority(@Param("authority") Authority authority);
}
