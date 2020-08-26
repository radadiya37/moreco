package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.FonctionDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Fonction} and its DTO {@link FonctionDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface FonctionMapper extends EntityMapper<FonctionDTO, Fonction> {



    default Fonction fromId(Long id) {
        if (id == null) {
            return null;
        }
        Fonction fonction = new Fonction();
        fonction.setId(id);
        return fonction;
    }
}
