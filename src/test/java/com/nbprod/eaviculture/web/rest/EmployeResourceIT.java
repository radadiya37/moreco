package com.nbprod.eaviculture.web.rest;

import com.nbprod.eaviculture.EAvicultureApp;
import com.nbprod.eaviculture.domain.Employe;
import com.nbprod.eaviculture.repository.EmployeRepository;
import com.nbprod.eaviculture.service.EmployeService;
import com.nbprod.eaviculture.service.dto.EmployeDTO;
import com.nbprod.eaviculture.service.mapper.EmployeMapper;

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
 * Integration tests for the {@link EmployeResource} REST controller.
 */
@SpringBootTest(classes = EAvicultureApp.class)
@AutoConfigureMockMvc
@WithMockUser
public class EmployeResourceIT {

    private static final String DEFAULT_PRENOM = "AAAAAAAAAA";
    private static final String UPDATED_PRENOM = "BBBBBBBBBB";

    private static final String DEFAULT_NOM = "AAAAAAAAAA";
    private static final String UPDATED_NOM = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_NUMERO_TEL = "AAAAAAAAAA";
    private static final String UPDATED_NUMERO_TEL = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_EMBAUCHE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_EMBAUCHE = LocalDate.now(ZoneId.systemDefault());

    private static final Long DEFAULT_SALAIRE = 1L;
    private static final Long UPDATED_SALAIRE = 2L;

    private static final String DEFAULT_ADRESSE = "AAAAAAAAAA";
    private static final String UPDATED_ADRESSE = "BBBBBBBBBB";

    private static final String DEFAULT_CIN = "AAAAAAAAAA";
    private static final String UPDATED_CIN = "BBBBBBBBBB";

    private static final LocalDate DEFAULT_DATE_NAISSANCE = LocalDate.ofEpochDay(0L);
    private static final LocalDate UPDATED_DATE_NAISSANCE = LocalDate.now(ZoneId.systemDefault());

    private static final String DEFAULT_SEXE = "AAAAAAAAAA";
    private static final String UPDATED_SEXE = "BBBBBBBBBB";

    @Autowired
    private EmployeRepository employeRepository;

    @Autowired
    private EmployeMapper employeMapper;

    @Autowired
    private EmployeService employeService;

    @Autowired
    private EntityManager em;

    @Autowired
    private MockMvc restEmployeMockMvc;

    private Employe employe;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employe createEntity(EntityManager em) {
        Employe employe = new Employe()
            .prenom(DEFAULT_PRENOM)
            .nom(DEFAULT_NOM)
            .email(DEFAULT_EMAIL)
            .numeroTel(DEFAULT_NUMERO_TEL)
            .dateEmbauche(DEFAULT_DATE_EMBAUCHE)
            .salaire(DEFAULT_SALAIRE)
            .adresse(DEFAULT_ADRESSE)
            .cin(DEFAULT_CIN)
            .dateNaissance(DEFAULT_DATE_NAISSANCE)
            .sexe(DEFAULT_SEXE);
        return employe;
    }
    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static Employe createUpdatedEntity(EntityManager em) {
        Employe employe = new Employe()
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .email(UPDATED_EMAIL)
            .numeroTel(UPDATED_NUMERO_TEL)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .salaire(UPDATED_SALAIRE)
            .adresse(UPDATED_ADRESSE)
            .cin(UPDATED_CIN)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .sexe(UPDATED_SEXE);
        return employe;
    }

    @BeforeEach
    public void initTest() {
        employe = createEntity(em);
    }

