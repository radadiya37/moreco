package com.nbprod.eaviculture.repository;

import com.nbprod.eaviculture.domain.Facture;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Facture entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FactureRepository extends JpaRepository<Facture, Long> {
}
