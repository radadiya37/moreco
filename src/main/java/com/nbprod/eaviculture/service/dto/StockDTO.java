package com.nbprod.eaviculture.service.dto;

import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.Stock} entity.
 */
public class StockDTO implements Serializable {
    
    private Long id;

    private String codeStock;

    private Long surface;

    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeStock() {
        return codeStock;
    }

    public void setCodeStock(String codeStock) {
        this.codeStock = codeStock;
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
        if (!(o instanceof StockDTO)) {
            return false;
        }

        return id != null && id.equals(((StockDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "StockDTO{" +
            "id=" + getId() +
            ", codeStock='" + getCodeStock() + "'" +
            ", surface=" + getSurface() +
            "}";
    }
}
