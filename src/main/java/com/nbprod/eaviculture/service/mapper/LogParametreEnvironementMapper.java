package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.LogParametreEnvironementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LogParametreEnvironement} and its DTO {@link LogParametreEnvironementDTO}.
 */
@Mapper(componentModel = "spring", uses = {PhaseProductionMapper.class})
public interface LogParametreEnvironementMapper extends EntityMapper<LogParametreEnvironementDTO, LogParametreEnvironement> {

    @Mapping(source = "phaseProduction.id", target = "phaseProductionId")
    LogParametreEnvironementDTO toDto(LogParametreEnvironement logParametreEnvironement);

    @Mapping(source = "phaseProductionId", target = "phaseProduction")
    LogParametreEnvironement toEntity(LogParametreEnvironementDTO logParametreEnvironementDTO);

    default LogParametreEnvironement fromId(Long id) {
        if (id == null) {
            return null;
        }
        LogParametreEnvironement logParametreEnvironement = new LogParametreEnvironement();
        logParametreEnvironement.setId(id);
        return logParametreEnvironement;
    }
}
