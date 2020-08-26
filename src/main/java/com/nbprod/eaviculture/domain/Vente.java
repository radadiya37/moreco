package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Vente.
 */
@Entity
@Table(name = "vente")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Vente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "prix_unitaire", precision = 21, scale = 2)
    private BigDecimal prixUnitaire;

    @Column(name = "description")
    private String description;

    @Column(name = "method_paiment")
    private String methodPaiment;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private Client client;

    @ManyToOne
    @JsonIgnoreProperties(value = "ventes", allowSetters = true)
    private PhaseProduction phaseProduction;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Vente quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public Vente prixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public String getDescription() {
        return description;
    }

    public Vente description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMethodPaiment() {
        return methodPaiment;
    }

    public Vente methodPaiment(String methodPaiment) {
        this.methodPaiment = methodPaiment;
        return this;
    }

    public void setMethodPaiment(String methodPaiment) {
        this.methodPaiment = methodPaiment;
    }

    public Client getClient() {
        return client;
    }

    public Vente client(Client client) {
        this.client = client;
        return this;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public PhaseProduction getPhaseProduction() {
        return phaseProduction;
    }

    public Vente phaseProduction(PhaseProduction phaseProduction) {
        this.phaseProduction = phaseProduction;
        return this;
    }

    public void setPhaseProduction(PhaseProduction phaseProduction) {
        this.phaseProduction = phaseProduction;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Vente)) {
            return false;
        }
        return id != null && id.equals(((Vente) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Vente{" +
            "id=" + getId() +
            ", quantite=" + getQuantite() +
            ", prixUnitaire=" + getPrixUnitaire() +
            ", description='" + getDescription() + "'" +
            ", methodPaiment='" + getMethodPaiment() + "'" +
            "}";
    }
}
