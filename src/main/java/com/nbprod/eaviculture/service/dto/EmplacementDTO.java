package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Emplacement} entity.
 */
public class EmplacementDTO implements Serializable {
    
    private Long id;

    private String codeEmplacement;

    private Long volume;

    private Boolean reserve;


    private Long stockId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeEmplacement() {
        return codeEmplacement;
    }

    public void setCodeEmplacement(String codeEmplacement) {
        this.codeEmplacement = codeEmplacement;
    }

    public Long getVolume() {
        return volume;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Boolean isReserve() {
        return reserve;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public Long getStockId() {
        return stockId;
    }

    public void setStockId(Long stockId) {
        this.stockId = stockId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof EmplacementDTO)) {
            return false;
        }

        return id != null && id.equals(((EmplacementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "EmplacementDTO{" +
            "id=" + getId() +
            ", codeEmplacement='" + getCodeEmplacement() + "'" +
            ", volume=" + getVolume() +
            ", reserve='" + isReserve() + "'" +
            ", stockId=" + getStockId() +
            "}";
    }
}
