package com.nbprod.eaviculture.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;

/**
 * A LogParametreEnvironement.
 */
@Entity
@Table(name = "log_parametre_environement")
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
public class LogParametreEnvironement implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "temperature")
    private Integer temperature;

    @Column(name = "humidite")
    private Integer humidite;

    @Column(name = "date_log")
    private LocalDate dateLog;

    @ManyToOne
    @JsonIgnoreProperties(value = "logParametreEnvironements", allowSetters = true)
    private PhaseProduction phaseProduction;

    // jhipster-needle-entity-add-field - JHipster will add fields here
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public LogParametreEnvironement temperature(Integer temperature) {
        this.temperature = temperature;
        return this;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidite() {
        return humidite;
    }

    public LogParametreEnvironement humidite(Integer humidite) {
        this.humidite = humidite;
        return this;
    }

    public void setHumidite(Integer humidite) {
        this.humidite = humidite;
    }

    public LocalDate getDateLog() {
        return dateLog;
    }

    public LogParametreEnvironement dateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
        return this;
    }

    public void setDateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
    }

    public PhaseProduction getPhaseProduction() {
        return phaseProduction;
    }

    public LogParametreEnvironement phaseProduction(PhaseProduction phaseProduction) {
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
        if (!(o instanceof LogParametreEnvironement)) {
            return false;
        }
        return id != null && id.equals(((LogParametreEnvironement) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LogParametreEnvironement{" +
            "id=" + getId() +
            ", temperature=" + getTemperature() +
            ", humidite=" + getHumidite() +
            ", dateLog='" + getDateLog() + "'" +
            "}";
    }
}
