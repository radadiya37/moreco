package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.EmplacementDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Emplacement} and its DTO {@link EmplacementDTO}.
 */
@Mapper(componentModel = "spring", uses = {StockMapper.class})
public interface EmplacementMapper extends EntityMapper<EmplacementDTO, Emplacement> {

    @Mapping(source = "stock.id", target = "stockId")
    EmplacementDTO toDto(Emplacement emplacement);

    @Mapping(source = "stockId", target = "stock")
    Emplacement toEntity(EmplacementDTO emplacementDTO);

    default Emplacement fromId(Long id) {
        if (id == null) {
            return null;
        }
        Emplacement emplacement = new Emplacement();
        emplacement.setId(id);
        return emplacement;
    }
}
