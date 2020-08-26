package com.nbprod.eaviculture.repository;

import com.nbprod.eaviculture.domain.LigneEclairage;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the LigneEclairage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LigneEclairageRepository extends JpaRepository<LigneEclairage, Long> {
}
