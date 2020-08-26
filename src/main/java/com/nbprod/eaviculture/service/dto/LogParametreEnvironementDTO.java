package com.nbprod.eaviculture.service.dto;

import java.time.LocalDate;
import java.io.Serializable;

/**
 * A DTO for the {@link com.nbprod.eaviculture.domain.LogParametreEnvironement} entity.
 */
public class LogParametreEnvironementDTO implements Serializable {
    
    private Long id;

    private Integer temperature;

    private Integer humidite;

    private LocalDate dateLog;


    private Long phaseProductionId;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getTemperature() {
        return temperature;
    }

    public void setTemperature(Integer temperature) {
        this.temperature = temperature;
    }

    public Integer getHumidite() {
        return humidite;
    }

    public void setHumidite(Integer humidite) {
        this.humidite = humidite;
    }

    public LocalDate getDateLog() {
        return dateLog;
    }

    public void setDateLog(LocalDate dateLog) {
        this.dateLog = dateLog;
    }

    public Long getPhaseProductionId() {
        return phaseProductionId;
    }

    public void setPhaseProductionId(Long phaseProductionId) {
        this.phaseProductionId = phaseProductionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof LogParametreEnvironementDTO)) {
            return false;
        }

        return id != null && id.equals(((LogParametreEnvironementDTO) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "LogParametreEnvironementDTO{" +
            "id=" + getId() +
            ", temperature=" + getTemperature() +
            ", humidite=" + getHumidite() +
            ", dateLog='" + getDateLog() + "'" +
            ", phaseProductionId=" + getPhaseProductionId() +
            "}";
    }
}