    @Test
    @Transactional
    public void createEmploye() throws Exception {
        int databaseSizeBeforeCreate = employeRepository.findAll().size();
        // Create the Employe
        EmployeDTO employeDTO = employeMapper.toDto(employe);
        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isCreated());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeCreate + 1);
        Employe testEmploye = employeList.get(employeList.size() - 1);
        assertThat(testEmploye.getPrenom()).isEqualTo(DEFAULT_PRENOM);
        assertThat(testEmploye.getNom()).isEqualTo(DEFAULT_NOM);
        assertThat(testEmploye.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testEmploye.getNumeroTel()).isEqualTo(DEFAULT_NUMERO_TEL);
        assertThat(testEmploye.getDateEmbauche()).isEqualTo(DEFAULT_DATE_EMBAUCHE);
        assertThat(testEmploye.getSalaire()).isEqualTo(DEFAULT_SALAIRE);
        assertThat(testEmploye.getAdresse()).isEqualTo(DEFAULT_ADRESSE);
        assertThat(testEmploye.getCin()).isEqualTo(DEFAULT_CIN);
        assertThat(testEmploye.getDateNaissance()).isEqualTo(DEFAULT_DATE_NAISSANCE);
        assertThat(testEmploye.getSexe()).isEqualTo(DEFAULT_SEXE);
    }

    @Test
    @Transactional
    public void createEmployeWithExistingId() throws Exception {
        int databaseSizeBeforeCreate = employeRepository.findAll().size();

        // Create the Employe with an existing ID
        employe.setId(1L);
        EmployeDTO employeDTO = employeMapper.toDto(employe);

        // An entity with an existing ID cannot be created, so this API call must fail
        restEmployeMockMvc.perform(post("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeCreate);
    }


    @Test
    @Transactional
    public void getAllEmployes() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        // Get all the employeList
        restEmployeMockMvc.perform(get("/api/employes?sort=id,desc"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.[*].id").value(hasItem(employe.getId().intValue())))
            .andExpect(jsonPath("$.[*].prenom").value(hasItem(DEFAULT_PRENOM)))
            .andExpect(jsonPath("$.[*].nom").value(hasItem(DEFAULT_NOM)))
            .andExpect(jsonPath("$.[*].email").value(hasItem(DEFAULT_EMAIL)))
            .andExpect(jsonPath("$.[*].numeroTel").value(hasItem(DEFAULT_NUMERO_TEL)))
            .andExpect(jsonPath("$.[*].dateEmbauche").value(hasItem(DEFAULT_DATE_EMBAUCHE.toString())))
            .andExpect(jsonPath("$.[*].salaire").value(hasItem(DEFAULT_SALAIRE.intValue())))
            .andExpect(jsonPath("$.[*].adresse").value(hasItem(DEFAULT_ADRESSE)))
            .andExpect(jsonPath("$.[*].cin").value(hasItem(DEFAULT_CIN)))
            .andExpect(jsonPath("$.[*].dateNaissance").value(hasItem(DEFAULT_DATE_NAISSANCE.toString())))
            .andExpect(jsonPath("$.[*].sexe").value(hasItem(DEFAULT_SEXE)));
    }
    
    @Test
    @Transactional
    public void getEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        // Get the employe
        restEmployeMockMvc.perform(get("/api/employes/{id}", employe.getId()))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON_VALUE))
            .andExpect(jsonPath("$.id").value(employe.getId().intValue()))
            .andExpect(jsonPath("$.prenom").value(DEFAULT_PRENOM))
            .andExpect(jsonPath("$.nom").value(DEFAULT_NOM))
            .andExpect(jsonPath("$.email").value(DEFAULT_EMAIL))
            .andExpect(jsonPath("$.numeroTel").value(DEFAULT_NUMERO_TEL))
            .andExpect(jsonPath("$.dateEmbauche").value(DEFAULT_DATE_EMBAUCHE.toString()))
            .andExpect(jsonPath("$.salaire").value(DEFAULT_SALAIRE.intValue()))
            .andExpect(jsonPath("$.adresse").value(DEFAULT_ADRESSE))
            .andExpect(jsonPath("$.cin").value(DEFAULT_CIN))
            .andExpect(jsonPath("$.dateNaissance").value(DEFAULT_DATE_NAISSANCE.toString()))
            .andExpect(jsonPath("$.sexe").value(DEFAULT_SEXE));
    }
    @Test
    @Transactional
    public void getNonExistingEmploye() throws Exception {
        // Get the employe
        restEmployeMockMvc.perform(get("/api/employes/{id}", Long.MAX_VALUE))
            .andExpect(status().isNotFound());
    }

    @Test
    @Transactional
    public void updateEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        int databaseSizeBeforeUpdate = employeRepository.findAll().size();

        // Update the employe
        Employe updatedEmploye = employeRepository.findById(employe.getId()).get();
        // Disconnect from session so that the updates on updatedEmploye are not directly saved in db
        em.detach(updatedEmploye);
        updatedEmploye
            .prenom(UPDATED_PRENOM)
            .nom(UPDATED_NOM)
            .email(UPDATED_EMAIL)
            .numeroTel(UPDATED_NUMERO_TEL)
            .dateEmbauche(UPDATED_DATE_EMBAUCHE)
            .salaire(UPDATED_SALAIRE)
            .adresse(UPDATED_ADRESSE)
            .cin(UPDATED_CIN)
            .dateNaissance(UPDATED_DATE_NAISSANCE)
            .sexe(UPDATED_SEXE);
        EmployeDTO employeDTO = employeMapper.toDto(updatedEmploye);

        restEmployeMockMvc.perform(put("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isOk());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeUpdate);
        Employe testEmploye = employeList.get(employeList.size() - 1);
        assertThat(testEmploye.getPrenom()).isEqualTo(UPDATED_PRENOM);
        assertThat(testEmploye.getNom()).isEqualTo(UPDATED_NOM);
        assertThat(testEmploye.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testEmploye.getNumeroTel()).isEqualTo(UPDATED_NUMERO_TEL);
        assertThat(testEmploye.getDateEmbauche()).isEqualTo(UPDATED_DATE_EMBAUCHE);
        assertThat(testEmploye.getSalaire()).isEqualTo(UPDATED_SALAIRE);
        assertThat(testEmploye.getAdresse()).isEqualTo(UPDATED_ADRESSE);
        assertThat(testEmploye.getCin()).isEqualTo(UPDATED_CIN);
        assertThat(testEmploye.getDateNaissance()).isEqualTo(UPDATED_DATE_NAISSANCE);
        assertThat(testEmploye.getSexe()).isEqualTo(UPDATED_SEXE);
    }

    @Test
    @Transactional
    public void updateNonExistingEmploye() throws Exception {
        int databaseSizeBeforeUpdate = employeRepository.findAll().size();

        // Create the Employe
        EmployeDTO employeDTO = employeMapper.toDto(employe);

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        restEmployeMockMvc.perform(put("/api/employes")
            .contentType(MediaType.APPLICATION_JSON)
            .content(TestUtil.convertObjectToJsonBytes(employeDTO)))
            .andExpect(status().isBadRequest());

        // Validate the Employe in the database
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeUpdate);
    }

    @Test
    @Transactional
    public void deleteEmploye() throws Exception {
        // Initialize the database
        employeRepository.saveAndFlush(employe);

        int databaseSizeBeforeDelete = employeRepository.findAll().size();

        // Delete the employe
        restEmployeMockMvc.perform(delete("/api/employes/{id}", employe.getId())
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isNoContent());

        // Validate the database contains one less item
        List<Employe> employeList = employeRepository.findAll();
        assertThat(employeList).hasSize(databaseSizeBeforeDelete - 1);
    }
}
