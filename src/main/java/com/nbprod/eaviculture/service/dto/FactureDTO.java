package com.nbprod.eaviculture.service.dto;

import java.time.LocalDate;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Facture} entity.
 */
public class FactureDTO implements Serializable {
    
    private Long id;

    private String numeroFacture;

    private LocalDate dateFacturation;

    private BigDecimal prixUnite;

    private Integer tva;

    private Integer quantite;

    private Long fraisLivraison;

    private String methodPaiment;

    private String etatFacture;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public LocalDate getDateFacturation() {
        return dateFacturation;
    }

    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public BigDecimal getPrixUnite() {
        return prixUnite;
    }

    public void setPrixUnite(BigDecimal prixUnite) {
        this.prixUnite = prixUnite;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getFraisLivraison() {
        return fraisLivraison;
    }

    public void setFraisLivraison(Long fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public String getMethodPaiment() {
        return methodPaiment;
    }

    public void setMethodPaiment(String methodPaiment) {
        this.methodPaiment = methodPaiment;
    }

    public String getEtatFacture() {
        return etatFacture;
    }

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof FactureDTO)) {
            return false;
        }

        return id != null && id.equals(((FactureDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "FactureDTO{" +
            "id=" + getId() +
            ", numeroFacture='" + getNumeroFacture() + "'" +
            ", dateFacturation='" + getDateFacturation() + "'" +
            ", prixUnite=" + getPrixUnite() +
            ", tva=" + getTva() +
            ", quantite=" + getQuantite() +
            ", fraisLivraison=" + getFraisLivraison() +
            ", methodPaiment='" + getMethodPaiment() + "'" +
            ", etatFacture='" + getEtatFacture() + "'" +
            "}";
    }
}
