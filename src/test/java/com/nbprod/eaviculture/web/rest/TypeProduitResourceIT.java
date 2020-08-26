package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.TypeProduit;
import com.nbprod.eaviculture.repository.TypeProduitRepository;
import com.nbprod.eaviculture.service.TypeProduitService;
import com.nbprod.eaviculture.service.dto.TypeProduitDTO;
import com.nbprod.eaviculture.service.mapper.TypeProduitMapper;

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
 * Integration tests for the {@link TypeProduitResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class TypeProduitResourceIT {

    private static final String DEFAULT_CODE_TYPE_PRODUIT = "AAAAAAAAAA";
    private static final String UPDATED_CODE_TYPE_PRODUIT = "BBBBBBBBBB";

    private static final String DEFAULT_DESIGNATION = "AAAAAAAAAA";
    private static final String UPDATED_DESIGNATION = "BBBBBBBBBB";

    @Autowired
    private TypeProduitRepository typeProduitRepository;

    @Autowired
    private TypeProduitMapper typeProduitMapper;

    @Autowired
    private TypeProduitService typeProduitService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restTypeProduitMockMvc;

    private TypeProduit typeProduit;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeProduit createEntity(EntityManager em) {
        TypeProduit typeProduit = new TypeProduit()
            .codeTypeProduit(DEFAULT_CODE_TYPE_PRODUIT)
            .designation(DEFAULT_DESIGNATION);
        return typeProduit;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static TypeProduit createUpdatedEntity(EntityManager em) {
        TypeProduit typeProduit = new TypeProduit()
            .codeTypeProduit(UPDATED_CODE_TYPE_PRODUIT)
            .designation(UPDATED_DESIGNATION);
        return typeProduit;
    }

    @BeforeEach
    public void initTest() {
        typeProduit = createEntity(em);
    }

    @Test
    @Transactional
    public void createTypeProduit() throws Exception {
        int databaseSizeBeforeCreate = typeProduitRepository.findAll().size();
        // Create the TypeProduit
        TypeProduitDTO typeProduitDTO = typeProduitMapper.toDto(typeProduit);
        restTypeProduitMockMvc.perform(post("/api/type-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeProduitDTO)))
            .andExpect(status().isCreated());

        // Validate the TypeProduit in the database
        List<TypeProduit> typeProduitList = typeProduitRepository.findAll();
        assertThat(typeProduitList).hasSize(databaseSizeBeforeCreate + 1);
        TypeProduit testTypeProduit = typeProduitList.get(typeProduitList.size() - 1);
        assertThat(testTypeProduit.getCodeTypeProduit()).isEqualTo(DEFAULT_CODE_TYPE_PRODUIT);
        assertThat(testTypeProduit.getDesignation()).isEqualTo(DEFAULT_DESIGNATION);
    }

    @Test
    @Transactional
    public void createTypeProduitWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = typeProduitRepository.findAll().size();

        // Create the TypeProduit with an existing ID
        typeProduit.setId(1L);
        TypeProduitDTO typeProduitDTO = typeProduitMapper.toDto(typeProduit);

        // An entity with an existing ID cannot be created, so this API call must fail
        restTypeProduitMockMvc.perform(post("/api/type-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeProduit in the database
        List<TypeProduit> typeProduitList = typeProduitRepository.findAll();
        assertThat(typeProduitList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllTypeProduits() throws Exception {
        // Initialize the database
        typeProduitRepository.saveAndFlush(typeProduit);

        // Get all the typeProduitList
        restTypeProduitMockMvc.perform(get("/api/type-produits?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(typeProduit.getId().intValue())))
            .andExpect(jsonPath("$.[*].codeTypeProduit").value(hasItem(DEFAULT_CODE_TYPE_PRODUIT)))
            .andExpect(jsonPath("$.[*].designation").value(hasItem(DEFAULT_DESIGNATION)));
    }
    
    @Test
    @Transactional
    public void getTypeProduit() throws Exception {
        // Initialize the database
        typeProduitRepository.saveAndFlush(typeProduit);

        // Get the typeProduit
        restTypeProduitMockMvc.perform(get("/api/type-produits/{id}", typeProduit.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(typeProduit.getId().intValue()))
            .andExpect(jsonPath("$.codeTypeProduit").value(DEFAULT_CODE_TYPE_PRODUIT))
            .andExpect(jsonPath("$.designation").value(DEFAULT_DESIGNATION));
    }
    @Test
    @Transactional
    public void getNonExistingTypeProduit() throws Exception {
        // Get the typeProduit
        restTypeProduitMockMvc.perform(get("/api/type-produits/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateTypeProduit() throws Exception {
        // Initialize the database
        typeProduitRepository.saveAndFlush(typeProduit);

        int databaseSizeBeforeUpdate = typeProduitRepository.findAll().size();

        // Update the typeProduit
        TypeProduit updatedTypeProduit = typeProduitRepository.findById(typeProduit.getId()).get();
        // Disconnect from session so that the updates on updatedTypeProduit are not directly saved in db
        em.detach(updatedTypeProduit);
        updatedTypeProduit
            .codeTypeProduit(UPDATED_CODE_TYPE_PRODUIT)
            .designation(UPDATED_DESIGNATION);
        TypeProduitDTO typeProduitDTO = typeProduitMapper.toDto(updatedTypeProduit);

        restTypeProduitMockMvc.perform(put("/api/type-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeProduitDTO)))
            .andExpect(status().isOk());

        // Validate the TypeProduit in the database
        List<TypeProduit> typeProduitList = typeProduitRepository.findAll();
        assertThat(typeProduitList).hasSize(databaseSizeBeforeUpdate);
        TypeProduit testTypeProduit = typeProduitList.get(typeProduitList.size() - 1);
        assertThat(testTypeProduit.getCodeTypeProduit()).isEqualTo(UPDATED_CODE_TYPE_PRODUIT);
        assertThat(testTypeProduit.getDesignation()).isEqualTo(UPDATED_DESIGNATION);
    }

    @Test
    @Transactional
    public void updateNonExistingTypeProduit() throws Exception {
        int databaseSizeBeforeUpdate = typeProduitRepository.findAll().size();

        // Create the TypeProduit
        TypeProduitDTO typeProduitDTO = typeProduitMapper.toDto(typeProduit);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restTypeProduitMockMvc.perform(put("/api/type-produits")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(typeProduitDTO)))
            .andExpect(status().isBadRequest());

        // Validate the TypeProduit in the database
        List<TypeProduit> typeProduitList = typeProduitRepository.findAll();
        assertThat(typeProduitList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteTypeProduit() throws Exception {
        // Initialize the database
        typeProduitRepository.saveAndFlush(typeProduit);

        int databaseSizeBeforeDelete = typeProduitRepository.findAll().size();

        // Delete the typeProduit
        restTypeProduitMockMvc.perform(delete("/api/type-produits/{id}", typeProduit.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<TypeProduit> typeProduitList = typeProduitRepository.findAll();
        assertThat(typeProduitList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
