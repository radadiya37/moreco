package com.nbprod.eaviculture.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Stock.
 */
@Entity
@Table(name = "stock")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Stock implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_stock")
    private String codeStock;

    @Column(name = "surface")
    private Long surface;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeStock() {
        return codeStock;
    }

    public Stock codeStock(String codeStock) {
        this.codeStock = codeStock;
        return this;
    }

    public void setCodeStock(String codeStock) {
        this.codeStock = codeStock;
    }

    public Long getSurface() {
        return surface;
    }

    public Stock surface(Long surface) {
        this.surface = surface;
        return this;
    }

    public void setSurface(Long surface) {
        this.surface = surface;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Stock)) {
            return false;
        }
        return id != null && id.equals(((Stock) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Stock{" +
            "id=" + getId() +
            ", codeStock='" + getCodeStock() + "'" +
            ", surface=" + getSurface() +
            "}";
    }
}
