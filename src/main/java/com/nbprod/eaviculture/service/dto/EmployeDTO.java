package com.nbprod.eaviculture.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Employe} entity.
 */
public class EmployeDTO implements Serializable {
    
    private Long id;

    private String prenom;

    private String nom;

    private String email;

    private String numeroTel;

    private LocalDate dateEmbauche;

    private Long salaire;

    private String adresse;

    private String cin;

    private LocalDate dateNaissance;

    private String sexe;


    private Long fonctionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public LocalDate getDateEmbauche() {
        return dateEmbauche;
    }

    public void setDateEmbauche(LocalDate dateEmbauche) {
        this.dateEmbauche = dateEmbauche;
    }

    public Long getSalaire() {
        return salaire;
    }

    public void setSalaire(Long salaire) {
        this.salaire = salaire;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public LocalDate getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(LocalDate dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public Long getFonctionId() {
        return fonctionId;
    }

    public void setFonctionId(Long fonctionId) {
        this.fonctionId = fonctionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmployeDTO)) {
            return false;
        }

        return id != null && id.equals(((EmployeDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmployeDTO{" +
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
            ", fonctionId=" + getFonctionId() +
            "}";
    }
}
