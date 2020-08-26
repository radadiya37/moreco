package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.service.PhaseProductionService;
import com.nbprod.eaviculture.web.rest.errors.BadRequestAlertException;
import com.nbprod.eaviculture.service.dto.PhaseProductionDTO;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link com.nbprod.eaviculture.domain.PhaseProduction}.
 */
@RestController
@RequestMapping("/api")
public class PhaseProductionResource {

    private final Logger log = LoggerFactory.getLogger(PhaseProductionResource.class);

    private static final String ENTITY_NAME = "phaseProduction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PhaseProductionService phaseProductionService;

    public PhaseProductionResource(PhaseProductionService phaseProductionService) {
        this.phaseProductionService = phaseProductionService;
    }

    /**
     * {@code POST  /phase-productions} : Create a new phaseProduction.
     *
     * @param phaseProductionDTO the phaseProductionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new phaseProductionDTO, or with status {@code 400 (Bad Request)} if the phaseProduction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/phase-productions")
    public ResponseEntity<PhaseProductionDTO> createPhaseProduction(@RequestBody PhaseProductionDTO phaseProductionDTO) throws URISyntaxException {
        log.debug("REST request to save PhaseProduction : {}", phaseProductionDTO);
        if (phaseProductionDTO.getId() != null) {
            throw new BadRequestAlertException("A new phaseProduction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PhaseProductionDTO result = phaseProductionService.save(phaseProductionDTO);
        return ResponseEntity.created(new URI("/api/phase-productions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /phase-productions} : Updates an existing phaseProduction.
     *
     * @param phaseProductionDTO the phaseProductionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated phaseProductionDTO,
     * or with status {@code 400 (Bad Request)} if the phaseProductionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the phaseProductionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/phase-productions")
    public ResponseEntity<PhaseProductionDTO> updatePhaseProduction(@RequestBody PhaseProductionDTO phaseProductionDTO) throws URISyntaxException {
        log.debug("REST request to update PhaseProduction : {}", phaseProductionDTO);
        if (phaseProductionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PhaseProductionDTO result = phaseProductionService.save(phaseProductionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, phaseProductionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /phase-productions} : get all the phaseProductions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of phaseProductions in body.
     */
    @GetMapping("/phase-productions")
    public List<PhaseProductionDTO> getAllPhaseProductions() {
        log.debug("REST request to get all PhaseProductions");
        return phaseProductionService.findAll();
    }

    /**
     * {@code GET  /phase-productions/:id} : get the "id" phaseProduction.
     *
     * @param id the id of the phaseProductionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the phaseProductionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/phase-productions/{id}")
    public ResponseEntity<PhaseProductionDTO> getPhaseProduction(@PathVariable Long id) {
        log.debug("REST request to get PhaseProduction : {}", id);
        Optional<PhaseProductionDTO> phaseProductionDTO = phaseProductionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(phaseProductionDTO);
    }

    /**
     * {@code DELETE  /phase-productions/:id} : delete the "id" phaseProduction.
     *
     * @param id the id of the phaseProductionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/phase-productions/{id}")
    public ResponseEntity<Void> deletePhaseProduction(@PathVariable Long id) {
        log.debug("REST request to delete PhaseProduction : {}", id);
        phaseProductionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
