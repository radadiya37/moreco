package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.PhaseProductionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link PhaseProduction} and its DTO {@link PhaseProductionDTO}.
 */
@Mapper(componentModel = "spring", uses = {BatimentMapper.class, DepenseMapper.class})
public interface PhaseProductionMapper extends EntityMapper<PhaseProductionDTO, PhaseProduction> {

    @Mapping(source = "batiment.id", target = "batimentId")
    @Mapping(source = "listeDepenses.id", target = "listeDepensesId")
    PhaseProductionDTO toDto(PhaseProduction phaseProduction);

    @Mapping(target = "logParametreEnvironements", ignore = true)
    @Mapping(target = "removeLogParametreEnvironement", ignore = true)
    @Mapping(source = "batimentId", target = "batiment")
    @Mapping(source = "listeDepensesId", target = "listeDepenses")
    PhaseProduction toEntity(PhaseProductionDTO phaseProductionDTO);

    default PhaseProduction fromId(Long id) {
        if (id == null) {
            return null;
        }
        PhaseProduction phaseProduction = new PhaseProduction();
        phaseProduction.setId(id);
        return phaseProduction;
    }
}
