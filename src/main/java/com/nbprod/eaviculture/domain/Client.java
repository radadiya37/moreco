package com.nbprod.eaviculture.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A Client.
 */
@Entity
@Table(name = "client")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class Client implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_complet")
    private String nomComplet;

    @Column(name = "numero_tel")
    private String numeroTel;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "email")
    private String email;

    @Column(name = "numero_compte_bancaire")
    private String numeroCompteBancaire;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNomComplet() {
        return nomComplet;
    }

    public Client nomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
        return this;
    }

    public void setNomComplet(String nomComplet) {
        this.nomComplet = nomComplet;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public Client numeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
        return this;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public String getAdresse() {
        return adresse;
    }

    public Client adresse(String adresse) {
        this.adresse = adresse;
        return this;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public Client email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumeroCompteBancaire() {
        return numeroCompteBancaire;
    }

    public Client numeroCompteBancaire(String numeroCompteBancaire) {
        this.numeroCompteBancaire = numeroCompteBancaire;
        return this;
    }

    public void setNumeroCompteBancaire(String numeroCompteBancaire) {
        this.numeroCompteBancaire = numeroCompteBancaire;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Client)) {
            return false;
        }
        return id != null && id.equals(((Client) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Client{" +
            "id=" + getId() +
            ", nomComplet='" + getNomComplet() + "'" +
            ", numeroTel='" + getNumeroTel() + "'" +
            ", adresse='" + getAdresse() + "'" +
            ", email='" + getEmail() + "'" +
            ", numeroCompteBancaire='" + getNumeroCompteBancaire() + "'" +
            "}";
    }
}
