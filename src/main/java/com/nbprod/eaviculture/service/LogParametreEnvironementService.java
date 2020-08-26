package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.LogParametreEnvironementDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.LogParametreEnvironement}.
 */
public interface LogParametreEnvironementService {

    /**
     * Save a logParametreEnvironement.
     *
     * @param logParametreEnvironementDTO the entity to save.
     * @return the persisted entity.
     */
    LogParametreEnvironementDTO save(LogParametreEnvironementDTO logParametreEnvironementDTO);

    /**
     * Get all the logParametreEnvironements.
     *
     * @return the list of entities.
     */
    List<LogParametreEnvironementDTO> findAll();


    /**
     * Get the "id" logParametreEnvironement.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<LogParametreEnvironementDTO> findOne(Long id);

    /**
     * Delete the "id" logParametreEnvironement.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
