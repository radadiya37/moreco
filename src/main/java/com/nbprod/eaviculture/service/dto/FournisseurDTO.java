package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Fournisseur} entity.
 */
public class FournisseurDTO implements Serializable {
    
    private Long id;

    private String nomComplete;

    private String adresse;

    private String email;

    private String numeroTel;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomComplete() {
        return nomComplete;
    }

    public void setNomComplete(String nomComplete) {
        this.nomComplete = nomComplete;
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

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FournisseurDTO)) {
            return false;
        }

        return id != null && id.equals(((FournisseurDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FournisseurDTO{" +
            "id=" + getId() +
            ", nomComplete='" + getNomComplete() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", numeroTel='" + getNumeroTel() + "'" +
            "}";
    }
}
