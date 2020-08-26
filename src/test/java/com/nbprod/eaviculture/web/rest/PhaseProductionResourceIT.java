package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.PhaseProduction;
import com.nbprod.eaviculture.repository.PhaseProductionRepository;
import com.nbprod.eaviculture.service.PhaseProductionService;
import com.nbprod.eaviculture.service.dto.PhaseProductionDTO;
import com.nbprod.eaviculture.service.mapper.PhaseProductionMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityManager;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link PhaseProductionResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class PhaseProductionResourceIT {

    private static final String DEFAULT_CODE_PHASE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_PHASE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_DEBUT = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEBUT = LocalDate.now(ZoneId.systemDefault());

    private static final LocalDate DEFAULT_DATE_FIN = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FIN = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_NOMBRE_POULETS = 1;
    private static final Integer UPDATED_NOMBRE_POULETS = 2;

    private static final Integer DEFAULT_NOMBRE_DECES = 1;
    private static final Integer UPDATED_NOMBRE_DECES = 2;

    @Autowired
    private PhaseProductionRepository phaseProductionRepository;

    @Autowired
    private PhaseProductionMapper phaseProductionMapper;

    @Autowired
    private PhaseProductionService phaseProductionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restPhaseProductionMockMvc;

    private PhaseProduction phaseProduction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhaseProduction createEntity(EntityManager em) {
        PhaseProduction phaseProduction = new PhaseProduction()
            .codePhase(DEFAULT_CODE_PHASE)
            .dateDebut(DEFAULT_DATE_DEBUT)
            .dateFin(DEFAULT_DATE_FIN)
            .nombrePoulets(DEFAULT_NOMBRE_POULETS)
            .nombreDeces(DEFAULT_NOMBRE_DECES);
        return phaseProduction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static PhaseProduction createUpdatedEntity(EntityManager em) {
        PhaseProduction phaseProduction = new PhaseProduction()
            .codePhase(UPDATED_CODE_PHASE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .nombrePoulets(UPDATED_NOMBRE_POULETS)
            .nombreDeces(UPDATED_NOMBRE_DECES);
        return phaseProduction;
    }

    @BeforeEach
    public void initTest() {
        phaseProduction = createEntity(em);
    }

    @Test
    @Transactional
    public void createPhaseProduction() throws Exception {
        int databaseSizeBeforeCreate = phaseProductionRepository.findAll().size();
        // Create the PhaseProduction
        PhaseProductionDTO phaseProductionDTO = phaseProductionMapper.toDto(phaseProduction);
        restPhaseProductionMockMvc.perform(post("/api/phase-productions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phaseProductionDTO)))
            .andExpect(status().isCreated());

        // Validate the PhaseProduction in the database
        List<PhaseProduction> phaseProductionList = phaseProductionRepository.findAll();
        assertThat(phaseProductionList).hasSize(databaseSizeBeforeCreate + 1);
        PhaseProduction testPhaseProduction = phaseProductionList.get(phaseProductionList.size() - 1);
        assertThat(testPhaseProduction.getCodePhase()).isEqualTo(DEFAULT_CODE_PHASE);
        assertThat(testPhaseProduction.getDateDebut()).isEqualTo(DEFAULT_DATE_DEBUT);
        assertThat(testPhaseProduction.getDateFin()).isEqualTo(DEFAULT_DATE_FIN);
        assertThat(testPhaseProduction.getNombrePoulets()).isEqualTo(DEFAULT_NOMBRE_POULETS);
        assertThat(testPhaseProduction.getNombreDeces()).isEqualTo(DEFAULT_NOMBRE_DECES);
    }

    @Test
    @Transactional
    public void createPhaseProductionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = phaseProductionRepository.findAll().size();

        // Create the PhaseProduction with an existing ID
        phaseProduction.setId(1L);
        PhaseProductionDTO phaseProductionDTO = phaseProductionMapper.toDto(phaseProduction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restPhaseProductionMockMvc.perform(post("/api/phase-productions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phaseProductionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhaseProduction in the database
        List<PhaseProduction> phaseProductionList = phaseProductionRepository.findAll();
        assertThat(phaseProductionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllPhaseProductions() throws Exception {
        // Initialize the database
        phaseProductionRepository.saveAndFlush(phaseProduction);

        // Get all the phaseProductionList
        restPhaseProductionMockMvc.perform(get("/api/phase-productions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(phaseProduction.getId().intValue())))
            .andExpect(jsonPath("$.[*].codePhase").value(hasItem(DEFAULT_CODE_PHASE)))
            .andExpect(jsonPath("$.[*].dateDebut").value(hasItem(DEFAULT_DATE_DEBUT.toString())))
            .andExpect(jsonPath("$.[*].dateFin").value(hasItem(DEFAULT_DATE_FIN.toString())))
            .andExpect(jsonPath("$.[*].nombrePoulets").value(hasItem(DEFAULT_NOMBRE_POULETS)))
            .andExpect(jsonPath("$.[*].nombreDeces").value(hasItem(DEFAULT_NOMBRE_DECES)));
    }
    
    @Test
    @Transactional
    public void getPhaseProduction() throws Exception {
        // Initialize the database
        phaseProductionRepository.saveAndFlush(phaseProduction);

        // Get the phaseProduction
        restPhaseProductionMockMvc.perform(get("/api/phase-productions/{id}", phaseProduction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(phaseProduction.getId().intValue()))
            .andExpect(jsonPath("$.codePhase").value(DEFAULT_CODE_PHASE))
            .andExpect(jsonPath("$.dateDebut").value(DEFAULT_DATE_DEBUT.toString()))
            .andExpect(jsonPath("$.dateFin").value(DEFAULT_DATE_FIN.toString()))
            .andExpect(jsonPath("$.nombrePoulets").value(DEFAULT_NOMBRE_POULETS))
            .andExpect(jsonPath("$.nombreDeces").value(DEFAULT_NOMBRE_DECES));
    }
    @Test
    @Transactional
    public void getNonExistingPhaseProduction() throws Exception {
        // Get the phaseProduction
        restPhaseProductionMockMvc.perform(get("/api/phase-productions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updatePhaseProduction() throws Exception {
        // Initialize the database
        phaseProductionRepository.saveAndFlush(phaseProduction);

        int databaseSizeBeforeUpdate = phaseProductionRepository.findAll().size();

        // Update the phaseProduction
        PhaseProduction updatedPhaseProduction = phaseProductionRepository.findById(phaseProduction.getId()).get();
        // Disconnect from session so that the updates on updatedPhaseProduction are not directly saved in db
        em.detach(updatedPhaseProduction);
        updatedPhaseProduction
            .codePhase(UPDATED_CODE_PHASE)
            .dateDebut(UPDATED_DATE_DEBUT)
            .dateFin(UPDATED_DATE_FIN)
            .nombrePoulets(UPDATED_NOMBRE_POULETS)
            .nombreDeces(UPDATED_NOMBRE_DECES);
        PhaseProductionDTO phaseProductionDTO = phaseProductionMapper.toDto(updatedPhaseProduction);

        restPhaseProductionMockMvc.perform(put("/api/phase-productions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phaseProductionDTO)))
            .andExpect(status().isOk());

        // Validate the PhaseProduction in the database
        List<PhaseProduction> phaseProductionList = phaseProductionRepository.findAll();
        assertThat(phaseProductionList).hasSize(databaseSizeBeforeUpdate);
        PhaseProduction testPhaseProduction = phaseProductionList.get(phaseProductionList.size() - 1);
        assertThat(testPhaseProduction.getCodePhase()).isEqualTo(UPDATED_CODE_PHASE);
        assertThat(testPhaseProduction.getDateDebut()).isEqualTo(UPDATED_DATE_DEBUT);
        assertThat(testPhaseProduction.getDateFin()).isEqualTo(UPDATED_DATE_FIN);
        assertThat(testPhaseProduction.getNombrePoulets()).isEqualTo(UPDATED_NOMBRE_POULETS);
        assertThat(testPhaseProduction.getNombreDeces()).isEqualTo(UPDATED_NOMBRE_DECES);
    }

    @Test
    @Transactional
    public void updateNonExistingPhaseProduction() throws Exception {
        int databaseSizeBeforeUpdate = phaseProductionRepository.findAll().size();

        // Create the PhaseProduction
        PhaseProductionDTO phaseProductionDTO = phaseProductionMapper.toDto(phaseProduction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restPhaseProductionMockMvc.perform(put("/api/phase-productions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(phaseProductionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the PhaseProduction in the database
        List<PhaseProduction> phaseProductionList = phaseProductionRepository.findAll();
        assertThat(phaseProductionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deletePhaseProduction() throws Exception {
        // Initialize the database
        phaseProductionRepository.saveAndFlush(phaseProduction);

        int databaseSizeBeforeDelete = phaseProductionRepository.findAll().size();

        // Delete the phaseProduction
        restPhaseProductionMockMvc.perform(delete("/api/phase-productions/{id}", phaseProduction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<PhaseProduction> phaseProductionList = phaseProductionRepository.findAll();
        assertThat(phaseProductionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
