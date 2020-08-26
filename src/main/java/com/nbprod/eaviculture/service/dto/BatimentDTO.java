package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Batiment} entity.
 */
public class BatimentDTO implements Serializable {
    
    private Long id;

    private String codeBatiment;

    private Long surface;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeBatiment() {
        return codeBatiment;
    }

    public void setCodeBatiment(String codeBatiment) {
        this.codeBatiment = codeBatiment;
    }

    public Long getSurface() {
        return surface;
    }

    public void setSurface(Long surface) {
        this.surface = surface;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BatimentDTO)) {
            return false;
        }

        return id != null && id.equals(((BatimentDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BatimentDTO{" +
            "id=" + getId() +
            ", codeBatiment='" + getCodeBatiment() + "'" +
            ", surface=" + getSurface() +
            "}";
    }
}
