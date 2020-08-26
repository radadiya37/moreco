package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.Vente;
import com.nbprod.eaviculture.repository.VenteRepository;
import com.nbprod.eaviculture.service.VenteService;
import com.nbprod.eaviculture.service.dto.VenteDTO;
import com.nbprod.eaviculture.service.mapper.VenteMapper;

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
import java.math.BigDecimal;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link VenteResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class VenteResourceIT {

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    private static final BigDecimal DEFAULT_PRIX_UNITAIRE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX_UNITAIRE = new BigDecimal(2);

    private static final String DEFAULT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_METHOD_PAIMENT = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_PAIMENT = "BBBBBBBBBB";

    @Autowired
    private VenteRepository venteRepository;

    @Autowired
    private VenteMapper venteMapper;

    @Autowired
    private VenteService venteService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restVenteMockMvc;

    private Vente vente;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vente createEntity(EntityManager em) {
        Vente vente = new Vente()
            .quantite(DEFAULT_QUANTITE)
            .prixUnitaire(DEFAULT_PRIX_UNITAIRE)
            .description(DEFAULT_DESCRIPTION)
            .methodPaiment(DEFAULT_METHOD_PAIMENT);
        return vente;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Vente createUpdatedEntity(EntityManager em) {
        Vente vente = new Vente()
            .quantite(UPDATED_QUANTITE)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .description(UPDATED_DESCRIPTION)
            .methodPaiment(UPDATED_METHOD_PAIMENT);
        return vente;
    }

    @BeforeEach
    public void initTest() {
        vente = createEntity(em);
    }

    @Test
    @Transactional
    public void createVente() throws Exception {
        int databaseSizeBeforeCreate = venteRepository.findAll().size();
        // Create the Vente
        VenteDTO venteDTO = venteMapper.toDto(vente);
        restVenteMockMvc.perform(post("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isCreated());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeCreate + 1);
        Vente testVente = venteList.get(venteList.size() - 1);
        assertThat(testVente.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testVente.getPrixUnitaire()).isEqualTo(DEFAULT_PRIX_UNITAIRE);
        assertThat(testVente.getDescription()).isEqualTo(DEFAULT_DESCRIPTION);
        assertThat(testVente.getMethodPaiment()).isEqualTo(DEFAULT_METHOD_PAIMENT);
    }

    @Test
    @Transactional
    public void createVenteWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = venteRepository.findAll().size();

        // Create the Vente with an existing ID
        vente.setId(1L);
        VenteDTO venteDTO = venteMapper.toDto(vente);

        // An entity with an existing ID cannot be created, so this API call must fail
        restVenteMockMvc.perform(post("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllVentes() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        // Get all the venteList
        restVenteMockMvc.perform(get("/api/ventes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(vente.getId().intValue())))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)))
            .andExpect(jsonPath("$.[*].prixUnitaire").value(hasItem(DEFAULT_PRIX_UNITAIRE.intValue())))
            .andExpect(jsonPath("$.[*].description").value(hasItem(DEFAULT_DESCRIPTION)))
            .andExpect(jsonPath("$.[*].methodPaiment").value(hasItem(DEFAULT_METHOD_PAIMENT)));
    }
    
    @Test
    @Transactional
    public void getVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        // Get the vente
        restVenteMockMvc.perform(get("/api/ventes/{id}", vente.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(vente.getId().intValue()))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE))
            .andExpect(jsonPath("$.prixUnitaire").value(DEFAULT_PRIX_UNITAIRE.intValue()))
            .andExpect(jsonPath("$.description").value(DEFAULT_DESCRIPTION))
            .andExpect(jsonPath("$.methodPaiment").value(DEFAULT_METHOD_PAIMENT));
    }
    @Test
    @Transactional
    public void getNonExistingVente() throws Exception {
        // Get the vente
        restVenteMockMvc.perform(get("/api/ventes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        int databaseSizeBeforeUpdate = venteRepository.findAll().size();

        // Update the vente
        Vente updatedVente = venteRepository.findById(vente.getId()).get();
        // Disconnect from session so that the updates on updatedVente are not directly saved in db
        em.detach(updatedVente);
        updatedVente
            .quantite(UPDATED_QUANTITE)
            .prixUnitaire(UPDATED_PRIX_UNITAIRE)
            .description(UPDATED_DESCRIPTION)
            .methodPaiment(UPDATED_METHOD_PAIMENT);
        VenteDTO venteDTO = venteMapper.toDto(updatedVente);

        restVenteMockMvc.perform(put("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isOk());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeUpdate);
        Vente testVente = venteList.get(venteList.size() - 1);
        assertThat(testVente.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testVente.getPrixUnitaire()).isEqualTo(UPDATED_PRIX_UNITAIRE);
        assertThat(testVente.getDescription()).isEqualTo(UPDATED_DESCRIPTION);
        assertThat(testVente.getMethodPaiment()).isEqualTo(UPDATED_METHOD_PAIMENT);
    }

    @Test
    @Transactional
    public void updateNonExistingVente() throws Exception {
        int databaseSizeBeforeUpdate = venteRepository.findAll().size();

        // Create the Vente
        VenteDTO venteDTO = venteMapper.toDto(vente);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restVenteMockMvc.perform(put("/api/ventes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(venteDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Vente in the database
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteVente() throws Exception {
        // Initialize the database
        venteRepository.saveAndFlush(vente);

        int databaseSizeBeforeDelete = venteRepository.findAll().size();

        // Delete the vente
        restVenteMockMvc.perform(delete("/api/ventes/{id}", vente.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Vente> venteList = venteRepository.findAll();
        assertThat(venteList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
