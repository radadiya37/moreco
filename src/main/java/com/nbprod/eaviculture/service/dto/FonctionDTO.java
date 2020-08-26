package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Fonction} entity.
 */
public class FonctionDTO implements Serializable {
    
    private Long id;

    private String prenom;

    private String codeFonction;

    private String description;

    
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

    public String getCodeFonction() {
        return codeFonction;
    }

    public void setCodeFonction(String codeFonction) {
        this.codeFonction = codeFonction;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FonctionDTO)) {
            return false;
        }

        return id != null && id.equals(((FonctionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FonctionDTO{" +
            "id=" + getId() +
            ", prenom='" + getPrenom() + "'" +
            ", codeFonction='" + getCodeFonction() + "'" +
            ", description='" + getDescription() + "'" +
            "}";
    }
}
