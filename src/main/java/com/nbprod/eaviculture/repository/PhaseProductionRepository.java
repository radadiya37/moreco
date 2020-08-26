package com.nbprod.eaviculture.repository;

import com.nbprod.eaviculture.domain.PhaseProduction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PhaseProduction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PhaseProductionRepository extends JpaRepository<PhaseProduction, Long> {
}
