package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Emplacement.
 */
@Entity
@Table(name = "emplacement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Emplacement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_emplacement")
    private String codeEmplacement;

    @Column(name = "volume")
    private Long volume;

    @Column(name = "reserve")
    private Boolean reserve;

    @ManyToOne
    @JsonIgnoreProperties(value = "emplacements", allowSetters = true)
    private Stock stock;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeEmplacement() {
        return codeEmplacement;
    }

    public Emplacement codeEmplacement(String codeEmplacement) {
        this.codeEmplacement = codeEmplacement;
        return this;
    }

    public void setCodeEmplacement(String codeEmplacement) {
        this.codeEmplacement = codeEmplacement;
    }

    public Long getVolume() {
        return volume;
    }

    public Emplacement volume(Long volume) {
        this.volume = volume;
        return this;
    }

    public void setVolume(Long volume) {
        this.volume = volume;
    }

    public Boolean isReserve() {
        return reserve;
    }

    public Emplacement reserve(Boolean reserve) {
        this.reserve = reserve;
        return this;
    }

    public void setReserve(Boolean reserve) {
        this.reserve = reserve;
    }

    public Stock getStock() {
        return stock;
    }

    public Emplacement stock(Stock stock) {
        this.stock = stock;
        return this;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Emplacement)) {
            return false;
        }
        return id != null && id.equals(((Emplacement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Emplacement{" +
            "id=" + getId() +
            ", codeEmplacement='" + getCodeEmplacement() + "'" +
            ", volume=" + getVolume() +
            ", reserve='" + isReserve() + "'" +
            "}";
    }
}
