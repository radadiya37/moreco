package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.LogParametreEnvironement;
import com.nbprod.eaviculture.repository.LogParametreEnvironementRepository;
import com.nbprod.eaviculture.service.LogParametreEnvironementService;
import com.nbprod.eaviculture.service.dto.LogParametreEnvironementDTO;
import com.nbprod.eaviculture.service.mapper.LogParametreEnvironementMapper;

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
 * Integration tests for the {@link LogParametreEnvironementResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LogParametreEnvironementResourceIT {

    private static final Integer DEFAULT_TEMPERATURE = 1;
    private static final Integer UPDATED_TEMPERATURE = 2;

    private static final Integer DEFAULT_HUMIDITE = 1;
    private static final Integer UPDATED_HUMIDITE = 2;

    private static final LocalDate DEFAULT_DATE_LOG = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_LOG = LocalDate.now(ZoneId.systemDefault());

    @Autowired
    private LogParametreEnvironementRepository logParametreEnvironementRepository;

    @Autowired
    private LogParametreEnvironementMapper logParametreEnvironementMapper;

    @Autowired
    private LogParametreEnvironementService logParametreEnvironementService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLogParametreEnvironementMockMvc;

    private LogParametreEnvironement logParametreEnvironement;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LogParametreEnvironement createEntity(EntityManager em) {
        LogParametreEnvironement logParametreEnvironement = new LogParametreEnvironement()
            .temperature(DEFAULT_TEMPERATURE)
            .humidite(DEFAULT_HUMIDITE)
            .dateLog(DEFAULT_DATE_LOG);
        return logParametreEnvironement;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LogParametreEnvironement createUpdatedEntity(EntityManager em) {
        LogParametreEnvironement logParametreEnvironement = new LogParametreEnvironement()
            .temperature(UPDATED_TEMPERATURE)
            .humidite(UPDATED_HUMIDITE)
            .dateLog(UPDATED_DATE_LOG);
        return logParametreEnvironement;
    }

    @BeforeEach
    public void initTest() {
        logParametreEnvironement = createEntity(em);
    }

    @Test
    @Transactional
    public void createLogParametreEnvironement() throws Exception {
        int databaseSizeBeforeCreate = logParametreEnvironementRepository.findAll().size();
        // Create the LogParametreEnvironement
        LogParametreEnvironementDTO logParametreEnvironementDTO = logParametreEnvironementMapper.toDto(logParametreEnvironement);
        restLogParametreEnvironementMockMvc.perform(post("/api/log-parametre-environements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logParametreEnvironementDTO)))
            .andExpect(status().isCreated());

        // Validate the LogParametreEnvironement in the database
        List<LogParametreEnvironement> logParametreEnvironementList = logParametreEnvironementRepository.findAll();
        assertThat(logParametreEnvironementList).hasSize(databaseSizeBeforeCreate + 1);
        LogParametreEnvironement testLogParametreEnvironement = logParametreEnvironementList.get(logParametreEnvironementList.size() - 1);
        assertThat(testLogParametreEnvironement.getTemperature()).isEqualTo(DEFAULT_TEMPERATURE);
        assertThat(testLogParametreEnvironement.getHumidite()).isEqualTo(DEFAULT_HUMIDITE);
        assertThat(testLogParametreEnvironement.getDateLog()).isEqualTo(DEFAULT_DATE_LOG);
    }

    @Test
    @Transactional
    public void createLogParametreEnvironementWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = logParametreEnvironementRepository.findAll().size();

        // Create the LogParametreEnvironement with an existing ID
        logParametreEnvironement.setId(1L);
        LogParametreEnvironementDTO logParametreEnvironementDTO = logParametreEnvironementMapper.toDto(logParametreEnvironement);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLogParametreEnvironementMockMvc.perform(post("/api/log-parametre-environements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logParametreEnvironementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LogParametreEnvironement in the database
        List<LogParametreEnvironement> logParametreEnvironementList = logParametreEnvironementRepository.findAll();
        assertThat(logParametreEnvironementList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLogParametreEnvironements() throws Exception {
        // Initialize the database
        logParametreEnvironementRepository.saveAndFlush(logParametreEnvironement);

        // Get all the logParametreEnvironementList
        restLogParametreEnvironementMockMvc.perform(get("/api/log-parametre-environements?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(logParametreEnvironement.getId().intValue())))
            .andExpect(jsonPath("$.[*].temperature").value(hasItem(DEFAULT_TEMPERATURE)))
            .andExpect(jsonPath("$.[*].humidite").value(hasItem(DEFAULT_HUMIDITE)))
            .andExpect(jsonPath("$.[*].dateLog").value(hasItem(DEFAULT_DATE_LOG.toString())));
    }
    
    @Test
    @Transactional
    public void getLogParametreEnvironement() throws Exception {
        // Initialize the database
        logParametreEnvironementRepository.saveAndFlush(logParametreEnvironement);

        // Get the logParametreEnvironement
        restLogParametreEnvironementMockMvc.perform(get("/api/log-parametre-environements/{id}", logParametreEnvironement.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(logParametreEnvironement.getId().intValue()))
            .andExpect(jsonPath("$.temperature").value(DEFAULT_TEMPERATURE))
            .andExpect(jsonPath("$.humidite").value(DEFAULT_HUMIDITE))
            .andExpect(jsonPath("$.dateLog").value(DEFAULT_DATE_LOG.toString()));
    }
    @Test
    @Transactional
    public void getNonExistingLogParametreEnvironement() throws Exception {
        // Get the logParametreEnvironement
        restLogParametreEnvironementMockMvc.perform(get("/api/log-parametre-environements/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLogParametreEnvironement() throws Exception {
        // Initialize the database
        logParametreEnvironementRepository.saveAndFlush(logParametreEnvironement);

        int databaseSizeBeforeUpdate = logParametreEnvironementRepository.findAll().size();

        // Update the logParametreEnvironement
        LogParametreEnvironement updatedLogParametreEnvironement = logParametreEnvironementRepository.findById(logParametreEnvironement.getId()).get();
        // Disconnect from session so that the updates on updatedLogParametreEnvironement are not directly saved in db
        em.detach(updatedLogParametreEnvironement);
        updatedLogParametreEnvironement
            .temperature(UPDATED_TEMPERATURE)
            .humidite(UPDATED_HUMIDITE)
            .dateLog(UPDATED_DATE_LOG);
        LogParametreEnvironementDTO logParametreEnvironementDTO = logParametreEnvironementMapper.toDto(updatedLogParametreEnvironement);

        restLogParametreEnvironementMockMvc.perform(put("/api/log-parametre-environements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logParametreEnvironementDTO)))
            .andExpect(status().isOk());

        // Validate the LogParametreEnvironement in the database
        List<LogParametreEnvironement> logParametreEnvironementList = logParametreEnvironementRepository.findAll();
        assertThat(logParametreEnvironementList).hasSize(databaseSizeBeforeUpdate);
        LogParametreEnvironement testLogParametreEnvironement = logParametreEnvironementList.get(logParametreEnvironementList.size() - 1);
        assertThat(testLogParametreEnvironement.getTemperature()).isEqualTo(UPDATED_TEMPERATURE);
        assertThat(testLogParametreEnvironement.getHumidite()).isEqualTo(UPDATED_HUMIDITE);
        assertThat(testLogParametreEnvironement.getDateLog()).isEqualTo(UPDATED_DATE_LOG);
    }

    @Test
    @Transactional
    public void updateNonExistingLogParametreEnvironement() throws Exception {
        int databaseSizeBeforeUpdate = logParametreEnvironementRepository.findAll().size();

        // Create the LogParametreEnvironement
        LogParametreEnvironementDTO logParametreEnvironementDTO = logParametreEnvironementMapper.toDto(logParametreEnvironement);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLogParametreEnvironementMockMvc.perform(put("/api/log-parametre-environements")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(logParametreEnvironementDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LogParametreEnvironement in the database
        List<LogParametreEnvironement> logParametreEnvironementList = logParametreEnvironementRepository.findAll();
        assertThat(logParametreEnvironementList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLogParametreEnvironement() throws Exception {
        // Initialize the database
        logParametreEnvironementRepository.saveAndFlush(logParametreEnvironement);

        int databaseSizeBeforeDelete = logParametreEnvironementRepository.findAll().size();

        // Delete the logParametreEnvironement
        restLogParametreEnvironementMockMvc.perform(delete("/api/log-parametre-environements/{id}", logParametreEnvironement.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LogParametreEnvironement> logParametreEnvironementList = logParametreEnvironementRepository.findAll();
        assertThat(logParametreEnvironementList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
