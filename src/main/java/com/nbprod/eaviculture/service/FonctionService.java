package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.FonctionDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.Fonction}.
 */
public interface FonctionService {

    /**
     * Save a fonction.
     *
     * @param fonctionDTO the entity to save.
     * @return the persisted entity.
     */
    FonctionDTO save(FonctionDTO fonctionDTO);

    /**
     * Get all the fonctions.
     *
     * @return the list of entities.
     */
    List<FonctionDTO> findAll();


    /**
     * Get the "id" fonction.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FonctionDTO> findOne(Long id);

    /**
     * Delete the "id" fonction.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
