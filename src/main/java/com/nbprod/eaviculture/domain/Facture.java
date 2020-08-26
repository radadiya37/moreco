package com.nbprod.eaviculture.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

/**
 * A Facture.
 */
@Entity
@Table(name = "facture")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Facture implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_facture")
    private String numeroFacture;

    @Column(name = "date_facturation")
    private LocalDate dateFacturation;

    @Column(name = "prix_unite", precision = 21, scale = 2)
    private BigDecimal prixUnite;

    @Column(name = "tva")
    private Integer tva;

    @Column(name = "quantite")
    private Integer quantite;

    @Column(name = "frais_livraison")
    private Long fraisLivraison;

    @Column(name = "method_paiment")
    private String methodPaiment;

    @Column(name = "etat_facture")
    private String etatFacture;

    @OneToMany(mappedBy = "facture")
    @Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    private Set<Depense> depenses = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumeroFacture() {
        return numeroFacture;
    }

    public Facture numeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
        return this;
    }

    public void setNumeroFacture(String numeroFacture) {
        this.numeroFacture = numeroFacture;
    }

    public LocalDate getDateFacturation() {
        return dateFacturation;
    }

    public Facture dateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
        return this;
    }

    public void setDateFacturation(LocalDate dateFacturation) {
        this.dateFacturation = dateFacturation;
    }

    public BigDecimal getPrixUnite() {
        return prixUnite;
    }

    public Facture prixUnite(BigDecimal prixUnite) {
        this.prixUnite = prixUnite;
        return this;
    }

    public void setPrixUnite(BigDecimal prixUnite) {
        this.prixUnite = prixUnite;
    }

    public Integer getTva() {
        return tva;
    }

    public Facture tva(Integer tva) {
        this.tva = tva;
        return this;
    }

    public void setTva(Integer tva) {
        this.tva = tva;
    }

    public Integer getQuantite() {
        return quantite;
    }

    public Facture quantite(Integer quantite) {
        this.quantite = quantite;
        return this;
    }

    public void setQuantite(Integer quantite) {
        this.quantite = quantite;
    }

    public Long getFraisLivraison() {
        return fraisLivraison;
    }

    public Facture fraisLivraison(Long fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
        return this;
    }

    public void setFraisLivraison(Long fraisLivraison) {
        this.fraisLivraison = fraisLivraison;
    }

    public String getMethodPaiment() {
        return methodPaiment;
    }

    public Facture methodPaiment(String methodPaiment) {
        this.methodPaiment = methodPaiment;
        return this;
    }

    public void setMethodPaiment(String methodPaiment) {
        this.methodPaiment = methodPaiment;
    }

    public String getEtatFacture() {
        return etatFacture;
    }

    public Facture etatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
        return this;
    }

    public void setEtatFacture(String etatFacture) {
        this.etatFacture = etatFacture;
    }

    public Set<Depense> getDepenses() {
        return depenses;
    }

    public Facture depenses(Set<Depense> depenses) {
        this.depenses = depenses;
        return this;
    }

    public Facture addDepense(Depense depense) {
        this.depenses.add(depense);
        depense.setFacture(this);
        return this;
    }

    public Facture removeDepense(Depense depense) {
        this.depenses.remove(depense);
        depense.setFacture(null);
        return this;
    }

    public void setDepenses(Set<Depense> depenses) {
        this.depenses = depenses;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Facture)) {
            return false;
        }
        return id != null && id.equals(((Facture) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Facture{" +
            "id=" + getId() +
            ", numeroFacture='" + getNumeroFacture() + "'" +
            ", dateFacturation='" + getDateFacturation() + "'" +
            ", prixUnite=" + getPrixUnite() +
            ", tva=" + getTva() +
            ", quantite=" + getQuantite() +
            ", fraisLivraison=" + getFraisLivraison() +
            ", methodPaiment='" + getMethodPaiment() + "'" +
            ", etatFacture='" + getEtatFacture() + "'" +
            "}";
    }
}
