package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.BatimentDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.Batiment}.
 */
public interface BatimentService {

    /**
     * Save a batiment.
     *
     * @param batimentDTO the entity to save.
     * @return the persisted entity.
     */
    BatimentDTO save(BatimentDTO batimentDTO);

    /**
     * Get all the batiments.
     *
     * @return the list of entities.
     */
    List<BatimentDTO> findAll();


    /**
     * Get the "id" batiment.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<BatimentDTO> findOne(Long id);

    /**
     * Delete the "id" batiment.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
