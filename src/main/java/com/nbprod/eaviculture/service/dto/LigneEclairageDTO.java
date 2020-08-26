package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.LigneEclairage} entity.
 */
public class LigneEclairageDTO implements Serializable {
    
    private Long id;

    private String codeLigne;

    private String description;

    private Boolean allume;

    private Integer luxMax;

    private Integer luxMin;

    private Integer luxChoisi;


    private Long batimentId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeLigne() {
        return codeLigne;
    }

    public void setCodeLigne(String codeLigne) {
        this.codeLigne = codeLigne;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAllume() {
        return allume;
    }

    public void setAllume(Boolean allume) {
        this.allume = allume;
    }

    public Integer getLuxMax() {
        return luxMax;
    }

    public void setLuxMax(Integer luxMax) {
        this.luxMax = luxMax;
    }

    public Integer getLuxMin() {
        return luxMin;
    }

    public void setLuxMin(Integer luxMin) {
        this.luxMin = luxMin;
    }

    public Integer getLuxChoisi() {
        return luxChoisi;
    }

    public void setLuxChoisi(Integer luxChoisi) {
        this.luxChoisi = luxChoisi;
    }

    public Long getBatimentId() {
        return batimentId;
    }

    public void setBatimentId(Long batimentId) {
        this.batimentId = batimentId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneEclairageDTO)) {
            return false;
        }

        return id != null && id.equals(((LigneEclairageDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneEclairageDTO{" +
            "id=" + getId() +
            ", codeLigne='" + getCodeLigne() + "'" +
            ", description='" + getDescription() + "'" +
            ", allume='" + isAllume() + "'" +
            ", luxMax=" + getLuxMax() +
            ", luxMin=" + getLuxMin() +
            ", luxChoisi=" + getLuxChoisi() +
            ", batimentId=" + getBatimentId() +
            "}";
    }
}
