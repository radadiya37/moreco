package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.ProduitDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Produit} and its DTO {@link ProduitDTO}.
 */
@Mapper(componentModel = "spring", uses = {TypeProduitMapper.class, EmplacementMapper.class})
public interface ProduitMapper extends EntityMapper<ProduitDTO, Produit> {

    @Mapping(source = "type.id", target = "typeId")
    @Mapping(source = "emplacement.id", target = "emplacementId")
    ProduitDTO toDto(Produit produit);

    @Mapping(source = "typeId", target = "type")
    @Mapping(source = "emplacementId", target = "emplacement")
    Produit toEntity(ProduitDTO produitDTO);

    default Produit fromId(Long id) {
        if (id == null) {
            return null;
        }
        Produit produit = new Produit();
        produit.setId(id);
        return produit;
    }
}
