package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A LigneEclairage.
 */
@Entity
@Table(name = "ligne_eclairage")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LigneEclairage implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_ligne")
    private String codeLigne;

    @Column(name = "description")
    private String description;

    @Column(name = "allume")
    private Boolean allume;

    @Column(name = "lux_max")
    private Integer luxMax;

    @Column(name = "lux_min")
    private Integer luxMin;

    @Column(name = "lux_choisi")
    private Integer luxChoisi;

    @ManyToOne
    @JsonIgnoreProperties(value = "ligneEclairages", allowSetters = true)
    private Batiment batiment;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeLigne() {
        return codeLigne;
    }

    public LigneEclairage codeLigne(String codeLigne) {
        this.codeLigne = codeLigne;
        return this;
    }

    public void setCodeLigne(String codeLigne) {
        this.codeLigne = codeLigne;
    }

    public String getDescription() {
        return description;
    }

    public LigneEclairage description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean isAllume() {
        return allume;
    }

    public LigneEclairage allume(Boolean allume) {
        this.allume = allume;
        return this;
    }

    public void setAllume(Boolean allume) {
        this.allume = allume;
    }

    public Integer getLuxMax() {
        return luxMax;
    }

    public LigneEclairage luxMax(Integer luxMax) {
        this.luxMax = luxMax;
        return this;
    }

    public void setLuxMax(Integer luxMax) {
        this.luxMax = luxMax;
    }

    public Integer getLuxMin() {
        return luxMin;
    }

    public LigneEclairage luxMin(Integer luxMin) {
        this.luxMin = luxMin;
        return this;
    }

    public void setLuxMin(Integer luxMin) {
        this.luxMin = luxMin;
    }

    public Integer getLuxChoisi() {
        return luxChoisi;
    }

    public LigneEclairage luxChoisi(Integer luxChoisi) {
        this.luxChoisi = luxChoisi;
        return this;
    }

    public void setLuxChoisi(Integer luxChoisi) {
        this.luxChoisi = luxChoisi;
    }

    public Batiment getBatiment() {
        return batiment;
    }

    public LigneEclairage batiment(Batiment batiment) {
        this.batiment = batiment;
        return this;
    }

    public void setBatiment(Batiment batiment) {
        this.batiment = batiment;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LigneEclairage)) {
            return false;
        }
        return id != null && id.equals(((LigneEclairage) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LigneEclairage{" +
            "id=" + getId() +
            ", codeLigne='" + getCodeLigne() + "'" +
            ", description='" + getDescription() + "'" +
            ", allume='" + isAllume() + "'" +
            ", luxMax=" + getLuxMax() +
            ", luxMin=" + getLuxMin() +
            ", luxChoisi=" + getLuxChoisi() +
            "}";
    }
}
