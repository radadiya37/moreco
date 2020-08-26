package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.Depense;
import com.nbprod.eaviculture.repository.DepenseRepository;
import com.nbprod.eaviculture.service.DepenseService;
import com.nbprod.eaviculture.service.dto.DepenseDTO;
import com.nbprod.eaviculture.service.mapper.DepenseMapper;

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
 * Integration tests for the {@link DepenseResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class DepenseResourceIT {

    private static final String DEFAULT_CODE_DEPENSE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_DEPENSE = "BBBBBBBBBB";

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    private static final LocalDate DEFAULT_DATE_DEMANDE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_DEMANDE = LocalDate.now(ZoneId.systemDefault());

    private static final Integer DEFAULT_TVA = 1;
    private static final Integer UPDATED_TVA = 2;

    private static final String DEFAULT_ETAT_DEPENSE = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_DEPENSE = "BBBBBBBBBB";

    @Autowired
    private DepenseRepository depenseRepository;

    @Autowired
    private DepenseMapper depenseMapper;

    @Autowired
    private DepenseService depenseService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restDepenseMockMvc;

    private Depense depense;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Depense createEntity(EntityManager em) {
        Depense depense = new Depense()
            .codeDepense(DEFAULT_CODE_DEPENSE)
            .quantite(DEFAULT_QUANTITE)
            .dateDemande(DEFAULT_DATE_DEMANDE)
            .tva(DEFAULT_TVA)
            .etatDepense(DEFAULT_ETAT_DEPENSE);
        return depense;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Depense createUpdatedEntity(EntityManager em) {
        Depense depense = new Depense()
            .codeDepense(UPDATED_CODE_DEPENSE)
            .quantite(UPDATED_QUANTITE)
            .dateDemande(UPDATED_DATE_DEMANDE)
            .tva(UPDATED_TVA)
            .etatDepense(UPDATED_ETAT_DEPENSE);
        return depense;
    }

    @BeforeEach
    public void initTest() {
        depense = createEntity(em);
    }

    @Test
    @Transactional
    public void createDepense() throws Exception {
        int databaseSizeBeforeCreate = depenseRepository.findAll().size();
        // Create the Depense
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);
        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isCreated());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeCreate + 1);
        Depense testDepense = depenseList.get(depenseList.size() - 1);
        assertThat(testDepense.getCodeDepense()).isEqualTo(DEFAULT_CODE_DEPENSE);
        assertThat(testDepense.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testDepense.getDateDemande()).isEqualTo(DEFAULT_DATE_DEMANDE);
        assertThat(testDepense.getTva()).isEqualTo(DEFAULT_TVA);
        assertThat(testDepense.getEtatDepense()).isEqualTo(DEFAULT_ETAT_DEPENSE);
    }

    @Test
    @Transactional
    public void createDepenseWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = depenseRepository.findAll().size();

        // Create the Depense with an existing ID
        depense.setId(1L);
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);

        // An entity with an existing ID cannot be created, so this API call must fail
        restDepenseMockMvc.perform(post("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllDepenses() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        // Get all the depenseList
        restDepenseMockMvc.perform(get("/api/depenses?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(depense.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeDepense").value(hasItem(DEFAULT_CODE_DEPENSE)))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)))
            .andExpect(jsonPath("$.[*].dateDemande").value(hasItem(DEFAULT_DATE_DEMANDE.toString())))
            .andExpect(jsonPath("$.[*].tva").value(hasItem(DEFAULT_TVA)))
            .andExpect(jsonPath("$.[*].etatDepense").value(hasItem(DEFAULT_ETAT_DEPENSE)));
    }
    
    @Test
    @Transactional
    public void getDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        // Get the depense
        restDepenseMockMvc.perform(get("/api/depenses/{id}", depense.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(depense.getId().intValue()))
            .andExpect(jsonPath("$.codeDepense").value(DEFAULT_CODE_DEPENSE))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE))
            .andExpect(jsonPath("$.dateDemande").value(DEFAULT_DATE_DEMANDE.toString()))
            .andExpect(jsonPath("$.tva").value(DEFAULT_TVA))
            .andExpect(jsonPath("$.etatDepense").value(DEFAULT_ETAT_DEPENSE));
    }
    @Test
    @Transactional
    public void getNonExistingDepense() throws Exception {
        // Get the depense
        restDepenseMockMvc.perform(get("/api/depenses/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        int databaseSizeBeforeUpdate = depenseRepository.findAll().size();

        // Update the depense
        Depense updatedDepense = depenseRepository.findById(depense.getId()).get();
        // Disconnect from session so that the updates on updatedDepense are not directly saved in db
        em.detach(updatedDepense);
        updatedDepense
            .codeDepense(UPDATED_CODE_DEPENSE)
            .quantite(UPDATED_QUANTITE)
            .dateDemande(UPDATED_DATE_DEMANDE)
            .tva(UPDATED_TVA)
            .etatDepense(UPDATED_ETAT_DEPENSE);
        DepenseDTO depenseDTO = depenseMapper.toDto(updatedDepense);

        restDepenseMockMvc.perform(put("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isOk());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeUpdate);
        Depense testDepense = depenseList.get(depenseList.size() - 1);
        assertThat(testDepense.getCodeDepense()).isEqualTo(UPDATED_CODE_DEPENSE);
        assertThat(testDepense.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testDepense.getDateDemande()).isEqualTo(UPDATED_DATE_DEMANDE);
        assertThat(testDepense.getTva()).isEqualTo(UPDATED_TVA);
        assertThat(testDepense.getEtatDepense()).isEqualTo(UPDATED_ETAT_DEPENSE);
    }

    @Test
    @Transactional
    public void updateNonExistingDepense() throws Exception {
        int databaseSizeBeforeUpdate = depenseRepository.findAll().size();

        // Create the Depense
        DepenseDTO depenseDTO = depenseMapper.toDto(depense);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restDepenseMockMvc.perform(put("/api/depenses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(depenseDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Depense in the database
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteDepense() throws Exception {
        // Initialize the database
        depenseRepository.saveAndFlush(depense);

        int databaseSizeBeforeDelete = depenseRepository.findAll().size();

        // Delete the depense
        restDepenseMockMvc.perform(delete("/api/depenses/{id}", depense.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Depense> depenseList = depenseRepository.findAll();
        assertThat(depenseList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
