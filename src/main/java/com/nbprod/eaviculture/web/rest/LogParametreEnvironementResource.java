package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.service.LogParametreEnvironementService;
import com.nbprod.eaviculture.web.rest.errors.BadRequestAlertException;
import com.nbprod.eaviculture.service.dto.LogParametreEnvironementDTO;

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
 * REST controller for managing {@link com.nbprod.eaviculture.domain.LogParametreEnvironement}.
 */
@RestController
@RequestMapping("/api")
public class LogParametreEnvironementResource {

    private final Logger log = LoggerFactory.getLogger(LogParametreEnvironementResource.class);

    private static final String ENTITY_NAME = "logParametreEnvironement";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LogParametreEnvironementService logParametreEnvironementService;

    public LogParametreEnvironementResource(LogParametreEnvironementService logParametreEnvironementService) {
        this.logParametreEnvironementService = logParametreEnvironementService;
    }

    /**
     * {@code POST  /log-parametre-environements} : Create a new logParametreEnvironement.
     *
     * @param logParametreEnvironementDTO the logParametreEnvironementDTO to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new logParametreEnvironementDTO, or with status {@code 400 (Bad Request)} if the logParametreEnvironement has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/log-parametre-environements")
    public ResponseEntity<LogParametreEnvironementDTO> createLogParametreEnvironement(@RequestBody LogParametreEnvironementDTO logParametreEnvironementDTO) throws URISyntaxException {
        log.debug("REST request to save LogParametreEnvironement : {}", logParametreEnvironementDTO);
        if (logParametreEnvironementDTO.getId() != null) {
            throw new BadRequestAlertException("A new logParametreEnvironement cannot already have an ID", ENTITY_NAME, "idexists");
        }
        LogParametreEnvironementDTO result = logParametreEnvironementService.save(logParametreEnvironementDTO);
        return ResponseEntity.created(new URI("/api/log-parametre-environements/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, false, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /log-parametre-environements} : Updates an existing logParametreEnvironement.
     *
     * @param logParametreEnvironementDTO the logParametreEnvironementDTO to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated logParametreEnvironementDTO,
     * or with status {@code 400 (Bad Request)} if the logParametreEnvironementDTO is not valid,
     * or with status {@code 500 (Internal Server Error)} if the logParametreEnvironementDTO couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/log-parametre-environements")
    public ResponseEntity<LogParametreEnvironementDTO> updateLogParametreEnvironement(@RequestBody LogParametreEnvironementDTO logParametreEnvironementDTO) throws URISyntaxException {
        log.debug("REST request to update LogParametreEnvironement : {}", logParametreEnvironementDTO);
        if (logParametreEnvironementDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        LogParametreEnvironementDTO result = logParametreEnvironementService.save(logParametreEnvironementDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, false, ENTITY_NAME, logParametreEnvironementDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /log-parametre-environements} : get all the logParametreEnvironements.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of logParametreEnvironements in body.
     */
    @GetMapping("/log-parametre-environements")
    public List<LogParametreEnvironementDTO> getAllLogParametreEnvironements() {
        log.debug("REST request to get all LogParametreEnvironements");
        return logParametreEnvironementService.findAll();
    }

    /**
     * {@code GET  /log-parametre-environements/:id} : get the "id" logParametreEnvironement.
     *
     * @param id the id of the logParametreEnvironementDTO to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the logParametreEnvironementDTO, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/log-parametre-environements/{id}")
    public ResponseEntity<LogParametreEnvironementDTO> getLogParametreEnvironement(@PathVariable Long id) {
        log.debug("REST request to get LogParametreEnvironement : {}", id);
        Optional<LogParametreEnvironementDTO> logParametreEnvironementDTO = logParametreEnvironementService.findOne(id);
        return ResponseUtil.wrapOrNotFound(logParametreEnvironementDTO);
    }

    /**
     * {@code DELETE  /log-parametre-environements/:id} : delete the "id" logParametreEnvironement.
     *
     * @param id the id of the logParametreEnvironementDTO to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/log-parametre-environements/{id}")
    public ResponseEntity<Void> deleteLogParametreEnvironement(@PathVariable Long id) {
        log.debug("REST request to delete LogParametreEnvironement : {}", id);
        logParametreEnvironementService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, false, ENTITY_NAME, id.toString())).build();
    }
}
