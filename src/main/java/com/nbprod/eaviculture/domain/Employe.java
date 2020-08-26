package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Employe.
 */
@Entity
@Table(name = "employe")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Employe implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "nom")
    private String nom;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_tel")
    private String numeroTel;

    @Column(name = "date_embauche")
    private LocalDate dateEmbauche;

    @Column(name = "salaire")
    private Long salaire;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "cin")
    private String cin;

    @Column(name = "date_naissance")
    private LocalDate dateNaissance;

    @Column(name = "sexe")
    private String sexe;

    @ManyToOne
    @JsonIgnoreProperties(value = "employes", allowSetters = true)
    private Fonction fonction;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public Employe prenom(String prenom) {
        this.prenom = prenom;
        return this;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public Employe nom(String nom) {
        this.nom = nom;
        return this;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public Employe email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public Employe numeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
        return this;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public Employe dateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
        return this;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Long getSalaire() {
        return salaire;
    }

    public Employe salaire(Long salaire) {
        this.salaire = salaire;
        return this;
    }

    public void setSalaire(Long salaire) {
        this.salaire = salaire;
    }

    public String getAdresse() {
        return adresse;
    }

    public Employe adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public Employe cin(String cin) {
        this.cin = cin;
        return this;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public Employe dateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
        return this;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public Employe sexe(String sexe) {
        this.sexe = sexe;
        return this;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Fonction getFonction() {
        return fonction;
    }

    public Employe fonction(Fonction fonction) {
        this.fonction = fonction;
        return this;
    }

    public void setFonction(Fonction fonction) {
        this.fonction = fonction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Employe)) {
            return false;
        }
        return id != null && id.equals(((Employe) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Employe{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", nom='" + getNom() + "'" +
            ", email='" + getEmail() + "'" +
            ", numeroTel='" + getNumeroTel() + "'" +
            ", dateEmbauche='" + getDateEmbauche() + "'" +
            ", salaire=" + getSalaire() +
            ", adresse='" + getAdresse() + "'" +
            ", cin='" + getCin() + "'" +
            ", dateNaissance='" + getDateNaissance() + "'" +
            ", sexe='" + getSexe() + "'" +
            "}";
    }
}
