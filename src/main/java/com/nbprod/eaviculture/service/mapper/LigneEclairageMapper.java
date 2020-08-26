package com.nbprod.eaviculture.service.mapper;


import com.nbprod.eaviculture.domain.*;
import com.nbprod.eaviculture.service.dto.LigneEclairageDTO;

import org.mapstruct.*;

/**
 * Mapper for the entity {@link LigneEclairage} and its DTO {@link LigneEclairageDTO}.
 */
@Mapper(componentModel = "spring", uses = {BatimentMapper.class})
public interface LigneEclairageMapper extends EntityMapper<LigneEclairageDTO, LigneEclairage> {

    @Mapping(source = "batiment.id", target = "batimentId")
    LigneEclairageDTO toDto(LigneEclairage ligneEclairage);

    @Mapping(source = "batimentId", target = "batiment")
    LigneEclairage toEntity(LigneEclairageDTO ligneEclairageDTO);

    default LigneEclairage fromId(Long id) {
        if (id == null) {
            return null;
        }
        LigneEclairage ligneEclairage = new LigneEclairage();
        ligneEclairage.setId(id);
        return ligneEclairage;
    }
}
