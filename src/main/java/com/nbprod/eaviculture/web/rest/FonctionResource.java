package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.service.FonctionService;
import com.nbprod.eaviculture.web.rest.errors.BadRequestAlertException;
import com.nbprod.eaviculture.service.dto.FonctionDTO;

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
 * REST controller for managing {@link com.nbprod.eaviculture.domain.Fonction}.
 */
@RestController
@RequestMapping("/api")
public class FonctionResource {

    private final Logger log = LoggerFactory.getLogger(FonctionResource.class);

    private static final String ENTITY_NAME = "fonction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final FonctionService fonctionService;

    public FonctionResource(FonctionService fonctionService) {
        this.fonctionService = fonctionService;
    }

    /**
     * {@code POST  /fonctions} : Create a new fonction.
     *
     * @param fonctionDTO the fonctionDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new fonctionDTO, or with status {@code 400 (Bad Request)} if the fonction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/fonctions")
    public ResponseEntity<FonctionDTO> createFonction(@RequestBody FonctionDTO fonctionDTO) throws URISyntaxException {
        log.debug("REST request to save Fonction : {}", fonctionDTO);
        if (fonctionDTO.getId() != null) {
            throw new BadRequestAlertException("A new fonction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        FonctionDTO result = fonctionService.save(fonctionDTO);
        return ResponseEntity.created(new URI("/api/fonctions/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /fonctions} : Updates an existing fonction.
     *
     * @param fonctionDTO the fonctionDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated fonctionDTO,
     * or with status {@code 400 (Bad Request)} if the fonctionDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the fonctionDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/fonctions")
    public ResponseEntity<FonctionDTO> updateFonction(@RequestBody FonctionDTO fonctionDTO) throws URISyntaxException {
        log.debug("REST request to update Fonction : {}", fonctionDTO);
        if (fonctionDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        FonctionDTO result = fonctionService.save(fonctionDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, fonctionDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /fonctions} : get all the fonctions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of fonctions in body.
     */
    @GetMapping("/fonctions")
    public List<FonctionDTO> getAllFonctions() {
        log.debug("REST request to get all Fonctions");
        return fonctionService.findAll();
    }

    /**
     * {@code GET  /fonctions/:id} : get the "id" fonction.
     *
     * @param id the id of the fonctionDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the fonctionDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/fonctions/{id}")
    public ResponseEntity<FonctionDTO> getFonction(@PathVariable Long id) {
        log.debug("REST request to get Fonction : {}", id);
        Optional<FonctionDTO> fonctionDTO = fonctionService.findOne(id);
        return ResponseUtil.wrapOrNotFound(fonctionDTO);
    }

    /**
     * {@code DELETE  /fonctions/:id} : delete the "id" fonction.
     *
     * @param id the id of the fonctionDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/fonctions/{id}")
    public ResponseEntity<Void> deleteFonction(@PathVariable Long id) {
        log.debug("REST request to delete Fonction : {}", id);
        fonctionService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
