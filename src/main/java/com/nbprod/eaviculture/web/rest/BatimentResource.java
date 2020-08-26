package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.service.BatimentService;
import com.nbprod.eaviculture.web.rest.errors.BadRequestAlertException;
import com.nbprod.eaviculture.service.dto.BatimentDTO;

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
 * REST controller for managing {@link com.nbprod.eaviculture.domain.Batiment}.
 */
@RestController
@RequestMapping("/api")
public class BatimentResource {

    private final Logger log = LoggerFactory.getLogger(BatimentResource.class);

    private static final String ENTITY_NAME = "batiment";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BatimentService batimentService;

    public BatimentResource(BatimentService batimentService) {
        this.batimentService = batimentService;
    }

    /**
     * {@code POST  /batiments} : Create a new batiment.
     *
     * @param batimentDTO the batimentDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new batimentDTO, or with status {@code 400 (Bad Request)} if the batiment has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/batiments")
    public ResponseEntity<BatimentDTO> createBatiment(@RequestBody BatimentDTO batimentDTO) throws URISyntaxException {
        log.debug("REST request to save Batiment : {}", batimentDTO);
        if (batimentDTO.getId() != null) {
            throw new BadRequestAlertException("A new batiment cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BatimentDTO result = batimentService.save(batimentDTO);
        return ResponseEntity.created(new URI("/api/batiments/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /batiments} : Updates an existing batiment.
     *
     * @param batimentDTO the batimentDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated batimentDTO,
     * or with status {@code 400 (Bad Request)} if the batimentDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the batimentDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/batiments")
    public ResponseEntity<BatimentDTO> updateBatiment(@RequestBody BatimentDTO batimentDTO) throws URISyntaxException {
        log.debug("REST request to update Batiment : {}", batimentDTO);
        if (batimentDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BatimentDTO result = batimentService.save(batimentDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, batimentDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /batiments} : get all the batiments.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of batiments in body.
     */
    @GetMapping("/batiments")
    public List<BatimentDTO> getAllBatiments() {
        log.debug("REST request to get all Batiments");
        return batimentService.findAll();
    }

    /**
     * {@code GET  /batiments/:id} : get the "id" batiment.
     *
     * @param id the id of the batimentDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the batimentDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/batiments/{id}")
    public ResponseEntity<BatimentDTO> getBatiment(@PathVariable Long id) {
        log.debug("REST request to get Batiment : {}", id);
        Optional<BatimentDTO> batimentDTO = batimentService.findOne(id);
        return ResponseUtil.wrapOrNotFound(batimentDTO);
    }

    /**
     * {@code DELETE  /batiments/:id} : delete the "id" batiment.
     *
     * @param id the id of the batimentDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/batiments/{id}")
    public ResponseEntity<Void> deleteBatiment(@PathVariable Long id) {
        log.debug("REST request to delete Batiment : {}", id);
        batimentService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
