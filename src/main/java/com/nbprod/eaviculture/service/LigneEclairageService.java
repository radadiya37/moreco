package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.LigneEclairageDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.LigneEclairage}.
 */
public interface LigneEclairageService {

    /**
     * Save a ligneEclairage.
     *
     * @param ligneEclairageDTO the entity to save.
     * @return the persisted entity.
     */
    LigneEclairageDTO save(LigneEclairageDTO ligneEclairageDTO);

    /**
     * Get all the ligneEclairages.
     *
     * @return the list of entities.
     */
    List<LigneEclairageDTO> findAll();


    /**
     * Get the "id" ligneEclairage.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LigneEclairageDTO> findOne(Long id);

    /**
     * Delete the "id" ligneEclairage.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
