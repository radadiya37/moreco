package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.EmplacementDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.Emplacement}.
 */
public interface EmplacementService {

    /**
     * Save a emplacement.
     *
     * @param emplacementDTO the entity to save.
     * @return the persisted entity.
     */
    EmplacementDTO save(EmplacementDTO emplacementDTO);

    /**
     * Get all the emplacements.
     *
     * @return the list of entities.
     */
    List<EmplacementDTO> findAll();


    /**
     * Get the "id" emplacement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<EmplacementDTO> findOne(Long id);

    /**
     * Delete the "id" emplacement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
