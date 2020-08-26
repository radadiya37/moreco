package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.BatimentDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link Batiment} and its DTO {@link BatimentDTO}.
 */
@Mapper(componentModel = "spring", uses = {})
public interface BatimentMapper extends EntityMapper<BatimentDTO, Batiment> {


    @Mapping(target = "ligneEclairages", ignore = true)
    @Mapping(target = "removeLigneEclairage", ignore = true)
    Batiment toEntity(BatimentDTO batimentDTO);

    default Batiment fromId(Long id) {
        if (id == null) {
            return null;
        }
        Batiment batiment = new Batiment();
        batiment.setId(id);
        return batiment;
    }
}
