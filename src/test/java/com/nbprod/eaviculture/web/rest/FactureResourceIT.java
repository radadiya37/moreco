package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.Facture;
import com.nbprod.eaviculture.repository.FactureRepository;
import com.nbprod.eaviculture.service.FactureService;
import com.nbprod.eaviculture.service.dto.FactureDTO;
import com.nbprod.eaviculture.service.mapper.FactureMapper;

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
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Integration tests for the {@link FactureResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class FactureResourceIT {

    private static final String DEFAULT_NUMERO_FACTURE = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_FACTURE = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_FACTURATION = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_FACTURATION = LocalDate.now(ZoneId.systemDefault());

    private static final BigDecimal DEFAULT_PRIX_UNITE = new BigDecimal(1);
    private static final BigDecimal UPDATED_PRIX_UNITE = new BigDecimal(2);

    private static final Integer DEFAULT_TVA = 1;
    private static final Integer UPDATED_TVA = 2;

    private static final Integer DEFAULT_QUANTITE = 1;
    private static final Integer UPDATED_QUANTITE = 2;

    private static final Long DEFAULT_FRAIS_LIVRAISON = 1L;
    private static final Long UPDATED_FRAIS_LIVRAISON = 2L;

    private static final String DEFAULT_METHOD_PAIMENT = "AAAAAAAAAA";
    private static final String UPDATED_METHOD_PAIMENT = "BBBBBBBBBB";

    private static final String DEFAULT_ETAT_FACTURE = "AAAAAAAAAA";
    private static final String UPDATED_ETAT_FACTURE = "BBBBBBBBBB";

    @Autowired
    private FactureRepository factureRepository;

    @Autowired
    private FactureMapper factureMapper;

    @Autowired
    private FactureService factureService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restFactureMockMvc;

    private Facture facture;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Facture createEntity(EntityManager em) {
        Facture facture = new Facture()
            .numeroFacture(DEFAULT_NUMERO_FACTURE)
            .dateFacturation(DEFAULT_DATE_FACTURATION)
            .prixUnite(DEFAULT_PRIX_UNITE)
            .tva(DEFAULT_TVA)
            .quantite(DEFAULT_QUANTITE)
            .fraisLivraison(DEFAULT_FRAIS_LIVRAISON)
            .methodPaiment(DEFAULT_METHOD_PAIMENT)
            .etatFacture(DEFAULT_ETAT_FACTURE);
        return facture;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Facture createUpdatedEntity(EntityManager em) {
        Facture facture = new Facture()
            .numeroFacture(UPDATED_NUMERO_FACTURE)
            .dateFacturation(UPDATED_DATE_FACTURATION)
            .prixUnite(UPDATED_PRIX_UNITE)
            .tva(UPDATED_TVA)
            .quantite(UPDATED_QUANTITE)
            .fraisLivraison(UPDATED_FRAIS_LIVRAISON)
            .methodPaiment(UPDATED_METHOD_PAIMENT)
            .etatFacture(UPDATED_ETAT_FACTURE);
        return facture;
    }

    @BeforeEach
    public void initTest() {
        facture = createEntity(em);
    }

    @Test
    @Transactional
    public void createFacture() throws Exception {
        int databaseSizeBeforeCreate = factureRepository.findAll().size();
        // Create the Facture
        FactureDTO factureDTO = factureMapper.toDto(facture);
        restFactureMockMvc.perform(post("/api/factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isCreated());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeCreate + 1);
        Facture testFacture = factureList.get(factureList.size() - 1);
        assertThat(testFacture.getNumeroFacture()).isEqualTo(DEFAULT_NUMERO_FACTURE);
        assertThat(testFacture.getDateFacturation()).isEqualTo(DEFAULT_DATE_FACTURATION);
        assertThat(testFacture.getPrixUnite()).isEqualTo(DEFAULT_PRIX_UNITE);
        assertThat(testFacture.getTva()).isEqualTo(DEFAULT_TVA);
        assertThat(testFacture.getQuantite()).isEqualTo(DEFAULT_QUANTITE);
        assertThat(testFacture.getFraisLivraison()).isEqualTo(DEFAULT_FRAIS_LIVRAISON);
        assertThat(testFacture.getMethodPaiment()).isEqualTo(DEFAULT_METHOD_PAIMENT);
        assertThat(testFacture.getEtatFacture()).isEqualTo(DEFAULT_ETAT_FACTURE);
    }

    @Test
    @Transactional
    public void createFactureWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = factureRepository.findAll().size();

        // Create the Facture with an existing ID
        facture.setId(1L);
        FactureDTO factureDTO = factureMapper.toDto(facture);

        // An entity with an existing ID cannot be created, so this API call must fail
        restFactureMockMvc.perform(post("/api/factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllFactures() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        // Get all the factureList
        restFactureMockMvc.perform(get("/api/factures?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(facture.getId().intValue())))
            .andExpect(jsonPath("$.[*].numeroFacture").value(hasItem(DEFAULT_NUMERO_FACTURE)))
            .andExpect(jsonPath("$.[*].dateFacturation").value(hasItem(DEFAULT_DATE_FACTURATION.toString())))
            .andExpect(jsonPath("$.[*].prixUnite").value(hasItem(DEFAULT_PRIX_UNITE.intValue())))
            .andExpect(jsonPath("$.[*].tva").value(hasItem(DEFAULT_TVA)))
            .andExpect(jsonPath("$.[*].quantite").value(hasItem(DEFAULT_QUANTITE)))
            .andExpect(jsonPath("$.[*].fraisLivraison").value(hasItem(DEFAULT_FRAIS_LIVRAISON.intValue())))
            .andExpect(jsonPath("$.[*].methodPaiment").value(hasItem(DEFAULT_METHOD_PAIMENT)))
            .andExpect(jsonPath("$.[*].etatFacture").value(hasItem(DEFAULT_ETAT_FACTURE)));
    }
    
    @Test
    @Transactional
    public void getFacture() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        // Get the facture
        restFactureMockMvc.perform(get("/api/factures/{id}", facture.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(facture.getId().intValue()))
            .andExpect(jsonPath("$.numeroFacture").value(DEFAULT_NUMERO_FACTURE))
            .andExpect(jsonPath("$.dateFacturation").value(DEFAULT_DATE_FACTURATION.toString()))
            .andExpect(jsonPath("$.prixUnite").value(DEFAULT_PRIX_UNITE.intValue()))
            .andExpect(jsonPath("$.tva").value(DEFAULT_TVA))
            .andExpect(jsonPath("$.quantite").value(DEFAULT_QUANTITE))
            .andExpect(jsonPath("$.fraisLivraison").value(DEFAULT_FRAIS_LIVRAISON.intValue()))
            .andExpect(jsonPath("$.methodPaiment").value(DEFAULT_METHOD_PAIMENT))
            .andExpect(jsonPath("$.etatFacture").value(DEFAULT_ETAT_FACTURE));
    }
    @Test
    @Transactional
    public void getNonExistingFacture() throws Exception {
        // Get the facture
        restFactureMockMvc.perform(get("/api/factures/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateFacture() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        int databaseSizeBeforeUpdate = factureRepository.findAll().size();

        // Update the facture
        Facture updatedFacture = factureRepository.findById(facture.getId()).get();
        // Disconnect from session so that the updates on updatedFacture are not directly saved in db
        em.detach(updatedFacture);
        updatedFacture
            .numeroFacture(UPDATED_NUMERO_FACTURE)
            .dateFacturation(UPDATED_DATE_FACTURATION)
            .prixUnite(UPDATED_PRIX_UNITE)
            .tva(UPDATED_TVA)
            .quantite(UPDATED_QUANTITE)
            .fraisLivraison(UPDATED_FRAIS_LIVRAISON)
            .methodPaiment(UPDATED_METHOD_PAIMENT)
            .etatFacture(UPDATED_ETAT_FACTURE);
        FactureDTO factureDTO = factureMapper.toDto(updatedFacture);

        restFactureMockMvc.perform(put("/api/factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isOk());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeUpdate);
        Facture testFacture = factureList.get(factureList.size() - 1);
        assertThat(testFacture.getNumeroFacture()).isEqualTo(UPDATED_NUMERO_FACTURE);
        assertThat(testFacture.getDateFacturation()).isEqualTo(UPDATED_DATE_FACTURATION);
        assertThat(testFacture.getPrixUnite()).isEqualTo(UPDATED_PRIX_UNITE);
        assertThat(testFacture.getTva()).isEqualTo(UPDATED_TVA);
        assertThat(testFacture.getQuantite()).isEqualTo(UPDATED_QUANTITE);
        assertThat(testFacture.getFraisLivraison()).isEqualTo(UPDATED_FRAIS_LIVRAISON);
        assertThat(testFacture.getMethodPaiment()).isEqualTo(UPDATED_METHOD_PAIMENT);
        assertThat(testFacture.getEtatFacture()).isEqualTo(UPDATED_ETAT_FACTURE);
    }

    @Test
    @Transactional
    public void updateNonExistingFacture() throws Exception {
        int databaseSizeBeforeUpdate = factureRepository.findAll().size();

        // Create the Facture
        FactureDTO factureDTO = factureMapper.toDto(facture);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restFactureMockMvc.perform(put("/api/factures")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(factureDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Facture in the database
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteFacture() throws Exception {
        // Initialize the database
        factureRepository.saveAndFlush(facture);

        int databaseSizeBeforeDelete = factureRepository.findAll().size();

        // Delete the facture
        restFactureMockMvc.perform(delete("/api/factures/{id}", facture.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Facture> factureList = factureRepository.findAll();
        assertThat(factureList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
