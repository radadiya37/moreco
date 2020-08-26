package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.service.LigneEclairageService;
import com.nbprod.eaviculture.web.rest.errors.BadRequestAlertException;
import com.nbprod.eaviculture.service.dto.LigneEclairageDTO;

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
 * REST controller for managing {@link com.nbprod.eaviculture.domain.LigneEclairage}.
 */
@RestController
@RequestMapping("/api")
public class LigneEclairageResource {

    private final Logger log = LoggerFactory.getLogger(LigneEclairageResource.class);

    private static final String ENTITY_NAME = "ligneEclairage";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LigneEclairageService ligneEclairageService;

    public LigneEclairageResource(LigneEclairageService ligneEclairageService) {
        this.ligneEclairageService = ligneEclairageService;
    }

    /**
     * {@code POST  /ligne-eclairages} : Create a new ligneEclairage.
     *
     * @param ligneEclairageDTO the ligneEclairageDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new ligneEclairageDTO, or with status {@code 400 (Bad Request)} if the ligneEclairage has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ligne-eclairages")
    public ResponseEntity<LigneEclairageDTO> createLigneEclairage(@RequestBody LigneEclairageDTO ligneEclairageDTO) throws URISyntaxException {
        log.debug("REST request to save LigneEclairage : {}", ligneEclairageDTO);
        if (ligneEclairageDTO.getId() != null) {
            throw new BadRequestAlertException("A new ligneEclairage cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LigneEclairageDTO result = ligneEclairageService.save(ligneEclairageDTO);
        return ResponseEntity.created(new URI("/api/ligne-eclairages/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ligne-eclairages} : Updates an existing ligneEclairage.
     *
     * @param ligneEclairageDTO the ligneEclairageDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated ligneEclairageDTO,
     * or with status {@code 400 (Bad Request)} if the ligneEclairageDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the ligneEclairageDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ligne-eclairages")
    public ResponseEntity<LigneEclairageDTO> updateLigneEclairage(@RequestBody LigneEclairageDTO ligneEclairageDTO) throws URISyntaxException {
        log.debug("REST request to update LigneEclairage : {}", ligneEclairageDTO);
        if (ligneEclairageDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LigneEclairageDTO result = ligneEclairageService.save(ligneEclairageDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, ligneEclairageDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ligne-eclairages} : get all the ligneEclairages.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ligneEclairages in body.
     */
    @GetMapping("/ligne-eclairages")
    public List<LigneEclairageDTO> getAllLigneEclairages() {
        log.debug("REST request to get all LigneEclairages");
        return ligneEclairageService.findAll();
    }

    /**
     * {@code GET  /ligne-eclairages/:id} : get the "id" ligneEclairage.
     *
     * @param id the id of the ligneEclairageDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the ligneEclairageDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ligne-eclairages/{id}")
    public ResponseEntity<LigneEclairageDTO> getLigneEclairage(@PathVariable Long id) {
        log.debug("REST request to get LigneEclairage : {}", id);
        Optional<LigneEclairageDTO> ligneEclairageDTO = ligneEclairageService.findOne(id);
        return ResponseUtil.wrapOrNotFound(ligneEclairageDTO);
    }

    /**
     * {@code DELETE  /ligne-eclairages/:id} : delete the "id" ligneEclairage.
     *
     * @param id the id of the ligneEclairageDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ligne-eclairages/{id}")
    public ResponseEntity<Void> deleteLigneEclairage(@PathVariable Long id) {
        log.debug("REST request to delete LigneEclairage : {}", id);
        ligneEclairageService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
