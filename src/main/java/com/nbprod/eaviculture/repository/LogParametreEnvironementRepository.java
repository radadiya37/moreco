package com.nbprod.eaviculture.repository;

import com.nbprod.eaviculture.domain.LogParametreEnvironement;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LogParametreEnvironement entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LogParametreEnvironementRepository extends JpaRepository<LogParametreEnvironement, Long> {
}
