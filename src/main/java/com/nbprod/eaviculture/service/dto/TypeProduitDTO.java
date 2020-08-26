package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.TypeProduit} entity.
 */
public class TypeProduitDTO implements Serializable {
    
    private Long id;

    private String codeTypeProduit;

    private String designation;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTypeProduit() {
        return codeTypeProduit;
    }

    public void setCodeTypeProduit(String codeTypeProduit) {
        this.codeTypeProduit = codeTypeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((TypeProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeProduitDTO{" +
            "id=" + getId() +
            ", codeTypeProduit='" + getCodeTypeProduit() + "'" +
            ", designation='" + getDesignation() + "'" +
            "}";
    }
}
