package com.nbprod.eaviculture.repository;

import com.nbprod.eaviculture.domain.Fonction;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Fonction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface FonctionRepository extends JpaRepository<Fonction, Long> {
}
