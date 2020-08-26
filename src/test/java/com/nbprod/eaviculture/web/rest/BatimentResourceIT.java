package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.Batiment;
import com.nbprod.eaviculture.repository.BatimentRepository;
import com.nbprod.eaviculture.service.BatimentService;
import com.nbprod.eaviculture.service.dto.BatimentDTO;
import com.nbprod.eaviculture.service.mapper.BatimentMapper;

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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link BatimentResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class BatimentResourceIT {

    private static final String DEFAULT_CODE_BATIMENT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_BATIMENT = "BBBBBBBBBB";

    private static final Long DEFAULT_SURFACE = 1L;
    private static final Long UPDATED_SURFACE = 2L;

    @Autowired
    private BatimentRepository batimentRepository;

    @Autowired
    private BatimentMapper batimentMapper;

    @Autowired
    private BatimentService batimentService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restBatimentMockMvc;

    private Batiment batiment;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Batiment createEntity(EntityManager em) {
        Batiment batiment = new Batiment()
            .codeBatiment(DEFAULT_CODE_BATIMENT)
            .surface(DEFAULT_SURFACE);
        return batiment;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Batiment createUpdatedEntity(EntityManager em) {
        Batiment batiment = new Batiment()
            .codeBatiment(UPDATED_CODE_BATIMENT)
            .surface(UPDATED_SURFACE);
        return batiment;
    }

    @BeforeEach
    public void initTest() {
        batiment = createEntity(em);
    }

    @Test
    @Transactional
    public void createBatiment() throws Exception {
        int databaseSizeBeforeCreate = batimentRepository.findAll().size();
        // Create the Batiment
        BatimentDTO batimentDTO = batimentMapper.toDto(batiment);
        restBatimentMockMvc.perform(post("/api/batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(batimentDTO)))
            .andExpect(status().isCreated());

        // Validate the Batiment in the database
        List<Batiment> batimentList = batimentRepository.findAll();
        assertThat(batimentList).hasSize(databaseSizeBeforeCreate + 1);
        Batiment testBatiment = batimentList.get(batimentList.size() - 1);
        assertThat(testBatiment.getCodeBatiment()).isEqualTo(DEFAULT_CODE_BATIMENT);
        assertThat(testBatiment.getSurface()).isEqualTo(DEFAULT_SURFACE);
    }

    @Test
    @Transactional
    public void createBatimentWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = batimentRepository.findAll().size();

        // Create the Batiment with an existing ID
        batiment.setId(1L);
        BatimentDTO batimentDTO = batimentMapper.toDto(batiment);

        // An entity with an existing ID cannot be created, so this API call must fail
        restBatimentMockMvc.perform(post("/api/batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(batimentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batiment in the database
        List<Batiment> batimentList = batimentRepository.findAll();
        assertThat(batimentList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllBatiments() throws Exception {
        // Initialize the database
        batimentRepository.saveAndFlush(batiment);

        // Get all the batimentList
        restBatimentMockMvc.perform(get("/api/batiments?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(batiment.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeBatiment").value(hasItem(DEFAULT_CODE_BATIMENT)))
            .andExpect(jsonPath("$.[*].surface").value(hasItem(DEFAULT_SURFACE.intValue())));
    }
    
    @Test
    @Transactional
    public void getBatiment() throws Exception {
        // Initialize the database
        batimentRepository.saveAndFlush(batiment);

        // Get the batiment
        restBatimentMockMvc.perform(get("/api/batiments/{id}", batiment.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(batiment.getId().intValue()))
            .andExpect(jsonPath("$.codeBatiment").value(DEFAULT_CODE_BATIMENT))
            .andExpect(jsonPath("$.surface").value(DEFAULT_SURFACE.intValue()));
    }
    @Test
    @Transactional
    public void getNonExistingBatiment() throws Exception {
        // Get the batiment
        restBatimentMockMvc.perform(get("/api/batiments/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateBatiment() throws Exception {
        // Initialize the database
        batimentRepository.saveAndFlush(batiment);

        int databaseSizeBeforeUpdate = batimentRepository.findAll().size();

        // Update the batiment
        Batiment updatedBatiment = batimentRepository.findById(batiment.getId()).get();
        // Disconnect from session so that the updates on updatedBatiment are not directly saved in db
        em.detach(updatedBatiment);
        updatedBatiment
            .codeBatiment(UPDATED_CODE_BATIMENT)
            .surface(UPDATED_SURFACE);
        BatimentDTO batimentDTO = batimentMapper.toDto(updatedBatiment);

        restBatimentMockMvc.perform(put("/api/batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(batimentDTO)))
            .andExpect(status().isOk());

        // Validate the Batiment in the database
        List<Batiment> batimentList = batimentRepository.findAll();
        assertThat(batimentList).hasSize(databaseSizeBeforeUpdate);
        Batiment testBatiment = batimentList.get(batimentList.size() - 1);
        assertThat(testBatiment.getCodeBatiment()).isEqualTo(UPDATED_CODE_BATIMENT);
        assertThat(testBatiment.getSurface()).isEqualTo(UPDATED_SURFACE);
    }

    @Test
    @Transactional
    public void updateNonExistingBatiment() throws Exception {
        int databaseSizeBeforeUpdate = batimentRepository.findAll().size();

        // Create the Batiment
        BatimentDTO batimentDTO = batimentMapper.toDto(batiment);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restBatimentMockMvc.perform(put("/api/batiments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(batimentDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Batiment in the database
        List<Batiment> batimentList = batimentRepository.findAll();
        assertThat(batimentList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteBatiment() throws Exception {
        // Initialize the database
        batimentRepository.saveAndFlush(batiment);

        int databaseSizeBeforeDelete = batimentRepository.findAll().size();

        // Delete the batiment
        restBatimentMockMvc.perform(delete("/api/batiments/{id}", batiment.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Batiment> batimentList = batimentRepository.findAll();
        assertThat(batimentList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
