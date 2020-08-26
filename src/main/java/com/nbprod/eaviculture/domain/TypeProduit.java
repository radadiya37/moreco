package com.nbprod.eaviculture.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;

/**
 * A TypeProduit.
 */
@Entity
@Table(name = "type_produit")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class TypeProduit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "code_type_produit")
    private String codeTypeProduit;

    @Column(name = "designation")
    private String designation;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCodeTypeProduit() {
        return codeTypeProduit;
    }

    public TypeProduit codeTypeProduit(String codeTypeProduit) {
        this.codeTypeProduit = codeTypeProduit;
        return this;
    }

    public void setCodeTypeProduit(String codeTypeProduit) {
        this.codeTypeProduit = codeTypeProduit;
    }

    public String getDesignation() {
        return designation;
    }

    public TypeProduit designation(String designation) {
        this.designation = designation;
        return this;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof TypeProduit)) {
            return false;
        }
        return id != null && id.equals(((TypeProduit) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "TypeProduit{" +
            "id=" + getId() +
            ", codeTypeProduit='" + getCodeTypeProduit() + "'" +
            ", designation='" + getDesignation() + "'" +
            "}";
    }
}
