package com.nbprod.eaviculture.service;

import com.nbprod.eaviculture.service.dto.DepenseDTO;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link com.nbprod.eaviculture.domain.Depense}.
 */
public interface DepenseService {

    /**
     * Save a depense.
     *
     * @param depenseDTO the entity to save.
     * @return the persisted entity.
     */
    DepenseDTO save(DepenseDTO depenseDTO);

    /**
     * Get all the depenses.
     *
     * @return the list of entities.
     */
    List<DepenseDTO> findAll();


    /**
     * Get the "id" depense.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DepenseDTO> findOne(Long id);

    /**
     * Delete the "id" depense.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
