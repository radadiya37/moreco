package com.nbprod.eaviculture.repository;

import com.nbprod.eaviculture.domain.Depense;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Depense entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DepenseRepository extends JpaRepository<Depense, Long> {
}
