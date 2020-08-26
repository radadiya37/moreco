package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.Fonction;
import com.nbprod.eaviculture.repository.FonctionRepository;
import com.nbprod.eaviculture.service.FonctionService;
import com.nbprod.eaviculture.service.dto.FonctionDTO;
import com.nbprod.eaviculture.service.mapper.FonctionMapper;

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
 * Integration tests for the {@link FonctionResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FonctionResourceIT {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_CODE_FONCTION = "AAAAAAAAAA";
    private static final String UPDATED_CODE_FONCTION = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    @Autowired
    private FonctionRepository fonctionRepository;

    @Autowired
    private FonctionMapper fonctionMapper;

    @Autowired
    private FonctionService fonctionService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFonctionMockMvc;

    private Fonction fonction;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fonction createEntity(EntityManager em) {
        Fonction fonction = new Fonction()
            .prenom(DEFAULT_PRENOM)
            .codeFonction(DEFAULT_CODE_FONCTION)
            .description(DEFAULT_DESCRIPTION);
        return fonction;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Fonction createUpdatedEntity(EntityManager em) {
        Fonction fonction = new Fonction()
            .prenom(UPDATED_PRENOM)
            .codeFonction(UPDATED_CODE_FONCTION)
            .description(UPDATED_DESCRIPTION);
        return fonction;
    }

    @BeforeEach
    public void initTest() {
        fonction = createEntity(em);
    }

    @Test
    @Transactional
    public void createFonction() throws Exception {
        int databaseSizeBeforeCreate = fonctionRepository.findAll().size();
        // Create the Fonction
        FonctionDTO fonctionDTO = fonctionMapper.toDto(fonction);
        restFonctionMockMvc.perform(post("/api/fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fonctionDTO)))
            .andExpect(status().isCreated());

        // Validate the Fonction in the database
        List<Fonction> fonctionList = fonctionRepository.findAll();
        assertThat(fonctionList).hasSize(databaseSizeBeforeCreate + 1);
        Fonction testFonction = fonctionList.get(fonctionList.size() - 1);
        assertThat(testFonction.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testFonction.getCodeFonction()).isEqualTo(DEFAULT_CODE_FONCTION);
        assertThat(testFonction.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
    }

    @Test
    @Transactional
    public void createFonctionWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = fonctionRepository.findAll().size();

        // Create the Fonction with an existing ID
        fonction.setId(1L);
        FonctionDTO fonctionDTO = fonctionMapper.toDto(fonction);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFonctionMockMvc.perform(post("/api/fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fonctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fonction in the database
        List<Fonction> fonctionList = fonctionRepository.findAll();
        assertThat(fonctionList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFonctions() throws Exception {
        // Initialize the database
        fonctionRepository.saveAndFlush(fonction);

        // Get all the fonctionList
        restFonctionMockMvc.perform(get("/api/fonctions?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(fonction.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].codeFonction").value(hasItem(DEFAULT_CODE_FONCTION)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)));
    }
    
    @Test
    @Transactional
    public void getFonction() throws Exception {
        // Initialize the database
        fonctionRepository.saveAndFlush(fonction);

        // Get the fonction
        restFonctionMockMvc.perform(get("/api/fonctions/{id}", fonction.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(fonction.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.codeFonction").value(DEFAULT_CODE_FONCTION))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION));
    }
    @Test
    @Transactional
    public void getNonExistingFonction() throws Exception {
        // Get the fonction
        restFonctionMockMvc.perform(get("/api/fonctions/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFonction() throws Exception {
        // Initialize the database
        fonctionRepository.saveAndFlush(fonction);

        int databaseSizeBeforeUpdate = fonctionRepository.findAll().size();

        // Update the fonction
        Fonction updatedFonction = fonctionRepository.findById(fonction.getId()).get();
        // Disconnect from session so that the updates on updatedFonction are not directly saved in db
        em.detach(updatedFonction);
        updatedFonction
            .prenom(UPDATED_PRENOM)
            .codeFonction(UPDATED_CODE_FONCTION)
            .description(UPDATED_DESCRIPTION);
        FonctionDTO fonctionDTO = fonctionMapper.toDto(updatedFonction);

        restFonctionMockMvc.perform(put("/api/fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fonctionDTO)))
            .andExpect(status().isOk());

        // Validate the Fonction in the database
        List<Fonction> fonctionList = fonctionRepository.findAll();
        assertThat(fonctionList).hasSize(databaseSizeBeforeUpdate);
        Fonction testFonction = fonctionList.get(fonctionList.size() - 1);
        assertThat(testFonction.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testFonction.getCodeFonction()).isEqualTo(UPDATED_CODE_FONCTION);
        assertThat(testFonction.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
    }

    @Test
    @Transactional
    public void updateNonExistingFonction() throws Exception {
        int databaseSizeBeforeUpdate = fonctionRepository.findAll().size();

        // Create the Fonction
        FonctionDTO fonctionDTO = fonctionMapper.toDto(fonction);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFonctionMockMvc.perform(put("/api/fonctions")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(fonctionDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Fonction in the database
        List<Fonction> fonctionList = fonctionRepository.findAll();
        assertThat(fonctionList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFonction() throws Exception {
        // Initialize the database
        fonctionRepository.saveAndFlush(fonction);

        int databaseSizeBeforeDelete = fonctionRepository.findAll().size();

        // Delete the fonction
        restFonctionMockMvc.perform(delete("/api/fonctions/{id}", fonction.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Fonction> fonctionList = fonctionRepository.findAll();
        assertThat(fonctionList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
