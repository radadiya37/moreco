package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.FactureDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Facture} and its DTO {@link FactureDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FactureMapper extends EntityMapper<FactureDTO, Facture> {


    @Mapping(target = "depenses", ignore = true)
    @Mapping(target = "removeDepense", ignore = true)
    Facture toEntity(FactureDTO factureDTO);

    default Facture fromId(Long id) {
        if (id == null) {
            return null;
        }
        Facture facture = new Facture();
        facture.setId(id);
        return facture;
    }
}
