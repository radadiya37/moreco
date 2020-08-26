package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.VenteDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Vente} and its DTO {@link VenteDTO}.
 */
@Mapper(componentModel = "spring", uses = {ClientMapper.class, PhaseProductionMapper.class})
public interface VenteMapper extends EntityMapper<VenteDTO, Vente> {

    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "phaseProduction.id", target = "phaseProductionId")
    VenteDTO toDto(Vente vente);

    @Mapping(source = "clientId", target = "client")
    @Mapping(source = "phaseProductionId", target = "phaseProduction")
    Vente toEntity(VenteDTO venteDTO);

    default Vente fromId(Long id) {
        if (id == null) {
            return null;
        }
        Vente vente = new Vente();
        vente.setId(id);
        return vente;
    }
}
