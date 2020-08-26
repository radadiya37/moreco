package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Client} entity.
 */
public class ClientDTO implements Serializable {
    
    private Long id;

    private String nomComplet;

    private String numeroTel;

    private String adresse;

    private String email;

    private String numeroCompteBancaire;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCompteBancaire() {
        return numeroCompteBancaire;
    }

    public void setNumeroCompteBancaire(String numeroCompteBancaire) {
        this.numeroCompteBancaire = numeroCompteBancaire;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ClientDTO)) {
            return false;
        }

        return id != null && id.equals(((ClientDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ClientDTO{" +
            "id=" + getId() +
            ", nomComplet='" + getNomComplet() + "'" +
            ", numeroTel='" + getNumeroTel() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", numeroCompteBancaire='" + getNumeroCompteBancaire() + "'" +
            "}";
    }
}
