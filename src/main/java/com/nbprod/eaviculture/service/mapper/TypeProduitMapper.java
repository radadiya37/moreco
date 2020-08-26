package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.TypeProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link TypeProduit} and its DTO {@link TypeProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface TypeProduitMapper extends EntityMapper<TypeProduitDTO, TypeProduit> {



    default TypeProduit fromId(Long id) {
        if (id == null) {
            return null;
        }
        TypeProduit typeProduit = new TypeProduit();
        typeProduit.setId(id);
        return typeProduit;
    }
}
