package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.TypeProduitDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.TypeProduit}.
 */
public interface TypeProduitService {

    /**
     * Save a typeProduit.
     *
     * @param typeProduitDTO the entity to save.
     * @return the persisted entity.
     */
    TypeProduitDTO save(TypeProduitDTO typeProduitDTO);

    /**
     * Get all the typeProduits.
     *
     * @return the list of entities.
     */
    List<TypeProduitDTO> findAll();


    /**
     * Get the "id" typeProduit.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TypeProduitDTO> findOne(Long id);

    /**
     * Delete the "id" typeProduit.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
