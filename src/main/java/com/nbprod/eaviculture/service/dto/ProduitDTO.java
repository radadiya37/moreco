package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Produit} entity.
 */
public class ProduitDTO implements Serializable {
    
    private Long id;

    private String codeProduit;

    private String designation;

    private BigDecimal prixUnitaire;


    private Long typeId;

    private Long emplacementId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public Long getTypeId() {
        return typeId;
    }

    public void setTypeId(Long typeProduitId) {
        this.typeId = typeProduitId;
    }

    public Long getEmplacementId() {
        return emplacementId;
    }

    public void setEmplacementId(Long emplacementId) {
        this.emplacementId = emplacementId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ProduitDTO)) {
            return false;
        }

        return id != null && id.equals(((ProduitDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProduitDTO{" +
            "id=" + getId() +
            ", codeProduit='" + getCodeProduit() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", typeId=" + getTypeId() +
            ", emplacementId=" + getEmplacementId() +
            "}";
    }
}
