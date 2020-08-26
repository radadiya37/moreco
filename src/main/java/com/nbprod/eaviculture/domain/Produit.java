package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A Produit.
 */
@Entity
@Table(name = "produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Produit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_produit")
    private String codeProduit;

    @Column(name = "designation")
    private String designation;

    @Column(name = "prix_unitaire", precision = 21, scale = 2)
    private BigDecimal prixUnitaire;

    @OneToOne
    @JoinColumn(unique = true)
    private TypeProduit type;

    @ManyToOne
    @JsonIgnoreProperties(value = "produits", allowSetters = true)
    private Emplacement emplacement;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeProduit() {
        return codeProduit;
    }

    public Produit codeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
        return this;
    }

    public void setCodeProduit(String codeProduit) {
        this.codeProduit = codeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public Produit designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public BigDecimal getPrixUnitaire() {
        return prixUnitaire;
    }

    public Produit prixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
        return this;
    }

    public void setPrixUnitaire(BigDecimal prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public TypeProduit getType() {
        return type;
    }

    public Produit type(TypeProduit typeProduit) {
        this.type = typeProduit;
        return this;
    }

    public void setType(TypeProduit typeProduit) {
        this.type = typeProduit;
    }

    public Emplacement getEmplacement() {
        return emplacement;
    }

    public Produit emplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
        return this;
    }

    public void setEmplacement(Emplacement emplacement) {
        this.emplacement = emplacement;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Produit)) {
            return false;
        }
        return id != null && id.equals(((Produit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Produit{" +
            "id=" + getId() +
            ", codeProduit='" + getCodeProduit() + "'" +
            ", designation='" + getDesignation() + "'" +
            ", prixUnitaire=" + getPrixUnitaire() +
            "}";
    }
}
