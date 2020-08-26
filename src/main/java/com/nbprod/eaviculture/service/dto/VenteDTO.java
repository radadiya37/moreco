package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Vente} entity.
 */
public class VenteDTO implements Serializable {
    
    private Long id;

    private Integer quantite;

    private BigDecimal prixUnitaire;

    private String description;

    private String methodPaiment;


    private Long clientId;

    private Long phaseProductionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethodPaiment() {
        return methodPaiment;
    }

    public void setMethodPaiment(String methodPaiment) {
        this.methodPaiment = methodPaiment;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Long getPhaseProductionId() {
        return phaseProductionId;
    }

    public void setPhaseProductionId(Long phaseProductionId) {
        this.phaseProductionId = phaseProductionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof VenteDTO)) {
            return false;
        }

        return id != null && id.equals(((VenteDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "VenteDTO{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", description='" + getDescription() + "'" +
            ", methodPaiment='" + getMethodPaiment() + "'" +
            ", clientId=" + getClientId() +
            ", phaseProductionId=" + getPhaseProductionId() +
            "}";
    }
}
