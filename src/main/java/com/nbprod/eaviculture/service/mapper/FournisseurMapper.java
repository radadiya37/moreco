package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.FournisseurDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fournisseur} and its DTO {@link FournisseurDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FournisseurMapper extends EntityMapper<FournisseurDTO, Fournisseur> {



    default Fournisseur fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fournisseur fournisseur = new Fournisseur();
        fournisseur.setId(id);
        return fournisseur;
    }
}
