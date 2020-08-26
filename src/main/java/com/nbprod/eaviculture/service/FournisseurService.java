package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.FournisseurDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.Fournisseur}.
 */
public interface FournisseurService {

    /**
     * Save a fournisseur.
     *
     * @param fournisseurDTO the entity to save.
     * @return the persisted entity.
     */
    FournisseurDTO save(FournisseurDTO fournisseurDTO);

    /**
     * Get all the fournisseurs.
     *
     * @return the list of entities.
     */
    List<FournisseurDTO> findAll();


    /**
     * Get the "id" fournisseur.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<FournisseurDTO> findOne(Long id);

    /**
     * Delete the "id" fournisseur.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
