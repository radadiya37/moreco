package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.PhaseProductionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.PhaseProduction}.
 */
public interface PhaseProductionService {

    /**
     * Save a phaseProduction.
     *
     * @param phaseProductionDTO the entity to save.
     * @return the persisted entity.
     */
    PhaseProductionDTO save(PhaseProductionDTO phaseProductionDTO);

    /**
     * Get all the phaseProductions.
     *
     * @return the list of entities.
     */
    List<PhaseProductionDTO> findAll();


    /**
     * Get the "id" phaseProduction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PhaseProductionDTO> findOne(Long id);

    /**
     * Delete the "id" phaseProduction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
