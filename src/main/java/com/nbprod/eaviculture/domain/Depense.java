package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A Depense.
 */
@Entity
@Table(name = "depense")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Depense implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_depense")
    private String codeDepense;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "date_demande")
    private LocalDate dateDemande;

    @Column(name = "tva")
    private Integer tva;

    @Column(name = "etat_depense")
    private String etatDepense;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private Fournisseur foursnisseur;

    @ManyToOne
    @JsonIgnoreProperties(value = "depenses", allowSetters = true)
    private Facture facture;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeDepense() {
        return codeDepense;
    }

    public Depense codeDepense(String codeDepense) {
        this.codeDepense = codeDepense;
        return this;
    }

    public void setCodeDepense(String codeDepense) {
        this.codeDepense = codeDepense;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Depense quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public LocalDate getDateDemande() {
        return dateDemande;
    }

    public Depense dateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
        return this;
    }

    public void setDateDemande(LocalDate dateDemande) {
        this.dateDemande = dateDemande;
    }

    public Integer getTva() {
        return tva;
    }

    public Depense tva(Integer tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public String getEtatDepense() {
        return etatDepense;
    }

    public Depense etatDepense(String etatDepense) {
        this.etatDepense = etatDepense;
        return this;
    }

    public void setEtatDepense(String etatDepense) {
        this.etatDepense = etatDepense;
    }

    public Fournisseur getFoursnisseur() {
        return foursnisseur;
    }

    public Depense foursnisseur(Fournisseur fournisseur) {
        this.foursnisseur = fournisseur;
        return this;
    }

    public void setFoursnisseur(Fournisseur fournisseur) {
        this.foursnisseur = fournisseur;
    }

    public Facture getFacture() {
        return facture;
    }

    public Depense facture(Facture facture) {
        this.facture = facture;
        return this;
    }

    public void setFacture(Facture facture) {
        this.facture = facture;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Depense)) {
            return false;
        }
        return id != null && id.equals(((Depense) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Depense{" +
            "id=" + getId() +
            ", codeDepense='" + getCodeDepense() + "'" +
            ", quantite=" + getQuantite() +
            ", dateDemande='" + getDateDemande() + "'" +
            ", tva=" + getTva() +
            ", etatDepense='" + getEtatDepense() + "'" +
            "}";
    }
}
