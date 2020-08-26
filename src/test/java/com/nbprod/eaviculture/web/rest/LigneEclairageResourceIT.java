package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.LigneEclairage;
import com.nbprod.eaviculture.repository.LigneEclairageRepository;
import com.nbprod.eaviculture.service.LigneEclairageService;
import com.nbprod.eaviculture.service.dto.LigneEclairageDTO;
import com.nbprod.eaviculture.service.mapper.LigneEclairageMapper;

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
 * Integration tests for the {@link LigneEclairageResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class LigneEclairageResourceIT {

    private static final String DEFAULT_CODE_LIGNE = "AAAAAAAAAA";
    private static final String UPDATED_CODE_LIGNE = "BBBBBBBBBB";

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final Boolean DEFAULT_ALLUME = false;
    private static final Boolean UPDATED_ALLUME = true;

    private static final Integer DEFAULT_LUX_MAX = 1;
    private static final Integer UPDATED_LUX_MAX = 2;

    private static final Integer DEFAULT_LUX_MIN = 1;
    private static final Integer UPDATED_LUX_MIN = 2;

    private static final Integer DEFAULT_LUX_CHOISI = 1;
    private static final Integer UPDATED_LUX_CHOISI = 2;

    @Autowired
    private LigneEclairageRepository ligneEclairageRepository;

    @Autowired
    private LigneEclairageMapper ligneEclairageMapper;

    @Autowired
    private LigneEclairageService ligneEclairageService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restLigneEclairageMockMvc;

    private LigneEclairage ligneEclairage;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneEclairage createEntity(EntityManager em) {
        LigneEclairage ligneEclairage = new LigneEclairage()
            .codeLigne(DEFAULT_CODE_LIGNE)
            .description(DEFAULT_DESCRIPTION)
            .allume(DEFAULT_ALLUME)
            .luxMax(DEFAULT_LUX_MAX)
            .luxMin(DEFAULT_LUX_MIN)
            .luxChoisi(DEFAULT_LUX_CHOISI);
        return ligneEclairage;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static LigneEclairage createUpdatedEntity(EntityManager em) {
        LigneEclairage ligneEclairage = new LigneEclairage()
            .codeLigne(UPDATED_CODE_LIGNE)
            .description(UPDATED_DESCRIPTION)
            .allume(UPDATED_ALLUME)
            .luxMax(UPDATED_LUX_MAX)
            .luxMin(UPDATED_LUX_MIN)
            .luxChoisi(UPDATED_LUX_CHOISI);
        return ligneEclairage;
    }

    @BeforeEach
    public void initTest() {
        ligneEclairage = createEntity(em);
    }

    @Test
    @Transactional
    public void createLigneEclairage() throws Exception {
        int databaseSizeBeforeCreate = ligneEclairageRepository.findAll().size();
        // Create the LigneEclairage
        LigneEclairageDTO ligneEclairageDTO = ligneEclairageMapper.toDto(ligneEclairage);
        restLigneEclairageMockMvc.perform(post("/api/ligne-eclairages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneEclairageDTO)))
            .andExpect(status().isCreated());

        // Validate the LigneEclairage in the database
        List<LigneEclairage> ligneEclairageList = ligneEclairageRepository.findAll();
        assertThat(ligneEclairageList).hasSize(databaseSizeBeforeCreate + 1);
        LigneEclairage testLigneEclairage = ligneEclairageList.get(ligneEclairageList.size() - 1);
        assertThat(testLigneEclairage.getCodeLigne()).isEqualTo(DEFAULT_CODE_LIGNE);
        assertThat(testLigneEclairage.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testLigneEclairage.isAllume()).isEqualTo(DEFAULT_ALLUME);
        assertThat(testLigneEclairage.getLuxMax()).isEqualTo(DEFAULT_LUX_MAX);
        assertThat(testLigneEclairage.getLuxMin()).isEqualTo(DEFAULT_LUX_MIN);
        assertThat(testLigneEclairage.getLuxChoisi()).isEqualTo(DEFAULT_LUX_CHOISI);
    }

    @Test
    @Transactional
    public void createLigneEclairageWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = ligneEclairageRepository.findAll().size();

        // Create the LigneEclairage with an existing ID
        ligneEclairage.setId(1L);
        LigneEclairageDTO ligneEclairageDTO = ligneEclairageMapper.toDto(ligneEclairage);

        // An entity with an existing ID cannot be created, so this API call must fail
        restLigneEclairageMockMvc.perform(post("/api/ligne-eclairages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneEclairageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LigneEclairage in the database
        List<LigneEclairage> ligneEclairageList = ligneEclairageRepository.findAll();
        assertThat(ligneEclairageList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllLigneEclairages() throws Exception {
        // Initialize the database
        ligneEclairageRepository.saveAndFlush(ligneEclairage);

        // Get all the ligneEclairageList
        restLigneEclairageMockMvc.perform(get("/api/ligne-eclairages?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(ligneEclairage.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeLigne").value(hasItem(DEFAULT_CODE_LIGNE)))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].allume").value(hasItem(DEFAULT_ALLUME.booleanValue())))
            .andExpect(jsonPath("$.[*].luxMax").value(hasItem(DEFAULT_LUX_MAX)))
            .andExpect(jsonPath("$.[*].luxMin").value(hasItem(DEFAULT_LUX_MIN)))
            .andExpect(jsonPath("$.[*].luxChoisi").value(hasItem(DEFAULT_LUX_CHOISI)));
    }
    
    @Test
    @Transactional
    public void getLigneEclairage() throws Exception {
        // Initialize the database
        ligneEclairageRepository.saveAndFlush(ligneEclairage);

        // Get the ligneEclairage
        restLigneEclairageMockMvc.perform(get("/api/ligne-eclairages/{id}", ligneEclairage.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(ligneEclairage.getId().intValue()))
            .andExpect(jsonPath("$.codeLigne").value(DEFAULT_CODE_LIGNE))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.allume").value(DEFAULT_ALLUME.booleanValue()))
            .andExpect(jsonPath("$.luxMax").value(DEFAULT_LUX_MAX))
            .andExpect(jsonPath("$.luxMin").value(DEFAULT_LUX_MIN))
            .andExpect(jsonPath("$.luxChoisi").value(DEFAULT_LUX_CHOISI));
    }
    @Test
    @Transactional
    public void getNonExistingLigneEclairage() throws Exception {
        // Get the ligneEclairage
        restLigneEclairageMockMvc.perform(get("/api/ligne-eclairages/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateLigneEclairage() throws Exception {
        // Initialize the database
        ligneEclairageRepository.saveAndFlush(ligneEclairage);

        int databaseSizeBeforeUpdate = ligneEclairageRepository.findAll().size();

        // Update the ligneEclairage
        LigneEclairage updatedLigneEclairage = ligneEclairageRepository.findById(ligneEclairage.getId()).get();
        // Disconnect from session so that the updates on updatedLigneEclairage are not directly saved in db
        em.detach(updatedLigneEclairage);
        updatedLigneEclairage
            .codeLigne(UPDATED_CODE_LIGNE)
            .description(UPDATED_DESCRIPTION)
            .allume(UPDATED_ALLUME)
            .luxMax(UPDATED_LUX_MAX)
            .luxMin(UPDATED_LUX_MIN)
            .luxChoisi(UPDATED_LUX_CHOISI);
        LigneEclairageDTO ligneEclairageDTO = ligneEclairageMapper.toDto(updatedLigneEclairage);

        restLigneEclairageMockMvc.perform(put("/api/ligne-eclairages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneEclairageDTO)))
            .andExpect(status().isOk());

        // Validate the LigneEclairage in the database
        List<LigneEclairage> ligneEclairageList = ligneEclairageRepository.findAll();
        assertThat(ligneEclairageList).hasSize(databaseSizeBeforeUpdate);
        LigneEclairage testLigneEclairage = ligneEclairageList.get(ligneEclairageList.size() - 1);
        assertThat(testLigneEclairage.getCodeLigne()).isEqualTo(UPDATED_CODE_LIGNE);
        assertThat(testLigneEclairage.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testLigneEclairage.isAllume()).isEqualTo(UPDATED_ALLUME);
        assertThat(testLigneEclairage.getLuxMax()).isEqualTo(UPDATED_LUX_MAX);
        assertThat(testLigneEclairage.getLuxMin()).isEqualTo(UPDATED_LUX_MIN);
        assertThat(testLigneEclairage.getLuxChoisi()).isEqualTo(UPDATED_LUX_CHOISI);
    }

    @Test
    @Transactional
    public void updateNonExistingLigneEclairage() throws Exception {
        int databaseSizeBeforeUpdate = ligneEclairageRepository.findAll().size();

        // Create the LigneEclairage
        LigneEclairageDTO ligneEclairageDTO = ligneEclairageMapper.toDto(ligneEclairage);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restLigneEclairageMockMvc.perform(put("/api/ligne-eclairages")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(ligneEclairageDTO)))
            .andExpect(status().isBadRequest());

        // Validate the LigneEclairage in the database
        List<LigneEclairage> ligneEclairageList = ligneEclairageRepository.findAll();
        assertThat(ligneEclairageList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteLigneEclairage() throws Exception {
        // Initialize the database
        ligneEclairageRepository.saveAndFlush(ligneEclairage);

        int databaseSizeBeforeDelete = ligneEclairageRepository.findAll().size();

        // Delete the ligneEclairage
        restLigneEclairageMockMvc.perform(delete("/api/ligne-eclairages/{id}", ligneEclairage.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<LigneEclairage> ligneEclairageList = ligneEclairageRepository.findAll();
        assertThat(ligneEclairageList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
