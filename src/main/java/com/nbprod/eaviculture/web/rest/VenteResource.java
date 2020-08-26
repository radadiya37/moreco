package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.service.VenteService;
import com.nbprod.eaviculture.web.rest.errors.BadRequestAlertException;
import com.nbprod.eaviculture.service.dto.VenteDTO;

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
 * REST controller for managing {@link com.nbprod.eaviculture.domain.Vente}.
 */
@RestController
@RequestMapping("/api")
public class VenteResource {

    private final Logger log = LoggerFactory.getLogger(VenteResource.class);

    private static final String ENTITY_NAME = "vente";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final VenteService venteService;

    public VenteResource(VenteService venteService) {
        this.venteService = venteService;
    }

    /**
     * {@code POST  /ventes} : Create a new vente.
     *
     * @param venteDTO the venteDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new venteDTO, or with status {@code 400 (Bad Request)} if the vente has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/ventes")
    public ResponseEntity<VenteDTO> createVente(@RequestBody VenteDTO venteDTO) throws URISyntaxException {
        log.debug("REST request to save Vente : {}", venteDTO);
        if (venteDTO.getId() != null) {
            throw new BadRequestAlertException("A new vente cannot already have an ID", ENTITY_NAME, "idexists");
        }
        VenteDTO result = venteService.save(venteDTO);
        return ResponseEntity.created(new URI("/api/ventes/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /ventes} : Updates an existing vente.
     *
     * @param venteDTO the venteDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated venteDTO,
     * or with status {@code 400 (Bad Request)} if the venteDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the venteDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/ventes")
    public ResponseEntity<VenteDTO> updateVente(@RequestBody VenteDTO venteDTO) throws URISyntaxException {
        log.debug("REST request to update Vente : {}", venteDTO);
        if (venteDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        VenteDTO result = venteService.save(venteDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, venteDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /ventes} : get all the ventes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of ventes in body.
     */
    @GetMapping("/ventes")
    public List<VenteDTO> getAllVentes() {
        log.debug("REST request to get all Ventes");
        return venteService.findAll();
    }

    /**
     * {@code GET  /ventes/:id} : get the "id" vente.
     *
     * @param id the id of the venteDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the venteDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/ventes/{id}")
    public ResponseEntity<VenteDTO> getVente(@PathVariable Long id) {
        log.debug("REST request to get Vente : {}", id);
        Optional<VenteDTO> venteDTO = venteService.findOne(id);
        return ResponseUtil.wrapOrNotFound(venteDTO);
    }

    /**
     * {@code DELETE  /ventes/:id} : delete the "id" vente.
     *
     * @param id the id of the venteDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/ventes/{id}")
    public ResponseEntity<Void> deleteVente(@PathVariable Long id) {
        log.debug("REST request to delete Vente : {}", id);
        venteService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
