package com.nbprod.eaviculture.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.PhaseProduction} entity.
 */
public class PhaseProductionDTO implements Serializable {
    
    private Long id;

    private String codePhase;

    private LocalDate dateDebut;

    private LocalDate dateFin;

    private Integer nombrePoulets;

    private Integer nombreDeces;


    private Long batimentId;

    private Long listeDepensesId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodePhase() {
        return codePhase;
    }

    public void setCodePhase(String codePhase) {
        this.codePhase = codePhase;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    public LocalDate getDateFin() {
        return dateFin;
    }

    public void setDateFin(LocalDate dateFin) {
        this.dateFin = dateFin;
    }

    public Integer getNombrePoulets() {
        return nombrePoulets;
    }

    public void setNombrePoulets(Integer nombrePoulets) {
        this.nombrePoulets = nombrePoulets;
    }

    public Integer getNombreDeces() {
        return nombreDeces;
    }

    public void setNombreDeces(Integer nombreDeces) {
        this.nombreDeces = nombreDeces;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
    }

    public Long getListeDepensesId() {
        return listeDepensesId;
    }

    public void setListeDepensesId(Long depenseId) {
        this.listeDepensesId = depenseId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof PhaseProductionDTO)) {
            return false;
        }

        return id != null && id.equals(((PhaseProductionDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PhaseProductionDTO{" +
            "id=" + getId() +
            ", codePhase='" + getCodePhase() + "'" +
            ", dateDebut='" + getDateDebut() + "'" +
            ", dateFin='" + getDateFin() + "'" +
            ", nombrePoulets=" + getNombrePoulets() +
            ", nombreDeces=" + getNombreDeces() +
            ", batimentId=" + getBatimentId() +
            ", listeDepensesId=" + getListeDepensesId() +
            "}";
    }
}
