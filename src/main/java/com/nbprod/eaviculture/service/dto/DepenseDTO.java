package com.nbprod.eaviculture.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Depense} entity.
 */
public class DepenseDTO implements Serializable {
    
    private Long id;

    private String codeDepense;

    private Integer quantite;

    private LocalDate dateDemande;

    private Integer tva;

    private String etatDepense;


    private Long foursnisseurId;

    private Long factureId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDepense() {
        return codeDepense;
    }

    public void setCodeDepense(String codeDepense) {
        this.codeDepense = codeDepense;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Integer getTva() {
        return tva;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public String getEtatDepense() {
        return etatDepense;
    }

    public void setEtatDepense(String etatDepense) {
        this.etatDepense = etatDepense;
    }

    public Long getFoursnisseurId() {
        return foursnisseurId;
    }

    public void setFoursnisseurId(Long fournisseurId) {
        this.foursnisseurId = fournisseurId;
    }

    public Long getFactureId() {
        return factureId;
    }

    public void setFactureId(Long factureId) {
        this.factureId = factureId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DepenseDTO)) {
            return false;
        }

        return id != null && id.equals(((DepenseDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DepenseDTO{" +
            "id=" + getId() +
            ", codeDepense='" + getCodeDepense() + "'" +
            ", quantite=" + getQuantite() +
            ", dateDemande='" + getDateDemande() + "'" +
            ", tva=" + getTva() +
            ", etatDepense='" + getEtatDepense() + "'" +
            ", foursnisseurId=" + getFoursnisseurId() +
            ", factureId=" + getFactureId() +
            "}";
    }
}
